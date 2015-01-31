package com.samir.commons.java.jarinstallerkit.windows;

import com.samir.commons.java.jarinstallerkit.*;
import com.samir.commons.java.jarinstallerkit.jarinstaller.JarInstaller;
import com.samir.commons.java.jarinstallerkit.jarinstaller.JarUnInstall;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

public class WindowsUnInstall extends JarUnInstall {

    public static final Logger LOGGER = Logger.getLogger(JarInstaller.class.getName());

    @Override
    public void executeUnInstall() {

        try {

            LOGGER.log(Level.INFO, "Removing reg entries");
            JavaRegistryHack.removeWindowRegister();

            Thread.sleep(10000);

            LOGGER.log(Level.INFO, "Removing apps");
            File appTmp = new File(Constants.FINAL_APP_TMP);
            if (appTmp.exists()) {
                appTmp.delete();
            }

            File app = new File(Constants.FINAL_APP);
            if (app.exists()) {
                app.delete();
            }

            LOGGER.log(Level.INFO, "Done");
        } catch (InterruptedException ex) {
            
            LOGGER.log(Level.SEVERE, null, ex);
            
        }
    }

}
