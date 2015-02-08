/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.samir.commons.java.jarinstallerkit;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author samir
 */
public class JarInstallerKitTest {
    
    public JarInstallerKitTest() {
    }

    @Test
    public void testStarter() throws Exception {
        String[] args = {"tmp"};
        JarInstallerKit.starter(args);
    }
    
}
