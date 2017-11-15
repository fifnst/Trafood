package id.trafood.trafood.Models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by kulinerin 1 on 06/11/2017.
 */

public class Location {
    @SerializedName("city")
    private String city;
    @SerializedName("district")
    private String district;
    @SerializedName("lat")
    private String lat;
    @SerializedName("lng")
    private String lng;

    public Location(String city, String district, String lat, String lng) {
        this.city = city;
        this.district = district;
        this.lat = lat;
        this.lng = lng;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }
}
