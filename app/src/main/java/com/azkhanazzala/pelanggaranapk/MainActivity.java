package com.azkhanazzala.pelanggaranapk;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    CardView add, history;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CardView add = (CardView) findViewById(R.id.CVaddpelanggar);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ad = new Intent(MainActivity.this, InputActivity.class);
                startActivity(ad);
            }
        });

        CardView history = (CardView) findViewById(R.id.CVHistoryPelanggar);
        history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent hi = new Intent(MainActivity.this, HistoryActivity.class);
                startActivity(hi);
            }
        });
    }
}