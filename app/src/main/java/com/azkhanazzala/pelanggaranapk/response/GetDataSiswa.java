package com.azkhanazzala.pelanggaranapk.response;

import com.google.gson.annotations.SerializedName;

public class GetDataSiswa {
    @SerializedName("status")
    int status;
    @SerializedName("message")
    String message;
    @SerializedName("data")
    DataSiswa dataSiswa;

    //List<Pelanggaran> ListDataPelanggaran;

    public int getStatus() {
        return status;
    }
    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }

    public DataSiswa getData() {
        return dataSiswa;
    }
    public void setData(DataSiswa pelanggaran) {
        this.dataSiswa = pelanggaran;
    }

    /*public List<Pelanggaran> getListDataPelanggaran(){
        return ListDataPelanggaran;
    }*/

}
