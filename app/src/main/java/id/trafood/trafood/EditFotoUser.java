package id.trafood.trafood;

import android.app.AlertDialog;
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
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.File;

import id.trafood.trafood.Models.PostPutDelUser;
import id.trafood.trafood.Rest.ApiClient;
import id.trafood.trafood.Rest.Connect;
import id.trafood.trafood.Rest.RestApi;
import pl.aprilapps.easyphotopicker.DefaultCallback;
import pl.aprilapps.easyphotopicker.EasyImage;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditFotoUser extends AppCompatActivity {
    public static final int REQUEST_CODE_CAMERA = 0012;
    public static final int REQUEST_CODE_GALLERY = 0013;

    private String [] items = {"Camera","Gallery"};
    ProgressDialog loading;
    RestApi restApi;
    Button simpan,select;
    Context mContext;
    ImageView ivFoto;
    SharedPrefManager sharedPrefManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_foto_user);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Edit Profil");

        sharedPrefManager = new SharedPrefManager(this);

        restApi = ApiClient.getClient().create(RestApi.class);
        mContext = this;

        ivFoto = (ImageView) findViewById(R.id.ivFotoUserEditnyal);
        simpan = (Button) findViewById(R.id.btnSaveFotoUserEdit);
        select = (Button) findViewById(R.id.select);

        simpan.setEnabled(false);

        select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openImage();
            }
        });

        simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loading = ProgressDialog.show(mContext, null, "Please wait....", true, false);
                simpandengangambar();
            }
        });

        Picasso.with(this).load(Connect.IMAGE_USER+getIntent().getStringExtra("FOTO")).into(ivFoto);
    }



    private void simpandengangambar() {
        ivFoto.buildDrawingCache();
        Bitmap bitmap = ivFoto.getDrawingCache();
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100,bos);
        byte[] bb = bos.toByteArray();
        String gambar = Base64.encodeToString(bb,Base64.DEFAULT);
        final String userid = sharedPrefManager.getSpUserid();
        Call<PostPutDelUser> putUser = restApi.putImageUser(gambar,userid);
        putUser.enqueue(new Callback<PostPutDelUser>() {
            @Override
            public void onResponse(Call<PostPutDelUser> call, Response<PostPutDelUser> response) {
                loading.dismiss();
                Toast.makeText(mContext, "Berhasil", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(mContext, EditProfilActivity.class);
                mContext.startActivity(intent);
            }

            @Override
            public void onFailure(Call<PostPutDelUser> call, Throwable t) {
                loading.dismiss();
                Toast.makeText(mContext, "Berhasil", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(mContext, EditProfilActivity.class);
                mContext.startActivity(intent);
            }
        });


    }


    private void openImage() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("options");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (items[i].equals("Camera")){
                    EasyImage.openCamera(EditFotoUser.this,REQUEST_CODE_CAMERA);
                }else if (items[i].equals("Gallery")){
                    EasyImage.openGallery(EditFotoUser.this, REQUEST_CODE_CAMERA);
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
                        Glide.with(EditFotoUser.this)
                                .load(imageFile)
                                .centerCrop()
                                .diskCacheStrategy(DiskCacheStrategy.ALL)
                                .into(ivFoto);
                        simpan.setEnabled(true);
                        break;

                    case REQUEST_CODE_GALLERY:
                        Glide.with(EditFotoUser.this)
                                .load(imageFile)
                                .centerCrop()
                                .diskCacheStrategy(DiskCacheStrategy.ALL)
                                .into(ivFoto);
                        simpan.setEnabled(true);
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
