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

public class AddActivity extends AppCompatActivity {

  DBConfig config;
  SQLiteDatabase db;
  Cursor cursor;

  Intent intentDisplay;
  EditText edtKeyword, edtJudul, edtDeskripsi, edtBahan, edtCara;
  Button btnUpload, btnReset;
  ImageView linkBack;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_add);

    config = new DBConfig(this);

    edtKeyword = findViewById(R.id.edt_keyword);
    edtJudul = findViewById(R.id.edt_judul);
    edtDeskripsi = findViewById(R.id.edt_deskripsi);
    edtBahan = findViewById(R.id.edt_bahan);
    edtCara = findViewById(R.id.edt_cara);

    btnReset = findViewById(R.id.btn_reset);
    btnReset.setOnClickListener(v -> {resetField();});

    linkBack = findViewById(R.id.img_back);
    linkBack.setOnClickListener(v -> {
      finish();
    });

    btnUpload = findViewById(R.id.btn_upload);
    btnUpload.setOnClickListener(v -> {

      if(edtKeyword.getText().toString().isEmpty() || edtJudul.getText().toString().isEmpty()
              || edtDeskripsi.getText().toString().isEmpty() || edtBahan.getText().toString().isEmpty() || edtCara.getText().toString().isEmpty() ){

        Toast.makeText(this, "Semua harus diisi!", Toast.LENGTH_SHORT).show();

      }else{

        db = config.getReadableDatabase();
        cursor = db.rawQuery("SELECT keyword FROM tbl_resep WHERE keyword = '" +
                edtKeyword.getText().toString() + "'",null);
        cursor.moveToFirst();

        if(cursor.getCount() > 0){
          Toast.makeText(this, "Keyword Sudah Ada!", Toast.LENGTH_SHORT).show();
        }else{

          db = config.getReadableDatabase();
          cursor = db.rawQuery("SELECT * FROM tbl_login",null);
          cursor.moveToFirst();

          String emailPenulis = cursor.getString(0);
          String penulis = cursor.getString(1);

          db = config.getWritableDatabase();
          db.execSQL("INSERT INTO tbl_resep(keyword, emailPenulis, penulis, judul, deskripsi, bahan, cara) VALUES('" +
                  edtKeyword.getText().toString() + "','" +
                  emailPenulis + "','" +
                  penulis + "','" +
                  edtJudul.getText().toString() + "','" +
                  edtDeskripsi.getText().toString() + "','" +
                  edtBahan.getText().toString() + "','" +
                  edtCara.getText().toString() + "')");

          intentDisplay = new Intent(AddActivity.this, MainActivity.class);
          intentDisplay.putExtra("notify", "Data berhasil diupload.");
          startActivity(intentDisplay);


        }
      }

    });


  }


  private void resetField()
  {
    edtKeyword.setText(null); edtJudul.setText(null); edtDeskripsi.setText(null); edtBahan.setText(null); edtCara.setText(null);
  }



}