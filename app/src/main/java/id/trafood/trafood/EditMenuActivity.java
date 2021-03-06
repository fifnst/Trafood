package id.trafood.trafood;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import id.trafood.trafood.Models.PostPutDelMenu;
import id.trafood.trafood.Rest.ApiClient;
import id.trafood.trafood.Rest.ApiInterface;
import id.trafood.trafood.Rest.Connect;
import id.trafood.trafood.Rest.RestApi;
import id.trafood.trafood.Rest.UtilsApi;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditMenuActivity extends AppCompatActivity {
    Context mContext;
    ApiInterface apiInterface;
    RestApi restApi;

    SharedPrefManager sharedPrefManager;

    EditText tvnamamenu, tvharga, tvdeskrpsi, tvtag;
    ImageView ivfotomenu;
    Button btnEditMenu;
    String useridmenu, useriduser, menuid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_menu);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Edit Menu");
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_green_24dp);
        getSupportActionBar().setElevation(0);

        tvnamamenu = (EditText) findViewById(R.id.etNamaMenuEdit);
        tvharga = (EditText) findViewById(R.id.etHargaMenuEdit);
        tvdeskrpsi = (EditText) findViewById(R.id.etDeskripsiMenuEdit);
        tvtag = (EditText) findViewById(R.id.etTagMenuEdit);
        ivfotomenu = (ImageView) findViewById(R.id.ivMenuEdit);
        btnEditMenu = (Button) findViewById(R.id.btnEditMenu);

        sharedPrefManager = new SharedPrefManager(this);
        //get data from spm
        useriduser = sharedPrefManager.getSpUserid();
        //get data from intent
        useridmenu = getIntent().getStringExtra("USERID");
        menuid = getIntent().getStringExtra("MENUID");

        //cek if you have login
        if (!sharedPrefManager.getSPSudahLogin()){
            startActivity(new Intent(EditMenuActivity.this,LoginActivity.class)
                    .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
            finish();
        }

        //cek if your userid is same with menu userid
       if (sharedPrefManager.getSPSudahLogin()){
            if (!useriduser.equals(useridmenu)){
                Intent intent = new Intent(EditMenuActivity.this, MainActivity.class);
                EditMenuActivity.this.startActivity(intent);
                Toast.makeText(mContext, "You dont have permission to enter this page", Toast.LENGTH_SHORT).show();
            }
        }

        mContext = this;
        apiInterface = UtilsApi.getApiServive();
        restApi = ApiClient.getClient().create(RestApi.class);

        //method untuk mengeset isi
        SetIsi();
    }

    private void SetIsi() {
        final String menuid = getIntent().getStringExtra("MENUID");
        apiInterface.menuDetail(menuid).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    JSONObject jsonResult = new JSONObject(response.body().string());
                    String namamenu = jsonResult.getJSONObject("result").getString("namamenu");
                    String deskripsi = jsonResult.getJSONObject("result").getString("deskripsi");
                    String harga = jsonResult.getJSONObject("result").getString("harga");
                    String tag = jsonResult.getJSONObject("result").getString("tag");
                    String active = jsonResult.getJSONObject("result").getString("active");
                    final String fotomenu = jsonResult.getJSONObject("result").getString("foto");
                    tvnamamenu.setText(namamenu);
                    tvdeskrpsi.setText(deskripsi);
                    tvharga.setText(harga);
                    tvtag.setText(tag);

                    Picasso.with(EditMenuActivity.this).load(Connect.IMAGE_MENU_URL+fotomenu)
                            .into(ivfotomenu);

                    ivfotomenu.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(EditMenuActivity.this, EditFotoMenuActivity.class);
                            intent.putExtra("FOTO", fotomenu);
                            intent.putExtra("MENUID", menuid);
                            intent.putExtra("USERID", sharedPrefManager.getSpUserid());
                            EditMenuActivity.this.startActivity(intent);

                        }
                    });

                    btnEditMenu.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Call<PostPutDelMenu> updateMenu = restApi.putMenu(
                                    getIntent().getStringExtra("MENUID"),
                                    tvnamamenu.getText().toString(),tvdeskrpsi.getText().toString(),tvharga.getText().toString(),
                                    tvtag.getText().toString(),sharedPrefManager.getSpUserid());
                            updateMenu.enqueue(new Callback<PostPutDelMenu>() {
                                @Override
                                public void onResponse(Call<PostPutDelMenu> call, Response<PostPutDelMenu> response) {
                                    finish();
                                    Toast.makeText(mContext, "Sukses update menu", Toast.LENGTH_SHORT).show();

                                    Intent intent = new Intent(mContext, DetailMenu.class);
                                    intent.putExtra("NAMAMENU",tvnamamenu.getText().toString());
                                    intent.putExtra("MENUID",getIntent().getStringExtra("MENUID"));
                                    intent.putExtra("HARGA",tvharga.getText().toString());
                                    intent.putExtra("FOTOMENU",fotomenu);
                                    intent.putExtra("USERID",sharedPrefManager.getSpUserid());
                                    mContext.startActivity(intent);
                                }

                                @Override
                                public void onFailure(Call<PostPutDelMenu> call, Throwable t) {
                                    Intent intent = new Intent(mContext, DetailMenu.class);
                                    intent.putExtra("NAMAMENU",tvnamamenu.getText().toString());
                                    intent.putExtra("MENUID",getIntent().getStringExtra("MENUID"));
                                    intent.putExtra("HARGA",tvharga.getText().toString());
                                    intent.putExtra("FOTOMENU",fotomenu);
                                    intent.putExtra("USERID",sharedPrefManager.getSpUserid());
                                    mContext.startActivity(intent);

                                    Toast.makeText(mContext, "Sukses update menu", Toast.LENGTH_LONG).show();
                                }
                            });
                        }
                    });
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
