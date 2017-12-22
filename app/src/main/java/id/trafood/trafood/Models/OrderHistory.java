package id.trafood.trafood.Models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by kulinerin 1 on 22/12/2017.
 */

public class OrderHistory {
    @SerializedName("trans_id")
    private String trans_id;

    @SerializedName("datecreate")
    private String datecreate;

    @SerializedName("courier_id")
    private String courier_id;

    @SerializedName("address_id")
    private String address_id;

    @SerializedName("totalprice")
    private String totalprice;

    @SerializedName("shippingcharge")
    private String shippingcharge;

    @SerializedName("distance")
    private String distance;

    @SerializedName("gdistance")
    private String gdistance;

    @SerializedName("userid")
    private String userid;

    @SerializedName("rmid")
    private String rmid;

    @SerializedName("status")
    private String status;

    @SerializedName("note")
    private String note;

    @SerializedName("recipient_name")
    private String recipient_name;

    @SerializedName("addressuser")
    private String addressuser;

    @SerializedName("namarm")
    private String namarm;

    @SerializedName("fotosampul")
    private String fotosampul;

    @SerializedName("alamatrm")
    private String alamatrm;


    public OrderHistory(String trans_id, String datecreate, String courier_id, String address_id, String totalprice, String shippingcharge, String distance, String gdistance, String userid, String rmid, String status, String note, String recipient_name, String addressuser, String namarm, String fotosampul, String alamatrm) {
        this.trans_id = trans_id;
        this.datecreate = datecreate;
        this.courier_id = courier_id;
        this.address_id = address_id;
        this.totalprice = totalprice;
        this.shippingcharge = shippingcharge;
        this.distance = distance;
        this.gdistance = gdistance;
        this.userid = userid;
        this.rmid = rmid;
        this.status = status;
        this.note = note;
        this.recipient_name = recipient_name;
        this.addressuser = addressuser;
        this.namarm = namarm;
        this.fotosampul = fotosampul;
        this.alamatrm = alamatrm;
    }


    public String getTrans_id() {
        return trans_id;
    }

    public void setTrans_id(String trans_id) {
        this.trans_id = trans_id;
    }

    public String getDatecreate() {
        return datecreate;
    }

    public void setDatecreate(String datecreate) {
        this.datecreate = datecreate;
    }

    public String getCourier_id() {
        return courier_id;
    }

    public void setCourier_id(String courier_id) {
        this.courier_id = courier_id;
    }

    public String getAddress_id() {
        return address_id;
    }

    public void setAddress_id(String address_id) {
        this.address_id = address_id;
    }

    public String getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(String totalprice) {
        this.totalprice = totalprice;
    }

    public String getShippingcharge() {
        return shippingcharge;
    }

    public void setShippingcharge(String shippingcharge) {
        this.shippingcharge = shippingcharge;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getGdistance() {
        return gdistance;
    }

    public void setGdistance(String gdistance) {
        this.gdistance = gdistance;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getRmid() {
        return rmid;
    }

    public void setRmid(String rmid) {
        this.rmid = rmid;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getRecipient_name() {
        return recipient_name;
    }

    public void setRecipient_name(String recipient_name) {
        this.recipient_name = recipient_name;
    }

    public String getAddressuser() {
        return addressuser;
    }

    public void setAddressuser(String addressuser) {
        this.addressuser = addressuser;
    }

    public String getNamarm() {
        return namarm;
    }

    public void setNamarm(String namarm) {
        this.namarm = namarm;
    }

    public String getFotosampul() {
        return fotosampul;
    }

    public void setFotosampul(String fotosampul) {
        this.fotosampul = fotosampul;
    }

    public String getAlamatrm() {
        return alamatrm;
    }

    public void setAlamatrm(String alamatrm) {
        this.alamatrm = alamatrm;
    }
}
