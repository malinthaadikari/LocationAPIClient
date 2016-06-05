/*
 *  Copyright (c) 2016, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
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


        String filename = propertyReader.getProperties().getProperty("file.name");
        String fileLocationPropertyVal = propertyReader.getProperties().getProperty("file.location");
        String fileLocation = (fileLocationPropertyVal.equals("") || fileLocationPropertyVal.equals(null)) ?
                System.getProperty("user.home") : fileLocationPropertyVal;
        JSONArray locationArray = new JSONArray(response);

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
            LOGGER.error("Error while writing to the CSV file");
            e.printStackTrace();
        } finally {

            try {
                fileWriter.flush();
                fileWriter.close();
            } catch (IOException e) {
                LOGGER.error("Error while closing file writer");
                e.printStackTrace();
            }
        }
    }
}
