package id.trafood.trafood;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import id.trafood.trafood.Models.PostPutDelRm;
import id.trafood.trafood.Rest.ApiClient;
import id.trafood.trafood.Rest.RestApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DaftarKedaiAlamat extends AppCompatActivity {
    EditText etkecamatan, tvkota, tvalamat;
    Button btnSave;
    Context mContext;
    SharedPrefManager sharedPrefManager;
    RestApi restApi;
    ProgressDialog loading;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar_kedai_alamat);
        etkecamatan = (EditText) findViewById(R.id.etKecamatanKedaiTambah);
        tvkota = (EditText) findViewById(R.id.etKotaKedaiTambah);
        tvalamat = (EditText) findViewById(R.id.etNamaAlamatKedaiTambah);
        btnSave = (Button) findViewById(R.id.btnTambahKedaiMap) ;

        sharedPrefManager = new SharedPrefManager(this);
        mContext = this;
        restApi = ApiClient.getClient().create(RestApi.class);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String kota = tvkota.getText().toString();
                String kecamatan = etkecamatan.getText().toString();
                String alamat = tvalamat.getText().toString();
                String userid = sharedPrefManager.getSpUserid();
                Call<PostPutDelRm> putDelRmCall = restApi.postalamatRM(kecamatan,kota,alamat,userid);
                putDelRmCall.enqueue(new Callback<PostPutDelRm>() {
                    @Override
                    public void onResponse(Call<PostPutDelRm> call, Response<PostPutDelRm> response) {
                        Intent intent = new Intent(mContext, DaftarKedaiPeta.class);
                        mContext.startActivity(intent);
                    }

                    @Override
                    public void onFailure(Call<PostPutDelRm> call, Throwable t) {
                        Intent intent = new Intent(mContext, DaftarKedaiPeta.class);
                        mContext.startActivity(intent);
                    }
                });



            }
        });


    }


}
