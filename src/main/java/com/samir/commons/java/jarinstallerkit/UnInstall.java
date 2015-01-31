package com.samir.commons.java.jarinstallerkit;


import com.samir.commons.java.jarinstallerkit.jarinstaller.JarUnInstall;
import com.samir.commons.java.jarinstallerkit.jarinstaller.JarUnInstallFactory;

public class UnInstall {

    public void main(String[] args) throws Exception {

        JarUnInstall jarUnInstall = JarUnInstallFactory.getjarUnstall();
        jarUnInstall.executeUnInstall();
        
    }

}
