package id.trafood.trafood;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.support.design.widget.TabLayout;


import java.util.ArrayList;

import id.trafood.trafood.OrderP.FragmentHistory;
import id.trafood.trafood.OrderP.FragmentOnprogress;
import id.trafood.trafood.OrderP.HistoryPagerAdapter;

public class HistoryActivity extends AppCompatActivity {
    TabLayout tableLayout;
    ViewPager viewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Edit Profil");
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_green_24dp);
        getSupportActionBar().setElevation(0);

        tableLayout = (TabLayout) findViewById(R.id.tlHistory);
        viewPager = (ViewPager) findViewById(R.id.vpHistory);
        Bundle bundle = new Bundle();
        FragmentHistory fragry = new FragmentHistory();
        FragmentOnprogress fragress = new FragmentOnprogress();
        fragry.setArguments(bundle);
        fragress.setArguments(bundle);

        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(fragress);
        fragments.add(fragry);

        ArrayList<String> titles = new ArrayList<>();
        titles.add("On Progress");
        titles.add("History");

        HistoryPagerAdapter adapter = new HistoryPagerAdapter(getSupportFragmentManager(), fragments,titles);

        viewPager.setAdapter(adapter);
        tableLayout.setupWithViewPager(viewPager);

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
