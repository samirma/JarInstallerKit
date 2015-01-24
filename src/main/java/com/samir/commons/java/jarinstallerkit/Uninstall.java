package com.samir.commons.java.jarinstallerkit;

import java.io.File;

public class Uninstall {

    public static void main(String[] args) throws Exception {

        System.out.println("Shutdown current client");
        JarInstallerKit.shutdownPrevious();

        System.out.println("Removing reg entries");
        JarInstallerKit.removeReg();

        Thread.sleep(10000);

        System.out.println("Removing apps");
        File appTmp = new File(Constants.FINAL_APP_TMP);
        if (appTmp.exists()) {
            appTmp.delete();
        }

        File app = new File(Constants.FINAL_APP);
        if (app.exists()) {
            app.delete();
        }

        System.out.println("Done");
    }

}
