/*
 *  Copyright text.
 *
 */

package com.malintha.LocationAPIClient;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class PropertyReader {

    private static final Log LOGGER = LogFactory.getLog(PropertyReader.class);
    private InputStream inputStream;

    public Properties getProperties() throws IOException {
        Properties properties = new Properties();
        String propertyFileName = APIClientConstants.CONFIG_FILE_NAME;

        try {

            inputStream = getClass().getClassLoader().getResourceAsStream(propertyFileName);

            if (inputStream != null) {
                properties.load(inputStream);
            } else {
                throw new FileNotFoundException("Property file " + propertyFileName +  " not found");
            }

        } catch (Exception e) {
            LOGGER.error("Exception: " + e);
        } finally {
            inputStream.close();
        }
        return properties;
    }
}