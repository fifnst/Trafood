package id.trafood.trafood;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.List;

import id.trafood.trafood.Models.GetOrder;
import id.trafood.trafood.Models.Order;
import id.trafood.trafood.OrderP.OrderDetailAdapter;
import id.trafood.trafood.Rest.ApiClient;
import id.trafood.trafood.Rest.ApiInterface;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailHistoryTransaksi extends Activity {

    TextView tvNamaPemesan, tvTeleponPemesan, tvAlamatpemesan,tvNamaKedai,tvAlamatKedai,tvNamaKurir,tvSubtotal,
            tvOngkir,tvParkir, tvTotal,tvJarak,tvCustom,tvNotransaksi, tvTgl;
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;
    ApiInterface apiInterface;
    SharedPrefManager sharedPrefManager;
    String namaPemesan, teleponPemesan, alamatpemesan,namaKedai,alamatKedai,namaKurir,subtotal,gdistance,status,
            ongkir,parkir, totall,kuririd,userid,transid,distance,price,per,unit,priceplus,perplus,tglTransaksi,custom;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_history_transaksi);
        transid = getIntent().getStringExtra("TRANSDID");
        //transid ="1712191513668974";

        setView();

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        apiInterface = ApiClient.getClient().create(ApiInterface.class);

        apiInterface.getHistoryDetail(transid).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    JSONObject jsonResult = new JSONObject(response.body().string());
                    // transid = jsonResult.getJSONObject("result").getString("trans_id");
                    tglTransaksi = jsonResult.getJSONObject("result").getString("datecreate");
                    totall = jsonResult.getJSONObject("result").getString("totalprice");
                    ongkir = jsonResult.getJSONObject("result").getString("shippingcharge");
                    gdistance = jsonResult.getJSONObject("result").getString("gdistance");
                    status = jsonResult.getJSONObject("result").getString("status");
                    namaPemesan = jsonResult.getJSONObject("result").getString("recipient_name");
                    teleponPemesan = jsonResult.getJSONObject("result").getString("telp");
                    alamatpemesan = jsonResult.getJSONObject("result").getString("addressuser");
                    custom = jsonResult.getJSONObject("result").getString("customaddress");
                    namaKedai = jsonResult.getJSONObject("result").getString("namarm");
                    alamatKedai = jsonResult.getJSONObject("result").getString("alamatrm");
                    namaKurir = jsonResult.getJSONObject("result").getString("namakurir");
                    //subtotal = jsonResult.getJSONObject("result").getString("totalprice");
                    Log.d("TAG",transid+namaPemesan+teleponPemesan+alamatpemesan+namaKedai+alamatKedai+gdistance+namaKurir+"transaksi");
                    DecimalFormat kursIndonesia = (DecimalFormat) DecimalFormat.getCurrencyInstance();
                    DecimalFormatSymbols formatRp = new DecimalFormatSymbols();
                    formatRp.setCurrencySymbol("Rp. ");
                    formatRp.setMonetaryDecimalSeparator(',');
                    formatRp.setGroupingSeparator('.');
                    kursIndonesia.setDecimalFormatSymbols(formatRp);

                    tvNamaKurir.setText(namaKurir);
                    tvNamaPemesan.setText(namaPemesan);
                    tvTeleponPemesan.setText(teleponPemesan);
                    tvAlamatpemesan.setText(alamatpemesan);
                    tvNamaKedai.setText(namaKedai);
                    tvAlamatKedai.setText(alamatKedai);
                    tvJarak.setText(gdistance);
                    tvCustom.setText(custom);
                    tvNotransaksi.setText("No. "+transid);
                    tvTgl.setText(tglTransaksi);

                    Double ongkirs = Double.parseDouble(ongkir);
                    Double totals = Double.parseDouble(totall);

                    tvOngkir.setText(kursIndonesia.format(ongkirs+0));
                    tvSubtotal.setText(kursIndonesia.format(totals+0));
                    tvTotal.setText(kursIndonesia.format(totals+ongkirs));

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

        Call <GetOrder> getOrderCall = apiInterface.getHistoryItem(transid);
        getOrderCall.enqueue(new Callback<GetOrder>() {
            @Override
            public void onResponse(Call<GetOrder> call, Response<GetOrder> response) {
                List<Order> orders = response.body().getListDataOrder();
//                Log.d("TAG","jumlah nya adalah"+String.valueOf(orders.size()));
                adapter = new OrderDetailAdapter(orders);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<GetOrder> call, Throwable t) {

            }
        });


    }

    private void setView() {
        tvNamaPemesan = (TextView) findViewById(R.id.tvNamaPememasanHistory);
        tvTeleponPemesan = (TextView) findViewById(R.id.tvTelpPemesanHistory);
        tvAlamatpemesan = (TextView) findViewById(R.id.tvAlamatPemesanHistory);
        tvNamaKedai = (TextView) findViewById(R.id.tvNameKedaiHistory);
        tvAlamatKedai = (TextView) findViewById(R.id.tvAlamatKedaiHistory);
        tvNamaKurir = (TextView) findViewById(R.id.tvKurirHistory);
        tvOngkir = (TextView) findViewById(R.id.tvBiayaKirimHistory);
        tvSubtotal = (TextView) findViewById(R.id.tvSubtotalHistory);
        tvTotal = (TextView) findViewById(R.id.tvTotalHistory);
        tvJarak = (TextView) findViewById(R.id.tvJarakKirimHistory);
        tvCustom = (TextView) findViewById(R.id.tvAlamatCustomHistory);
        recyclerView = (RecyclerView) findViewById(R.id.rvHistorys);
        tvTgl  = (TextView) findViewById(R.id.tvTanggalHistory);
        tvNotransaksi  = (TextView) findViewById(R.id.tvTransaksiHistory);

    }
}
