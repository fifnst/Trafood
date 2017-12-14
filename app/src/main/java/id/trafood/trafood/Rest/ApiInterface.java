package id.trafood.trafood.Rest;

import id.trafood.trafood.Home.GetModelMenu;
import id.trafood.trafood.Home.ModelMenu;
import id.trafood.trafood.Models.GetArticle;
import id.trafood.trafood.Models.GetComment;
import id.trafood.trafood.Models.GetGalery;
import id.trafood.trafood.Models.GetLike;
import id.trafood.trafood.Models.GetLocation;
import id.trafood.trafood.Models.GetMenu;
import id.trafood.trafood.Models.GetMenuDetail;
import id.trafood.trafood.Models.GetOrder;
import id.trafood.trafood.Models.GetRumahmakan;
import id.trafood.trafood.Models.GetUserView;
import id.trafood.trafood.Models.GetUserVote;
import id.trafood.trafood.Models.PostPutDelMenu;
import id.trafood.trafood.Models.PostPutDelOrder;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by kulinerin 1 on 13/10/2017.
 */

public interface ApiInterface {
//
    @GET("index.php/LoginSystem")
    Call<ResponseBody> loginRequest(@Query("email") String email,
                                    @Query("password") String password);

    @GET("index.php/rm/rmbydistance")
    Call<GetRumahmakan> getRumahmakan(@Query("lat") String lat,
                                       @Query("lng") String lng,
                                       @Query("cari") String cari,
                                       @Query("fasilitas") String fasilitas,
                                       @Query("fsatu") String fsatu,
                                       @Query("fdua") String fdua,
                                       @Query("ftiga") String ftiga,
                                       @Query("fempat") String fempat,
                                       @Query("flima") String flima);

    @GET("index.php/rumahmakan")
    Call<GetRumahmakan> by(@Query("rmid") String rmid);


    @GET("index.php/menudirm")
    Call<GetMenu> getMenu (@Query("rmid") String rmid);

    @GET("index.php/menudirm/menulimit")
    Call<GetModelMenu> getMenuLimitRm (@Query("rmid") String rmid);

   // @GET("index.php/menurm")s
   // Call<GetMenu> menu();

    @GET("index.php/galery")
    Call<GetGalery> getGalery (@Query("rmid") String rmid);

    @GET("index.php/comment")
    Call<GetComment> getComment (@Query("rmid") String rmid);

    @GET("index.php/profil")
    Call<GetUserView> getProfil (@Query("userid") String userid );

    @GET("index.php/profilreview")
    Call<GetUserVote> getVote (@Query("userid") String userid );

    @GET("index.php/menurm")
    Call<GetModelMenu> getModelMenu(@Query("lat") String lat,
                                    @Query("lng") String lng);

    @GET("index.php/rumahmakanlimit")
    Call<GetRumahmakan> getRMlimit(@Query("lat") String lat,
                                   @Query("lng") String lng);

    @GET("index.php/articleuser")
    Call<GetArticle> getArticleLimit();

    @GET("index.php/searchmenu")
    Call<GetModelMenu> getMenu(@Query("lat") String lat,
                               @Query("lng") String lng,
                               @Query("cari") String cari,
                               @Query("like") String like,
                               @Query("jarak") String jarak,
                               @Query("termurah") String termurah,
                               @Query("mulaiharga") String mulaiharga,
                               @Query("sampaiharga") String sampaiharga,
                               @Query("fasilitas") String fasilitas,
                               @Query("fsatu") String fsatu,
                               @Query("fdua") String fdua,
                               @Query("ftiga") String ftiga,
                               @Query("fempat") String fempat,
                               @Query("flima") String flima);

    @GET("index.php/searchmenu/searchlike")
    Call<GetModelMenu> getMenuLike(@Query("lat") String lat,
                               @Query("lng") String lng,
                               @Query("cari") String cari,
                               @Query("like") String like,
                               @Query("jarak") String jarak,
                               @Query("termurah") String termurah,
                               @Query("mulaiharga") String mulaiharga,
                               @Query("sampaiharga") String sampaiharga,
                               @Query("fasilitas") String fasilitas,
                               @Query("fsatu") String fsatu,
                               @Query("fdua") String fdua,
                               @Query("ftiga") String ftiga,
                               @Query("fempat") String fempat,
                               @Query("flima") String flima);

    @GET("index.php/artcile")
    Call<GetArticle> getArticle();

    @GET("index.php/artcile/articleuser")
    Call<GetArticle> getArticleUser(@Query("userid") String userid);

    @GET("index.php/location")
    Call<GetLocation> getLocation();

    @GET("index.php/location")
    Call<GetLocation> getLocationSearch(@Query("keyword") String keyword);

    @GET("index.php/Nomorotomatis/nomormenu")
    Call<ResponseBody> nomormenu();

    @GET("index.php/rm/rumahmakanRingkasan")
    Call<ResponseBody> ringkasanRm(@Query("rmid") String rmid);

    @GET("index.php/Menudetail/detailmenu")
    Call<ResponseBody> menuDetail(@Query("menuid") String menuid);

    @GET("index.php/profil/getuser")
    Call<ResponseBody> userGet(@Query("userid") String userid);

    @GET("index.php/like")
    Call<GetModelMenu> userLike(@Query("userid") String userid);

    @GET("index.php/is_duplicate/url")
    Call<ResponseBody> cekUrl(@Query("url") String url);

    //@FormUrlEncoded
    @GET("index.php/loginSystem/ceklogin")
    Call<ResponseBody> loginGoogle(@Query("email") String email,
                                   @Query("nama") String nama);

    @GET("index.php/is_duplicate/provider")
    Call<ResponseBody> cekProvider(@Query("email") String namamenu);

    @GET("index.php/like/ceklikes")
    Call<ResponseBody> cekLike(@Query("menuid") String menuid,
                               @Query("userid") String userid);

    @GET("index.php/like/savelikes")
    Call<ResponseBody> saveLike(@Query("menuid") String menuid,
                               @Query("userid") String userid);

    @GET("index.php/transaction/cart")
    Call<GetOrder> getCart(@Query("userid") String userid);

    @GET("index.php/order")
    Call<ResponseBody> getOrder(@Query("trans_id") String trans_id);

    @GET("index.php/transaction/cart/cart")
    Call<ResponseBody> getCartDetail(@Query("userid") String userid);

    @GET("index.php/order/detail")
    Call<GetOrder> getOrderDetail(@Query("trans_id") String trans_id);

    @GET("index.php/transaction/order/nomor")
    Call<ResponseBody> getNomorOrder();

    @GET("index.php/transaction/cart/cekrm")
    Call<ResponseBody> cekRm (@Query("userid") String userid,
                              @Query("menuid") String menuid);


}
