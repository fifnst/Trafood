package id.trafood.trafood.Models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by kulinerin 1 on 14/12/2017.
 */

public class AddressP {
    @SerializedName("address_id")
    private String address_id;

    @SerializedName("address_name")
    private String address_name;

    @SerializedName("recipient_name")
    private String recipient_name;

    @SerializedName("address")
    private String address;

    @SerializedName("telp")
    private String telp;

    @SerializedName("city")
    private String city;

    @SerializedName("userid")
    private String userid;

    @SerializedName("lat")
    private String lat;

    @SerializedName("lng")
    private  String lng;

    public AddressP(String address_id, String address_name, String recipient_name, String address, String telp, String city, String userid, String lat, String lng) {
        this.address_id = address_id;
        this.address_name = address_name;
        this.recipient_name = recipient_name;
        this.address = address;
        this.telp = telp;
        this.city = city;
        this.userid = userid;
        this.lat = lat;
        this.lng = lng;
    }

    public String getAddress_id() {
        return address_id;
    }

    public void setAddress_id(String address_id) {
        this.address_id = address_id;
    }

    public String getAddress_name() {
        return address_name;
    }

    public void setAddress_name(String address_name) {
        this.address_name = address_name;
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

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
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

