package com.azkhanazzala.pelanggaranapk.rest;

import com.azkhanazzala.pelanggaranapk.response.GetFotoSiswa;
import com.azkhanazzala.pelanggaranapk.response.GetLogin;
import com.azkhanazzala.pelanggaranapk.response.GetDataSiswa;
import com.azkhanazzala.pelanggaranapk.response.GetPelanggaran;
import com.azkhanazzala.pelanggaranapk.response.InputPelanggaran;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiInterface {


    @FormUrlEncoded
    @POST("inputpelanggaran.php")
    Call<GetLogin> login(
            @Query("function") String function,
            @Field("email") String email,
            @Field("password") String password
    );

    @GET("inputpelanggaran.php")
    Call<GetDataSiswa> getDataSiswaByName(
            @Query("function") String function,
            @Query("nama_siswa") String name
    );

    @GET("inputpelanggaran.php")
    Call<GetFotoSiswa> getFotoSiswa(
            @Query("function") String function
    );


    @FormUrlEncoded
    @POST("inputpelanggaran.php")
    Call<InputPelanggaran> inputPelanggaran(
            @Query("function") String function,
            @Field("nis") String nis,
            @Field("jenis_pelanggaran") String jenisPelanggaran,
            @Field("keterangan") String keterangan,
            @Field("tanggal") String tanggal,
            @Field("id_user") String idUser
    );

    @GET("retrieve.php")
    Call<GetPelanggaran> ardRetrieveData();

    @FormUrlEncoded
    @POST("get.php")
    Call<GetPelanggaran> ardGetData(
            @Field("id") int id
    );

    @FormUrlEncoded
    @POST("delete.php")
    Call<GetPelanggaran> ardDeleteData(
            @Field("id") int id
    );
}
