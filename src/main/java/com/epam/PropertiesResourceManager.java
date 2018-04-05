package com.epam;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class PropertiesResourceManager {
    private Properties properties = new Properties();

    public PropertiesResourceManager() {
        properties = new Properties();
    }


    public String getProperty(final String key) {
        return properties.getProperty(key);
    }

    public PropertiesResourceManager(final String resourceName) {
        properties = appendFromResource(properties, resourceName);
    }

    private Properties appendFromResource(final Properties objProperties, final String resourceName) {
        InputStream inStream = this.getClass().getClassLoader().getResourceAsStream(resourceName);

        if (inStream != null) {
            try {
                objProperties.load(inStream);
                inStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.err.println(String.format("Resource \"%1$s\" could not be found", resourceName));
        }
        return objProperties;
    }
}