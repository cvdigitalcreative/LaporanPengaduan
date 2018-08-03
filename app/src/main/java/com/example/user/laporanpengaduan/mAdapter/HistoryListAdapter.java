package com.example.user.laporanpengaduan.mAdapter;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.user.laporanpengaduan.R;
import com.example.user.laporanpengaduan.mData.HistoryInfo;

import java.util.ArrayList;

public class HistoryListAdapter extends BaseAdapter{
    private Context context;
    private ArrayList<HistoryInfo> historyInfo;
    private LayoutInflater inflater;

    public HistoryListAdapter(Context context, ArrayList<HistoryInfo> historyInfo) {
        this.context = context;
        this.historyInfo = historyInfo;
    }

    @Override
    public int getCount() {
        return historyInfo.size();
    }

    @Override
    public Object getItem(int position) {
        return historyInfo.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.history_item,parent,false);
        }

        TextView tvTanggal = convertView.findViewById(R.id.tv_tanggalItem);
        TextView tvKategori = convertView.findViewById(R.id.tv_kategoriItem);
        TextView tvNama = convertView.findViewById(R.id.tv_namaItem);
        TextView tvDeskripsi = convertView.findViewById(R.id.tv_deskripsiItem);
        ImageView ivFoto = convertView.findViewById(R.id.iv_fotoItem);
        TextView tvLokasi = convertView.findViewById(R.id.tv_lokasiItem);
        TextView tvJawaban = convertView.findViewById(R.id.tv_jawaban);

        final HistoryInfo hInfo = (HistoryInfo) this.getItem(position);

        tvTanggal.setText("Dilaporkan pada tanggal : "+hInfo.getTanggal());
        tvKategori.setText("Kategori pelaporan: "+hInfo.getKategori());
        tvNama.setText("Nama pelapor : "+hInfo.getNama());
        tvDeskripsi.setText("Deskripsi laporan : "+hInfo.getDeskripsi());
        tvLokasi.setText("Lokasi laporan : "+hInfo.getLokasi());
        Glide.with(context).load(hInfo.getUrl()).into(ivFoto);
        if(hInfo.getStatus().equals("Pengaduan belum dikerjakan")){
            tvJawaban.setText(hInfo.getStatus());
            tvJawaban.setBackgroundColor(Color.RED);
            tvJawaban.setTextColor(Color.WHITE);
        }
        else if(hInfo.getStatus().equals("Pengaduan sedang dikerjakan")){
            tvJawaban.setText(hInfo.getStatus());
            tvJawaban.setBackgroundColor(Color.BLUE);
            tvJawaban.setTextColor(Color.WHITE);
        }
        else if(hInfo.getStatus().equals("Pengaduan sudah dikerjakan")){
            tvJawaban.setText(hInfo.getStatus());
            tvJawaban.setBackgroundColor(Color.GREEN);
            tvJawaban.setTextColor(Color.BLACK);
        }

        return convertView;
    }
}
