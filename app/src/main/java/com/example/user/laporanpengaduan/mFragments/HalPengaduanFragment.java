package com.example.user.laporanpengaduan.mFragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.user.laporanpengaduan.R;

public class HalPengaduanFragment extends Fragment {
    private Button btnPengaduan;

    public HalPengaduanFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_hal_pengaduan, container, false);

        btnPengaduan = (Button)view.findViewById(R.id.btn_pengaduan);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable final Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        btnPengaduan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(savedInstanceState == null){
                    getFragmentManager().beginTransaction()
                            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                            .replace(R.id.frame_container,
                                    new PengaduanFragment(),
                                    PengaduanFragment.class.getSimpleName())
                            .addToBackStack(null).commit();
                }
                else{
                    Toast.makeText(getContext().getApplicationContext(),"gagal", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
