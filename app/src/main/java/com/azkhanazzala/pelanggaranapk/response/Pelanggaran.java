package com.azkhanazzala.pelanggaranapk.response;

import com.google.gson.annotations.SerializedName;

public class Pelanggaran {

    @SerializedName("id")
    private String id;

    @SerializedName("nama")
    private String nama;

    @SerializedName("jenis_pelanggaran")
    private String jenis_pelanggaran;

    @SerializedName("keterangan")
    private String keterangan;


    @SerializedName("tanggal")
    private String tanggal;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getJenis_pelanggaran() {
        return jenis_pelanggaran;
    }

    public void setJenis_pelanggaran(String jenis_pelanggaran) {
        this.jenis_pelanggaran = jenis_pelanggaran;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }
}
