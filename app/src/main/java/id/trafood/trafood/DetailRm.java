package id.trafood.trafood;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import id.trafood.trafood.Adapter.DetailRumahmakanAdapater;
import id.trafood.trafood.Models.GetRumahmakan;
import id.trafood.trafood.Models.Rumahmakan;
import id.trafood.trafood.Rest.ApiClient;
import id.trafood.trafood.Rest.ApiInterface;
import id.trafood.trafood.Rest.Connect;
import id.trafood.trafood.Rumahmakan.Adapter.RmViewPagerAdapter;
import id.trafood.trafood.Rumahmakan.Fragment_Gallery;
import id.trafood.trafood.Rumahmakan.Fragment_Info;
import id.trafood.trafood.Rumahmakan.Fragment_ListMenu;
import id.trafood.trafood.Rumahmakan.Fragment_Review;
import id.trafood.trafood.Rumahmakan.Fragment_map;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailRm extends AppCompatActivity {
    TextView textViewnamaRM,textViewkategori, textViewAlamat;
    ImageView iVfotoRm;
    ViewPager myViewPager;
    TabLayout mytabLayout;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // this.supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_detail_rm);

        textViewnamaRM = (TextView) findViewById(R.id.tvnamarms);
        textViewkategori = (TextView) findViewById(R.id.tvKategoriRms);
        textViewAlamat = (TextView) findViewById(R.id.tvAlamatrms);
        iVfotoRm = (ImageView) findViewById(R.id.ivFotoRmDetails);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
       // getSupportActionBar().hide();

        Intent mIntent = getIntent();
        String getNamaRM = mIntent.getStringExtra("NAMARM");
        //getSupportActionBar().setTitle(getNamaRM);
        toolbar.setTitle(getNamaRM);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        String url = mIntent.getStringExtra("URL");
        String rmid = mIntent.getStringExtra("RMID");
        String foto = mIntent.getStringExtra("FOTO");
        String kategori = mIntent.getStringExtra("KATEGORI");
        String alamat = mIntent.getStringExtra("ALAMAT");
        String lat = mIntent.getStringExtra("LAT");
        String longi = mIntent.getStringExtra("LONG");

        textViewnamaRM.setText(getNamaRM);
        textViewkategori.setText(kategori);
        textViewAlamat.setText(alamat);
        Picasso.with(this).load(Connect.IMAGE_RM_URL+foto).error(R.mipmap.ic_launcher).into(iVfotoRm);

        myViewPager = (ViewPager) findViewById(R.id.vpMenuRM);
        mytabLayout = (TabLayout) findViewById(R.id.tlListMenuRM);

       // NestedScrollView scrollView = (NestedScrollView) findViewById(R.id.scrollableView);
       // scrollView.setFillViewport(true);

        //send data from activity to fragment
        Bundle bundle = new Bundle();
        bundle.putString("rmid", rmid);
        bundle.putString("lat", lat);
        bundle.putString("longs", longi);
        Fragment_ListMenu fragtry = new Fragment_ListMenu();
        Fragment_map fragmap = new Fragment_map();
        Fragment_Gallery fraglery = new Fragment_Gallery();
        Fragment_Info fragfo = new Fragment_Info();
        Fragment_Review fragview = new Fragment_Review();
        fragtry.setArguments(bundle);
        fragmap.setArguments(bundle);
        fraglery.setArguments(bundle);
        fragfo.setArguments(bundle);
        fragview.setArguments(bundle);

        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(fragtry);
        fragments.add(fragmap);
        fragments.add(fraglery);
        fragments.add(fragview);
        fragments.add(fragfo);

        ArrayList<String> titles = new ArrayList<>();
        titles.add("Menu");
        titles.add("Map");
        titles.add("Gallery");
        titles.add("Review");
        titles.add("Info");


        RmViewPagerAdapter adapter = new RmViewPagerAdapter(getSupportFragmentManager(), fragments,titles);

        myViewPager.setAdapter(adapter);
        mytabLayout.setupWithViewPager(myViewPager);




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
