package id.trafood.trafood;

import android.app.ProgressDialog;
import android.content.Context;
import android.nfc.Tag;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;
import java.util.Random;

import id.trafood.trafood.Models.Article;
import id.trafood.trafood.Models.Courier;
import id.trafood.trafood.Models.GetCourier;
import id.trafood.trafood.OrderP.CourierAdapter;
import id.trafood.trafood.Rest.ApiClient;
import id.trafood.trafood.Rest.ApiInterface;
import id.trafood.trafood.Rest.RestApi;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CourierActivity extends AppCompatActivity {

    ApiInterface apiInterface;
    RestApi restApi;
    Context mContext;
    LinearLayout linearTidakAda,linearAda;
    TextView pengumuman;
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;
    String userLat,userLng,kLat,kLng,namarm,kodetrans,telp,namauser,real_distance,google_distance,
            address,userid,kodeAlamar,total,rmid,custom;
    SharedPrefManager sharedPrefManager;
    ProgressDialog loading;

    public static  CourierActivity ca;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courier);
        getSupportActionBar().setTitle("Kurir");
        getSupportActionBar().setElevation(0);
        sharedPrefManager = new SharedPrefManager(this);
        recyclerView = (RecyclerView) findViewById(R.id.rvKurir);
        linearAda = (LinearLayout) findViewById(R.id.LnAdaKurir);
        linearTidakAda = (LinearLayout) findViewById(R.id.LnTidakAdaKurir);
        pengumuman = (TextView) findViewById(R.id.tvPengumumanKurir);

        linearTidakAda.setVisibility(View.GONE);
        linearAda.setVisibility(View.GONE);

        userLat = getIntent().getStringExtra("LATU");
        userLng = getIntent().getStringExtra("LNGU");
        kLat = getIntent().getStringExtra("LATK");
        kLng = getIntent().getStringExtra("LNGK");
        namarm = getIntent().getStringExtra("NAMARM");
       // kodetrans = getIntent().getStringExtra("KODE");
        telp = getIntent().getStringExtra("TELEPON");
        real_distance = getIntent().getStringExtra("RDISTANCE");
        google_distance = getIntent().getStringExtra("GDISTANCE");
        address = getIntent().getStringExtra("ADDRESS");
        total = getIntent().getStringExtra("TOTAL");
        rmid = getIntent().getStringExtra("RMID");
        custom = getIntent().getStringExtra("CUSTOM");
        userid = sharedPrefManager.getSpUserid();
        namauser = sharedPrefManager.getSPNama();

        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        restApi = ApiClient.getClient().create(RestApi.class);
        layoutManager = new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(layoutManager);
        mContext = this;
        ca = this;
        loading = ProgressDialog.show(mContext, null, "Please wait.... ", true, false);
        thissa();
    }

    @Override
    protected void onResume() {
        super.onResume();
        thissa();
    }

    public void thissa() {

        bawanomer();
        cekKurir();



    }

    private void bawanomer() {
        apiInterface.getNomorOrder().enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    JSONObject jsonResult = new JSONObject(response.body().string());
                    kodetrans = jsonResult.getString("result");

                    Log.d("TAG",kodetrans+" trans ");
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
        restApi.getNomor().enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    JSONObject jsonResult = new JSONObject(response.body().string());
                    kodeAlamar = jsonResult.getString("result");

                    Log.d("TAG",kodeAlamar);
                }catch (JSONException e){
                    e.printStackTrace();
                } catch (IOException e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }

    private void cekKurir() {
        Call<GetCourier> getCourierCall = apiInterface.getKurir(kLat,kLng,userLat,userLng);
        getCourierCall.enqueue(new Callback<GetCourier>() {
            @Override
            public void onResponse(Call<GetCourier> call, Response<GetCourier> response) {
              if (response.body().getStatus().equals("201")){
                    linearTidakAda.setVisibility(View.VISIBLE);
                    pengumuman.setText("Kedai berada diluar jangkuan kurir");
                    loading.dismiss();
               }if (response.body().getStatus().equals("200")){
                    masukanorder();
                    masukanalamat();
                    linearAda.setVisibility(View.VISIBLE);
                    List<Courier> couriers = response.body().getListDataCourier();
                    Log.d("TAG",String.valueOf(couriers.size()));
                    adapter = new CourierAdapter(couriers);
                    recyclerView.setAdapter(adapter);
                    loading.dismiss();
                }
                
            }

            @Override
            public void onFailure(Call<GetCourier> call, Throwable t) {
                linearTidakAda.setVisibility(View.VISIBLE);
                pengumuman.setText("Gagal mendapatkan Kurir");
                loading.dismiss();
                Log.d("TAG","gagal");
            }
        });
    }

    private void masukanorder() {
        restApi.postOrder(kodetrans,kodeAlamar,total,google_distance,real_distance,userid,rmid).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Log.d("TAG","input order berhasil"+"berhasil kode transaksi"+kodetrans);
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
            }
        });


    }



    private void masukanalamat() {
        restApi.inputAddress(userid,kodeAlamar,namauser,address,custom,telp,userLat,userLng).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Log.d("TAG","alama"+"berhasil kode alamat"+kodeAlamar+custom);
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }
}
