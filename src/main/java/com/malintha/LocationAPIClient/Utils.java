/*
 *  Copyright text
 */

package com.malintha.LocationAPIClient;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.malintha.LocationAPIClient.beans.LocationBean;
import org.json.JSONArray;
import org.json.JSONException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class Utils {

    private static final Log LOGGER = LogFactory.getLog(Utils.class);
    //Delimiter used in CSV file
    private static final String COMMA_DELIMITER = ",";
    private static final String NEW_LINE_SEPARATOR = "\n";

    //CSV file header
    private static final String FILE_HEADER = "id,name,type,latitude,longitude";

    public static void persistInFile(PropertyReader propertyReader, String response) throws JSONException, IOException {


        String filename = propertyReader.getProperties().getProperty(APIClientConstants.FILE_NAME_PROPERTY);
        String fileLocationPropertyVal = propertyReader.getProperties().getProperty(APIClientConstants.FILE_LOCATION_PROPERTY);
        String fileLocation = (fileLocationPropertyVal.equals("") || fileLocationPropertyVal.equals(null)) ?
                System.getProperty(APIClientConstants.USER_HOME) : fileLocationPropertyVal;

        JSONArray locationArray = new JSONArray(response);

        if (locationArray.length() == 0) {
            LOGGER.warn("Location details does not exist");
        }

        Type listType = new TypeToken<List<LocationBean>>() {
        }.getType();
        List<LocationBean> locationInfoList = new Gson().fromJson(locationArray.toString(), listType);

        FileWriter fileWriter = null;

        try {
            fileWriter = new FileWriter(fileLocation + File.separator + filename);

            fileWriter.append(FILE_HEADER);

            fileWriter.append(NEW_LINE_SEPARATOR);

            for (LocationBean locationBean : locationInfoList) {
                fileWriter.append(String.valueOf(locationBean.get_id()));
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(locationBean.getName());
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(locationBean.getType());
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(locationBean.getGeo_position().getLatitude());
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(locationBean.getGeo_position().getLongitude());
                fileWriter.append(NEW_LINE_SEPARATOR);
            }

        } catch (Exception e) {
            LOGGER.error("Error while writing to the CSV file " + e);
        } finally {

            try {
                fileWriter.flush();
                fileWriter.close();
            } catch (IOException e) {
                LOGGER.error("Error while closing file writer" + e);
            }
        }
    }
}
