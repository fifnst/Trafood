package id.trafood.trafood;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.List;

import id.trafood.trafood.Home.GetModelMenu;
import id.trafood.trafood.Home.ModelMenu;
import id.trafood.trafood.Home.SearchMenuAdapater;
import id.trafood.trafood.Models.GetMenu;
import id.trafood.trafood.Rest.ApiClient;
import id.trafood.trafood.Rest.ApiInterface;
import id.trafood.trafood.Rest.RestApi;
import id.trafood.trafood.Rest.UtilsApi;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RingkasanKedaiActivity extends AppCompatActivity  {

    Context mContext;
    ApiInterface apiInterface;
    RestApi restApi;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    public static RingkasanKedaiActivity rka;


    SharedPrefManager sharedPrefManager;
    TextView tvAlamat, tvKategori, tvDeskripsi, tvNamaRM, tvKecamatan, tvKota,tvRating,tvUlasan, tvOrangRating, tvVisit;
    TextView tvSunday, tvBukaSunday, tvTutupSunday;
    TextView tvMonday, tvBukaMonday, tvTutupMonday;
    TextView tvTuesday, tvBukaTuesday, tvTutupTuesday;
    TextView tvWednesday, tvBukaWednesday, tvTutupWednesday;
    TextView tvThrusday, tvBukaThrusday, tvTutupThrusday;
    TextView tvFriday, tvBukaFriday, tvTutupFriday;
    TextView tvSaturday, tvBukaSaturday, tvTutupSaturday;
    ImageView ivFasilitas, ivFSatu, ivFDua, ivFtiga, ivFEmpat, ivFLima;
    TextView tvFasilitas, tvFSatu, tvFDua, tvFTiga, tvFEmpat, tvFlima;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ringkasan_kedai);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Ringkasan Kedai");

        tvRating = (TextView) findViewById(R.id.tvRatingKedai);
        tvUlasan = (TextView) findViewById(R.id.tvUlasanKedai);
        tvOrangRating =(TextView) findViewById(R.id.tvOrangRatingKedai);
        tvVisit =    (TextView) findViewById(R.id.tvVisitKedai);
        tvDeskripsi = (TextView) findViewById(R.id.tvDeskripsiKedai);
        tvAlamat = (TextView) findViewById(R.id.tvAlamatKedai);
        tvKategori = (TextView) findViewById(R.id.tvKategoriKedai);
        tvNamaRM = (TextView) findViewById(R.id.tvNamaKedai);
        tvKecamatan = (TextView) findViewById(R.id.tvKecamatanKedai);
        tvKota = (TextView) findViewById(R.id.tvKotaKedai);

        tvSunday = (TextView) findViewById(R.id.tvSundayKedai);
        tvBukaSunday = (TextView) findViewById(R.id.tvBukaSundayKedai);
        tvTutupSunday = (TextView) findViewById(R.id.tvTutupSundayKedai);

        tvMonday = (TextView) findViewById(R.id.tvMondayKedai);
        tvBukaMonday = (TextView) findViewById(R.id.tvBukaMondayKedai);
        tvTutupMonday = (TextView) findViewById(R.id.tvTutupMondayKedai);

        tvTuesday = (TextView) findViewById(R.id.tvSelasaKedai);
        tvBukaTuesday = (TextView) findViewById(R.id.tvBukaTuesdayKedai);
        tvTutupTuesday = (TextView) findViewById(R.id.tvTutupTuesdayKedai);

        tvWednesday = (TextView) findViewById(R.id.tvWednesdayKedai);
        tvBukaWednesday = (TextView) findViewById(R.id.tvBukaWednesdayKedai);
        tvTutupWednesday = (TextView) findViewById(R.id.tvTutupWednesdayKedai);

        tvThrusday = (TextView) findViewById(R.id.tvThursdayKedai);
        tvBukaThrusday = (TextView) findViewById(R.id.tvBukaThursdayKedai);
        tvTutupThrusday = (TextView) findViewById(R.id.tvTutupThursdayKedai);

        tvFriday = (TextView) findViewById(R.id.tvFridayKedai);
        tvBukaFriday = (TextView) findViewById(R.id.tvBukaFridayKedai);
        tvTutupFriday = (TextView) findViewById(R.id.tvTutupFridayKedai);

        tvSaturday = (TextView) findViewById(R.id.tvSaturdayKedai);
        tvBukaSaturday = (TextView) findViewById(R.id.tvBukaSaturdayKedai);
        tvTutupSaturday = (TextView) findViewById(R.id.tvTutupSaturdayKedai);

        tvFasilitas = (TextView) findViewById(R.id.tvFasilitasKedai);
        tvFSatu = (TextView) findViewById(R.id.tvFSatuKedai);
        tvFDua = (TextView) findViewById(R.id.tvFDuaKedai);
        tvFTiga = (TextView) findViewById(R.id.tvFTigaKedai);
        tvFEmpat = (TextView) findViewById(R.id.tvFEmpatKedai);
        tvFlima = (TextView) findViewById(R.id.tvFLimaKedai);

        ivFSatu =(ImageView) findViewById(R.id.ivFSatuKedai);
        ivFasilitas =(ImageView) findViewById(R.id.ivFasilitasKedai);
        ivFDua = (ImageView) findViewById(R.id.ivFDuaKedai);
        ivFtiga =(ImageView) findViewById(R.id.ivFTigaKedai);
        ivFEmpat =(ImageView) findViewById(R.id.ivFEmpatKedai);
        ivFLima =(ImageView) findViewById(R.id.ivFLimaKedai);

        recyclerView = (RecyclerView) findViewById(R.id.rvMenuKedai);


        ivFSatu.setVisibility(View.GONE);
        ivFasilitas.setVisibility(View.GONE);
        ivFDua.setVisibility(View.GONE);
        ivFtiga.setVisibility(View.GONE);
        ivFEmpat.setVisibility(View.GONE);
        ivFLima.setVisibility(View.GONE);

        tvSunday.setVisibility(View.GONE);
        tvBukaSunday.setVisibility(View.GONE);
        tvTutupSunday.setVisibility(View.GONE);

        tvMonday.setVisibility(View.GONE);
        tvBukaMonday.setVisibility(View.GONE);
        tvTutupMonday.setVisibility(View.GONE);

        tvTuesday.setVisibility(View.GONE);
        tvBukaTuesday.setVisibility(View.GONE);
        tvTutupTuesday.setVisibility(View.GONE);

        tvWednesday.setVisibility(View.GONE);
        tvBukaWednesday.setVisibility(View.GONE);
        tvTutupWednesday.setVisibility(View.GONE);

        tvThrusday.setVisibility(View.GONE);
        tvBukaThrusday.setVisibility(View.GONE);
        tvTutupThrusday.setVisibility(View.GONE);

        tvFriday.setVisibility(View.GONE);
        tvBukaFriday.setVisibility(View.GONE);
        tvTutupFriday.setVisibility(View.GONE);

        tvSaturday.setVisibility(View.GONE);
        tvBukaSaturday.setVisibility(View.GONE);
        tvTutupSaturday.setVisibility(View.GONE);

        mContext = this;
        apiInterface = UtilsApi.getApiServive();
        sharedPrefManager = new SharedPrefManager(this);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        restApi = ApiClient.getClient().create(RestApi.class);
        rka =this;

        if (!sharedPrefManager.getSPSudahLogin()){
            startActivity(new Intent(RingkasanKedaiActivity.this,LoginActivity.class)
                    .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
            finish();

        }
        Panggilmenu();
        SetIsi();


    }


    private void SetIsi() {
        String rmid = getIntent().getStringExtra("RMID");
        String userid = getIntent().getStringExtra("USERID");
        apiInterface.ringkasanRm(rmid).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    JSONObject jsonObject = new JSONObject(response.body().string());
                    String namarm = jsonObject.getJSONObject("result").getString("namarm");
                    String kategori = jsonObject.getJSONObject("result").getString("kategorirm");
                    String alamat = jsonObject.getJSONObject("result").getString("alamat");
                    String latitude = jsonObject.getJSONObject("result").getString("latitude");
                    String longitude = jsonObject.getJSONObject("result").getString("longitude");
                    String buka = jsonObject.getJSONObject("result").getString("buka");
                    String tutup = jsonObject.getJSONObject("result").getString("tutup");
                    String fotoSampul = jsonObject.getJSONObject("result").getString("fotosampul");
                    String deskripsi = jsonObject.getJSONObject("result").getString("deskripsirm");
                    String sunday = jsonObject.getJSONObject("result").getString("sunday");
                    String monday = jsonObject.getJSONObject("result").getString("monday");
                    String tuesday = jsonObject.getJSONObject("result").getString("tuesday");
                    String wednesday =jsonObject.getJSONObject("result").getString("wednesday");
                    String thursday = jsonObject.getJSONObject("result").getString("thursday");
                    String friday = jsonObject.getJSONObject("result").getString("friday");
                    String saturday = jsonObject.getJSONObject("result").getString("saturday");
                    String fasilitas = jsonObject.getJSONObject("result").getString("fasilitas");
                    String fSatu = jsonObject.getJSONObject("result").getString("fsatu");
                    String fDua = jsonObject.getJSONObject("result").getString("fdua");
                    String fTiga = jsonObject.getJSONObject("result").getString("ftiga");
                    String fEmpat = jsonObject.getJSONObject("result").getString("fempat");
                    String fLima = jsonObject.getJSONObject("result").getString("flima");
                    String kecamatan = jsonObject.getJSONObject("result").getString("kecamatan");
                    String kota = jsonObject.getJSONObject("result").getString("kota");
                    String review = jsonObject.getJSONObject("result").getString("review");
                    String visit = jsonObject.getJSONObject("result").getString("visit");
                    String rating1 = jsonObject.getJSONObject("result").getString("visit");
                    String rating2 =  jsonObject.getJSONObject("result").getString("rating2");
                    String rating3 = jsonObject.getJSONObject("result").getString("rating3");
                    String rating4 = jsonObject.getJSONObject("result").getString("rating4");

                    DecimalFormat df = new DecimalFormat("#.#");
                    tvNamaRM.setText("Kedai "+ namarm);
                    tvDeskripsi.setText(deskripsi);
                    tvAlamat.setText(alamat);
                    tvKategori.setText("Kategori kedai :" + kategori);
                    tvKecamatan.setText(kecamatan);
                    tvKota.setText(kota);
                    tvTutupSunday.setText(sunday);
                    tvVisit.setText(visit);
                    tvOrangRating.setText(visit + "orang");
                   // tvUlasan.setText(df.format(review));

                    Double rating1d = Double.parseDouble(rating1);
                    Double rating2d = Double.parseDouble(rating2);
                    Double rating3d = Double.parseDouble(rating3);
                    Double rating4d = Double.parseDouble(rating4);
                    Double total = rating1d+rating2d+rating3d+rating4d;

                    tvRating.setText(Double.toString(total));

                    if (sunday.equals("1")){
                        tvSunday.setText(R.string.sunday);
                        tvBukaSunday.setText(" : " + buka);
                        tvTutupSunday.setText(" - " + tutup);
                        tvSunday.setVisibility(View.VISIBLE);
                        tvBukaSunday.setVisibility(View.VISIBLE);
                        tvTutupSunday.setVisibility(View.VISIBLE);
                    }
                    if (monday.equals("1")){
                        tvMonday.setText(R.string.monday);
                        tvBukaMonday.setText(" : " + buka);
                        tvTutupMonday.setText(" - " + tutup);
                        tvMonday.setVisibility(View.VISIBLE);
                        tvBukaMonday.setVisibility(View.VISIBLE);
                        tvTutupMonday.setVisibility(View.VISIBLE);
                    }
                    if (tuesday.equals("1")){
                        tvTuesday.setText(R.string.teusday);
                        tvBukaTuesday.setText(" : " + buka);
                        tvTutupTuesday.setText(" - " + tutup);
                        tvTuesday.setVisibility(View.VISIBLE);
                        tvBukaTuesday.setVisibility(View.VISIBLE);
                        tvTutupTuesday.setVisibility(View.VISIBLE);
                    }
                    if (wednesday.equals("1")){
                        tvWednesday.setText(R.string.wednesday);
                        tvBukaWednesday.setText(" : " + buka);
                        tvTutupWednesday.setText(" - " + tutup);
                        tvWednesday.setVisibility(View.VISIBLE);
                        tvBukaWednesday.setVisibility(View.VISIBLE);
                        tvTutupWednesday.setVisibility(View.VISIBLE);
                    }
                    if (thursday.equals("1")){
                        tvThrusday.setText(R.string.thursday);
                        tvBukaThrusday.setText(" : " + buka);
                        tvTutupThrusday.setText(" - " + tutup);
                        tvThrusday.setVisibility(View.VISIBLE);
                        tvBukaThrusday.setVisibility(View.VISIBLE);
                        tvTutupThrusday.setVisibility(View.VISIBLE);

                    }
                    if (friday.equals("1")){
                        tvFriday.setText(R.string.friday);
                        tvBukaFriday.setText(" : " + buka);
                        tvTutupFriday.setText(" - " + tutup);
                        tvFriday.setVisibility(View.VISIBLE);
                        tvBukaFriday.setVisibility(View.VISIBLE);
                        tvTutupFriday.setVisibility(View.VISIBLE);

                    }
                    if (saturday.equals("1")){
                        tvSaturday.setText(R.string.saturday);
                        tvBukaSaturday.setText(" : " + buka);
                        tvTutupSaturday.setText(" - " + tutup);
                        tvSaturday.setVisibility(View.VISIBLE);
                        tvBukaSaturday.setVisibility(View.VISIBLE);
                        tvTutupSaturday.setVisibility(View.VISIBLE);
                    }


                    if (fasilitas.equals("1")){
                        ivFasilitas.setVisibility(View.VISIBLE);
                        tvFasilitas.setVisibility(View.VISIBLE);
                        tvFasilitas.setText(R.string.wifi);
                    }
                    if (fSatu.equals("1") ){
                        ivFSatu.setVisibility(View.VISIBLE);
                        tvFSatu.setVisibility(View.VISIBLE);
                        tvFSatu.setText(R.string.parking);
                    }
                    if (fDua.equals("1") ){
                        ivFDua.setVisibility(View.VISIBLE);
                        tvFDua.setVisibility(View.VISIBLE);
                        tvFDua.setText(R.string.music);
                    }
                    if (fTiga.equals("1")){
                        ivFtiga.setVisibility(View.VISIBLE);
                        tvFTiga.setVisibility(View.VISIBLE);
                        tvFTiga.setText(R.string.mosque);
                    }
                    if (fEmpat.equals("1")){
                        ivFEmpat.setVisibility(View.VISIBLE);
                        tvFEmpat.setVisibility(View.VISIBLE);
                        tvFEmpat.setText(R.string.toilet);
                    }
                    if (fLima.equals("1")){
                        ivFLima.setVisibility(View.VISIBLE);
                        tvFlima.setVisibility(View.VISIBLE);
                        tvFlima.setText(R.string.smoking);
                    }


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

    private void Panggilmenu() {
        String rmid = getIntent().getStringExtra("RMID");
        Call<GetModelMenu>  getMenuCall = restApi.getMenudiRm(rmid);
        getMenuCall.enqueue(new Callback<GetModelMenu>() {
            @Override
            public void onResponse(Call<GetModelMenu> call, Response<GetModelMenu> response) {
                List<ModelMenu> modelMenuList = response.body().getLisModelmenu();
                adapter = new SearchMenuAdapater(modelMenuList);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<GetModelMenu> call, Throwable t) {

            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }


}
