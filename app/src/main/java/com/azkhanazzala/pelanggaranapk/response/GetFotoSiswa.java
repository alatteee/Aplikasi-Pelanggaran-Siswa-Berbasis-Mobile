package com.azkhanazzala.pelanggaranapk.response;

import com.google.gson.annotations.SerializedName;

public class GetFotoSiswa {

    @SerializedName("status")
    int status;
    @SerializedName("message")
    String message;
    @SerializedName("data")
    String dataFotoSiswa;

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

    public String getData() {
        return dataFotoSiswa;
    }
    public void setData(String fotoSiswa) {
        this.dataFotoSiswa = fotoSiswa;
    }
}
