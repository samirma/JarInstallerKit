package com.samir.commons.java.jarinstallerkit;

import com.samir.commons.java.jarinstallerkit.jarinstaller.InstallerFactory;
import com.samir.commons.java.jarinstallerkit.jarinstaller.JarInstaller;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Used to interact with the OS
 *
 * @author samir
 */
public class JarInstallerKit {

    public static final Logger LOGGER = Logger.getLogger(JarInstallerKit.class.getName());


    public static void starter(final String[] args) throws Exception {
        LOGGER.log(Level.INFO, "shutdown Previous");

        final JarInstaller jarInstaller = InstallerFactory.getJarInstaller();
        jarInstaller.starter(args);

    }
}
