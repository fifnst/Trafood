package id.trafood.trafood;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import java.util.ArrayList;

import id.trafood.trafood.Home.FilterMenu;
import id.trafood.trafood.Home.FilterRm;
import id.trafood.trafood.Home.ViewpagerFilterAdapater;
import id.trafood.trafood.Rumahmakan.Adapter.RmViewPagerAdapter;

public class FilterActivity extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Filter");

        tabLayout = (TabLayout) findViewById(R.id.tlFilter);
        viewPager = (ViewPager) findViewById(R.id.vpFilter);
        String lats = getIntent().getStringExtra("LAT");
        String lngs = getIntent().getStringExtra("LNG");
        String name = getIntent().getStringExtra("NAME");
        //this is for filter
        String likes = getIntent().getStringExtra("LIKES");
        String price = getIntent().getStringExtra("PRICES");
        String distance = getIntent().getStringExtra("DISTANCES");

        //this is for fasility
        String expensive = getIntent().getStringExtra("EXPENSIVES");
        String cheapest = getIntent().getStringExtra("CHEAPESTS");
        String wifi = getIntent().getStringExtra("WIFI");
        String mushola = getIntent().getStringExtra("MUSHOLA");
        String music = getIntent().getStringExtra("MUSIC");
        String parkir = getIntent().getStringExtra("PARKIR");
        String smoking = getIntent().getStringExtra("SMOKING");
        String wc = getIntent().getStringExtra("WC");


        String message = getIntent().getStringExtra("SEARCH");
        Bundle bundle = new Bundle();
        bundle.putString("LAT", lats);
        bundle.putString("LNG", lngs);
        bundle.putString("NAME", name);
        bundle.putString("SEARCH", message);

        bundle.putString("LIKES", likes);
        bundle.putString("PRICES", price);
        bundle.putString("DISTANCES", distance);
        //filter
        bundle.putString("EXPENSIVES", expensive);
        bundle.putString("CHEAPESTS", cheapest);
        bundle.putString("WIFI", wifi);
        bundle.putString("MUSHOLA", mushola);
        bundle.putString("MUSIC", music);
        bundle.putString("PARKIR", parkir);
        bundle.putString("SMOKING", smoking);
        bundle.putString("WC", wc);

        FilterMenu filnu = new FilterMenu();
        FilterRm filrm = new FilterRm();
        filnu.setArguments(bundle);
        filrm.setArguments(bundle);

        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(filnu);
        //fragments.add(filrm);

        ArrayList<String> title = new ArrayList<>();
        title.add("Menu & rumhamakan");
        //title.add("Rumah Makan");

        ViewpagerFilterAdapater adapater = new ViewpagerFilterAdapater(getSupportFragmentManager(), fragments, title);

        viewPager.setAdapter(adapater);
        tabLayout.setupWithViewPager(viewPager);

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
