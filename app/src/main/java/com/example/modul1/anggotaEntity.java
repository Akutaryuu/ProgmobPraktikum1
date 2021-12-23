package com.example.modul1;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class anggotaEntity {
    @PrimaryKey
    public int idAnggota;

    @ColumnInfo(name = "Nama")
    public String fullname;

    @ColumnInfo(name = "NomorTelepon")
    public int notelp;

    @ColumnInfo(name = "Email")
    public String email;

    @ColumnInfo(name = "AlasanBergabung")
    public String alasan;

    @ColumnInfo(name = "JenisKelamin")
    public String jeniskelamin;

    @ColumnInfo(name = "Hobi")
    public String hobi;

    @ColumnInfo(name = "Usia")
    public int usia;

}
