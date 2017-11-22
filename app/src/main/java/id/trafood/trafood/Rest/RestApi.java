package id.trafood.trafood.Rest;

import id.trafood.trafood.Models.GetMenu;
import id.trafood.trafood.Models.Menu;
import id.trafood.trafood.Models.PostPutDelMenu;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

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
}
