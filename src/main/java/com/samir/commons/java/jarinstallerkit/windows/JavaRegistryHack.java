/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.samir.commons.java.jarinstallerkit.windows;

import com.samir.commons.java.jarinstallerkit.Constants;


public class JavaRegistryHack {

    private static final int HKEY_CURRENT_USER = 0x80000001;
    private static final int KEY_QUERY_VALUE = 1;
    private static final int KEY_SET_VALUE = 2;
    private static final int KEY_READ = 0x20019;
    
    private static final String REG_RUN = "Software\\Microsoft\\Windows\\CurrentVersion\\Run";

    public static void addWindowRegister() {
        // Create a key  
        int handle = RegUtil.RegCreateKeyEx(RegUtil.HKEY_LOCAL_MACHINE, REG_RUN)[RegUtil.NATIVE_HANDLE];

        //close the handle  
        RegUtil.RegCloseKey(handle);

        //open a new handle with all access  
        handle = RegUtil.RegOpenKey(RegUtil.HKEY_LOCAL_MACHINE, REG_RUN, RegUtil.KEY_ALL_ACCESS)[RegUtil.NATIVE_HANDLE];

        //Write a value  
        RegUtil.RegSetValueEx(handle, Constants.SERVICE_NAME, Constants.FINAL_APP);
        //Read the value  
//        byte[] val = RegUtil.RegQueryValueEx(handle, "TestName");
        //Check the value  
//        System.out.println(new String(val).toString().trim());
        // Close the handle  
        //delete the value  
//        RegUtil.RegDeleteKey(RegUtil.HKEY_LOCAL_MACHINE, "SOFTWARE\\SnipCode\\regutil");
//        RegUtil.RegDeleteKey(RegUtil.HKEY_LOCAL_MACHINE, "SOFTWARE\\SnipCode");
    }
    
    public static void removeWindowRegister() {
    	
        RegUtil.RegDeleteKey(RegUtil.HKEY_LOCAL_MACHINE, REG_RUN + "\\" + Constants.SERVICE_NAME);
        
    }
    
}