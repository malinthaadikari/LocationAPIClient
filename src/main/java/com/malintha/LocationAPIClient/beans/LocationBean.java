/*
 *  Copyright text
 */

package com.malintha.LocationAPIClient.beans;

import java.io.Serializable;

public class LocationBean implements Serializable {
    private String _id;
    private String name;
    private String type;
    ;
    private GEOInfo geo_position;

    public LocationBean() {
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public GEOInfo getGeo_position() {
        return geo_position;
    }

    public void setGeo_position(GEOInfo geo_position) {
        this.geo_position = geo_position;
    }

    public class GEOInfo {
        private String latitude;
        private String longitude;

        public String getLatitude() {
            return latitude;
        }

        public void setLatitude(String latitude) {
            this.latitude = latitude;
        }

        public String getLongitude() {
            return longitude;
        }

        public void setLongitude(String longitude) {
            this.longitude = longitude;
        }
    }
}