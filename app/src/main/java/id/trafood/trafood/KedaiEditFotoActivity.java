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

import id.trafood.trafood.Models.PostPutDelRm;
import id.trafood.trafood.Rest.ApiClient;
import id.trafood.trafood.Rest.Connect;
import id.trafood.trafood.Rest.RestApi;
import pl.aprilapps.easyphotopicker.DefaultCallback;
import pl.aprilapps.easyphotopicker.EasyImage;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class KedaiEditFotoActivity extends AppCompatActivity {
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
        setContentView(R.layout.activity_kedai_edit_foto);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Rubah Foto");
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_green_24dp);
        getSupportActionBar().setElevation(0);

        imageView = (ImageView) findViewById(R.id.ivFotoKedaiEdit);
        btnSave = (Button) findViewById(R.id.btnSaveEditFotoKedai);
        select = (Button) findViewById(R.id.btnSelecImageFotoEdit);
        sharedPrefManager = new SharedPrefManager(this);
        btnSave.setEnabled(false);
        String foto = getIntent().getStringExtra("FOTO");


        Picasso.with(this).load(Connect.IMAGE_RM_URL+foto).into(imageView);
        mContext = this;
        restApi = ApiClient.getClient().create(RestApi.class);

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
        final String rmid = getIntent().getStringExtra("RMID");
        final String userid = sharedPrefManager.getSpUserid();
        Call<PostPutDelRm> putDelMenuCall = restApi.putFotoRM(gambar,userid,rmid);
        putDelMenuCall.enqueue(new Callback<PostPutDelRm>() {
            @Override
            public void onResponse(Call<PostPutDelRm> call, Response<PostPutDelRm> response) {
                loading.dismiss();
                Toast.makeText(mContext, "Sukses update", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(mContext, RingkasanKedaiActivity.class);
                intent.putExtra("TITLE","Ringkasan Kedai");
                intent.putExtra("RMID", rmid);
                intent.putExtra("USERID", userid);
                mContext.startActivity(intent);
            }

            @Override
            public void onFailure(Call<PostPutDelRm> call, Throwable t) {
                loading.dismiss();
                Toast.makeText(mContext, "Sukses update", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(mContext, RingkasanKedaiActivity.class);
                intent.putExtra("TITLE","Ringkasan Kedai");
                intent.putExtra("RMID", rmid);
                intent.putExtra("USERID", userid);
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
                    EasyImage.openCamera(KedaiEditFotoActivity.this,REQUEST_CODE_CAMERA);
                }else if (items[i].equals("Gallery")){
                    EasyImage.openGallery(KedaiEditFotoActivity.this, REQUEST_CODE_CAMERA);
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
                        Glide.with(KedaiEditFotoActivity.this)
                                .load(imageFile)
                                .centerCrop()
                                .diskCacheStrategy(DiskCacheStrategy.ALL)
                                .into(imageView);
                        btnSave.setEnabled(true);
                        break;

                    case REQUEST_CODE_GALLERY:
                        Glide.with(KedaiEditFotoActivity.this)
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
