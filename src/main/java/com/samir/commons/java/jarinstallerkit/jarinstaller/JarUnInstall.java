/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.samir.commons.java.jarinstallerkit.jarinstaller;

import com.samir.commons.java.jarinstallerkit.serviceComunicator.ServiceCommunicator;
import com.samir.commons.java.jarinstallerkit.serviceComunicator.ServiceCommunicatorFactory;

/**
 *
 * @author samir
 */
public abstract class JarUnInstall {

    protected final ServiceCommunicator serviceCommunicator;

    public JarUnInstall() {
        serviceCommunicator = ServiceCommunicatorFactory.getServiceCommunicator();
    }

    public void execute() {
        System.out.println("Shutdown current client");
        serviceCommunicator.shutdownPrevious();

        executeUnInstall();

    }

    public abstract void executeUnInstall();

}
