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

public class PluginDescription {

    private String name;
    private String mainClass;

    public PluginDescription(String name, String mainClass) {
        this.name = name;
        this.mainClass = mainClass;
    }

    public String getMainClass() {
        return mainClass;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return String.format("PluginDescription={Name:%s,MainClasss:%s}", name, mainClass);
    }
}
