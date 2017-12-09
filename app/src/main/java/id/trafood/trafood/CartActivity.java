package id.trafood.trafood;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

import id.trafood.trafood.Home.GetModelMenu;
import id.trafood.trafood.Home.ModelMenu;
import id.trafood.trafood.Home.SuggestionMenuAdapater;
import id.trafood.trafood.Models.GetOrder;
import id.trafood.trafood.Models.Order;
import id.trafood.trafood.OrderP.CartAdapter;
import id.trafood.trafood.Rest.ApiClient;
import id.trafood.trafood.Rest.ApiInterface;
import id.trafood.trafood.Rest.UtilsApi;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CartActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;

    LinearLayout linearcart,linearBelumLogin;
    ApiInterface apiInterface;
    public  static CartActivity ca;
    SharedPrefManager sharedPrefManager;
    TextView tvCartKedai, tvCartLokasiKedai;
    Button buttonCart;
    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Keranjang");
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_green_24dp);
        getSupportActionBar().setElevation(0);
        recyclerView = (RecyclerView) findViewById(R.id.rvCart);
        linearBelumLogin = (LinearLayout) findViewById(R.id.linearCartBelumlogin);
        tvCartKedai = (TextView) findViewById(R.id.tvCartKedai);
        tvCartLokasiKedai = (TextView) findViewById(R.id.tvCartLokasiKedai);
        buttonCart = (Button) findViewById(R.id.buttonCart);
        linearcart = (LinearLayout) findViewById(R.id.LinearCart);

        layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        apiInterface = UtilsApi.getApiServive();

        buttonCart.setVisibility(View.GONE);
        linearcart.setVisibility(View.GONE);
        linearBelumLogin.setVisibility(View.GONE);
        sharedPrefManager = new SharedPrefManager(this);
        final String userId = sharedPrefManager.getSpUserid();
        mContext = this;
        ca= this;
        if (sharedPrefManager.getSPSudahLogin()){
            linearcart.setVisibility(View.VISIBLE);
            buttonCart.setVisibility(View.VISIBLE);
            isiRv(userId);
            isiDetail(userId);
        }else {
            linearBelumLogin.setVisibility(View.VISIBLE);
        }

        buttonCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inputCart();
            }
        });

    }

    private void inputCart() {
        apiInterface.getNomorOrder().enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    JSONObject jsonResult = new JSONObject(response.body().string());
                    String nomor = jsonResult.getString("result");

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

    private void isiDetail(final String userId) {
        Call<GetOrder> orderCall = apiInterface.getCart(userId);
        orderCall.enqueue(new Callback<GetOrder>() {
            @Override
            public void onResponse(Call<GetOrder> call, Response<GetOrder> response) {
                if (response.body().getStatus().equals("200")){
                    List<Order> orders = response.body().getListDataOrder();
                    adapter = new CartAdapter(orders);
                    recyclerView.setAdapter(adapter);

                    Log.d("Retrofit Get ", "Jumlah data Rumah makan: " +
                            String.valueOf(orders.size()));

                }else {
                    tvCartKedai.setText(userId);
                }
            }

            @Override
            public void onFailure(Call<GetOrder> call, Throwable t) {
                tvCartKedai.setText(userId);
            }
        });

    }

    private void isiRv(String userId) {
        apiInterface.getCartDetail(userId).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                try {
                    JSONObject jsonResult = new JSONObject(response.body().string());
                    String namarm = jsonResult.getJSONObject("result").getString("namarm");
                    String alamatrm = jsonResult.getJSONObject("result").getString("alamatrm");
                    tvCartKedai.setText(namarm);
                    tvCartLokasiKedai.setText(alamatrm);
                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                // tvCartKedai.setText("11120170010asw");
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
