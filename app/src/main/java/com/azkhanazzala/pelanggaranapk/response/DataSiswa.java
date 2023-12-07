package com.azkhanazzala.pelanggaranapk.response;

import com.google.gson.annotations.SerializedName;

public class DataSiswa {
    @SerializedName("nis")
    private String nis;

    @SerializedName("nama")
    private String nama;

    @SerializedName("kelas")
    private  String kelas;

    @SerializedName("jurusan")
    private  String jurusan;

    @SerializedName("angkatan")
    private  String angkatan;

    @SerializedName("foto")
    private  String foto;

    /*@SerializedName("date")
    private String date;*/

    public DataSiswa(String nis, String nama, String kelas, String jurusan, String angkatan, String foto) {
        this.nis = nis;
        this.nama = nama;
        this.kelas = kelas;
        this.jurusan = jurusan;
        this.angkatan = angkatan;
        this.foto = foto;
    }


    public String getNis() {
        return nis;
    }
    public void setNis(String nis) {
        this.nis = nis;
    }

    public String getNama() { return nama; }
    public void setNama(String nama) {this.nama = nama; }

    public String getKelas() { return kelas; }
    public void setKelas(String kelas) {this.kelas = kelas; }

    public String getJurusan() { return jurusan; }
    public void setJurusan(String jurusan) {this.jurusan = jurusan; }

    public String getAngkatan() { return angkatan; }
    public void setAngkatan(String angkatan) {this.angkatan = angkatan; }

    public String getFoto() { return foto; }
    public void setFoto(String foto) {this.foto = foto; }

    /*public String getDate(){
        return date;
    }*/

}
