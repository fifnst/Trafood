package id.trafood.trafood.Rest;

import id.trafood.trafood.Home.GetModelMenu;
import id.trafood.trafood.Models.GetMenu;
import id.trafood.trafood.Models.Menu;
import id.trafood.trafood.Models.PostPutDelAddress;
import id.trafood.trafood.Models.PostPutDelMenu;
import id.trafood.trafood.Models.PostPutDelOrder;
import id.trafood.trafood.Models.PostPutDelRm;
import id.trafood.trafood.Models.PostPutDelUser;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

/**
 * Created by kulinerin 1 on 16/10/2017.
 */

public interface RestApi {
    //get json
    @FormUrlEncoded
    @POST("index.php/Menu")
    Call<PostPutDelMenu> postMenu(//@Field("menuid") String menuid,
                                  @Field("namamenu") String namamenu,
                                  @Field("deskripsi") String deskripsi,
                                  @Field("harga") String harga,
                                  @Field("tag") String tag,
                                  @Field("fotofile") String fotofile,
                                  @Field("userid") String userid,
                                  @Field("rmid") String rmid);

    @GET("index.php/menus/menuKedai")
    Call<GetModelMenu> getMenudiRm(@Query("rmid") String rmid);

    @FormUrlEncoded
    @PUT("index.php/menu")
    Call<PostPutDelMenu> putMenu(@Field("menuid") String menuid,
                                 @Field("namamenu") String namamenu,
                                 @Field("deskripsi") String deskripsi,
                                 @Field("harga") String harga,
                                 @Field("tag") String tag,
                                 @Field("userid") String userid);

    @FormUrlEncoded
    @PUT("index.php/menu/updatephoto")
    Call<PostPutDelMenu> putFotoMenu(@Field("fotofile") String fotofile,
                                     @Field("userid") String userid,
                                     @Field("menuid") String menuid);
    @FormUrlEncoded
    @PUT("index.php/rm/rumahmakan/updatephoto")
    Call<PostPutDelRm> putFotoRM(@Field("fotofile") String fotofile,
                                 @Field("userid") String userid,
                                 @Field("rmid") String rmid);

    @FormUrlEncoded
    @PUT("index.php/rm/rumahmakan/alamat")
    Call<PostPutDelRm> putalamatRM(@Field("kecamatan") String kecamatan,
                                   @Field("kota") String kota,
                                   @Field("alamat") String alamat,
                                   @Field("userid") String userid,
                                   @Field("rmid") String rmid);
    @FormUrlEncoded
    @PUT("index.php/rm/rumahmakan/latlng")
    Call<PostPutDelRm> putLatLngRm(@Field("latitude") String latitude,
                                   @Field("longitude") String longitude,
                                   @Field("userid") String userid,
                                   @Field("rmid") String rmid);

    @FormUrlEncoded
    @PUT("index.php/rm/rumahmakan")
    Call<PostPutDelRm> putRm(@Field("namarm") String namarm,
                                   @Field("url") String url,
                                   @Field("deskripsirm") String deskripsirm,
                                   @Field("kategorirm") String kategorirm,
                                   @Field("buka") String buka,
                                   @Field("tutup") String tutup,
                                   @Field("sunday") String sunday,
                                   @Field("monday") String monday,
                                   @Field("tuesday") String tusday,
                                   @Field("wednesday") String wednesday,
                                   @Field("thursday") String thrusday,
                                   @Field("friday") String friday,
                                   @Field("saturday") String saturday,
                                   @Field("fasilitas") String fasilitas,
                                   @Field("fsatu") String fastu,
                                   @Field("fdua") String fdua,
                                   @Field("ftiga") String ftiga,
                                   @Field("fempat") String fempat,
                                   @Field("flima") String flima,
                                   @Field("notelp") String notelp,
                                   @Field("userid") String userid,
                                   @Field("rmid") String rmid);
    @FormUrlEncoded
    @PUT("index.php/user")
    Call<PostPutDelUser> Putuser(@Field("nama") String nama,
                                 @Field("jk") String jk,
                                 @Field("tgllahir") String tgllahir,
                                 @Field("telp") String telp,
                                 @Field("kota") String kota,
                                 @Field("alamat") String alamat,
                                 @Field("tentang") String tentang,
                                 @Field("userid") String userid);

    @FormUrlEncoded
    @PUT("index.php/user/upload")
    Call<PostPutDelUser> putImageUser(@Field("fotofile") String fotofile,
                                      @Field("userid") String userid);

    @FormUrlEncoded
    @POST("index.php/rm/daftar_rm")
    Call<PostPutDelRm> postKedai(@Field("namarm") String namarm,
                                   @Field("url") String url,
                                   @Field("deskripsirm") String deskripsirm,
                                   @Field("notelp") String notelp,
                                   @Field("userid") String userid);

    @FormUrlEncoded
    @PUT("index.php/rm/daftar_rm/updatephoto")
    Call<PostPutDelRm> postFotoRM(@Field("fotofile") String fotofile,
                                 @Field("userid") String userid);

    @FormUrlEncoded
    @PUT("index.php/rm/daftar_rm/alamat")
    Call<PostPutDelRm> postalamatRM(@Field("kecamatan") String kecamatan,
                                   @Field("kota") String kota,
                                   @Field("alamat") String alamat,
                                   @Field("userid") String userid);
    @FormUrlEncoded
    @PUT("index.php/rm/daftar_rm/latlng")
    Call<PostPutDelRm> postLatLngRm(@Field("latitude") String latitude,
                                   @Field("longitude") String longitude,
                                   @Field("userid") String userid);

    @FormUrlEncoded
    @PUT("index.php/daftar_rm/info")
    Call<PostPutDelRm> postInfoRm(@Field("kategorirm") String kategorirm,
                             @Field("buka") String buka,
                             @Field("tutup") String tutup,
                             @Field("sunday") String sunday,
                             @Field("monday") String monday,
                             @Field("tuesday") String tusday,
                             @Field("wednesday") String wednesday,
                             @Field("thursday") String thrusday,
                             @Field("friday") String friday,
                             @Field("saturday") String saturday,
                             @Field("fasilitas") String fasilitas,
                             @Field("fsatu") String fastu,
                             @Field("fdua") String fdua,
                             @Field("ftiga") String ftiga,
                             @Field("fempat") String fempat,
                             @Field("flima") String flima,
                             @Field("userid") String userid);

    @FormUrlEncoded
    @POST("index.php/transaction/cart")
    Call<PostPutDelOrder> postCart(@Field("menuid") String menuid,
                                   @Field("userid") String userid,
                                   @Field("qty") String qty,
                                   @Field("notes") String notes);

    @FormUrlEncoded
    @POST("index.php/transaction/address")
    Call<ResponseBody> postAddress(@Field("userid") String userid,
                                  @Field("address_name") String address_name,
                                  @Field("recipient_name") String recipient_name,
                                  @Field("address") String address,
                                  @Field("telp") String telp,
                                  @Field("city") String city,
                                  @Field("lat") String lat,
                                  @Field("lng") String lng);

    @FormUrlEncoded
    @POST("index.php/transaction/order")
    Call<ResponseBody> postOrder(@Field("trans_id") String trans_id,
                                 @Field("address_id") String address_id,
                                 @Field("totalprice") String totalprice,
                                 @Field("gdistance") String gdistance,
                                 @Field("distance") String distance,
                                 @Field("userid") String userid,
                                 @Field("rmid") String rmid);

    @FormUrlEncoded
    @PUT("index.php/transaction/order")
    Call<ResponseBody> putOrder(@Field("courier_id") String courier_id,
                                 @Field("unit") String unit,
                                 @Field("status") String status,
                                 @Field("trans_id") String trans_id,
                                 @Field("shippingcharge") String shippingcharge);

    @GET("index.php/transaction/address/nomor")
    Call<ResponseBody> getNomor();

    @FormUrlEncoded
    @POST("index.php/transaction/address/input")
    Call<ResponseBody> inputAddress(@Field("userid") String userid,
                                   @Field("address_id") String address_id,
                                   @Field("recipient_name") String recipient_name,
                                   @Field("address") String address,
                                   @Field("telp") String telp,
                                   @Field("lat") String lat,
                                   @Field("lng") String lng);

    @FormUrlEncoded
    @POST("index.php/transaction/order/detail")
    Call<ResponseBody> postOrderDetail(@Field("userid") String userid,
                                       @Field("trans_id") String trans_id);

}
