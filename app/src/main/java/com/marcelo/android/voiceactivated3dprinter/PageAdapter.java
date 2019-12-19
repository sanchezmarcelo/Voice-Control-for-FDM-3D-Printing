package com.marcelo.android.voiceactivated3dprinter;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
/*
@Author: Marcelo Sanchez
@Version: 12/3/2019
 */
public class PageAdapter extends FragmentPagerAdapter {

    ArrayList<Fragment> fragList;
    ArrayList<String> fragTitleList;


    PageAdapter(FragmentManager fragmentManager){
        super(fragmentManager);
        fragList = new ArrayList<>();
        fragTitleList = new ArrayList<>();

    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragList.get(position);
    }

    public void addFragment(Fragment fragment, String title){
        fragList.add(fragment);
        fragTitleList.add(title);

    }


    @Override
    public int getCount() {
        return fragList.size();
    }

    @Override
    public CharSequence getPageTitle(int position){
        return fragTitleList.get(position);
    }
}
