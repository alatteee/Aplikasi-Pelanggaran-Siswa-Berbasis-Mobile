package com.azkhanazzala.pelanggaranapk.response;

import com.google.gson.annotations.SerializedName;

public class FotoSiswa {

    @SerializedName("imageData")
    private String fotoSiswa;

    public FotoSiswa(String fotoSiswa) {
        this.fotoSiswa = fotoSiswa;
    }

    public String getFotoSiswa() {
        return fotoSiswa;
    }
    public void setFotoSiswa(String fotoSiswa) {
        this.fotoSiswa = fotoSiswa;
    }
}
