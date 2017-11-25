package id.trafood.trafood;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import id.trafood.trafood.Rest.ApiInterface;
import id.trafood.trafood.Rest.Connect;
import id.trafood.trafood.Rest.UtilsApi;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditProfilActivity extends AppCompatActivity {

    SharedPrefManager sharedPrefManager;
    ImageView ivFoto;
    EditText etNamauser,etTgLahir, etTelepon, etKota, etAlamat, etTentang;
    Spinner spJk;
    ApiInterface apiInterface;
    Context mContext;
  //  String[] jkada = getResources().getStringArray(R.array.jeniskelamin);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profil);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Edit Profil");

        sharedPrefManager = new SharedPrefManager(this);

        ivFoto = (ImageView) findViewById(R.id.ivFotoUserEdit);
        etNamauser = (EditText) findViewById(R.id.etNamaUserEdit);
        etTgLahir = (EditText) findViewById(R.id.etDOBEdit);
        etTelepon = (EditText) findViewById(R.id.etNoTelpUserEdit);
        etKota = (EditText) findViewById(R.id.etKotaUserEdit);
        etAlamat = (EditText) findViewById(R.id.etAlamatUserEdit);
        etTentang = (EditText) findViewById(R.id.etTentangUserEdit);
        spJk = (Spinner) findViewById(R.id.spJkEdit);

        mContext =this;
        apiInterface = UtilsApi.getApiServive();
        sharedPrefManager = new SharedPrefManager(this);

        ambiluser();
    }

    private void ambiluser() {
        String userid = sharedPrefManager.getSpUserid();
        apiInterface.userGet(userid).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    JSONObject jsonObject = new JSONObject(response.body().string());
                    String namauser = jsonObject.getJSONObject("result").getString("nama");
                    String username = jsonObject.getJSONObject("result").getString("username");
                    String tgllahir = jsonObject.getJSONObject("result").getString("tgllahir");
                    String noTelp = jsonObject.getJSONObject("result").getString("telp");
                    String jk = jsonObject.getJSONObject("result").getString("jk");
                    String fotouser = jsonObject.getJSONObject("result").getString("fotouser");
                    String alamat = jsonObject.getJSONObject("result").getString("alamat");
                    String tentang = jsonObject.getJSONObject("result").getString("tentang");
                    String kota = jsonObject.getJSONObject("result").getString("kota");

                    etNamauser.setText(namauser);
                    etTgLahir.setText(tgllahir);
                    etTelepon.setText(noTelp);
                    etKota.setText(kota);
                    etAlamat.setText(alamat);
                    etTentang.setText(tentang);
                    Picasso.with(mContext).load(Connect.IMAGE_USER+fotouser).into(ivFoto);

                   if (jk.equals("Pria") ){
                        spJk.setSelection(0,false);
                    }
                   if (jk.equals("Wanita")){
                        spJk.setSelection(1,false);
                    }

                } catch (JSONException e) {
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
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
