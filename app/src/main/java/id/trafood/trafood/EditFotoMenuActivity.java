package id.trafood.trafood;

import android.app.AlertDialog;
import android.app.ProgressDialog;
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
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.File;

import id.trafood.trafood.Models.PostPutDelMenu;
import id.trafood.trafood.Rest.ApiClient;
import id.trafood.trafood.Rest.Connect;
import id.trafood.trafood.Rest.RestApi;
import id.trafood.trafood.Rest.UtilsApi;
import pl.aprilapps.easyphotopicker.DefaultCallback;
import pl.aprilapps.easyphotopicker.EasyImage;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditFotoMenuActivity extends AppCompatActivity {

    public static final int REQUEST_CODE_CAMERA = 0012;
    public static final int REQUEST_CODE_GALLERY = 0013;

    private String [] items = {"Camera","Gallery"};
    ProgressDialog loading;

    ImageView ivfoto;
    Button button,select;
    RestApi restApi;
    TextView pathFotoMen;

    SharedPrefManager sharedPrefManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_foto_menu);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Ganti Foto");

        ivfoto = (ImageView) findViewById(R.id.ivFotoMenuEdit);
        button = (Button) findViewById(R.id.btnSaveEditFotoMenu);
        pathFotoMen = (TextView) findViewById(R.id.tvPathFotoMenuEdit);
        select = (Button) findViewById(R.id.btnSelectImage);

        String foto = getIntent().getStringExtra("FOTO");
        String menuid = getIntent().getStringExtra("MENUID");
        Picasso.with(this).load(Connect.IMAGE_MENU_URL+foto).into(ivfoto);

        restApi = ApiClient.getClient().create(RestApi.class);
        button.setEnabled(false);

        sharedPrefManager = new SharedPrefManager(this);

        select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openImage();
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loading = ProgressDialog.show(EditFotoMenuActivity.this, null, "Please wait.... Uploading Image", true, false);
                upload();
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
                    EasyImage.openCamera(EditFotoMenuActivity.this,REQUEST_CODE_CAMERA);
                }else if (items[i].equals("Gallery")){
                    EasyImage.openGallery(EditFotoMenuActivity.this, REQUEST_CODE_CAMERA);
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
                        Glide.with(EditFotoMenuActivity.this)
                                .load(imageFile)
                                .centerCrop()
                                .diskCacheStrategy(DiskCacheStrategy.ALL)
                                .into(ivfoto);
                        pathFotoMen.setText(imageFile.getName());
                        button.setEnabled(true);
                        break;

                    case REQUEST_CODE_GALLERY:
                        Glide.with(EditFotoMenuActivity.this)
                                .load(imageFile)
                                .centerCrop()
                                .diskCacheStrategy(DiskCacheStrategy.ALL)
                                .into(ivfoto);
                        pathFotoMen.setText(imageFile.getName());
                        button.setEnabled(true);
                        break;
                }
            }
        });
    }
    private void upload() {
        //convert dulu gambarnya ke base 64
        ivfoto.buildDrawingCache();
        Bitmap bitmap = ivfoto.getDrawingCache();
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100,bos);
        byte[] bb = bos.toByteArray();
        String gambar = Base64.encodeToString(bb,Base64.DEFAULT);

        Call<PostPutDelMenu> putDelMenuCall = restApi.putFotoMenu(gambar,sharedPrefManager.getSpUserid()
                ,getIntent().getStringExtra("MENUID"));
        putDelMenuCall.enqueue(new Callback<PostPutDelMenu>() {
            @Override
            public void onResponse(Call<PostPutDelMenu> call, Response<PostPutDelMenu> response) {
             loading.dismiss();
             Intent intent = new Intent(EditFotoMenuActivity.this, EditMenuActivity.class);
             intent.putExtra("MENUID", getIntent().getStringExtra("MENUID"));
             intent.putExtra("USERID", sharedPrefManager.getSpUserid());
             EditFotoMenuActivity.this.startActivity(intent);
                Toast.makeText(EditFotoMenuActivity.this, "Sukses", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<PostPutDelMenu> call, Throwable t) {
            loading.dismiss();
                Toast.makeText(EditFotoMenuActivity.this, "Gagal", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(EditFotoMenuActivity.this, EditMenuActivity.class);
                intent.putExtra("MENUID", getIntent().getStringExtra("MENUID"));
                intent.putExtra("USERID", sharedPrefManager.getSpUserid());
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
