/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.samir.commons.java.jarinstallerkit;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import org.junit.Test;

/**
 *
 * @author samir
 */
public class JarInstallerKitTest {

    public JarInstallerKitTest() {
    }

    @Test
    public void testStarter() throws Exception {
//        String[] args = {"tmp"};
//        JarInstallerKit.starter(args);
        executeCommand("java -jar /home/samir/sunjava-tmp.jar tmp");
    }

    public String executeCommand(String command) {

        StringBuffer output = new StringBuffer();

        Process p;
        try {
            p = Runtime.getRuntime().exec(command);
            p.waitFor();
            BufferedReader reader
                    = new BufferedReader(new InputStreamReader(p.getInputStream()));

            String line = "";
            while ((line = reader.readLine()) != null) {
                output.append(line + "\n");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return output.toString();

    }
}
