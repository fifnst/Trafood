package id.trafood.trafood;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import id.trafood.trafood.Models.PostPutDelRm;
import id.trafood.trafood.Rest.ApiClient;
import id.trafood.trafood.Rest.ApiInterface;
import id.trafood.trafood.Rest.RestApi;
import id.trafood.trafood.Rest.UtilsApi;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DaftarKedaiActivity extends AppCompatActivity {
    SharedPrefManager sharedPrefManager;
    EditText etNamaKedai, eturl, etDeskrpsi, etNoTelp;
    Button buttonSave;
    RestApi restApi;
    ApiInterface apiInterface;
    Context mContext;
    TextView warningurl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar_kedai);

        etNamaKedai = (EditText) findViewById(R.id.etNamaKEdaiTambah);
        eturl = (EditText) findViewById(R.id.etUurlKEdaiTambah);
        etDeskrpsi = (EditText) findViewById(R.id.etDeskripsiKEdaiTambah);
        etNoTelp = (EditText) findViewById(R.id.etTelpKEdaiTambah);
        warningurl = (TextView) findViewById(R.id.WarningUrlTambah);

        warningurl.setVisibility(View.GONE);
        sharedPrefManager = new SharedPrefManager(this);
        buttonSave = (Button) findViewById(R.id.btnSaveTambahKedai);
        mContext = this;
        restApi = ApiClient.getClient().create(RestApi.class);
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String namakedai = etNamaKedai.getText().toString();
                String uurl = eturl.getText().toString();
                String deskripsi = etDeskrpsi.getText().toString();
                String notelp = etNoTelp.getText().toString();
                String userid = sharedPrefManager.getSpUserid();
                Call<PostPutDelRm> putDelRmCall = restApi.postKedai(namakedai,uurl,deskripsi,notelp,userid);
                putDelRmCall.enqueue(new Callback<PostPutDelRm>() {
                    @Override
                    public void onResponse(Call<PostPutDelRm> call, Response<PostPutDelRm> response) {
                        Toast.makeText(mContext, "Suskses", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(DaftarKedaiActivity.this,DaftarKedaiFoto.class);
                        DaftarKedaiActivity.this.startActivity(intent);
                    }

                    @Override
                    public void onFailure(Call<PostPutDelRm> call, Throwable t) {
                        Toast.makeText(mContext, "Suskses", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(DaftarKedaiActivity.this,DaftarKedaiFoto.class);
                        DaftarKedaiActivity.this.startActivity(intent);
                    }
                });

            }
        });

        eturl.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                apiInterface.cekUrl(eturl.getText().toString()).enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response.body().string());
                            String uuurl = jsonObject.getString("status");
                            if (uuurl.equals("200")){
                                warningurl.setVisibility(View.GONE);
                                buttonSave.setEnabled(true);
                            }else{
                                warningurl.setVisibility(View.VISIBLE);
                                buttonSave.setEnabled(false);
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

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }
}
