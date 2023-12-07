package com.azkhanazzala.pelanggaranapk;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.azkhanazzala.pelanggaranapk.response.GetPelanggaran;
import com.azkhanazzala.pelanggaranapk.response.Pelanggaran;
import com.azkhanazzala.pelanggaranapk.rest.ApiClient;
import com.azkhanazzala.pelanggaranapk.rest.ApiInterface;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class HistoryActivity extends AppCompatActivity {
    private RecyclerView rvData;
    private RecyclerView.Adapter adData;
    private RecyclerView.LayoutManager lmData;
    private List<Pelanggaran> listData = new ArrayList<>();

    private ProgressBar pbData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        rvData = findViewById(R.id.rvPelanggaran);
        pbData = findViewById(R.id.pbData);

        lmData = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rvData.setLayoutManager(lmData);

    }

    @Override
    protected void onResume() {
        super.onResume();
        retrieveData();
    }

    public void retrieveData() {
        pbData.setVisibility(View.VISIBLE);

        ApiInterface ardData = ApiClient.getClient().create(ApiInterface.class);
        Call<GetPelanggaran> tampilData = ardData.ardRetrieveData();

        tampilData.enqueue(new Callback<GetPelanggaran>() {
            @Override
            public void onResponse(Call<GetPelanggaran> call, Response<GetPelanggaran> response) {
                String kode = response.body().getStatus();
                String pesan = response.body().getMessage();

                Toast.makeText(HistoryActivity.this, "Kode : " + kode + " | Pesan : " + pesan, Toast.LENGTH_LONG).show();

                listData = response.body().getListDataPelanggaran();
                adData = new PelanggarAdapter(HistoryActivity.this, listData);
                rvData.setAdapter(adData);
                adData.notifyDataSetChanged();

                pbData.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onFailure(Call<GetPelanggaran> call, Throwable t) {
                Toast.makeText(HistoryActivity.this, "Gagal Konek Ke Server : " + t.getMessage(), Toast.LENGTH_LONG).show();

                pbData.setVisibility(View.INVISIBLE);
            }
        });
    }

    public void onBackPressed() {
        Intent list_act = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(list_act);
        finish();
    }
}