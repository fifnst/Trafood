package id.trafood.trafood;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

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
import id.trafood.trafood.Rest.UtilsApi;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TesLoginGoogle extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {
    TextView etNama, etEmail, etFoto;
    Button logout;
    GoogleApiClient googleApiClient;
    SharedPrefManager sharedPrefManager;
    ApiInterface apiInterface;
    Context mContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tes_login_google);
        etNama = (TextView) findViewById(R.id.tesnama);
        etEmail = (TextView) findViewById(R.id.tesEmail);
        etFoto = (TextView) findViewById(R.id.TesFoto);
        logout = (Button) findViewById(R.id.logotuTes);
        Intent tes = getIntent();

        etNama.setText(tes.getStringExtra("NAME"));
        etEmail.setText(tes.getStringExtra("EMAIL"));
        etFoto.setText(tes.getStringExtra("FOTO"));
        mContext = this;
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        sharedPrefManager = new SharedPrefManager(this);



        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        googleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Auth.GoogleSignInApi.signOut(googleApiClient).setResultCallback(
                        new ResultCallback<Status>() {
                            @Override
                            public void onResult(@NonNull Status status) {
                                Intent intent = new Intent(TesLoginGoogle.this, MainActivity.class);
                                TesLoginGoogle.this.startActivity(intent);
                            }
                        }
                );
            }
        });


    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}
