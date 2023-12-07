package com.azkhanazzala.pelanggaranapk.response;

import com.google.gson.annotations.SerializedName;

public class Login {
    @SerializedName("id_user")
    private String idUser;

    @SerializedName("username")
    private String username;

    @SerializedName("password")
    private String password;

    @SerializedName("email")
    private String email;

    public Login(
            String idUser,
            String username,
            String password,
            String email
    ) {
        this.idUser = idUser;
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public String getIdUser() { return idUser; }
    public void setIdUser(String idUser) {this.idUser = idUser; }

    public String getUsername() { return username; }
    public void setUsername(String username) {this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) {this.password = password; }

    public String getEmail() { return email; }
    public void setEmail(String email) {this.email = email; }
}
