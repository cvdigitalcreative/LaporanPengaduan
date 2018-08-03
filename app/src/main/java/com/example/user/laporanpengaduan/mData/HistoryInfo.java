package com.example.user.laporanpengaduan.mData;

public class HistoryInfo {
    private String nama;
    private String kategori;
    private String deskripsi;
    private String lokasi;
    private String tanggal;
    private String url;
    private String gmail;
    private String status;

    public HistoryInfo() {}

    public HistoryInfo(String nama, String kategori, String deskripsi, String lokasi, String tanggal, String url, String gmail, String status) {
        this.nama = nama;
        this.kategori = kategori;
        this.deskripsi = deskripsi;
        this.lokasi = lokasi;
        this.tanggal = tanggal;
        this.url = url;
        this.gmail = gmail;
        this.status = status;
    }

    public String getNama() {
        return nama;
    }

    public String getKategori() {
        return kategori;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public String getLokasi() {
        return lokasi;
    }

    public String getTanggal() {
        return tanggal;
    }

    public String getUrl() {
        return url;
    }

    public String getGmail() {
        return gmail;
    }

    public String getStatus() {
        return status;
    }
}
