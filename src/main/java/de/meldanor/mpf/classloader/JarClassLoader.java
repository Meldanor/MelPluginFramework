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

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Path;

public class JarClassLoader extends URLClassLoader {

    private URL url;

    public JarClassLoader(File file) throws MalformedURLException {
        this(file.toURI().toURL());
    }

    public JarClassLoader(Path path) throws MalformedURLException {
        this(path.toUri().toURL());
    }

    public JarClassLoader(URL url) {
        super(new URL[]{url});
        this.url = url;
    }

    public URL getUrl() {
        return url;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof JarClassLoader))
            return false;
        if (obj == this)
            return true;
        JarClassLoader that = (JarClassLoader) obj;
        return this.url.equals(that.url);
    }

    @Override
    public int hashCode() {
        return this.url.hashCode();
    }

    @Override
    public String toString() {
        return String.format("JarClassLoader={URL=%s}", url);
    }
}
