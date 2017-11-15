package id.trafood.trafood.Rest;

import java.util.List;

import id.trafood.trafood.Home.GetModelMenu;
import id.trafood.trafood.Models.GetArticle;
import id.trafood.trafood.Models.GetComment;
import id.trafood.trafood.Models.GetGalery;
import id.trafood.trafood.Models.GetLocation;
import id.trafood.trafood.Models.GetMenu;
import id.trafood.trafood.Models.GetMenuDetail;
import id.trafood.trafood.Models.GetRumahmakan;
import id.trafood.trafood.Models.GetUserView;
import id.trafood.trafood.Models.GetUserVote;
import id.trafood.trafood.Models.PostPutDelRumahmakan;
import id.trafood.trafood.Models.UserView;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

/**
 * Created by kulinerin 1 on 13/10/2017.
 */

public interface ApiInterface {
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
    @FormUrlEncoded
    @POST("kontak")
    Call<PostPutDelRumahmakan> postRumahmakan(@Field("rmid") String nama,
                                          @Field("namarm") String nomor);
    @FormUrlEncoded
    @PUT("kontak")
    Call<PostPutDelRumahmakan> putRumahmakan(@Field("id") String id,
                                     @Field("nama") String nama,
                                     @Field("nomor") String nomor);
    @FormUrlEncoded
    @HTTP(method = "DELETE", path = "kontak", hasBody = true)
    Call<PostPutDelRumahmakan> deleteKontak(@Field("id") String id);

    //@FormUrlEncoded
    @GET("index.php/rumahmakan")
    Call<GetRumahmakan> by(@Query("rmid") String rmid);


    @GET("index.php/menudirm")
    Call<GetMenu> getMenu (@Query("rmid") String rmid);

   // @GET("index.php/menurm")
   // Call<GetMenu> menu();

    @GET("index.php/galery")
    Call<GetGalery> getGalery (@Query("rmid") String rmid);

    @GET("index.php/comment")
    Call<GetComment> getComment (@Query("rmid") String rmid);

    @GET("index.php/menudetail")
    Call<GetMenuDetail> getDetailMenu (@Query("menuid") String menuid);

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

    @GET("index.php/artcile")
    Call<GetArticle> getArticle();

    @GET("index.php/location")
    Call<GetLocation> getLocation();

}
