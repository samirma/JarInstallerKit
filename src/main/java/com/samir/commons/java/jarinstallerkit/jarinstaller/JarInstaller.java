/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.samir.commons.java.jarinstallerkit.jarinstaller;

import com.samir.commons.java.jarinstallerkit.Constants;
import com.samir.commons.java.jarinstallerkit.serviceComunicator.ServiceCommunicator;
import com.samir.commons.java.jarinstallerkit.serviceComunicator.ServiceCommunicatorFactory;
import com.samir.commons.java.jarinstallerkit.windows.JarWindowsInstaller;
import com.samir.commons.java.jarinstallerkit.windows.JavaRegistryHack;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author samir
 */
public abstract class JarInstaller {
    public static final String CURRENT_PATH = System.getProperty("java.class.path");
    public final Logger LOGGER = Logger.getLogger(JarInstaller.class.getName());
    
    private final ServiceCommunicator serviceCommunicator;
    
    public JarInstaller() {
        serviceCommunicator = ServiceCommunicatorFactory.getServiceCommunicator();
    }

    public void disconnectorService() {
        
        serviceCommunicator.disconnectorService();
        
    }

    public void shutdownPrevious() {
        serviceCommunicator.shutdownPrevious();
    }

    public void copyFile(String ORIGEM, String DESTINO) throws Exception {
        InputStream is = new FileInputStream(ORIGEM);
        OutputStream os = new FileOutputStream(DESTINO);
        new File(DESTINO).delete();
        int c;
        while ((c = is.read()) != -1) {
            // System.out.print((char) c);
            os.write(c);
        }
        is.close();
        os.close();
    }

    public boolean isWindows() {
        String osName = System.getProperty("os.name").toLowerCase();
        return osName.contains("windows") || osName.contains("2000");
    }

    /**
     * Remove client registry from Windows
     */
    public void removeReg() {
        JavaRegistryHack.removeWindowRegister();
    }

    public void starter(final String[] args) throws Exception {
        LOGGER.log(Level.INFO, "shutdown Previous");
        shutdownPrevious();
        LOGGER.log(Level.INFO, "start disconnector Service");
        disconnectorService();
        File finalDir = new File(Constants.getInstallDirectorySeparator());
        if (finalDir.canWrite()) {
            Runtime runtime = Runtime.getRuntime();
            if (args.length == 0) {
                LOGGER.log(Level.INFO, "call tmp");
                copyFile(CURRENT_PATH, Constants.FINAL_APP_TMP);
                runtime.exec("java -jar \"" + Constants.FINAL_APP_TMP + "\" tmp");
                System.exit(0);
            }
            if ("tmp".equals(args[0])) {
                LOGGER.log(Level.INFO, "call init");
                new File(Constants.FINAL_APP).delete();
                copyFile(Constants.CURRENT_PATH, Constants.FINAL_APP);
                runtime.exec("java -jar \"" + Constants.FINAL_APP + "\" init");
                System.exit(0);
            }
            if ("init".equals(args[0])) {
                LOGGER.log(Level.INFO, "init");
            }
        }
        //Remove the old tmp file
        try {
            File appTmp = new File(Constants.FINAL_APP_TMP);
            if (appTmp.exists()) {
                appTmp.delete();
            }
        } catch (Exception e) {
        }
        setupBootRegister();
    }

    public abstract void setupBootRegister();
    
}
