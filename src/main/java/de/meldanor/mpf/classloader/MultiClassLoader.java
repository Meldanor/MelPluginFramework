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

package de.meldanor.mpf.classloader;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class MultiClassLoader extends ClassLoader {

    private Map<URL, JarClassLoader> registeredLoaders;

    public MultiClassLoader() {
        registeredLoaders = new HashMap<URL, JarClassLoader>();
    }

    public <T> T createClazz(String clazzName, URL jarFile, Class<? extends T> clazz) throws Exception {
        JarClassLoader loader = addClassLoader(jarFile);
        Class<? extends T> loadedClass = loader.loadClass(clazzName).asSubclass(clazz);
        return loadedClass.newInstance();
    }

    private JarClassLoader addClassLoader(URL jarFile) {
        JarClassLoader loader = new JarClassLoader(jarFile);
        this.registeredLoaders.put(jarFile, loader);

        return loader;
    }

    public boolean unloadClazz(URL jarFile) throws Throwable {
        JarClassLoader loader = this.registeredLoaders.remove(jarFile);

        if (loader != null) {
            loader.clearAssertionStatus();
            loader.close();
            this.finalize();
            return true;
        }
        return false;
    }

}
