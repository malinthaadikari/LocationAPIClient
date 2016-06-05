/*
 *  Copyright text
 */

package com.malintha.LocationAPIClient;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/*
This is the main class. Users should provide the city name as the first command line argument with execution
 */
public class APIClientTester {

    private final static Log LOGGER = LogFactory.getLog(APIClientTester.class);

    public static void main(String[] args) throws Exception {

        PropertyReader propertyReader = new PropertyReader();
        APIClient apiClient = new APIClient(propertyReader);

        //handling the case when user doesn't provide the city name
        if(args.length == 0) {
            LOGGER.error("City name has not been provided");
        }
        else {
            Utils.persistInFile(propertyReader, apiClient.getLocationInfo(args[0]));
        }
    }
}