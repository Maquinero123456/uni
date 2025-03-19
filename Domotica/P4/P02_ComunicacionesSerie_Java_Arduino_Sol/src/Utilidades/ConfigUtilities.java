package Utilidades;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigUtilities {
	public static boolean loadConfig(Properties properties, String configFile) {
		try {
			BufferedInputStream input = new BufferedInputStream(
					new FileInputStream(configFile));
			try {
				properties.load(input);
			} finally {
				input.close();
			}
			return true;
		} catch (FileNotFoundException e) {
			// No configuration file exists.
		} catch (IOException e) {
			System.err.println("Failed to read configuration file: "
					+ configFile);
			e.printStackTrace();
		}
		return false;
	}
	
	public static void saveConfig(Properties properties, String configFile) {
		try {
			File fp = new File(configFile);
			if (fp.exists()) {
				File targetFp = new File(configFile + ".bak");
				if (targetFp.exists()) {
					targetFp.delete();
				}
				fp.renameTo(targetFp);
			}
			FileOutputStream output = new FileOutputStream(configFile);
			try {
				properties.store(output, "Configuration for "+ configFile);
			} finally {
				output.close();
			}
		} catch (IOException e) {
			System.err.println("failed to save configuration to " + configFile);
			e.printStackTrace();
		}
	}
}
