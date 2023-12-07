package com.azkhanazzala.pelanggaranapk.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetPelanggaran {
    @SerializedName("status")
    String status;

    @SerializedName("message")
    String message;

    @SerializedName("data")
    List<Pelanggaran> listDataPelanggaran;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Pelanggaran> getListDataPelanggaran() {
        return listDataPelanggaran;
    }

    public void setListDataPelanggaran(List<Pelanggaran> listDataPelanggaran) {
        this.listDataPelanggaran = listDataPelanggaran;
    }
}
