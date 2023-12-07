package com.azkhanazzala.pelanggaranapk.response;

import com.google.gson.annotations.SerializedName;

public class GetLogin {

    @SerializedName("status")
    boolean status;
    @SerializedName("message")
    String message;
    @SerializedName("data")
    Login dataLogin;

    public boolean getStatus() {return status;}
    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }

    public Login getData() {
        return dataLogin;
    }
    public void setData(Login loginUser) {this.dataLogin = loginUser;}

}
