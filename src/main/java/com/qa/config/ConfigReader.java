package com.qa.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Utility class to read properties from config.properties file.
 * The file is assumed to be located in the root of the test resources directory.
 */
public class ConfigReader {

    private static Properties properties;
    private final static String CONFIG_FILE_PATH = "src/main/resources/config.properties";

    /**
     * Loads the properties file once when the class is first accessed.
     */
    static {
        try (FileInputStream inputStream = new FileInputStream(CONFIG_FILE_PATH)) {
            properties = new Properties();
            properties.load(inputStream);
        } catch (IOException e) {
            // Log the error or throw a runtime exception if configuration is critical
            System.err.println("ERROR: Could not load configuration file at: " + CONFIG_FILE_PATH);
            e.printStackTrace();
            throw new RuntimeException("Failed to load configuration file.", e);
        }
    }

    /**
     * Retrieves the value of a property based on its key.
     * @param key The property key (e.g., "baseURL").
     * @return The property value.
     */
    public static String getProperty(String key) {
        if (properties == null) {
            // This should ideally not happen if the static block executed successfully
            throw new IllegalStateException("Configuration properties were not loaded. Check console for file path errors.");
        }
        String value = properties.getProperty(key);
        if (value == null) {
            System.err.println("WARNING: Property key '" + key + "' not found in config.properties.");
        }
        return value;
    }
}