package id.trafood.trafood.Models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by TRAFOOD on 12/14/2017.
 */

public class Address {
    @SerializedName("userid")
    private String userid;

    @SerializedName("recipient_name")
    private String recipient_name;

    @SerializedName("address")
    private String address;

    @SerializedName("address_id")
    private String address_id;

    @SerializedName("telp")
    private String telp;

    @SerializedName("city")
    private String city;

    @SerializedName("lat")
    private String lat;

    @SerializedName("lng")
    private  String lng;

    public Address(String userid, String recipient_name, String address, String address_id, String telp, String city, String lat, String lng) {
        this.userid = userid;
        this.recipient_name = recipient_name;
        this.address = address;
        this.address_id = address_id;
        this.telp = telp;
        this.city = city;
        this.lat = lat;
        this.lng = lng;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getRecipient_name() {
        return recipient_name;
    }

    public void setRecipient_name(String recipient_name) {
        this.recipient_name = recipient_name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress_id() {
        return address_id;
    }

    public void setAddress_id(String address_id) {
        this.address_id = address_id;
    }

    public String getTelp() {
        return telp;
    }

    public void setTelp(String telp) {
        this.telp = telp;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
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
