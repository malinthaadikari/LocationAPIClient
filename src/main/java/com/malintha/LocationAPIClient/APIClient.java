/*
 *  Copyright text
 */

package com.malintha.LocationAPIClient;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.httpclient.HttpException;

public class APIClient {

    private final static Log LOGGER = LogFactory.getLog(APIClient.class);
    private PropertyReader propertyReader;

    public APIClient(PropertyReader propertyReader){
        this.propertyReader = propertyReader;
    }

    public String getLocationInfo(String cityName) throws IOException {

        String url =  propertyReader.getProperties().getProperty(APIClientConstants.ENDPOINT_PROPERTY) + cityName;

        URL urlObj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) urlObj.openConnection();

        con.setRequestMethod("GET");
        //Handling Bad request error.
        if (Integer.toString(con.getResponseCode()).equals("400")){
            LOGGER.error("Response 400 with bad request");
            throw new HttpException("Bad Request");
        }

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