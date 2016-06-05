/*
 *  Copyright text
 */

package com.malintha.LocationAPIClient;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.json.JSONException;

public class APIClient {

    private final static Log LOGGER = LogFactory.getLog(APIClient.class);
    private PropertyReader propertyReader;

    public APIClient(PropertyReader propertyReader){
        this.propertyReader = propertyReader;
    }

    public String getWeatherInfo(String location) throws IOException, JSONException {

        String url =  propertyReader.getProperties().getProperty("position.endpoint") + location;

        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        con.setRequestMethod("GET");

        int responseCode = con.getResponseCode();
        LOGGER.info(String.valueOf(responseCode));
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        return response.toString();
    }

}
