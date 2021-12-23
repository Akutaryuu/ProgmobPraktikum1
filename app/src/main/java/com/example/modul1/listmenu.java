package com.example.modul1;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class listmenu extends AppCompatActivity {
    private RecyclerView recyclerView;
    private com.example.modul1.anggotaAdapter AnggotaAdapter;
    private AppDatabase database;
    private Button savebtn, backbtn;
    private List<anggotaEntity> list = new ArrayList<>();
    private AlertDialog.Builder dialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listmenu);
        recyclerView = findViewById(R.id.recycler_view);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        database = AppDatabase.getInstance(getApplicationContext());
        list.clear();
        list.addAll(database.anggotaDao().getAll());
        AnggotaAdapter = new anggotaAdapter(getApplicationContext(), list);
        //popupcrud
        AnggotaAdapter.setDialog(new anggotaAdapter.Dialog() {
            @Override
            public void onClick(int position) {
                //option button
                final CharSequence[] dialogItem = {"Edit", "Hapus"};

                dialog = new AlertDialog.Builder(com.example.modul1.listmenu.this);
                dialog.setItems(dialogItem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which){
                            case 0:
                                //melakukan update/edit
                                Intent intent = new Intent( listmenu.this, MainActivity.class);
                                //menambhkan data dari listanggota
                                intent.putExtra("idAnggota", list.get(position).idAnggota);
                                startActivity(intent);
                                break;

                            //melakukan delete
                            case 1:
                                anggotaEntity anggotaEntity = list.get(position);
                                database.anggotaDao().delete(anggotaEntity);
                                //refresh
                                onStart();
                                break;
                        }
                    }
                });
                dialog.show();
            }
        });

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(), RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(AnggotaAdapter);

        savebtn = findViewById(R.id.btnsimpan);
        backbtn = findViewById(R.id.btnexit);

        savebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Data Telah Disimpan " , Toast.LENGTH_SHORT).show();
                Intent pindahAct = new Intent(listmenu.this, home.class);
                startActivity(pindahAct);
            }
        });

        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishAffinity();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        list.clear();
        list.addAll(database.anggotaDao().getAll());
        AnggotaAdapter.notifyDataSetChanged();
    }
}
