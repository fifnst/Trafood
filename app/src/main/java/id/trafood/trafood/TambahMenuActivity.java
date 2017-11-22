package id.trafood.trafood;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.support.v7.app.AlertDialog;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

import id.trafood.trafood.Models.PostPutDelMenu;
import id.trafood.trafood.Rest.ApiClient;
import id.trafood.trafood.Rest.ApiInterface;
import id.trafood.trafood.Rest.Connect;
import id.trafood.trafood.Rest.RestApi;
import id.trafood.trafood.Rest.UtilsApi;
import okhttp3.ResponseBody;
import pl.aprilapps.easyphotopicker.EasyImage;
import pl.aprilapps.easyphotopicker.DefaultCallback;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Field;

public class TambahMenuActivity extends AppCompatActivity {

    public static final int REQUEST_CODE_CAMERA = 0012;
    public static final int REQUEST_CODE_GALLERY = 0013;

    EditText kodemenu,userid,namamenu,harga,deskripsi,tag,path,rmid;
    ImageView fotomenu;
    Button btnInput;
    Context mContext;
    ApiInterface apiInterface;
    RestApi restApi;
    private String [] items = {"Camera","Gallery"};
    ProgressDialog loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_menu);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Tambah Menu");

        rmid = (EditText) findViewById(R.id.etRmIDMenuInput);
        kodemenu =(EditText)findViewById(R.id.tvKodeMenu);
        btnInput = (Button) findViewById(R.id.btnTambahMenu);
        userid = (EditText) findViewById(R.id.etUserIdMenuInput);
        fotomenu = (ImageView) findViewById(R.id.ivMenuInput);
        path = (EditText) findViewById(R.id.tvPathMenu);
        namamenu = (EditText) findViewById(R.id.etNamaMenuInput);
        harga = (EditText) findViewById(R.id.etHargaMenuInput);
        deskripsi = (EditText) findViewById(R.id.etDeskripsiMenuInput);
        tag = (EditText) findViewById(R.id.etTagMenuInput);

        userid.setText(getIntent().getStringExtra("USERID"));
        rmid.setText(getIntent().getStringExtra("RMID"));

        kodemenu.setVisibility(View.GONE);
        userid.setVisibility(View.GONE);
        path.setVisibility(View.GONE);
        rmid.setVisibility(View.GONE);



        mContext = this;
        apiInterface = UtilsApi.getApiServive();
        apiInterface.nomormenu().enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    JSONObject jsonResult = new JSONObject(response.body().string());
                    String menuid = jsonResult.getString("result");
                    kodemenu.setText(menuid);

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

        fotomenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openImage();
            }
        });

        restApi = ApiClient.getClient().create(RestApi.class);
        btnInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (path.getText().toString().equals("") ){
                    Toast.makeText(TambahMenuActivity.this, "Foto harus di isi", Toast.LENGTH_SHORT).show();
                }
                else if (namamenu.getText().toString().equals("") ){
                    Toast.makeText(TambahMenuActivity.this, "Namam menu harus di isi", Toast.LENGTH_SHORT).show();
                }
                else if (deskripsi.getText().toString().equals("") ){
                    Toast.makeText(TambahMenuActivity.this, "Deskripsi harus di isi", Toast.LENGTH_SHORT).show();
                }
                else if (harga.getText().toString().equals("") ){
                    Toast.makeText(TambahMenuActivity.this, "Harga harus di isi", Toast.LENGTH_SHORT).show();
                }
                else if (tag.getText().toString().equals("") ){
                    Toast.makeText(TambahMenuActivity.this, "Tag harus di isi", Toast.LENGTH_SHORT).show();
                }
                if (!path.getText().toString().equals("")
                        &&!tag.getText().toString().equals("")
                        &&!harga.getText().toString().equals("")
                        &&!deskripsi.getText().toString().equals("")
                        &&!namamenu.getText().toString().equals(""))
                {
                loading = ProgressDialog.show(mContext, null, "Please wait.... Uploading Image", true, false);
                //check jika siapa tahu datanya kosong
                upload();
                }
            }
        });

    }

    private void upload() {

            String menuid = kodemenu.getText().toString();
            String koderm = rmid.getText().toString();
            String kodeuser = userid.getText().toString();
            String strnamamenu = namamenu.getText().toString();
            String strDeskripsi = deskripsi.getText().toString();
            String strHarga  = harga.getText().toString();
            String strTag = tag.getText().toString();
            String foto = path.getText().toString();

            //BitmapDrawable drawable = (BitmapDrawable) fotomenu.getDrawable();
            fotomenu.buildDrawingCache();
            Bitmap bitmap = fotomenu.getDrawingCache();
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG,100,bos);
            byte[]bb = bos.toByteArray();
            String gambar = Base64.encodeToString(bb,Base64.DEFAULT);
            //path.setText(gambar);


            Call<PostPutDelMenu> postMenuCall = restApi.postMenu(strnamamenu,strDeskripsi,strHarga,strTag,gambar,kodeuser,koderm);
            postMenuCall.enqueue(new Callback<PostPutDelMenu>() {
                @Override
                public void onResponse(Call<PostPutDelMenu> call, Response<PostPutDelMenu> response) {
                    loading.dismiss();
                    Toast.makeText(TambahMenuActivity.this, "Sukses Input Menu", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onFailure(Call<PostPutDelMenu> call, Throwable t) {
                    Toast.makeText(TambahMenuActivity.this, "Gagal Input", Toast.LENGTH_SHORT).show();
                }
            });

    }

    private void openImage() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Options");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (items[i].equals("Camera")){
                    EasyImage.openCamera(TambahMenuActivity.this,REQUEST_CODE_CAMERA);
                }else if (items[i].equals("Gallery")){
                    EasyImage.openGallery(TambahMenuActivity.this, REQUEST_CODE_CAMERA);
                }
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        EasyImage.handleActivityResult(requestCode, resultCode, data, this, new DefaultCallback() {
            @Override
            public void onImagePicked(File imageFile, EasyImage.ImageSource source, int type) {
                switch (type){
                    case REQUEST_CODE_CAMERA:
                        Glide.with(TambahMenuActivity.this)
                                .load(imageFile)
                                .centerCrop()
                                .diskCacheStrategy(DiskCacheStrategy.ALL)
                                .into(fotomenu);
                        path.setText(imageFile.getName());
                        break;

                    case REQUEST_CODE_GALLERY:
                        Glide.with(TambahMenuActivity.this)
                                  .load(imageFile)
                                .centerCrop()
                                .diskCacheStrategy(DiskCacheStrategy.ALL)
                                .into(fotomenu);
                        path.setText(imageFile.getName());
                        break;
                }
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
