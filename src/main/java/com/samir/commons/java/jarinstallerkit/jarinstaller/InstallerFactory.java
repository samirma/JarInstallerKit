/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.samir.commons.java.jarinstallerkit.jarinstaller;

import com.samir.commons.java.jarinstallerkit.linux.JarLinuxInstaller;
import com.samir.commons.java.jarinstallerkit.windows.JarWindowsInstaller;

/**
 *
 * @author samir
 */
public class InstallerFactory {

    public static boolean isWindows() {
        String osName = System.getProperty("os.name").toLowerCase();
        return (osName.contains("windows") || osName.contains("2000"));
    }

    static public JarInstaller getJarInstaller() {

        JarInstaller jarInstaller = null;

        if (isWindows()) {
            jarInstaller = new JarWindowsInstaller();
        } else if (isLinux()) {
            jarInstaller = new JarLinuxInstaller();
        }

        return jarInstaller;
    }

    public static boolean isLinux() {
        String osName = System.getProperty("os.name").toLowerCase();
        final boolean contains = osName.contains("linux");
        return contains;
    }

}
