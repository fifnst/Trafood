package id.trafood.trafood.Models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by kulinerin 1 on 07/12/2017.
 */

public class Order {
    @SerializedName("menuid")
    private String menuid;
    @SerializedName("namamenu")
    private String namamenu;
    @SerializedName("harga")
    private String harga;
    @SerializedName("namarm")
    private String namarm;
    @SerializedName("rmid")
    private String rmid;
    @SerializedName("foto")
    private String foto;
    @SerializedName("qty")
    private String qty;
    @SerializedName("tarns_id")
    private String tarns_id;

    public Order(String menuid, String namamenu, String harga, String namarm, String rmid, String foto, String qty, String tarns_id) {
        this.menuid = menuid;
        this.namamenu = namamenu;
        this.harga = harga;
        this.namarm = namarm;
        this.rmid = rmid;
        this.foto = foto;
        this.qty = qty;
        this.tarns_id = tarns_id;
    }

    public String getMenuid() {
        return menuid;
    }

    public void setMenuid(String menuid) {
        this.menuid = menuid;
    }

    public String getNamamenu() {
        return namamenu;
    }

    public void setNamamenu(String namamenu) {
        this.namamenu = namamenu;
    }

    public String getHarga() {
        return harga;
    }

    public void setHarga(String harga) {
        this.harga = harga;
    }

    public String getNamarm() {
        return namarm;
    }

    public void setNamarm(String namarm) {
        this.namarm = namarm;
    }

    public String getRmid() {
        return rmid;
    }

    public void setRmid(String rmid) {
        this.rmid = rmid;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public String getTarns_id() {
        return tarns_id;
    }

    public void setTarns_id(String tarns_id) {
        this.tarns_id = tarns_id;
    }
}
