package id.trafood.trafood;

import android.support.v4.app.Fragment;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TableLayout;

import java.util.ArrayList;

import id.trafood.trafood.OrderP.FragmentDetailHistory;
import id.trafood.trafood.OrderP.FragmentHistory;
import id.trafood.trafood.OrderP.FragmentOnprogress;
import id.trafood.trafood.OrderP.FragmentStatus;
import id.trafood.trafood.OrderP.HistoryPagerAdapter;

public class StatusPengirimanActivity extends AppCompatActivity {
    TabLayout tableLayout;
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status_pengiriman);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Status Pengiriman");
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_green_24dp);
        getSupportActionBar().setElevation(0);

        tableLayout = (TabLayout) findViewById(R.id.tlStatus);
        viewPager = (ViewPager) findViewById(R.id.vpStatus);
        String transid = getIntent().getStringExtra("TRANSID");
        //String transid = "1712191513668974";

        Bundle bundle = new Bundle();
        bundle.putString("TRANSID",transid);
        FragmentStatus fragry = new FragmentStatus();
        FragmentDetailHistory fragress = new FragmentDetailHistory();
        fragry.setArguments(bundle);
        fragress.setArguments(bundle);

        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(fragry);
        fragments.add(fragress);
        ArrayList<String> titles = new ArrayList<>();
        titles.add("Status");
        titles.add("Detail");

        HistoryPagerAdapter adapter = new HistoryPagerAdapter(getSupportFragmentManager(), fragments,titles);

        viewPager.setAdapter(adapter);
        tableLayout.setupWithViewPager(viewPager);

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
