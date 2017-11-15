package id.trafood.trafood.Rest;

import id.trafood.trafood.Models.GetMenu;
import id.trafood.trafood.Models.Menu;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by kulinerin 1 on 16/10/2017.
 */

public interface RestApi {
    //get json
    @GET("index.php/getMenu.php")
    Call<GetMenu> getMenu();
}
