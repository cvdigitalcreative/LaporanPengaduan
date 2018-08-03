package com.example.user.laporanpengaduan.mFragments;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.user.laporanpengaduan.R;
import com.example.user.laporanpengaduan.mAdapter.HistoryListAdapter;
import com.example.user.laporanpengaduan.mData.HistoryInfo;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;


public class HistoryFragment extends Fragment {
    private DatabaseReference databaseRef;
    private HistoryListAdapter adapter;
    private ProgressDialog progressDialog;
    private ArrayList<HistoryInfo> infoHistory;
    private ListView lv;

    public HistoryFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_history, container, false);

        lv = (ListView)view.findViewById(R.id.lv_LPPengaduan);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Silahkan tunggu proses memuat...");
        progressDialog.show();

        infoHistory = new ArrayList<>();
        databaseRef = FirebaseDatabase.getInstance().getReference("user/");

        databaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
               progressDialog.dismiss();
               infoHistory.clear();

                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    HistoryInfo info = snapshot.getValue(HistoryInfo.class);
                    infoHistory.add(info);
                }

                adapter = new HistoryListAdapter(getContext(), infoHistory);
                lv.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                progressDialog.dismiss();
            }
        });

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getActivity().getApplicationContext(), "halo", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
