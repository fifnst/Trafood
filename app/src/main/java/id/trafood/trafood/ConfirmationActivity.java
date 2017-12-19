package id.trafood.trafood;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.List;

import id.trafood.trafood.Models.GetCourier;
import id.trafood.trafood.Models.GetOrder;
import id.trafood.trafood.Models.Order;
import id.trafood.trafood.OrderP.OrderDetailAdapter;
import id.trafood.trafood.Rest.ApiClient;
import id.trafood.trafood.Rest.ApiInterface;
import id.trafood.trafood.Rest.RestApi;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ConfirmationActivity extends AppCompatActivity {

    TextView tvNamaPemesan, tvTeleponPemesan, tvAlamatpemesan,tvNamaKedai,tvAlamatKedai,tvNamaKurir,tvSubtotal,
                tvOngkir,tvParkir, tvTotal,tvJarak;
    Button btnConfirmation;
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;
    ApiInterface apiInterface;
    RestApi restApi;
    Context mContext;
    public static ConfirmationActivity ca;
    SharedPrefManager sharedPrefManager;
    ProgressDialog loading;
    String namaPemesan, teleponPemesan, alamatpemesan,namaKedai,alamatKedai,namaKurir,subtotal,gdistance,
            ongkir,parkir, totall,kuririd,userid,transid,distance,price,per,unit,priceplus,perplus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmation);
        getSupportActionBar().setTitle("Confrimation");
        getSupportActionBar().setElevation(0);
        findView();
        kuririd = getIntent().getStringExtra("COURIERID");
        //kuririd ="12548514402178";
        sharedPrefManager = new SharedPrefManager(this);
        userid = sharedPrefManager.getSpUserid();

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        restApi = ApiClient.getClient().create(RestApi.class);
        mContext = this;
        ca = this;
        loading = ProgressDialog.show(mContext,null,"Please Wait...", true,false);


        getKurir(kuririd); //get kurir dan set harganya
        getTrans(); //get order
        Log.d("TAG",userid+"ini pas diluar"+kuririd);

        btnConfirmation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                simpanorderDetail(); //untuk menyimpan order detail barang apa saja yang di order
                simpan();
            }
        });
    }

    private void simpanorderDetail() {
        restApi.postOrderDetail(userid,transid).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                loading.dismiss();
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }

    private void simpan() {
        restApi.putOrder(kuririd,unit,"1",transid,totall,subtotal).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Intent intent = new Intent(mContext,ThanksActivity.class);
                mContext.startActivity(intent);
                Log.d("TAG","SUKSES");
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }

    private void getKurir(String kuririd) {
        this.kuririd = kuririd;
        apiInterface.getHargaKurir("12548514402178").enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    JSONObject jsonResult = new JSONObject(response.body().string());
                    namaKurir = jsonResult.getJSONObject("result").getString("c_name");
                    price = jsonResult.getJSONObject("result").getString("price");
                    per = jsonResult.getJSONObject("result").getString("per");
                    unit = jsonResult.getJSONObject("result").getString("unit");
                    priceplus = jsonResult.getJSONObject("result").getString("priceplus");
                    perplus = jsonResult.getJSONObject("result").getString("perplus");
                    Log.d("TAG",namaKurir+price+per+unit + "kurir");

                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.d("TAG", "gagal  dapat kurir");
            }
        });

    }

    private void getTrans() {

        apiInterface.getOrder(userid).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    JSONObject jsonResult = new JSONObject(response.body().string());
                    transid = jsonResult.getJSONObject("result").getString("trans_id");
                    namaPemesan = jsonResult.getJSONObject("result").getString("recipient_name");
                    teleponPemesan = jsonResult.getJSONObject("result").getString("telp");
                    alamatpemesan = jsonResult.getJSONObject("result").getString("address");
                    namaKedai = jsonResult.getJSONObject("result").getString("namarm");
                    alamatKedai = jsonResult.getJSONObject("result").getString("alamatrm");
                    //subtotal = jsonResult.getJSONObject("result").getString("totalprice");
                    distance = jsonResult.getJSONObject("result").getString("distance");
                    gdistance = jsonResult.getJSONObject("result").getString("gdistance");
                    Log.d("TAG",transid+namaPemesan+teleponPemesan+alamatpemesan+namaKedai+alamatKedai+distance+gdistance+"transaksi");
                    setDetailOrder();//set detail order


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

        apiInterface.getCartDetail(userid).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                try {
                    JSONObject jsonResult = new JSONObject(response.body().string());
                    /*namarm = jsonResult.getJSONObject("result").getString("namarm");
                    alamatrm = jsonResult.getJSONObject("result").getString("alamatrm");
                    latitude = jsonResult.getJSONObject("result").getString("latitude");
                    longitude = jsonResult.getJSONObject("result").getString("longitude");
                    rmid = jsonResult.getJSONObject("result").getString("rmid");*/
                    subtotal = jsonResult.getString("total");
                    Log.d("TAG","subtotal"+subtotal);
                    setTex();
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

    private void setTex() {
        tvNamaKurir.setText(namaKurir);
        tvNamaPemesan.setText(namaPemesan);
        tvTeleponPemesan.setText(teleponPemesan);
        tvAlamatpemesan.setText(alamatpemesan);
        tvNamaKedai.setText(namaKedai);
        tvAlamatKedai.setText(alamatKedai);
        tvJarak.setText(gdistance);
       // tvSubtotal.setText("Rp. "+subtotal);
        hitungOngkir(); //method untuk hitung ongkir
    }

    private void hitungOngkir() {
        Double jarak = Double.parseDouble(distance);//*0.001
        Double harga = Double.parseDouble(price);
        Double perr = Double.parseDouble(per);
        Double hargapluss = Double.parseDouble(priceplus);
        Double perrplus = Double.parseDouble(perplus);
        //untuk menghitung yang satuan per km
        Double realdistance = Math.ceil(jarak/1000.0); //bulatkan angka nya dulu ke atas
        Double disper = realdistance/perr;
        Double ini = Math.ceil(disper/1.0); //dibulatkan lagi angkanya
        tvParkir.setText(" ");
        Double total = Double.parseDouble(subtotal);

        //bikin format rupiah
        DecimalFormat kursIndonesia = (DecimalFormat) DecimalFormat.getCurrencyInstance();
        DecimalFormatSymbols formatRp = new DecimalFormatSymbols();
        formatRp.setCurrencySymbol("Rp. ");
        formatRp.setMonetaryDecimalSeparator(',');
        formatRp.setGroupingSeparator('.');
        kursIndonesia.setDecimalFormatSymbols(formatRp);

        tvSubtotal.setText(kursIndonesia.format(total));
        if (unit.equals("km")){
            tvOngkir.setText(kursIndonesia.format(ini*harga));
            tvTotal.setText(kursIndonesia.format(total+(ini*harga)));
            totall = String.valueOf(harga*ini);
            loading.dismiss();
        }if (unit.equals("transaction")){
            tvOngkir.setText(kursIndonesia.format(harga*perr));
            tvTotal.setText(kursIndonesia.format((harga*perr)+total));
            totall = String.valueOf(harga*perr);
            loading.dismiss();
        }//untuk menghitung yang kilometer pertama dan keduanya berbeda
        if (unit.equals("kmplus")){
            if (realdistance < perr){
                tvOngkir.setText(kursIndonesia.format(harga*1));
                tvTotal.setText(kursIndonesia.format(harga+total));
                totall = String.valueOf(harga);
                loading.dismiss();
            }if (realdistance > perr){
                Double sisa = realdistance-perr;
                Double bagi = sisa/perrplus;
                Double hasill = Math.ceil(bagi/1.0);
                Double nyaan = hasill*hargapluss;
                tvOngkir.setText(kursIndonesia.format(harga+nyaan));
                tvTotal.setText(kursIndonesia.format((harga+nyaan)+total));
                Double tambah = harga+nyaan;
                totall = String.valueOf(harga+nyaan);
                loading.dismiss();
            }
        }


    }

    private void setDetailOrder() {
        Call<GetOrder> getOrderCall = apiInterface.getCart(userid);
        getOrderCall.enqueue(new Callback<GetOrder>() {
            @Override
            public void onResponse(Call<GetOrder> call, Response<GetOrder> response) {
                List<Order> orders = response.body().getListDataOrder();
                Log.d("TAG","jumlah nya adalah"+String.valueOf(orders.size()));
                adapter = new OrderDetailAdapter(orders);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<GetOrder> call, Throwable t) {

            }
        });
    }

    private void findView() {
        tvNamaPemesan = (TextView) findViewById(R.id.tvNamaPememasanConfirmation);
        tvTeleponPemesan = (TextView) findViewById(R.id.tvTelpPemesanConfirmation);
        tvAlamatpemesan = (TextView) findViewById(R.id.tvAlamatPemesanConfirmation);
        tvNamaKedai = (TextView) findViewById(R.id.tvNameKedaiConfirmation);
        tvAlamatKedai = (TextView) findViewById(R.id.tvAlamatKedaiConfirmation);
        tvNamaKurir = (TextView) findViewById(R.id.tvKurirConfirmation);
        tvOngkir = (TextView) findViewById(R.id.tvBiayaKirimConfirmation);
        tvParkir = (TextView) findViewById(R.id.tvBiayaParkirConfirmation);
        tvSubtotal = (TextView) findViewById(R.id.tvSubtotalConfirmation);
        tvTotal = (TextView) findViewById(R.id.tvTotalConfirmation);
        tvJarak = (TextView) findViewById(R.id.tvJarakKirim);
        btnConfirmation = (Button) findViewById(R.id.btnConf);
        recyclerView = (RecyclerView) findViewById(R.id.rvConfirmation);

    }
}
