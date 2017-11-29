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
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.io.ByteArrayOutputStream;
import java.io.File;

import id.trafood.trafood.Models.PostPutDelRm;
import id.trafood.trafood.Rest.ApiClient;
import id.trafood.trafood.Rest.RestApi;
import pl.aprilapps.easyphotopicker.DefaultCallback;
import pl.aprilapps.easyphotopicker.EasyImage;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DaftarKedaiFoto extends AppCompatActivity {
    public static final int REQUEST_CODE_CAMERA = 0012;
    public static final int REQUEST_CODE_GALLERY = 0013;

    private String [] items = {"Camera","Gallery"};
    ProgressDialog loading;
    ImageView imageView;
    Button btnSave, select;
    Context mContext;
    RestApi restApi;
    SharedPrefManager sharedPrefManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar_kedai_foto);

        imageView = (ImageView) findViewById(R.id.ivFotoTambahKedai);
        btnSave = (Button) findViewById(R.id.btnSaveTambahFotoKedai);
        select = (Button) findViewById(R.id.btnSelectFotoKedaiTambah);
        sharedPrefManager = new SharedPrefManager(this);
        //btnSave.setEnabled(false);
        restApi = ApiClient.getClient().create(RestApi.class);
        mContext = this;
        select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openImage();
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                loading = ProgressDialog.show(mContext, null, "Please wait.... Uploading Image", true, false);
                upload();
            }
        });
    }

    private void upload() {
        imageView.buildDrawingCache();
        Bitmap bitmap = imageView.getDrawingCache();
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100,bos);
        byte[] bb = bos.toByteArray();
        String gambar = Base64.encodeToString(bb,Base64.DEFAULT);
        final String userid = sharedPrefManager.getSpUserid();

        Call<PostPutDelRm> putDelRmCall = restApi.postFotoRM(gambar,userid);
        putDelRmCall.enqueue(new Callback<PostPutDelRm>() {
            @Override
            public void onResponse(Call<PostPutDelRm> call, Response<PostPutDelRm> response) {
                Toast.makeText(mContext, "Sukses", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(mContext, DaftarKedaiAlamat.class);
                mContext.startActivity(intent);
            }

            @Override
            public void onFailure(Call<PostPutDelRm> call, Throwable t) {
                Toast.makeText(mContext, "Suskses", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(mContext, DaftarKedaiAlamat.class);
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
                    EasyImage.openCamera(DaftarKedaiFoto.this,REQUEST_CODE_CAMERA);
                }else if (items[i].equals("Gallery")){
                    EasyImage.openGallery(DaftarKedaiFoto.this, REQUEST_CODE_CAMERA);
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
                        Glide.with(DaftarKedaiFoto.this)
                                .load(imageFile)
                                .centerCrop()
                                .diskCacheStrategy(DiskCacheStrategy.ALL)
                                .into(imageView);
                        btnSave.setEnabled(true);
                        break;

                    case REQUEST_CODE_GALLERY:
                        Glide.with(DaftarKedaiFoto.this)
                                .load(imageFile)
                                .centerCrop()
                                .diskCacheStrategy(DiskCacheStrategy.ALL)
                                .into(imageView);
                        btnSave.setEnabled(true);
                        break;
                }
            }
        });
    }

}
