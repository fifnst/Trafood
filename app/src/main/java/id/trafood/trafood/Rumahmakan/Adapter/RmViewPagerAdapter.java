package id.trafood.trafood.Rumahmakan.Adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

/**
 * Created by kulinerin 1 on 18/10/2017.
 */

public class RmViewPagerAdapter extends FragmentStatePagerAdapter {
    ArrayList<Fragment> fragmentArrayList;
    //ArrayList<String> titles;
    Bundle fragmentBundle;
    public RmViewPagerAdapter(FragmentManager fm, ArrayList<Fragment> fragmentArrayList) {
        super(fm);
        this.fragmentArrayList = fragmentArrayList;
        //this.titles = titles;

    }

    @Override
    public Fragment getItem(int position) {

        return fragmentArrayList.get(position);
    }


    @Override
    public int getCount() {
        return fragmentArrayList.size();
    }

    /*@Override
    public CharSequence getPageTitle(int position) {
        return titles.get(position);
    }*/
}
