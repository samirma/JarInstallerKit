/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.samir.commons.java.jarinstallerkit.jarinstaller;

import com.samir.commons.java.jarinstallerkit.windows.WindowsUnInstall;

/**
 *
 * @author samir
 */
public class JarUnInstallFactory {

    public static JarUnInstall getjarUnstall() {
        return new WindowsUnInstall();
    }
    
}
