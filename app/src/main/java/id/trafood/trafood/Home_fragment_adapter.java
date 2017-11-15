package id.trafood.trafood;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

/**
 * Created by kulinerin 1 on 12/10/2017.
 */

public class Home_fragment_adapter extends FragmentStatePagerAdapter {

    ArrayList<Fragment> fragmentArrayList;
    ArrayList<String> titles;

    public Home_fragment_adapter(FragmentManager fm, ArrayList<Fragment> fragmentArrayList, ArrayList<String>titles) {
        super(fm);
        this.fragmentArrayList = fragmentArrayList;
        this.titles = titles;
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentArrayList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentArrayList.size();
    }

    @Override
    public CharSequence getPageTitle(int position){
        return titles.get(position);
    }
}
