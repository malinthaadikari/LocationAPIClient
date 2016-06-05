/*
 *  Copyright text
 */

package com.malintha.LocationAPIClient;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;

/*
This is the main class. Users should provide the city name as the first command line argument with execution
 */
public class APIClientTester {


    private final static Log LOGGER = LogFactory.getLog(APIClientTester.class);

    public static void main(String[] args) throws IOException, JSONException {

        String response;
        PropertyReader propertyReader = new PropertyReader();
        APIClient apiClient = new APIClient(propertyReader);

        //handling the case when user doesn't provide the city name
        if (args.length == 0) {
            LOGGER.error("City name has not been provided. Please re-run with a city name");
        } else {
            response = apiClient.getLocationInfo(args[0]);
            if (new JSONArray(response).length() == 0) {
                LOGGER.warn("Got empty response from API. Nothing will be written to the CSV file");
            } else {
                Utils.persistInFile(propertyReader, response);
            }
        }
    }
}