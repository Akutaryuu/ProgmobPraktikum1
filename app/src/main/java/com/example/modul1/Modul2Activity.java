package com.example.modul1;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Modul2Activity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modul2);

        TextView HNama = findViewById(R.id.hasil_nama);
        TextView HTelepon = findViewById(R.id.hasil_telepon);
        TextView HGender = findViewById(R.id.hasil_gender);
        TextView HUsia = findViewById(R.id.hasil_usia);
        TextView HAlasan = findViewById(R.id.hasil_alasan);
        TextView HHobi = findViewById(R.id.hasil_hobi);
        TextView HEmail = findViewById(R.id.hasil_email);
        Button menubutton = findViewById(R.id.menubtn);
        Button exitbutton = findViewById(R.id.exitbtn);



        Intent getanggota = getIntent();
        String nama = getanggota.getStringExtra("Nama");
        HNama.setText(nama);
        HUsia.setText(getanggota.getStringExtra("Umur"));
        HGender.setText(getanggota.getStringExtra("Jenis Kelamin"));
        HTelepon.setText(getanggota.getStringExtra("Nomor Telepon"));
        HEmail.setText(getanggota.getStringExtra("Email"));
        HHobi.setText(getanggota.getStringExtra("Hobi"));
        HAlasan.setText(getanggota.getStringExtra("Alasan"));

        // toast
        Toast.makeText(this,"Selamat datang " + getanggota.getStringExtra("Nama"), Toast.LENGTH_SHORT).show();


        // method kembali
        menubutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pindahActivity = new Intent(Modul2Activity.this, home.class);
                startActivity(pindahActivity);
            }
        });

        //method keluar
        exitbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishAffinity();
            }
        });
    }

}