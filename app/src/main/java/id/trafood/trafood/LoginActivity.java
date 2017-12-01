package id.trafood.trafood;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.OptionalPendingResult;
import com.google.android.gms.common.api.ResultCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import id.trafood.trafood.Rest.ApiClient;
import id.trafood.trafood.Rest.ApiInterface;
import id.trafood.trafood.Rest.UtilsApi;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener, GoogleApiClient.OnConnectionFailedListener {

    @BindView(R.id.etEmail)    EditText etEmail;
    @BindView(R.id.etPassword) EditText etPassword;
    @BindView(R.id.btnLogin) Button btnLogin;
    @BindView(R.id.googleSignin) SignInButton btnGoogleSignin;
    ProgressDialog loading;
    private static final int RC_SIGN_IN = 007;
    Context mContext;
    ApiInterface apiInterface,cekLogin;

    SharedPrefManager sharedPrefManager;

    GoogleApiClient googleApiClient;
    SignInButton btnSignin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        ButterKnife.bind(this);

        mContext = this;
        apiInterface = UtilsApi.getApiServive();
        sharedPrefManager = new SharedPrefManager(this);
        cekLogin = ApiClient.getClient().create(ApiInterface.class);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loading = ProgressDialog.show(mContext, null, "Please wait....", true, false);
                requestLogin();
            }
        });

        if (sharedPrefManager.getSPSudahLogin()){
            startActivity(new Intent(LoginActivity.this,MainActivity.class)
                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
            finish();
        }

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        googleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API,gso)
                .build();

        btnGoogleSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signinGoogle();
            }
        });
    }

    private void signinGoogle() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN){
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);
        }

    }

    private void handleSignInResult(GoogleSignInResult result) {
        Log.d("TAG","HandleSignInResult"+result.isSuccess());
        if (result.isSuccess()){
            //Signed in SuccesFully, intent
            GoogleSignInAccount acct = result.getSignInAccount();

            final String photo = acct.getId();
            final String name = acct.getDisplayName();
            final String email = acct.getEmail();
           /*  */

            apiInterface.loginGoogle(email,name).enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    try {
                        JSONObject jsonObject = new JSONObject(response.body().string());
                        if (jsonObject.getString("status").equals("200")){
                            String nama = jsonObject.getJSONObject("result").getString("nama");
                            String foto = jsonObject.getJSONObject("result").getString("fotouser");
                            String email = jsonObject.getJSONObject("result").getString("email");
                            String userid = jsonObject.getJSONObject("result").getString("userid");
                            String rumahmakan = jsonObject.getJSONObject("result").getString("rumahmakan");
                            sharedPrefManager.saveSPString(SharedPrefManager.SP_NAMA, nama);
                            sharedPrefManager.saveSPString(SharedPrefManager.SP_EMAIL, email);
                            sharedPrefManager.saveSPString(SharedPrefManager.SP_FOTO, foto);
                            sharedPrefManager.saveSPString(SharedPrefManager.SP_RUMAHMAKAN, rumahmakan);
                            sharedPrefManager.saveSPString(SharedPrefManager.SP_USERID, userid);
                            // Shared Pref ini berfungsi untuk menjadi trigger session login
                            sharedPrefManager.saveSPBoolean(SharedPrefManager.SP_SUDAH_LOGIN, true);
                            Toast.makeText(mContext, "Login Berhasil, selamat datang "+nama, Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(mContext, MainActivity.class)
                                    .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
                            finish();


                        }if (jsonObject.getString("status").equals("502")){
                            Toast.makeText(mContext, "Register Succesfull, Klik tombol login google untuk masuk", Toast.LENGTH_LONG).show();
                            String nama = jsonObject.getJSONObject("result").getString("nama");
                            String foto = jsonObject.getJSONObject("result").getString("fotouser");
                            String email = jsonObject.getJSONObject("result").getString("email");
                            String userid = jsonObject.getJSONObject("result").getString("userid");
                            String rumahmakan = jsonObject.getJSONObject("result").getString("rumahmakan");
                            sharedPrefManager.saveSPString(SharedPrefManager.SP_NAMA, nama);
                            sharedPrefManager.saveSPString(SharedPrefManager.SP_EMAIL, email);
                            sharedPrefManager.saveSPString(SharedPrefManager.SP_FOTO, foto);
                            sharedPrefManager.saveSPString(SharedPrefManager.SP_RUMAHMAKAN, rumahmakan);
                            sharedPrefManager.saveSPString(SharedPrefManager.SP_USERID, userid);
                            // Shared Pref ini berfungsi untuk menjadi trigger session login
                            sharedPrefManager.saveSPBoolean(SharedPrefManager.SP_SUDAH_LOGIN, true);
                            startActivity(new Intent(mContext, MainActivity.class)
                                    .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
                            finish();
                        } if (jsonObject.getString("status").equals("fail")){
                            Intent intent = new Intent(LoginActivity.this, TesLoginGoogle.class);
                            intent.putExtra("NAME", name);
                            intent.putExtra("EMAIL", email);
                            intent.putExtra("FOTO", photo);
                            LoginActivity.this.startActivity(intent);
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
    }

    @Override
    protected void onStart() {
        super.onStart();

        OptionalPendingResult<GoogleSignInResult> opr = Auth.GoogleSignInApi.silentSignIn(googleApiClient);
        // If the user's cached credentials are valid, the OptionalPendingResult will be "done"
        // and the GoogleSignInResult will be available instantly.
       if (opr.isDone()){
            GoogleSignInResult result = opr.get();
            handleSignInResult(result);
        }else {
            // If the user has not previously signed in on this device or the sign-in has expired,
            // this asynchronous branch will attempt to sign in the user silently.  Cross-device
            // single sign-on will occur in this branch.
            showProgressDialog();
            opr.setResultCallback(new ResultCallback<GoogleSignInResult>() {
                @Override
                public void onResult(@NonNull GoogleSignInResult googleSignInResult) {
                    hideProgressDialog();
                    handleSignInResult(googleSignInResult);
                }
           });
        }
    }

    private void hideProgressDialog() {
        if (loading != null && loading.isShowing()){
            loading.hide();
        }
    }

    private void showProgressDialog() {
        if (loading == null){
            loading = new ProgressDialog(this);
            loading.setMessage("Logging in... please Wait");
            loading.setIndeterminate(true);
        }
        loading.show();
    }

    private void requestLogin() {
        apiInterface.loginRequest(etEmail.getText().toString(),etPassword.getText().toString())
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()){
                            loading.dismiss();
                            try {
                                JSONObject jsonObject = new JSONObject(response.body().string());
                                if (jsonObject.getString("status").equals("200")){
                                    // Jika login berhasil maka data nama yang ada di response API
                                    // akan diparsing ke activity selanjutnya.
                                    Toast.makeText(mContext, "LOGIN SUCCESFULL", Toast.LENGTH_SHORT).show();
                                    String nama = jsonObject.getJSONObject("result").getString("nama");
                                    String foto = jsonObject.getJSONObject("result").getString("fotouser");
                                    String email = jsonObject.getJSONObject("result").getString("email");
                                    String userid = jsonObject.getJSONObject("result").getString("userid");
                                    String rumahmakan = jsonObject.getJSONObject("result").getString("rumahmakan");
                                    sharedPrefManager.saveSPString(SharedPrefManager.SP_NAMA, nama);
                                    sharedPrefManager.saveSPString(SharedPrefManager.SP_EMAIL, email);
                                    sharedPrefManager.saveSPString(SharedPrefManager.SP_FOTO, foto);
                                    sharedPrefManager.saveSPString(SharedPrefManager.SP_RUMAHMAKAN, rumahmakan);
                                    sharedPrefManager.saveSPString(SharedPrefManager.SP_USERID, userid);
                                    // Shared Pref ini berfungsi untuk menjadi trigger session login
                                    sharedPrefManager.saveSPBoolean(SharedPrefManager.SP_SUDAH_LOGIN, true);
                                    startActivity(new Intent(mContext, MainActivity.class)
                                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
                                    finish();
                                } else {
                                    String error_message = jsonObject.getString("error_msg");
                                    Toast.makeText(mContext, error_message, Toast.LENGTH_SHORT).show();
                                }


                            } catch (JSONException e){
                                e.printStackTrace();
                            } catch (IOException e){
                                e.printStackTrace();
                            }
                        }
                        else {
                            loading.dismiss();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Log.e("debug","onFailure: ERROR > " + toString());
                        loading.dismiss();
                    }
                });
    }


    @Override
    public void onClick(View view) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Log.d("Connection Failed","Connect Failed"+connectionResult);
    }
}
