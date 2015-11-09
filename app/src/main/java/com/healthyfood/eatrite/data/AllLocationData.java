package com.healthyfood.eatrite.data;

/**
 * Created by ADMIN on 27-10-2015.
 */
public class AllLocationData {

    String locationId,locationLine1,locationLine2,landmark,city,state,country,pincode;

    public AllLocationData(String locationId, String locationLine1, String locationLine2, String landmark, String city, String state, String country, String pincode) {
        this.locationId = locationId;
        this.locationLine1 = locationLine1;
        this.locationLine2 = locationLine2;
        this.landmark = landmark;
        this.city = city;
        this.state = state;
        this.country = country;
        this.pincode = pincode;
    }

    public String getLocationId() {
        return locationId;
    }

    public void setLocationId(String locationId) {
        this.locationId = locationId;
    }

    public String getLocationLine1() {
        return locationLine1;
    }

    public void setLocationLine1(String locationLine1) {
        this.locationLine1 = locationLine1;
    }

    public String getLocationLine2() {
        return locationLine2;
    }

    public void setLocationLine2(String locationLine2) {
        this.locationLine2 = locationLine2;
    }

    public String getLandmark() {
        return landmark;
    }

    public void setLandmark(String landmark) {
        this.landmark = landmark;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }
}
