
**APIClient**

This is a API client for location API which provides location information of cities. 

**Build the project:** 

Please run following command to build the project

    mvn clean install


**Run the project:**

Go to the target folder location and run following command providing the city name. Eg: To get location information of Berlin

    java -jar GoEuroTest.jar "Berlin"

    
**Customizing:**
 
 You can customize API endpoint and CSV file saving location by editing **config.properties** file found in **src/main/resources** folder location. 
 
 1. **positions.endpoint** - API endpoint location
 2. **file.name** - Preferred file name of the CSV file
 3. **file.location** - Preferred file location of the CSV file (If you keep this empty, system will save file to "user.home" location of your operating system.
                  
         position.endpoint = http://api.goeuro.com/api/v2/position/suggest/en/
         file.name = LocationData.csv
         file.location = /home/malintha/ba
