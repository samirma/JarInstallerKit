/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.samir.commons.java.jarinstallerkit.serviceComunicator;

/**
 *
 * @author samir
 */
public class ServiceCommunicatorFactory {

    public static ServiceCommunicator getServiceCommunicator() {
        return new SocketServiceCommunicator();
    }
    
    
    
}
