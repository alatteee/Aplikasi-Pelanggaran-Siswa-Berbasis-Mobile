package com.azkhanazzala.pelanggaranapk;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.azkhanazzala.pelanggaranapk.response.GetDataSiswa;
import com.azkhanazzala.pelanggaranapk.response.GetFotoSiswa;
import com.azkhanazzala.pelanggaranapk.response.InputPelanggaran;
import com.azkhanazzala.pelanggaranapk.rest.ApiClient;
import com.azkhanazzala.pelanggaranapk.rest.ApiInterface;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InputActivity extends AppCompatActivity {


    String selectedItem;
    ImageView fotosiswa;
    Spinner jenispelanggaran;
    Button submit;
    EditText keterangan, searchNama;
    TextView nis, nama, kelas, jurusan, angkatan;
    ApiInterface mApiInterface;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);

        sharedPreferences = getSharedPreferences("MyPreferences", Context.MODE_PRIVATE);

        mApiInterface = ApiClient.getClient().create(ApiInterface.class);

        setUICallback();
        handleSendData();
    }


    public void setUICallback() {
        searchNama = findViewById(R.id.searchNama);
        searchNama.setOnEditorActionListener(new EditText.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    handleActionSearch(searchNama.getText().toString());
                    return true;
                }
                return false;
            }
        });
    }


    public void handleActionSearch(String dataNama) {
        Log.e("Nama", dataNama);
        handleGetFotoSiswa();

        nis = findViewById(R.id.txtNis);
        nama = findViewById(R.id.txtNama);
        kelas = findViewById(R.id.txtKelas);
        jurusan = findViewById(R.id.txtJurusan);
        angkatan = findViewById(R.id.txtAngkatan);

        keterangan = findViewById(R.id.Etketerangan);

        submit = findViewById(R.id.submitButton);

        try {
            Call<GetDataSiswa> dataSiswa = mApiInterface.getDataSiswaByName("get_siswa_by_name", dataNama);
            dataSiswa.enqueue(new Callback<GetDataSiswa>() {
                @Override
                public void onResponse(Call<GetDataSiswa> call, Response<GetDataSiswa> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        hideSoftKeyboard(searchNama);
                        handleGetFotoSiswa();
                        handleSpinner();

                        nis.setText(response.body().getData().getNis());
                        nama.setText(response.body().getData().getNama());
                        kelas.setText(response.body().getData().getKelas());
                        jurusan.setText(response.body().getData().getJurusan());
                        angkatan.setText(response.body().getData().getAngkatan());
                    } else {
                        Log.e("Get Data Siswa " + response.code(), "Response body is null or request not successful.");
                    }
                }

                @Override
                public void onFailure(Call<GetDataSiswa> call, Throwable t) {
                    Log.e("Failure Get Data Siswa", t.toString());
                }
            });
        } catch (Exception e) {
            Log.e("Error Get Data Siswa", e.toString());
        }

    }

    private void handleGetFotoSiswa() {
        fotosiswa = findViewById(R.id.fotoSiswa);

        try {
            Call<GetFotoSiswa> fotoSiswa = mApiInterface.getFotoSiswa("get_siswa_image");
            fotoSiswa.enqueue(new Callback<GetFotoSiswa>() {

                @Override
                public void onResponse(Call<GetFotoSiswa> call, Response<GetFotoSiswa> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        Log.d("Get Data " + response.code(), response.body().getData().toString());
                        String imageDataBase64 = response.body().getData();
                        byte[] imageDataBytes = Base64.decode(imageDataBase64, Base64.DEFAULT);
                        Bitmap decodedImage = BitmapFactory.decodeByteArray(imageDataBytes, 0, imageDataBytes.length);
                        fotosiswa.setImageBitmap(decodedImage);
                    } else {
                        Log.e("Get Data Image " + response.code(), "Response body is null or request not successful.");
                    }
                }

                @Override
                public void onFailure(Call<GetFotoSiswa> call, Throwable t) {
                    Log.e("Failure Get Image", t.toString());
                }
            });
        } catch (Exception e) {
            Log.e("Error Get Image", e.toString());
        }
    }

    private void handleSpinner() {
        jenispelanggaran = findViewById(R.id.jenisPelanggaran);
        ArrayList<String> itemPelanggaran = new ArrayList<>();
        itemPelanggaran.add("Tidak memakai atribut sekolah");
        itemPelanggaran.add("Bolos");
        itemPelanggaran.add("Merokok di lingkungan sekolah");
        itemPelanggaran.add("Merusak Fasilitas sekolah");
        itemPelanggaran.add("Tawuran");
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, itemPelanggaran);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        jenispelanggaran.setAdapter(adapter);
        jenispelanggaran.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedItem = (String) jenispelanggaran.getItemAtPosition(position);
                Log.e("Selected spinner item", selectedItem);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void handleSendData() {

        keterangan = findViewById(R.id.Etketerangan);
        submit = findViewById(R.id.submitButton);


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
                String tanggalSubmit = sdf.format(new Date());
                String dataKeterangan = keterangan.getText().toString();
                String idUser = sharedPreferences.getString("idUser", "1");

                Log.e("tanggal dan keterangan", tanggalSubmit.toString() + " " + dataKeterangan);

                try {
                    Call<InputPelanggaran> inputPelanggaran = mApiInterface.inputPelanggaran(
                             "insert_pelanggaran",
                            nis.getText().toString(),
                            selectedItem,
                            dataKeterangan,
                            tanggalSubmit.toString(),
                            idUser
                    );
                    inputPelanggaran.enqueue(new Callback<InputPelanggaran>() {
                        @Override
                        public void onResponse(Call<InputPelanggaran> call, Response<InputPelanggaran> response) {
                            if (response.isSuccessful()) {
                                handleToast(response.body().getMessage());
                                finish();
                            } else {
                                handleToast(response.code() + " " + response.body().getMessage());
                            }
                        }

                        @Override
                        public void onFailure(Call<InputPelanggaran> call, Throwable t) {
                            Log.e("Retrofit Post Insert", t.toString());
                        }
                    });
                } catch (Exception e) {
                    Log.e("Error insert", e.toString());
                }

          }
        });

    }

    private void hideSoftKeyboard(EditText editText) {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
    }

    private void handleToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

}