/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.samir.commons.java.jarinstallerkit.jarinstaller;

import com.samir.commons.java.jarinstallerkit.Constants;
import com.samir.commons.java.jarinstallerkit.serviceComunicator.ServiceCommunicator;
import com.samir.commons.java.jarinstallerkit.serviceComunicator.ServiceCommunicatorFactory;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.apache.commons.io.FileUtils;

/**
 *
 * @author samir
 */
public abstract class JarInstaller {

    public static final String CMD_INIT = "init";
    public static final String CMD_TMP = "tmp";

    public static final String CURRENT_PATH = System.getProperty("java.class.path");
    public static final String HOME_PATH = System.getProperty("user.home");
    public static final String JAR_TOKEN = "#jarInstaller#";
    private static final Logger LOGGER = Logger.getLogger(JarInstaller.class.getName());

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

    public void moveFile(String origin, String destiny) throws Exception {
        LOGGER.log(Level.INFO, String.format("Moving from %s to %s", origin, destiny));

        final File fileOrigin = new File(origin);

        final File fileDestiny = new File(destiny);

        FileUtils.copyFile(fileOrigin, fileDestiny);

        fileOrigin.delete();

    }

    public void starter(final String[] args) throws Exception {
        LOGGER.log(Level.INFO, "shutdown Previous");
        shutdownPrevious();
        LOGGER.log(Level.INFO, "start disconnector Service");
        disconnectorService();
        File finalDir = new File(Constants.getInstallDirectorySeparator());
        if (finalDir.canWrite()) {
            doInstallLifeCycle(args);
        }
        //Remove the old tmp file
        try {
            File appTmp = new File(Constants.FINAL_APP_TMP);
            if (appTmp.exists()) {
                appTmp.delete();
            }
        } catch (Exception e) {
        }
        
    }

    public void doInstallLifeCycle(final String[] args) throws Exception {
        Runtime runtime = Runtime.getRuntime();
        if (args.length == 0) {
            moveFile(CURRENT_PATH, Constants.FINAL_APP_TMP);
            final String pathExec = "java -jar \"" + Constants.FINAL_APP_TMP + "\" tmp";
            runtime.exec(pathExec);
            LOGGER.log(Level.INFO, pathExec);
            JOptionPane.showMessageDialog(null,
                    "args.length == 0",
                    "test",
                    JOptionPane.WARNING_MESSAGE);
            System.exit(0);
        } else {
            final String firstArg = args[0];
            if (null != firstArg) {
                switch (firstArg) {
                    case CMD_TMP:
                        moveFile(Constants.CURRENT_PATH, Constants.FINAL_APP);
                        final String pathExec = "java -jar \"" + Constants.FINAL_APP + "\" init";
                        runtime.exec(pathExec);
                        LOGGER.log(Level.INFO, pathExec);
                        JOptionPane.showMessageDialog(null,
                                "args == tmp",
                                "test",
                                JOptionPane.WARNING_MESSAGE);
                        System.exit(0);
                    case CMD_INIT:
                        LOGGER.log(Level.INFO, "init");
                        JOptionPane.showMessageDialog(null,
                                "args == init",
                                "test",
                                JOptionPane.WARNING_MESSAGE);
                        setupBootRegister();
                        break;
                }
            }
        }
    }

    public abstract void setupBootRegister();

}
