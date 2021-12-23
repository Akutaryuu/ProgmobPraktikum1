package com.example.modul1;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Objects;

import com.example.modul1.AppDatabase;
import com.example.modul1.anggotaEntity;

public class MainActivity extends AppCompatActivity {

    private EditText EditNama, EditEmail, EditNotelp, EditAlasan;
    private Button BtnSave;
    private AppDatabase database;
    private RadioGroup EditGender;
    private RadioButton radiobtnchoose;
    private Integer usia;
    private RadioButton choosenradiobtn;
    Boolean stat=false;
    Boolean statRadio=false;
    Boolean statCheckBox=false;
    private RadioButton rbPria;
    private RadioButton rbWanita;
    String checkNama, checkEmail, chekNotelp, checkAlasan;
    private CheckBox cbEsport, cbOlahraga, cbTrading,cbMakan,cbTraveling,cbTidur;
    private int idAnggota = 0;
    private boolean isEdit = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditNama = findViewById(R.id.editTextPersonName);
        EditNotelp = findViewById(R.id.editTextNumber);
        EditEmail = findViewById(R.id.editTextEmailAddress);
        EditAlasan = findViewById(R.id.editTextAlasan);
        EditGender = findViewById(R.id.radioGroupGender);
        BtnSave = findViewById(R.id.buttonSubmit);

        //buat validasi
        rbPria = findViewById(R.id.radioButtonMale);
        rbWanita = findViewById(R.id.radioButtonFemale);
        cbOlahraga = findViewById(R.id.checkBoxOlahraga);
        cbEsport = findViewById(R.id.checkBoxEsport);
        cbTraveling = findViewById(R.id.checkBoxTraveling);
        cbMakan = findViewById(R.id.checkBoxMakan);
        cbTrading = findViewById(R.id.checkBoxTrading);
        cbTidur = findViewById(R.id.checkBoxTidur);

        database = AppDatabase.getInstance(getApplicationContext());

        Intent intent = getIntent();
        idAnggota = intent.getIntExtra("idAnggota",0);
        if (idAnggota>0){
            isEdit = true;
            choosenradiobtn = findViewById(EditGender.getCheckedRadioButtonId());

            //melakukan edit dengan idunik
            anggotaEntity anggotaEntity = database.anggotaDao().get(idAnggota);
            EditNama.setText(anggotaEntity.fullname);
            EditNotelp.setText("" + anggotaEntity.notelp);
            EditEmail.setText(anggotaEntity.email);
            EditAlasan.setText(anggotaEntity.alasan);
            //choosenradiobtn.setText(anggotaEntity.jeniskelamin);

        }else {
            isEdit = false;
        }

        BtnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                radiobtnchoose = findViewById(EditGender.getCheckedRadioButtonId());

                checkNama = EditNama.getText().toString();
                chekNotelp = EditNotelp.getText().toString();
                checkEmail = EditEmail.getText().toString();
                checkAlasan = EditAlasan.getText().toString();
//                    TextView checkAngkel = (TextView) findViewById(R.id.jumlahAngkel);
                //VALIDASI KOSONG//
                if(EditNama.getText().toString().length()==0){
                    EditNama.setError("Nama Wajib Diisi");
                }else if (EditNotelp.getText().toString().length()==0){
                    EditNotelp.setError("Nomor Telepon Wajib Diisi");
                }else if (EditEmail.getText().toString().length()==0){
                    EditEmail.setError("Email Wajib Diisi");
                }else if (EditAlasan.getText().toString().length()==0) {
                    EditAlasan.setError("Alasan Wajib Diisi");
                }else {
                    stat = true;
                }
                if (rbPria.isChecked() || rbWanita.isChecked()){
                    statRadio = true;
                }else {
                    statRadio = false;
                    Toast.makeText(getApplicationContext(), "Gender Wajib Diisi", Toast.LENGTH_SHORT).show();
                }
                if (cbOlahraga.isChecked() || cbEsport.isChecked() || cbTraveling.isChecked() || cbTrading.isChecked()||cbMakan.isChecked() || cbTidur.isChecked()){
                    statCheckBox = true;
                }else{
                    statCheckBox = false;
                    Toast.makeText(getApplicationContext(), "Pilihan Hobi Wajib Diisi",Toast.LENGTH_SHORT).show();
                }
                if (stat==true&&statRadio==true&statCheckBox==true){
                    showDialog();
                    stat=false;
                }
            }
        });
        Objects.requireNonNull(getSupportActionBar()).setTitle("Pendaftaran Klub Anime");

        setupSeekBar();
        }

    private void showDialog(){
        EditText editTextName = (EditText) findViewById(R.id.editTextPersonName);
        EditText editTextNumber = (EditText) findViewById(R.id.editTextNumber);
        EditText editTextEmail = (EditText) findViewById(R.id.editTextEmailAddress);
        EditText editTextAlasan = (EditText) findViewById(R.id.editTextAlasan);
        RadioGroup radioGroupGenders = (RadioGroup) findViewById(R.id.radioGroupGender);
        RadioButton radioButtonChosen = (RadioButton) findViewById(radioGroupGenders.getCheckedRadioButtonId());
        TextView Usia = (TextView) findViewById(R.id.usia);

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        dialogBuilder.setTitle("Konfirmasi Data");

        dialogBuilder.setMessage("Apakah anda sudah yakin dengan data berikut?\n\n" +
                "Nama: " + editTextName.getText() + "\n" +
                "No. Telepon: " + editTextNumber.getText() + "\n" +
                "Jenis Kelamin: " + radioButtonChosen.getText() + "\n" +
                "Umur : " + Usia.getText() + "\n" +
                "Email: " + editTextEmail.getText() + "\n" +
                "Alasan: " + editTextAlasan.getText() + "\n" +
                "Hobi: " + getCheckedBoxValue() + "\n");

        dialogBuilder.setPositiveButton("Ya", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Intent intent = new Intent(MainActivity.this, Modul2Activity.class);
                        Toast.makeText(getApplicationContext(), "Data diterima!", Toast.LENGTH_SHORT).show();
                        intent.putExtra("Nama",editTextName.getText().toString());
                        intent.putExtra("Nomor Telepon", editTextNumber.getText().toString());
                        intent.putExtra("Umur", Usia.getText().toString());
                        intent.putExtra("Jenis Kelamin", radioButtonChosen.getText().toString());
                        intent.putExtra("Hobi", getCheckedBoxValue());
                        intent.putExtra("Email", editTextEmail.getText().toString());
                        intent.putExtra("Alasan", editTextAlasan.getText().toString());

                        if (isEdit){
                            database.anggotaDao().update(idAnggota, EditNama.getText().toString(), Integer.parseInt(EditNotelp.getText().toString()), EditEmail.getText().toString(), EditAlasan.getText().toString() , radiobtnchoose.getText().toString(),getCheckedBoxValue(),usia);
                        }else {
                            database.anggotaDao().insertAll(EditNama.getText().toString(), Integer.parseInt(EditNotelp.getText().toString()), EditEmail.getText().toString(), EditAlasan.getText().toString(), radiobtnchoose.getText().toString(),getCheckedBoxValue(),usia);
                        }


                        startActivity(intent);
                        dialog.cancel();
                    }
                });
        dialogBuilder.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

        AlertDialog confirmDialog = dialogBuilder.create();

        confirmDialog.show();
    }

    private String getCheckedBoxValue() {
        CheckBox checkBoxOlahraga = (CheckBox) findViewById(R.id.checkBoxOlahraga);
        CheckBox checkBoxEsport = (CheckBox) findViewById(R.id.checkBoxEsport);
        CheckBox checkBoxMakan = (CheckBox) findViewById(R.id.checkBoxMakan);
        CheckBox checkBoxTrading = (CheckBox) findViewById(R.id.checkBoxTrading);
        CheckBox checkBoxTraveling = (CheckBox) findViewById(R.id.checkBoxTraveling);
        CheckBox checkBoxTidur = (CheckBox) findViewById(R.id.checkBoxTidur);
        String checkedValue = "";

        if (checkBoxOlahraga.isChecked()) {
            checkedValue += checkBoxOlahraga.getText() + " ";
        }
        if (checkBoxEsport.isChecked()) {
            checkedValue += checkBoxEsport.getText() + " ";
        }
        if (checkBoxMakan.isChecked()) {
            checkedValue += checkBoxMakan.getText() + " ";
        }
        if (checkBoxTrading.isChecked()) {
            checkedValue += checkBoxTrading.getText() + " ";
        }
        if (checkBoxTraveling.isChecked()) {
            checkedValue += checkBoxTraveling.getText() + " ";
        }
        if (checkBoxTidur.isChecked()) {
            checkedValue += checkBoxTidur.getText() + " ";
        }

        return checkedValue;
    }

    private void setupSeekBar() {
        int MIN = 1;
        int MAX = 30;
        int STEP = 1;

        SeekBar seekBarUsia = (SeekBar) findViewById(R.id.seekBarUsia);
        TextView textViewUsiaValue = (TextView) findViewById(R.id.usia);

        seekBarUsia.setMax((MAX - MIN) / STEP);

        seekBarUsia.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
                textViewUsiaValue.setText(String.format(progress + " Tahun"));
                usia = progress;;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }}