package com.example.modul1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class home extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        TextView addlist = findViewById(R.id.addanggota);
        TextView ceklist = findViewById(R.id.listanggota);

        addlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pindahactivity = new Intent(home.this, MainActivity.class);
                startActivity(pindahactivity);
            }
        });

        ceklist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pindahAct = new Intent(home.this, listmenu.class);
                startActivity(pindahAct);
            }
        });
        Button quitbutton = findViewById(R.id.quitbtn);
        //method keluar
        quitbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishAffinity();
            }
        });
    }

}
