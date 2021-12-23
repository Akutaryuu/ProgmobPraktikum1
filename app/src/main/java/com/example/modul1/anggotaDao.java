package com.example.modul1;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Query;
import androidx.room.Update;
import androidx.room.Insert;
import java.util.List;

@Dao
public interface anggotaDao {

    @Query("SELECT * FROM anggotaEntity")
    List<anggotaEntity> getAll();

    @Query("INSERT INTO anggotaEntity (Nama,NomorTelepon,Email,AlasanBergabung,JenisKelamin,Hobi,Usia) VALUES(:Nama,:NomorTelepon,:Email,:Alasan,:JenisKelamin,:Hobi,:Usia)")
    void insertAll(String Nama, Integer NomorTelepon, String Email, String Alasan, String JenisKelamin, String Hobi, Integer Usia);

    //edit
    @Query("UPDATE anggotaEntity SET Nama=:Nama,  NomorTelepon=:NomorTelepon,Email=:Email,AlasanBergabung=:Alasan, JenisKelamin=:JenisKelamin, Hobi=:Hobi, Usia=:Usia WHERE idAnggota=:idAnggota")
    void update(int idAnggota, String Nama, Integer NomorTelepon, String Email, String Alasan, String JenisKelamin, String Hobi, Integer Usia);

    @Query("SELECT * FROM anggotaEntity WHERE idAnggota=:idAnggota")
    anggotaEntity get(int idAnggota);

    @Delete
    void delete(anggotaEntity anggotaEntity);
}
