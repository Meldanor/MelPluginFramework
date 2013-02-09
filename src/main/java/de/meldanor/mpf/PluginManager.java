/*
 * Copyright (C) 2013 Kilian Gaertner
 * 
 * This file is part of MelPluginFramework.
 * 
 * MelPluginFramework is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, version 3 of the License.
 * 
 * MelPluginFramework is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with MelPluginFramework.  If not, see <http://www.gnu.org/licenses/>.
 */

package de.meldanor.mpf;

import java.io.BufferedReader;
import java.io.File;
import java.io.FilenameFilter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import org.xeustechnologies.jcl.JarClassLoader;
import org.xeustechnologies.jcl.JclObjectFactory;

import de.meldanor.mpf.plugin.MPFPlugin;

public class PluginManager {

    public static final String PLUGIN_ENDING = ".jar";
    public static final String PLUGIN_DESCRIPTION_ENDING = ".txt";

    private List<MPFPlugin> pluginList;

    private JarClassLoader clazzLoader;

    public PluginManager(String pluginPath) {
        this.clazzLoader = new JarClassLoader();
        loadPlugins(pluginPath);
    }

    private void loadPlugins(String pluginPath) {
        File pluginDir = new File(pluginPath);
        File[] pluginFiles = pluginDir.listFiles(new FilenameFilter() {
            public boolean accept(File dir, String name) {
                return name.endsWith(PLUGIN_ENDING);
            }
        });

        System.out.println("Loading " + pluginFiles.length + " plugins...");
        this.pluginList = new ArrayList<MPFPlugin>(pluginFiles.length);

        for (File file : pluginFiles) {
            try {
                loadPlugin(file);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void loadPlugin(File pluginFile) throws Exception {
        ZipFile zipFile = new ZipFile(pluginFile);

        ZipEntry ze = zipFile.getEntry("plugin.txt");
        if (ze == null) {
            zipFile.close();
            throw new Exception("Plugin description not found!");
        }

        PluginDescription desc = readDescription(zipFile.getInputStream(ze));
        if (desc == null) {
            zipFile.close();
            throw new Exception("Plugin description not found!");
        }

        zipFile.close();

        clazzLoader.add(pluginFile.toURI().toURL());
        JclObjectFactory factor = JclObjectFactory.getInstance();
        Object o = factor.create(clazzLoader, desc.getMainClass());
        MPFPlugin plugin = (MPFPlugin)o;
        this.pluginList.add(plugin);
    }

    private PluginDescription readDescription(InputStream in) throws Exception {
        BufferedReader bReader = new BufferedReader(new InputStreamReader(in));
        String line = "";
        String name = null;
        String mainClass = null;
        while ((line = bReader.readLine()) != null) {
            if (line.startsWith("Name:")) {
                name = line.substring("Name: ".length(), line.length());
            } else if (line.startsWith("Main:")) {
                mainClass = line.substring("Main: ".length(), line.length());
            }
        }
        bReader.close();
        return new PluginDescription(name, mainClass);
    }

    public List<MPFPlugin> getPluginList() {
        return pluginList;
    }
}
