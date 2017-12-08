package id.trafood.trafood;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import id.trafood.trafood.Rest.ApiClient;
import id.trafood.trafood.Rest.ApiInterface;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SettingProfilActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {
    GoogleApiClient googleApiClient;
    SharedPrefManager sharedPrefManager;
    LinearLayout linearProfil, linearKedai, linearLogout,linearPassword;
    ApiInterface apiInterface;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_profil);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Pengaturan Akun");
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_green_24dp);
        getSupportActionBar().setElevation(0);

        sharedPrefManager = new SharedPrefManager(this);

        linearProfil = (LinearLayout) findViewById(R.id.linearProfil);
        linearKedai = (LinearLayout) findViewById(R.id.linearKedai);
        linearLogout = (LinearLayout) findViewById(R.id.linearLogout);
        linearPassword = (LinearLayout) findViewById(R.id.linearGantiPassword);

        if (!sharedPrefManager.getSPSudahLogin()){
            startActivity(new Intent(SettingProfilActivity.this,LoginActivity.class)
                    .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
            finish();
        }
        String rumahmakan = sharedPrefManager.getSpRumahmakan();
        linearKedai.setVisibility(View.GONE);

        if (rumahmakan.equals("1")){
            linearKedai.setVisibility(View.VISIBLE);
        }

        apiInterface = ApiClient.getClient().create(ApiInterface.class);


        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        googleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();


        linearKedai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SettingProfilActivity.this, RingkasanKedaiActivity.class);
                intent.putExtra("TITLE", "Edit Kedai");
                intent.putExtra("RMID", getIntent().getStringExtra("RMID"));
                intent.putExtra("USERID",getIntent().getStringExtra("USERID"));
                SettingProfilActivity.this.startActivity(intent);
            }
        });

        linearProfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SettingProfilActivity.this, EditProfilActivity.class);
                intent.putExtra("USERID", sharedPrefManager.getSpUserid());
                SettingProfilActivity.this.startActivity(intent);
            }
        });

        linearPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SettingProfilActivity.this, ChangePasswordActivity.class);
                intent.putExtra("USERID", sharedPrefManager.getSpUserid());
                SettingProfilActivity.this.startActivity(intent);
            }
        });

        linearLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                new AlertDialog.Builder(SettingProfilActivity.this)
                        .setMessage("Arey You sure you want to logout?")
                        .setCancelable(false)
                        .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                String email = sharedPrefManager.getSPEmail();
                                apiInterface.cekProvider(email).enqueue(new Callback<ResponseBody>() {
                                    @Override
                                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                                        try {
                                            JSONObject jsonObject = new JSONObject(response.body().string());
                                            if (jsonObject.getString("status").equals("google")){
                                                Auth.GoogleSignInApi.signOut(googleApiClient).setResultCallback(
                                                        new ResultCallback<Status>() {
                                                            @Override
                                                            public void onResult(@NonNull Status status) {
                                                                sharedPrefManager.saveSPBoolean(SharedPrefManager.SP_SUDAH_LOGIN, false);
                                                                startActivity(new Intent(view.getContext(), MainActivity.class));
                                                            }
                                                        }
                                                );
                                            }else {
                                                sharedPrefManager.saveSPBoolean(SharedPrefManager.SP_SUDAH_LOGIN, false);
                                                startActivity(new Intent(view.getContext(), MainActivity.class));
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
                        })
                        .setNegativeButton("No", null)
                        .show();
            }
        });

    }

    private void logout() {
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

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}
