package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText Nim, Nama, TglLahir, Alamat;
    private RadioButton Male, Female;
    private Spinner Jurusan;

    DBMahasiswa dbMahasiswa = new DBMahasiswa(this);

    //variabel untuk menyimpan data dari user//
    private String setNim,setNama,setTglLahir,setAlamat,setGender,setJurusan;

    boolean keluar = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Nim = findViewById(R.id.nim);
        Nama = findViewById(R.id.nama);
        TglLahir = findViewById(R.id.date);
        Alamat = findViewById(R.id.alamat);
        Male = findViewById(R.id.male);
        Female = findViewById(R.id.female);
        Jurusan = findViewById(R.id.jurusan);
        Button Simpan = findViewById(R.id.save);
        Button Lihat = findViewById(R.id.readData);

        DBMahasiswa dbMahasiswa = new DBMahasiswa(getBaseContext());

        Simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setData();
                saveData();
                Toast.makeText(getApplicationContext(),"Data telah disimpan",Toast.LENGTH_SHORT).show();
                clearData();
            }
        });

        Lihat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,ViewData.class));
            }
        });
    }

    private void setData() {
        setNim = Nim.getText().toString();
        setNama = Nama.getText().toString();
        setAlamat = Alamat.getText().toString();
        setTglLahir = TglLahir.getText().toString();
        setJurusan = Jurusan.getSelectedItem().toString();
            if(Male.isChecked()) {
                setGender = Male.getText().toString();
            } else if(Female.isChecked()) {
                setGender = Female.getText().toString();
            }
    }

    private void saveData() {
        //Mendapatkan Repository dengan Mode Menulis
        SQLiteDatabase create = dbMahasiswa.getWritableDatabase();

        //Membuat Map Baru, Yang Berisi Nama Kolom dan Data Yang Ingin Dimasukan
        ContentValues values = new ContentValues();
        values.put(DBMahasiswa.MyColumns.NIM, setNim);
        values.put(DBMahasiswa.MyColumns.Nama, setNama);
        values.put(DBMahasiswa.MyColumns.Jurusan, setJurusan);
        values.put(DBMahasiswa.MyColumns.JenisKelamin, setGender);
        values.put(DBMahasiswa.MyColumns.TanggalLahir, setTglLahir);
        values.put(DBMahasiswa.MyColumns.Alamat, setAlamat);

        //Menambahkan Baris Baru, Berupa Data Yang Sudah Diinputkan pada Kolom didalam Database
        create.insert(DBMahasiswa.MyColumns.NamaTabel, null, values);
    }

    private void clearData() {
        Nim.setText("");
        Nama.setText("");
        TglLahir.setText("");
        Alamat.setText("");
    }

    @Override
    public void onBackPressed() {
        if (keluar) {
            super.onBackPressed();
            return;
        }

        this.keluar = true;
        Toast.makeText(this, "tekan lagi untuk keluar", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                keluar=false;
            }
        }, 2000);
    }
}
