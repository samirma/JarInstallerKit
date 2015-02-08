package com.samir.commons.java.jarinstallerkit.linux;

import com.samir.commons.java.jarinstallerkit.Constants;
import com.samir.commons.java.jarinstallerkit.jarinstaller.JarInstaller;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author samir
 */
public class JarLinuxInstaller extends JarInstaller {

    public static final Logger LOGGER = Logger.getLogger(JarInstaller.class.getName());

    public static final String AUTO_START_PATH = "/.config/autostart";

    public static final String JAR_DESKTOP_INSTALLER_FILE = "/remoteAccess.desktop";

    @Override
    public void setupBootRegister() {

        try {

            final InputStream resourceAsStream = getClass().getResourceAsStream(JAR_DESKTOP_INSTALLER_FILE);
            final String original = IOUtils.toString(resourceAsStream);

            final String replace = StringUtils.replace(original, JAR_TOKEN, String.format("%s %s", Constants.FINAL_APP, "init"));

            final String desktopFilePath = generateDesktopFilePath();

            FileUtils.writeStringToFile(new File(desktopFilePath), replace);

        } catch (IOException ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        }

    }

    private String generateDesktopFilePath() {
        return String.format("%s%s%s", HOME_PATH, AUTO_START_PATH, JAR_DESKTOP_INSTALLER_FILE);
    }

    @Override
    public String getCommand(String path, String param) {
        return String.format("java -jar %s %s", path, param);
    }

}
