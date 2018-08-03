package com.example.user.laporanpengaduan.mFragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bluejamesbond.text.DocumentView;
import com.example.user.laporanpengaduan.R;

public class BerandaFragment extends Fragment {
    public BerandaFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_beranda, container, false);

        DocumentView documentView = (DocumentView) view.findViewById(R.id.doc_beranda);
        String text = getString(R.string.deskripsi);
        documentView.setText(text);
        return view;
    }
}
