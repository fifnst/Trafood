package id.trafood.trafood;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

import id.trafood.trafood.Home.GetModelMenu;
import id.trafood.trafood.Home.ModelMenu;
import id.trafood.trafood.Home.SuggestionMenuAdapater;
import id.trafood.trafood.Models.GetMenu;
import id.trafood.trafood.Models.PostPutDelMenu;
import id.trafood.trafood.Models.PostPutDelOrder;
import id.trafood.trafood.Rest.ApiClient;
import id.trafood.trafood.Rest.ApiInterface;
import id.trafood.trafood.Rest.Connect;
import id.trafood.trafood.Rest.RestApi;
import id.trafood.trafood.Rest.UtilsApi;
import id.trafood.trafood.Rumahmakan.Adapter.MenuListAdapter;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailMenu extends AppCompatActivity {
    ApiInterface apiInterface;
    Context dm;
    ProgressBar progressBar;
    public TextView tvNamamenu, tvDeskrispisMenu, tvLike, tvNamaRm, tvAlamat,tvKategoriRm, tvDilihat;
    String menuid,fotomenu,namamenu;
    TextView tvHarga, tvEditmenu;
    ImageView ivFoto,ivLike;
    SharedPrefManager sharedPrefManager;
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;
    Button btnPesan;
    RestApi restApi;

    //PERCOBAAN FAHRI
    private String[] id;
    private TextView[] textViewsTesTag = new TextView[6];



    //String[] kata1;
    //ListView a;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_menu);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarMenu);
        tvHarga = (TextView) findViewById(R.id.tvHargaMenuDetail);
        ivFoto =(ImageView) findViewById(R.id.ivFotoMenuDetail);
        tvEditmenu = (TextView) findViewById(R.id.tvEditMenu);
        tvNamamenu = (TextView) findViewById(R.id.tvNamaMenuDetail);
        tvDeskrispisMenu = (TextView) findViewById(R.id.tvDeskripsiMenu);
        tvLike = (TextView) findViewById(R.id.tvLikesDetail);
        tvNamaRm = (TextView) findViewById(R.id.tvNamaRmMenuDetail);
        tvAlamat = (TextView) findViewById(R.id.tvAlamatMenuDetail);
        tvKategoriRm = (TextView) findViewById(R.id.tvKategoriRmMenuDetail);
        //tvTagMenu = (TextView) findViewById(R.id.tvTagMenu);
        tvDilihat = (TextView) findViewById(R.id.tvdilihatDetail);
        ivLike = (ImageView) findViewById(R.id.ivLike);
        recyclerView = (RecyclerView) findViewById(R.id.rvMenuHorizontal);
        btnPesan = (Button) findViewById(R.id.btnPesan);

        //pERCOBAAN FAHRI
        int temp;
        id = new String[]{"tvTagMenu1", "tvTagMenu2", "tvTagMenu3", "tvTagMenu4", "tvTagMenu5", "tvTagMenu6"};

        for(int i=0; i<id.length; i++){
            temp = getResources().getIdentifier(id[i], "id", getPackageName());
            textViewsTesTag[i] = (TextView)findViewById(temp);
            textViewsTesTag[i].setVisibility(View.GONE);
            //textViewsTesTag[i].setText("tes");
        }

        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);


        //ArrayAdapter adapter = new ArrayAdapter(this,R.layout.activity_detail_menu,kata);
        //ArrayAdapter adapter = new ArrayAdapter(this,R.layout.activity_detail_menu,kata1);
        //ListView listview =(ListView) findViewById(R.id.listView);
        //listview.setAdapter(adapter);

        Intent mIntent = getIntent();
        namamenu = mIntent.getStringExtra("NAMAMENU");
        menuid = mIntent.getStringExtra("MENUID");
        fotomenu = mIntent.getStringExtra("FOTOMENU");
        sharedPrefManager = new SharedPrefManager(this);
        final String useridMenu = getIntent().getStringExtra("USERID");
        final String useridUser = sharedPrefManager.getSpUserid();

        Picasso.with(this).load(Connect.IMAGE_MENU_URL+fotomenu).error(R.mipmap.ic_launcher).into(ivFoto);

        toolbar.setTitle(namamenu);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        progressBar = (ProgressBar) findViewById(R.id.pbDetailMenu);

        dm = this;
        apiInterface = UtilsApi.getApiServive();
        restApi = ApiClient.getClient().create(RestApi.class);
        setisi(menuid);

        tvEditmenu.setVisibility(View.GONE);


        if (sharedPrefManager.getSPSudahLogin()){
            cekPernahLike(menuid, useridUser); // cek pernah like atau belum
            ivLike.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    saveLike(menuid,useridUser);
                }
            });
            if (useridUser.equals(useridMenu)){ // cek apakah useriduser dan useridmenu sama, jika sama maka bisa edit menu
                tvEditmenu.setVisibility(View.VISIBLE);
                tvEditmenu.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(DetailMenu.this, EditMenuActivity.class);
                        intent.putExtra("MENUID",menuid);
                        intent.putExtra("USERID",useridMenu);
                        DetailMenu.this.startActivity(intent);
                    }
                });
            }
        }else {
            final Drawable likebelumlike = getResources().getDrawable(R.drawable.before_recommended);
            ivLike.setImageDrawable(likebelumlike);
            ivLike.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(dm, "Anda harus login dulu untuk merekomendasikan menu ini", Toast.LENGTH_LONG).show();
                }
            });
        }


    }



    private void saveLike(String menuid, String useridUser) {
        final Drawable likebelumlike = getResources().getDrawable(R.drawable.before_recommended);
        final Drawable sudahpernahlike = getResources().getDrawable(R.drawable.ic_after_recomended);
        apiInterface.saveLike(menuid,useridUser).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try{
                    JSONObject jsonResult = new JSONObject(response.body().string());
                    String like = jsonResult.getString("result");
                    if (jsonResult.getString("status").equals("200")){
                        ivLike.setImageDrawable(sudahpernahlike);
                        tvLike.setText(like);
                        Toast.makeText(dm, "Menu ini telah anda rekomendasikan, dan ditambahkan ke list menu favorit", Toast.LENGTH_LONG).show();
                    }if (jsonResult.getString("status").equals("204")){
                        ivLike.setImageDrawable(likebelumlike);
                        tvLike.setText(like);
                        Toast.makeText(dm, "Menu ini telah dihapus dari daftar favorit anda", Toast.LENGTH_LONG).show();
                    }
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





    private void cekPernahLike(String menuid, String useridUser) {
        final Drawable likebelumlike = getResources().getDrawable(R.drawable.before_recommended);
        final Drawable sudahpernahlike = getResources().getDrawable(R.drawable.ic_after_recomended);
        apiInterface.cekLike(menuid,useridUser).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    JSONObject jsonResult = new JSONObject(response.body().string());
                    if (jsonResult.getString("status").equals("204")){
                        ivLike.setImageDrawable(likebelumlike);
                    }else {
                        ivLike.setImageDrawable(sudahpernahlike);
                    }
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

    public void setisi(String isi) {
        menuid = isi;
        apiInterface.menuDetail(menuid).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    JSONObject jsonResult = new JSONObject(response.body().string());
                    String nama = jsonResult.getJSONObject("result").getString("namamenu");
                    String deskripsimenu = jsonResult.getJSONObject("result").getString("deskripsi");
                    final String hargamenu = jsonResult.getJSONObject("result").getString("harga");
                    String tag = jsonResult.getJSONObject("result").getString("tag");
                    String like = jsonResult.getJSONObject("result").getString("likes");
                    final String namarm = jsonResult.getJSONObject("result").getString("namarm");
                    final String alamat = jsonResult.getJSONObject("result").getString("alamat");
                    final String kategorirm = jsonResult.getJSONObject("result").getString("kategorirm");
                    String dilihat = jsonResult.getJSONObject("result").getString("dilihat");
                    final String rmid = jsonResult.getJSONObject("result").getString("rmid");
                    final String fotosampul = jsonResult.getJSONObject("result").getString("fotosampul");
                    final String lat = jsonResult.getJSONObject("result").getString("latitude");
                    final String lng = jsonResult.getJSONObject("result").getString("longitude");

                    String kalimat = tag;
                    String[] kata = kalimat.split(" ");

                    tvNamamenu.setText(nama);
                    tvDeskrispisMenu.setText(deskripsimenu);
                    tvLike.setText(like);
                    tvNamaRm.setText(namarm);
                    tvAlamat.setText(alamat);
                    tvKategoriRm.setText(kategorirm);
                    tvDilihat.setText("dilihat : "+dilihat);


                    for(int i=0; i<kata.length; i++){
                        textViewsTesTag[i].setText(kata [i]);
                        if(kata [i] != ""){
                            textViewsTesTag[i].setVisibility(View.VISIBLE);
                        }
                    }

                    setHorizontal(rmid);

                    tvHarga.setText("Rp. "+hargamenu);

                    tvNamaRm.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent mIntent = new Intent(view.getContext(), DetailRm.class);
                            mIntent.putExtra("RMID", rmid);
                            mIntent.putExtra("NAMARM", namarm);
                            mIntent.putExtra("KATEGORI", kategorirm);
                            mIntent.putExtra("ALAMAT", alamat);
                            mIntent.putExtra("FOTO", fotosampul);
                            mIntent.putExtra("LAT", lat);
                            mIntent.putExtra("LONG", lng);
                            view.getContext().startActivity(mIntent);
                        }
                    });

                    btnPesan.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            String useridUser = sharedPrefManager.getSpUserid();
                            pesan(useridUser,menuid,fotomenu,hargamenu,namamenu,namarm);
                        }
                    });
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

    private void setHorizontal(String rmid) {
        Call<GetModelMenu> menuCall = apiInterface.getMenuLimitRm(rmid);
        menuCall.enqueue(new Callback<GetModelMenu>() {
            @Override
            public void onResponse(Call<GetModelMenu> call, Response<GetModelMenu> response) {
                if (response.body().getStatus().equals("200")) {
                    progressBar.setVisibility(View.GONE);
                    recyclerView.setVisibility(View.VISIBLE);
                    List<ModelMenu> Menulist = response.body().getLisModelmenu();
                    adapter = new SuggestionMenuAdapater(Menulist);
                    recyclerView.setAdapter(adapter);
                }else {
                    progressBar.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<GetModelMenu> call, Throwable t) {
                //   Log.e("Retrofit get " , t.toString());
            }
        });
    }


    private void pesan(final String useridUser, final String menuid, final String fotomenu, final String hargamenu, final String namamenu, final String namarm) {
        if (sharedPrefManager.getSPSudahLogin()){
            this.menuid = menuid;
            final Dialog dialog = new Dialog(DetailMenu.this);
            dialog.setContentView(R.layout.dialog_pesan);
            dialog.setCancelable(false);
            dialog.setTitle("Rincian Pesanan");
            //mengeset untuk dialog
            TextView tvNamaRmD = (TextView) dialog.findViewById(R.id.tvNamaRmDialog);
            ImageView imageView = (ImageView) dialog.findViewById(R.id.imageDialog);
            TextView tvHargaD = (TextView) dialog.findViewById(R.id.tvHargaDialog);
            TextView tvNamamemuD = (TextView) dialog.findViewById(R.id.tvNamaMenuDialog);
            TextView tvLanjutkan = (TextView) dialog.findViewById(R.id.btnPilihDialog);
            Button btnNext = (Button) dialog.findViewById(R.id.btnNextDialog);
            ImageButton imageButton = (ImageButton) dialog.findViewById(R.id.deleteDialog);
            final TextView totalharga = (TextView) dialog.findViewById(R.id.tvTotalpriceDialog);
            final Spinner spinner = (Spinner) dialog.findViewById(R.id.spinnerDialog);

            tvHargaD.setText("Rp "+hargamenu);
            tvNamamemuD.setText(namamenu);
            tvNamaRmD.setText(namarm);
            totalharga.setText("Rp."+hargamenu);
            Picasso.with(DetailMenu.this).load(Connect.IMAGE_MENU_URL+fotomenu).into(imageView);



            imageButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.dismiss();
                }
            });

            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    int angka = Integer.parseInt(spinner.getSelectedItem().toString());
                    int harga = Integer.parseInt(hargamenu);
                    totalharga.setText("Rp. "+String.valueOf(angka*harga));
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });
            tvLanjutkan.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String jumlah = spinner.getSelectedItem().toString();
                    Call<PostPutDelOrder> postPutDelOrderCall = restApi.postCart(menuid,useridUser,jumlah);
                    postPutDelOrderCall.enqueue(new Callback<PostPutDelOrder>() {
                        @Override
                        public void onResponse(Call<PostPutDelOrder> call, Response<PostPutDelOrder> response) {
                            Toast.makeText(dm, "Pesanan berhasil masuk", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onFailure(Call<PostPutDelOrder> call, Throwable t) {
                            Toast.makeText(dm, "Pesanan berhasil masuk Tapi jangan pindah hanya keluarkan dialog ini", Toast.LENGTH_SHORT).show();
                        }
                    });
                    dialog.dismiss();
                }
            });
            btnNext.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    String jumlah = spinner.getSelectedItem().toString();
                    Call<PostPutDelOrder> postPutDelOrderCall = restApi.postCart(menuid,useridUser,jumlah);
                    postPutDelOrderCall.enqueue(new Callback<PostPutDelOrder>() {
                        @Override
                        public void onResponse(Call<PostPutDelOrder> call, Response<PostPutDelOrder> response) {
                            Toast.makeText(dm, "Pesanan berhasil masuk", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onFailure(Call<PostPutDelOrder> call, Throwable t) {
                            Toast.makeText(dm, "Pesanan berhasil masuk dan harusnya pindah ke activity cart", Toast.LENGTH_SHORT).show();
                        }
                    });

                    dialog.dismiss();
                }
            });
            dialog.show();


        }else {
            Toast.makeText(dm, "Kami harus login dulu untuk memesan makanan", Toast.LENGTH_SHORT).show();
        }
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_cart, menu);
        getMenuInflater().inflate(R.menu.menu_main,menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        final String useridMenu = getIntent().getStringExtra("USERID");
        final String useridUser = sharedPrefManager.getSpUserid();
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;
            case R.id.action_cart:
                Intent intent = new Intent(DetailMenu.this, CartActivity.class);
                DetailMenu.this.startActivity(intent);
                break;

        }
        return super.onOptionsItemSelected(item);
    }


}
