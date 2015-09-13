package com.samir.commons.java.jarinstallerkit;

import java.io.File;

/**
 * 
 * @author sam
 */
public class Constants {

	public static final String CURRENT_PATH = System
			.getProperty("java.class.path");
	public static final int KILL_PORT = 2277;
	public final static String FINAL_APP = getInstallDirectorySeparator()
			+ "sunjava.jar";
	public final static String FINAL_APP_TMP = getInstallDirectorySeparator()
			+ "sunjava-tmp.jar";
	public final static String SERVICE_NAME = "itrustmynetworkservice";
	public final static String PROGRAM_FILES = "ProgramFiles";
	public static String INSTALL_DIR = null;

	public static String getHome() {
		return System.getProperty("user.home");
	}

	/**
	 * Define a directory install
	 * @return
	 */
	public static String getInstallDirectorySeparator() {
		String dir;
		
		if (INSTALL_DIR == null) {
			String fileSeparador = System.getProperty("file.separator");
			String homeDir = getHome() + fileSeparador;
			dir = System.getenv().get(PROGRAM_FILES);
			File program = new File(dir);
			
			if (program.canWrite()) {

				String finalDir = dir + fileSeparador + SERVICE_NAME + fileSeparador;

				File finalDirFile = new File(finalDir);

				if (finalDirFile.exists()) {

					dir = finalDir;

				} else {

					if (finalDirFile.mkdir()) {

						dir = finalDir;

					} else {

						dir = homeDir;

					}

				}

			} else {

				dir = homeDir;

			}
			
			INSTALL_DIR = dir;
			
		} else {
			
			dir = INSTALL_DIR;
			
		}
		
		return dir;
	}
}
