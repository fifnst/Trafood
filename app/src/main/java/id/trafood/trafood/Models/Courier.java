package id.trafood.trafood.Models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by kulinerin 1 on 15/12/2017.
 */

public class Courier {
    @SerializedName("courier_id")
    private String courier_id;

    @SerializedName("c_name")
    private String c_name;

    @SerializedName("c_logo")
    private String c_logo;

    @SerializedName("c_userid")
    private String c_userid;

    @SerializedName("c_email")
    private String c_email;

    @SerializedName("c_telp")
    private String c_telp;

    @SerializedName("c_address")
    private String c_address;

    @SerializedName("c_open")
    private String c_open;

    @SerializedName("c_close")
    private String c_close;

    @SerializedName("c_region")
    private String c_region;

    @SerializedName("c_lat")
    private String c_lat;

    @SerializedName("c_lng")
    private String c_lng;

    @SerializedName("c_range")
    private String c_range;

    public Courier(String courier_id, String c_name, String c_logo, String c_userid, String c_email, String c_telp, String c_address, String c_open, String c_close, String c_region, String c_lat, String c_lng, String c_range) {
        this.courier_id = courier_id;
        this.c_name = c_name;
        this.c_logo = c_logo;
        this.c_userid = c_userid;
        this.c_email = c_email;
        this.c_telp = c_telp;
        this.c_address = c_address;
        this.c_open = c_open;
        this.c_close = c_close;
        this.c_region = c_region;
        this.c_lat = c_lat;
        this.c_lng = c_lng;
        this.c_range = c_range;
    }

    public String getCourier_id() {
        return courier_id;
    }

    public void setCourier_id(String courier_id) {
        this.courier_id = courier_id;
    }

    public String getC_name() {
        return c_name;
    }

    public void setC_name(String c_name) {
        this.c_name = c_name;
    }

    public String getC_logo() {
        return c_logo;
    }

    public void setC_logo(String c_logo) {
        this.c_logo = c_logo;
    }

    public String getC_userid() {
        return c_userid;
    }

    public void setC_userid(String c_userid) {
        this.c_userid = c_userid;
    }

    public String getC_email() {
        return c_email;
    }

    public void setC_email(String c_email) {
        this.c_email = c_email;
    }

    public String getC_telp() {
        return c_telp;
    }

    public void setC_telp(String c_telp) {
        this.c_telp = c_telp;
    }

    public String getC_address() {
        return c_address;
    }

    public void setC_address(String c_address) {
        this.c_address = c_address;
    }

    public String getC_open() {
        return c_open;
    }

    public void setC_open(String c_open) {
        this.c_open = c_open;
    }

    public String getC_close() {
        return c_close;
    }

    public void setC_close(String c_close) {
        this.c_close = c_close;
    }

    public String getC_region() {
        return c_region;
    }

    public void setC_region(String c_region) {
        this.c_region = c_region;
    }

    public String getC_lat() {
        return c_lat;
    }

    public void setC_lat(String c_lat) {
        this.c_lat = c_lat;
    }

    public String getC_lng() {
        return c_lng;
    }

    public void setC_lng(String c_lng) {
        this.c_lng = c_lng;
    }

    public String getC_range() {
        return c_range;
    }

    public void setC_range(String c_range) {
        this.c_range = c_range;
    }
}
