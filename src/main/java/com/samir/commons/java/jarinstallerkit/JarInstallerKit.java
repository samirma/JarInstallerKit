package com.samir.commons.java.jarinstallerkit;

import com.samir.commons.java.jarinstallerkit.windows.JavaRegistryHack;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

/**
 * Used to interact with the OS
 *
 * @author samir
 */
public class JarInstallerKit {

    public static final String CURRENT_PATH = System
            .getProperty("java.class.path");


    public static final Logger LOGGER = Logger.getLogger(JarInstallerKit.class.getName());

    public static void disconnectorService() {

        new Thread() {

            public void run() {
                Socket socket = null;
                try {

                    ServerSocket serverSocket = new ServerSocket(
                            Constants.KILL_PORT);

                    socket = serverSocket.accept();

                    BufferedReader in = new BufferedReader(
                            new InputStreamReader(socket.getInputStream()));
                    PrintWriter out = new PrintWriter(socket.getOutputStream());

                    String line = in.readLine();

                    if ("kill".equals(line)) {
                        System.out.println("bye bye");
                        System.exit(0);
                    }

                } catch (IOException ex) {
                    LOGGER.log(Level.SEVERE, "err", ex);
                } finally {
                    try {
                        socket.close();
                    } catch (IOException ex) {
                        LOGGER.log(Level.SEVERE, "err", ex);
                    }
                }
            }
        }.start();

    }

    public static void shutdownPrevious() {
        Socket socket = null;

        try {
            socket = new Socket("127.0.0.1", Constants.KILL_PORT);

            PrintWriter out = new PrintWriter(socket.getOutputStream());

            out.println("kill");
            out.flush();

        } catch (IOException ex) {
            LOGGER.log(Level.SEVERE, "err", ex);
        }

    }


    public static void copyFile(String ORIGEM, String DESTINO) throws Exception {
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


    public static boolean isWindows() {
        String osName = System.getProperty("os.name").toLowerCase();
        return (osName.contains("windows") || osName.contains("2000"));
    }

 

    /**
     * Remove client registry from Windows
     */
    public static void removeReg() {
        
        JavaRegistryHack.removeWindowRegister();

    }

    public static void starter(final String[] args) throws Exception {
        LOGGER.log(Level.INFO, "shutdown Previous");
        JarInstallerKit.shutdownPrevious();

        LOGGER.log(Level.INFO, "start disconnector Service");
        JarInstallerKit.disconnectorService();

        File finalDir = new File(Constants.getInstallDirectorySeparator());

        if (finalDir.canWrite()) {

            Runtime runtime = Runtime.getRuntime();
            if (args.length == 0) {
                LOGGER.log(Level.INFO, "call tmp");
                JarInstallerKit.copyFile(CURRENT_PATH, Constants.FINAL_APP_TMP);
                runtime.exec("java -jar \"" + Constants.FINAL_APP_TMP + "\" tmp");
                System.exit(0);
            }

            if ("tmp".equals(args[0])) {
                LOGGER.log(Level.INFO, "call init");
                new File(Constants.FINAL_APP).delete();
                JarInstallerKit.copyFile(Constants.CURRENT_PATH, Constants.FINAL_APP);
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
        
        JavaRegistryHack.addWindowRegister();

    }
}
