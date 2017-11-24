package id.trafood.trafood;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import id.trafood.trafood.Rest.Connect;

public class EditFotoMenuActivity extends AppCompatActivity {

    ImageView ivfoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_foto_menu);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Ganti Foto");

        ivfoto = (ImageView) findViewById(R.id.ivFotoMenuEdit);
        String foto = getIntent().getStringExtra("FOTO");
        String menuid = getIntent().getStringExtra("MENUID");
        Picasso.with(this).load(Connect.IMAGE_MENU_URL+foto).into(ivfoto);

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
