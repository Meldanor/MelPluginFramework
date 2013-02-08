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

import java.util.List;

import de.meldanor.mpf.plugin.MPFPlugin;

public class Main {

    public static void main(String[] args) {
        PluginManager pm = new PluginManager("src/test/resources");
        List<MPFPlugin> list = pm.getPluginList();
        for (MPFPlugin mpfPlugin : list) {
            mpfPlugin.test();
        }
    }
}
