package id.trafood.trafood;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import id.trafood.trafood.Models.PostPutDelUser;
import id.trafood.trafood.Rest.ApiClient;
import id.trafood.trafood.Rest.ApiInterface;
import id.trafood.trafood.Rest.Connect;
import id.trafood.trafood.Rest.RestApi;
import id.trafood.trafood.Rest.UtilsApi;
import okhttp3.ResponseBody;
import pl.aprilapps.easyphotopicker.DefaultCallback;
import pl.aprilapps.easyphotopicker.EasyImage;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditProfilActivity extends AppCompatActivity {


    SharedPrefManager sharedPrefManager;
    ImageView ivFoto;
    TextView path;
    EditText etNamauser,etTgLahir, etTelepon, etKota, etAlamat, etTentang;
    Spinner spJk;
    ApiInterface apiInterface;
    Context mContext;
    DatePickerDialog datePickerDialog;
    SimpleDateFormat simpleDateFormat;
    RestApi restApi;
    Button simpan;
    ProgressDialog loading;
  //  String[] jkada = getResources().getStringArray(R.array.jeniskelamin);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profil);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Edit Profil");
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_green_24dp);
        getSupportActionBar().setElevation(0);

        sharedPrefManager = new SharedPrefManager(this);

        ivFoto = (ImageView) findViewById(R.id.ivFotoUserEdit);
        etNamauser = (EditText) findViewById(R.id.etNamaUserEdit);
        etTgLahir = (EditText) findViewById(R.id.etDOBEdit);
        etTelepon = (EditText) findViewById(R.id.etNoTelpUserEdit);
        etKota = (EditText) findViewById(R.id.etKotaUserEdit);
        etAlamat = (EditText) findViewById(R.id.etAlamatUserEdit);
        etTentang = (EditText) findViewById(R.id.etTentangUserEdit);
        spJk = (Spinner) findViewById(R.id.spJkEdit);
        path = (TextView) findViewById(R.id.path);
        simpan = (Button) findViewById(R.id.btnSimpan);

        mContext =this;
        apiInterface = UtilsApi.getApiServive();
        restApi = ApiClient.getClient().create(RestApi.class);
        sharedPrefManager = new SharedPrefManager(this);

        simpleDateFormat = new SimpleDateFormat("dd-mm-yyyy", Locale.US);

        etTgLahir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDateDialog();
            }
        });

        ambiluser();
        path.setVisibility(View.GONE);



        simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loading = ProgressDialog.show(mContext, null, "Please wait....", true, false);
                simpantanpagambar();
            }
        });
    }

    private void simpantanpagambar() {
        final String userid = sharedPrefManager.getSpUserid();
        String nama = etNamauser.getText().toString();
        String alamat = etAlamat.getText().toString();
        String kota = etKota.getText().toString();
        String telepon = etTelepon.getText().toString();
        String tentang = etTentang.getText().toString();
        String tglLahit = etTgLahir.getText().toString();
        String jk = spJk.getSelectedItem().toString();

        Call<PostPutDelUser> putUser = restApi.Putuser(nama,jk,tglLahit,telepon,kota,alamat,tentang,userid);
        putUser.enqueue(new Callback<PostPutDelUser>() {
            @Override
            public void onResponse(Call<PostPutDelUser> call, Response<PostPutDelUser> response) {
                loading.dismiss();
                Intent intent = new Intent(mContext,MainActivity.class);
                mContext.startActivity(intent);
                Toast.makeText(mContext, "Berhasil.. ", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<PostPutDelUser> call, Throwable t) {
                loading.dismiss();
                Toast.makeText(mContext, "Berhasil..", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(mContext,MainActivity.class);
                mContext.startActivity(intent);
            }
        });
    }


    private void showDateDialog() {
        //untuk get tanggal sekarang
        Calendar calendar = Calendar.getInstance();

        //inisilisasi date picker
        datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                //set calender untuk menampung tanggal yang dipilih
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, month, day);

                etTgLahir.setText(simpleDateFormat.format(newDate.getTime()));
            }
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));

        datePickerDialog.show();
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
                    final String fotouser = jsonObject.getJSONObject("result").getString("fotouser");
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
                    ivFoto.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(mContext, EditFotoUser.class);
                            intent.putExtra("FOTO", fotouser);
                            mContext.startActivity(intent);
                        }
                    });
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
