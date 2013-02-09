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

import java.io.File;
import java.util.List;
import java.util.Scanner;

import de.meldanor.mpf.plugin.MPFPlugin;

public class Main {

    public static void main(String[] args) {
        PluginManager pm = new PluginManager("src/test/resources");
        List<MPFPlugin> list = pm.getPluginList();
        MPFPlugin helloPlugin = null;
        for (MPFPlugin mpfPlugin : list) {
            mpfPlugin.test();
            helloPlugin = mpfPlugin;
        }
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.println("Press enter to deload the hello plugin");
            scanner.nextLine();
            System.out.println(pm.unloadPlugin(helloPlugin));
        } catch (Exception e) {
            e.printStackTrace();
        } catch (Throwable e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        try {
            System.out.println("Press enter to load the hello plugin again");
            scanner.nextLine();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            File curPlugin = new File("src/test/resources/HelloPlugin.jar");
            System.out.println(curPlugin.renameTo(new File(curPlugin.getParentFile(), "HelloPlugin_old.jar")));
            File newPlugin = new File("src/test/resources/HelloPlugin_new.ja");
            System.out.println(newPlugin.renameTo(new File(newPlugin.getParentFile(), "HelloPlugin.jar")));

            pm.loadPlugin(new File("src/test/resources/HelloPlugin.jar"));
        } catch (Exception e) {

            e.printStackTrace();
        }
        pm.getPluginList();
        for (MPFPlugin mpfPlugin : list) {
            mpfPlugin.test();
        }
    }
}
