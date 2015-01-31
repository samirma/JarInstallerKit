/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.samir.commons.java.jarinstallerkit.serviceComunicator;

import com.samir.commons.java.jarinstallerkit.Constants;
import static com.samir.commons.java.jarinstallerkit.serviceComunicator.ServiceCommunicator.LOGGER;
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
public class SocketServiceCommunicator extends ServiceCommunicator{
    
    public static final Logger LOGGER = Logger.getLogger(SocketServiceCommunicator.class.getName());
    
    @Override
      public void disconnectorService() {
        new Thread() {
            @Override
            public void run() {
                Socket socket = null;
                try {
                    ServerSocket serverSocket = new ServerSocket(Constants.KILL_PORT);
                    socket = serverSocket.accept();
                    BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
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

    @Override
    public void shutdownPrevious() {
        Socket socket;
        try {
            socket = new Socket("127.0.0.1", Constants.KILL_PORT);
            PrintWriter out = new PrintWriter(socket.getOutputStream());
            out.println("kill");
            out.flush();
            socket.close();
        } catch (IOException ex) {
            LOGGER.log(Level.SEVERE, "err", ex);
        }
    }
    
}
