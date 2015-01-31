/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.samir.commons.java.jarinstallerkit.serviceComunicator;

import com.samir.commons.java.jarinstallerkit.Constants;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author samir
 */
public abstract class ServiceCommunicator {
    
    public static final Logger LOGGER = Logger.getLogger(ServiceCommunicator.class.getName());

    public abstract void disconnectorService();

    public abstract void shutdownPrevious();

}
