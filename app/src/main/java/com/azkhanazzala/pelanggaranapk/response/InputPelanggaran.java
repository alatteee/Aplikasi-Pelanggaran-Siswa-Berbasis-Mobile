package com.azkhanazzala.pelanggaranapk.response;

import com.google.gson.annotations.SerializedName;

public class InputPelanggaran {
    @SerializedName("status")
    int status;
    @SerializedName("message")
    String message;

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
}
