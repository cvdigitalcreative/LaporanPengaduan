package com.example.user.laporanpengaduan.mAdapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.user.laporanpengaduan.mFragments.AboutFragment;
import com.example.user.laporanpengaduan.mFragments.BerandaFragment;
import com.example.user.laporanpengaduan.mFragments.HistoryFragment;
import com.example.user.laporanpengaduan.mFragments.HomeFragment;
import com.example.user.laporanpengaduan.mFragments.ProfileFragment;

public class TabHomePagerAdapter extends FragmentPagerAdapter {
    private String[] title = new String[]{
            "Beranda" , "Pengaduan", "History", "Logout", "About"
    };

    public TabHomePagerAdapter(FragmentManager fm) {
        super(fm);
    }

    //method ini yang akan memanipulasi penampilan Fragment dilayar
    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position){
            case 0:
                fragment = new BerandaFragment();
                break;
            case 1:
                fragment = new HomeFragment();
                break;
            case 2:
                fragment = new HistoryFragment();
                break;
            case 3:
                fragment = new ProfileFragment();
                break;
            case 4:
                fragment = new AboutFragment();
                break;
            default:
                fragment = null;
                break;
        }

        return fragment;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return title[position];
    }

    @Override
    public int getCount() {
        return title.length;
    }
}
