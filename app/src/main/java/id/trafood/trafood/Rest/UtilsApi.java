package id.trafood.trafood.Rest;

/**
 * Created by kulinerin 1 on 20/11/2017.
 */

public class UtilsApi {
    public static final String BASE_URL_API = "http://rest.trafood.id/index.php/";

    public static ApiInterface getApiServive(){
        return RetrofitClient.getClient(BASE_URL_API).create(ApiInterface.class);
    }
}
