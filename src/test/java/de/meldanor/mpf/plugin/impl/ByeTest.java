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

package de.meldanor.mpf.plugin.impl;

import de.meldanor.mpf.plugin.MPFPlugin;
import de.meldanor.mpf.plugin.impl.bye.ClassTest;

public class ByeTest implements MPFPlugin {

    public void test() {
        System.out.println("ByePlugin says 'Bye'");
        ClassTest.test1();
        new ClassTest();
    }

}
