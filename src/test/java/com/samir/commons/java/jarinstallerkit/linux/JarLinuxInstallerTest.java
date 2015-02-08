/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.samir.commons.java.jarinstallerkit.linux;

import com.samir.commons.java.jarinstallerkit.jarinstaller.InstallerFactory;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author samir
 */
public class JarLinuxInstallerTest {
    
    @Test
    public void testSetupBootRegister() {
        
        final JarLinuxInstaller jarLinuxInstaller = new JarLinuxInstaller();
        
        final boolean linux = InstallerFactory.isLinux();
        assertTrue(linux);
        
        jarLinuxInstaller.setupBootRegister();
        
    }
    
}
