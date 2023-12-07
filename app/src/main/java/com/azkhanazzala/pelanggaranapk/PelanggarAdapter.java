package com.azkhanazzala.pelanggaranapk;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.azkhanazzala.pelanggaranapk.response.DataSiswa;
import com.azkhanazzala.pelanggaranapk.response.GetPelanggaran;
import com.azkhanazzala.pelanggaranapk.response.Pelanggaran;
import com.azkhanazzala.pelanggaranapk.rest.ApiClient;
import com.azkhanazzala.pelanggaranapk.rest.ApiInterface;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PelanggarAdapter extends RecyclerView.Adapter<PelanggarAdapter.HolderData> {

    private Context ctx;
    private List<Pelanggaran> listData;
    private int idPelanggaran;

    public PelanggarAdapter(Context ctx, List<Pelanggaran> listData) {
        this.ctx = ctx;
        this.listData = listData;
    }

    @NonNull
    @Override
    public PelanggarAdapter.HolderData onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.pelanggaran_item, parent, false);
        HolderData holder = new HolderData(layout);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull PelanggarAdapter.HolderData holder, int position) {
        Pelanggaran dm = listData.get(position);

        holder.tvNama.setText(dm.getNama());
        holder.tvJenisPelanggaran.setText(dm.getJenis_pelanggaran());
        holder.tvKeterangan.setText(dm.getKeterangan());
        holder.tvTanggal.setText(dm.getTanggal());
        holder.tvId.setText(String.valueOf(dm.getId()));
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    public class HolderData extends RecyclerView.ViewHolder {
        TextView tvId, tvNama, tvJenisPelanggaran, tvKeterangan, tvTanggal;
        public HolderData(@NonNull View itemView) {
            super(itemView);

            tvNama = itemView.findViewById(R.id.tv_nama);
            tvJenisPelanggaran = itemView.findViewById(R.id.tv_jenisPelanggaran);
            tvKeterangan = itemView.findViewById(R.id.tv_keterangan);
            tvTanggal = itemView.findViewById(R.id.tv_tanggal);
            tvId = itemView.findViewById(R.id.tvId);


    }
}
}

