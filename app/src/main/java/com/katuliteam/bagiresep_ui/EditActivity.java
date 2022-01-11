package com.katuliteam.bagiresep_ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class EditActivity extends AppCompatActivity {

  DBConfig config;
  SQLiteDatabase db;
  Cursor cursor;

  Button btnUbah, btnBatal;
  Intent intentDisplay;
  EditText edtJudul, edtDeskripsi, edtBahan, edtCara;
  ImageView linkBack;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_edit);

    config = new DBConfig(this);

    edtJudul = findViewById(R.id.edt_judul);
    edtDeskripsi = findViewById(R.id.edt_deskripsi);
    edtBahan = findViewById(R.id.edt_bahan);
    edtCara = findViewById(R.id.edt_cara);

    linkBack = findViewById(R.id.img_back);
    linkBack.setOnClickListener(v -> {
      finish();
    });

    btnBatal = findViewById(R.id.btn_batal);
    btnBatal.setOnClickListener(v -> {
      finish();
    });

    btnUbah = findViewById(R.id.btn_ubah);
    Bundle extras = getIntent().getExtras();
    if( extras == null ){
      finish();
    }else{

      String keyword = extras.getString("keyword");

      db = config.getReadableDatabase();
      cursor = db.rawQuery("SELECT * FROM tbl_resep WHERE keyword = '" + keyword + "'",null);
      cursor.moveToFirst();

      edtJudul.setText(cursor.getString(3));
      edtDeskripsi.setText(cursor.getString(4));
      edtBahan.setText(cursor.getString(5));
      edtCara.setText(cursor.getString(6));

      btnUbah.setOnClickListener(v -> {

        if( edtJudul.getText().toString().isEmpty() || edtDeskripsi.getText().toString().isEmpty()
                || edtBahan.getText().toString().isEmpty() || edtCara.getText().toString().isEmpty() ){

          Toast.makeText(this, "Semua harus diisi!", Toast.LENGTH_SHORT).show();

        }else{

          db = config.getWritableDatabase();
          db.execSQL("UPDATE tbl_resep SET judul = '" + edtJudul.getText().toString()
                  + "', deskripsi = '" + edtDeskripsi.getText().toString()
                  + "', bahan = '" + edtBahan.getText().toString()
                  + "', cara = '" + edtCara.getText().toString()
                  + "' WHERE keyword = '" + keyword + "'");

          intentDisplay = new Intent(getApplicationContext(), MainActivity.class);
          intentDisplay.putExtra("notify", "Data berhasil diubah.");
          startActivity(intentDisplay);
          finish();

        }

      });

    }



  }
}