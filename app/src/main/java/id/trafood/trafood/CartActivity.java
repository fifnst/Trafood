package id.trafood.trafood;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

import id.trafood.trafood.Models.GetOrder;
import id.trafood.trafood.Models.Order;
import id.trafood.trafood.OrderP.CartAdapter;
import id.trafood.trafood.Rest.ApiClient;
import id.trafood.trafood.Rest.ApiInterface;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CartActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;
    ApiInterface apiInterface;
    public  static CartActivity ca;
    SharedPrefManager sharedPrefManager;
    TextView tvCartKedai, tvCartLokasiKedai;
    Button buttonCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        recyclerView = (RecyclerView) findViewById(R.id.rvCart);

        tvCartKedai = (TextView) findViewById(R.id.tvCartKedai);
        tvCartLokasiKedai = (TextView) findViewById(R.id.tvCartLokasiKedai);
        buttonCart = (Button) findViewById(R.id.buttonCart);

        layoutManager=new LinearLayoutManager(CartActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        sharedPrefManager = new SharedPrefManager(this);
        String userId = sharedPrefManager.getSpUserid();

        ca= this;
        isiRv(userId);

        isiDetail(userId);


    }

    private void isiDetail(String userId) {
        apiInterface.getOrder(userId).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    JSONObject jsonObject = new JSONObject(response.body().string());
                    String namarm = jsonObject.getString("namarm");
                    tvCartKedai.setText(namarm);
                    String alamatrm =jsonObject.getString( "alamatrm");
                    tvCartLokasiKedai.setText(alamatrm);
                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }

    private void isiRv(String userId) {
        Call<GetOrder> getOrderCall = apiInterface.getCart(userId);
        getOrderCall.enqueue(new Callback<GetOrder>() {
            @Override
            public void onResponse(Call<GetOrder> call, Response<GetOrder> response) {
                List<Order> orders = response.body().getListDataOrder();
                adapter = new CartAdapter(orders);
                recyclerView.setAdapter(adapter);

            }

            @Override
            public void onFailure(Call<GetOrder> call, Throwable t) {

            }
        });
    }
}
