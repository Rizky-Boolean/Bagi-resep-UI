package com.katuliteam.bagiresep_ui;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

  DBConfig config;
  SQLiteDatabase db;
  Cursor cursor;

  TextView txtJudul, txtPenulis, txtDeskripsi, txtBahan, txtMasak;
  String judul, penulis, deskripsi, bahan, caraMasak;
  ImageView linkBack;


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_detail);

    config = new DBConfig(this);

    linkBack = findViewById(R.id.img_back);
    linkBack.setOnClickListener(v -> {
      finish();
    });

    txtJudul = findViewById(R.id.txt_judul);
    txtPenulis = findViewById(R.id.txt_penulis);
    txtDeskripsi = findViewById(R.id.txt_deskripsi);
    txtBahan = findViewById(R.id.txt_bahan);
    txtMasak = findViewById(R.id.txt_cara);

    judul = "Judul tidak diset!";
    penulis = "Penulis tidak diset!";
    deskripsi = "Deskripsi tidak diset!";
    bahan = "Bahan tidak diset!";
    caraMasak = "Cara masak tidak diset!";

    Bundle extras = getIntent().getExtras();
    if( extras != null ){

      db = config.getReadableDatabase();
      cursor = db.rawQuery("SELECT * FROM tbl_resep WHERE keyword = '" + extras.getString("keyword") + "'",null);
      cursor.moveToFirst();

      judul = cursor.getString(3);
      penulis = cursor.getString(2);
      deskripsi = cursor.getString(4);
      bahan = cursor.getString(5);
      caraMasak = cursor.getString(6);

    }else{
      finish();
    }

    txtJudul.setText(judul);
    txtPenulis.setText("Penulis : " + penulis);
    txtDeskripsi.setText(deskripsi);
    txtBahan.setText(bahan);
    txtMasak.setText(caraMasak);


  }
}