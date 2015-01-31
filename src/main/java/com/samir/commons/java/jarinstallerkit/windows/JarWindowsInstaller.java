package com.samir.commons.java.jarinstallerkit.windows;

import com.samir.commons.java.jarinstallerkit.jarinstaller.JarInstaller;

/**
 * Used to interact with the OS
 *
 * @author samir
 */
public class JarWindowsInstaller extends JarInstaller {

    @Override
    public void setupBootRegister() {
        JavaRegistryHack.addWindowRegister();
    }
    
}
