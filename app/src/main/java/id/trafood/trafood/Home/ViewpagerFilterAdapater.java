package id.trafood.trafood.Home;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

/**
 * Created by kulinerin 1 on 08/11/2017.
 */

public class ViewpagerFilterAdapater extends FragmentStatePagerAdapter {
    ArrayList<Fragment> fragmentArrayList;
    ArrayList<String> title;

    public ViewpagerFilterAdapater(FragmentManager fm, ArrayList<Fragment> fragmentArrayList, ArrayList<String> title) {
        super(fm);
        this.fragmentArrayList = fragmentArrayList;
        this.title = title;
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
        return title.get(position);
    }
}
