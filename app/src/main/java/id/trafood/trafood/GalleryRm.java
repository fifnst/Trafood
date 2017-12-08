package id.trafood.trafood;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import id.trafood.trafood.Rest.Connect;

public class GalleryRm extends AppCompatActivity {

    ImageView ivReview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery_rm);
        Intent mIntent = getIntent();
        String galery = mIntent.getStringExtra("FOTOGALERY");
        ivReview = (ImageView) findViewById(R.id.image_preview);
        Picasso.with(this).load(Connect.IMAGE_GALERY+galery).error(R.mipmap.ic_launcher).into(ivReview);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarGallery);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Gallery");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_green_24dp);
        getSupportActionBar().setElevation(0);
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
