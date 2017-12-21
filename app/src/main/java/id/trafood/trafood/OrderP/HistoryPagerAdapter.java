package id.trafood.trafood.OrderP;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

/**
 * Created by kulinerin 1 on 21/12/2017.
 */

public class HistoryPagerAdapter extends FragmentStatePagerAdapter {
    ArrayList<Fragment> fragmentArrayList;
    ArrayList<String> strings;

    public HistoryPagerAdapter(FragmentManager fm, ArrayList<Fragment> fragmentArrayList, ArrayList<String> strings) {
        super(fm);
        this.fragmentArrayList = fragmentArrayList;
        this.strings = strings;
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
    public CharSequence getPageTitle(int position) {
        return strings.get(position);
    }
}
