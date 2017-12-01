package id.trafood.trafood.Models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by kulinerin 1 on 13/10/2017.
 */

public class Rumahmakan {
    @SerializedName("rmid")
    private String rmid;

    @SerializedName("namarm")
    private  String namarm;

    @SerializedName("alamat")
    private String alamat;

    @SerializedName("fotosampul")
    private String fotosampul;

    @SerializedName("fotorm")
    private String fotorm;

    @SerializedName("fasilitas")
    private int fasilitas;

    @SerializedName("fsatu")
    private int fsatu;

    @SerializedName("fdua")
    private int fdua;

    @SerializedName("ftiga")
    private int ftiga;

    @SerializedName("fempat")
    private int fempat;

    @SerializedName("flima")
    private int flima;

    @SerializedName("url")
    private String url;

    @SerializedName("deskripsirm")
    private String deskripsirm;

    @SerializedName ("kota")
    private String kota;

    @SerializedName("userid")
    private String userid;

    @SerializedName("kategorirm")
    private String kategorirm;

    @SerializedName("latitude")
    private String latitude;

    @SerializedName("longitude")
    private String longitude;

    @SerializedName ("sunday")
    private int sunday;

    @SerializedName("monday")
    private int monday;

    @SerializedName("teusday")
    private int tuesday;

    @SerializedName("wednesday")
    private int wednesday;

    @SerializedName("thursday")
    private int thursday;

    @SerializedName("friday")
    private int friday;

    @SerializedName("saturday")
    private int saturday;

    @SerializedName("notelp")
    private String notelp;

    @SerializedName("buka")
    private String buka;

    @SerializedName("tutup")
    private String tutup;

    @SerializedName("kecamatan")
    private String kecamatan;

    @SerializedName("distance")
    private String distance;

    @SerializedName("rating1")
    private String rating1;

    @SerializedName("rating2")
    private String rating2;

    @SerializedName("rating3")
    private String rating3;

    @SerializedName("rating4")
    private String rating4;

    public Rumahmakan(String rmid, String namarm, String alamat, String fotosampul, String fotorm, int fasilitas, int fsatu, int fdua, int ftiga, int fempat, int flima, String url, String deskripsirm, String kota, String userid, String kategorirm, String latitude, String longitude, int sunday, int monday, int tuesday, int wednesday, int thursday, int friday, int saturday, String notelp, String buka, String tutup, String kecamatan, String distance, String rating1, String rating2, String rating3, String rating4) {
        this.rmid = rmid;
        this.namarm = namarm;
        this.alamat = alamat;
        this.fotosampul = fotosampul;
        this.fotorm = fotorm;
        this.fasilitas = fasilitas;
        this.fsatu = fsatu;
        this.fdua = fdua;
        this.ftiga = ftiga;
        this.fempat = fempat;
        this.flima = flima;
        this.url = url;
        this.deskripsirm = deskripsirm;
        this.kota = kota;
        this.userid = userid;
        this.kategorirm = kategorirm;
        this.latitude = latitude;
        this.longitude = longitude;
        this.sunday = sunday;
        this.monday = monday;
        this.tuesday = tuesday;
        this.wednesday = wednesday;
        this.thursday = thursday;
        this.friday = friday;
        this.saturday = saturday;
        this.notelp = notelp;
        this.buka = buka;
        this.tutup = tutup;
        this.kecamatan = kecamatan;
        this.distance = distance;
        this.rating1 = rating1;
        this.rating2 = rating2;
        this.rating3 = rating3;
        this.rating4 = rating4;
    }

    public String getRmid() {
        return rmid;
    }

    public void setRmid(String rmid) {
        this.rmid = rmid;
    }

    public String getNamarm() {
        return namarm;
    }

    public void setNamarm(String namarm) {
        this.namarm = namarm;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getFotosampul() {
        return fotosampul;
    }

    public void setFotosampul(String fotosampul) {
        this.fotosampul = fotosampul;
    }

    public String getFotorm() {
        return fotorm;
    }

    public void setFotorm(String fotorm) {
        this.fotorm = fotorm;
    }

    public int getFasilitas() {
        return fasilitas;
    }

    public void setFasilitas(int fasilitas) {
        this.fasilitas = fasilitas;
    }

    public int getFsatu() {
        return fsatu;
    }

    public void setFsatu(int fsatu) {
        this.fsatu = fsatu;
    }

    public int getFdua() {
        return fdua;
    }

    public void setFdua(int fdua) {
        this.fdua = fdua;
    }

    public int getFtiga() {
        return ftiga;
    }

    public void setFtiga(int ftiga) {
        this.ftiga = ftiga;
    }

    public int getFempat() {
        return fempat;
    }

    public void setFempat(int fempat) {
        this.fempat = fempat;
    }

    public int getFlima() {
        return flima;
    }

    public void setFlima(int flima) {
        this.flima = flima;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDeskripsirm() {
        return deskripsirm;
    }

    public void setDeskripsirm(String deskripsirm) {
        this.deskripsirm = deskripsirm;
    }

    public String getKota() {
        return kota;
    }

    public void setKota(String kota) {
        this.kota = kota;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getKategorirm() {
        return kategorirm;
    }

    public void setKategorirm(String kategorirm) {
        this.kategorirm = kategorirm;
    }

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

    public int getSunday() {
        return sunday;
    }

    public void setSunday(int sunday) {
        this.sunday = sunday;
    }

    public int getMonday() {
        return monday;
    }

    public void setMonday(int monday) {
        this.monday = monday;
    }

    public int getTuesday() {
        return tuesday;
    }

    public void setTuesday(int tuesday) {
        this.tuesday = tuesday;
    }

    public int getWednesday() {
        return wednesday;
    }

    public void setWednesday(int wednesday) {
        this.wednesday = wednesday;
    }

    public int getThursday() {
        return thursday;
    }

    public void setThursday(int thursday) {
        this.thursday = thursday;
    }

    public int getFriday() {
        return friday;
    }

    public void setFriday(int friday) {
        this.friday = friday;
    }

    public int getSaturday() {
        return saturday;
    }

    public void setSaturday(int saturday) {
        this.saturday = saturday;
    }

    public String getNotelp() {
        return notelp;
    }

    public void setNotelp(String notelp) {
        this.notelp = notelp;
    }

    public String getBuka() {
        return buka;
    }

    public void setBuka(String buka) {
        this.buka = buka;
    }

    public String getTutup() {
        return tutup;
    }

    public void setTutup(String tutup) {
        this.tutup = tutup;
    }

    public String getKecamatan() {
        return kecamatan;
    }

    public void setKecamatan(String kecamatan) {
        this.kecamatan = kecamatan;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getRating1() {
        return rating1;
    }

    public void setRating1(String rating1) {
        this.rating1 = rating1;
    }

    public String getRating2() {
        return rating2;
    }

    public void setRating2(String rating2) {
        this.rating2 = rating2;
    }

    public String getRating3() {
        return rating3;
    }

    public void setRating3(String rating3) {
        this.rating3 = rating3;
    }

    public String getRating4() {
        return rating4;
    }

    public void setRating4(String rating4) {
        this.rating4 = rating4;
    }
}
