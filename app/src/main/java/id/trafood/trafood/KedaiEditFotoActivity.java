package id.trafood.trafood;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import id.trafood.trafood.Rest.Connect;

public class KedaiEditFotoActivity extends AppCompatActivity {

    ImageView imageView;
    Button btnSave;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kedai_edit_foto);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Rubah Foto");

        imageView = (ImageView) findViewById(R.id.ivFotoKedaiEdit);
        btnSave = (Button) findViewById(R.id.btnSaveEditFotoKedai);

        String foto = getIntent().getStringExtra("FOTO");
        String rmid = getIntent().getStringExtra("RMID");

        Picasso.with(this).load(Connect.IMAGE_RM_URL+foto).into(imageView);


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
