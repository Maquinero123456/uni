package practicas_eldom.config;

import java.util.Properties;

import Utilidades.ConfigUtilities;

public class ConfigurationConnect{
	private 	Properties 			configApp = new Properties();

	public ConfigurationConnect() {
		
		ConfigUtilities.loadConfig(configApp, ConfigProperties.CONFIG_FILE);
		
	}
	
	public Properties getProperties() {
		return configApp;
	}
	
	public void setProperty(String property, String value) {
		configApp.setProperty(property, value);
		SaveConfig();
	}
	
	public String getProperty(String property) {
		return configApp.getProperty(property);
	}
	
	public void SaveConfig() {
		ConfigUtilities.saveConfig(configApp, ConfigProperties.CONFIG_FILE);
	}
}
