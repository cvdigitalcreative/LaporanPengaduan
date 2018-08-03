package com.example.user.laporanpengaduan.mFragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.user.laporanpengaduan.R;
import com.example.user.laporanpengaduan.mFragments.HalPengaduanFragment;

public class HomeFragment extends Fragment {
    private Button btnPengaduan;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_home, container, false);

        getFragmentManager().beginTransaction()
                .add(R.id.frame_container,
                        new HalPengaduanFragment(),
                        HalPengaduanFragment.class.getSimpleName()).commit();

        return view;
    }
}
