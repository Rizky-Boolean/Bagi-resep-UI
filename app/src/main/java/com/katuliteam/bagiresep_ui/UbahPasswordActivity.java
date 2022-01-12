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

public class UbahPasswordActivity extends AppCompatActivity {

  DBConfig config;
  SQLiteDatabase db;
  Cursor cursor;

  ImageView linkBack;
  Intent intentDisplay;
  EditText edtPasswordLama, edtPasswordBaru, edtKonfirmasiPassword;
  Button btnGanti, btnReset;

  String email;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_ubah_password);

    config = new DBConfig(this);

    edtPasswordLama = findViewById(R.id.edt_password_lama);
    edtPasswordBaru = findViewById(R.id.edt_password_baru);
    edtKonfirmasiPassword = findViewById(R.id.edt_konfirmasi_password);

    Bundle extras = getIntent().getExtras();
    if( extras != null ){
      email = extras.getString("email");
    }else{
      startActivity(new Intent(UbahPasswordActivity.this, MainActivity.class));
      finish();
    }

    linkBack = findViewById(R.id.img_back);
    linkBack.setOnClickListener(v -> {
      finish();
    });

    btnReset = findViewById(R.id.btn_reset);
    btnReset.setOnClickListener(v -> {resetField();});

    btnGanti = findViewById(R.id.btn_ubah);
    btnGanti.setOnClickListener(v -> {

      if(edtPasswordLama.getText().toString().isEmpty() || edtPasswordBaru.getText().toString().isEmpty()
              || edtKonfirmasiPassword.getText().toString().isEmpty()){

        Toast.makeText(this, "Semua harus diisi!", Toast.LENGTH_SHORT).show();

      }else{

        String passwordLama = edtPasswordLama.getText().toString();
        String passwordBaru = edtPasswordBaru.getText().toString();
        String konfirmasiPassword = edtKonfirmasiPassword.getText().toString();

        if( !passwordBaru.equals(konfirmasiPassword) ){
          Toast.makeText(this, "Konfirmasi password salah!", Toast.LENGTH_SHORT).show();
        }else{
          db = config.getReadableDatabase();
          cursor = db.rawQuery("SELECT * FROM tbl_user WHERE email = '" + email + "' AND password = '" + passwordLama + "'" ,null);
          cursor.moveToFirst();

          if(cursor.getCount() < 1 || cursor.getCount() > 1){
            Toast.makeText(this, "Password lama salah!", Toast.LENGTH_SHORT).show();
          }else{

            db = config.getWritableDatabase();
            db.execSQL("UPDATE tbl_user SET password = '" + passwordBaru + "' WHERE email = '" + email + "'");

            intentDisplay = new Intent(UbahPasswordActivity.this, MainActivity.class);
            intentDisplay.putExtra("akun", "Password berhasil diganti.");
            startActivity(intentDisplay);
            finish();

          }
        }

      }

    });



  }

  private void resetField()
  {
    edtPasswordLama.setText(null); edtPasswordLama.setText(null); edtKonfirmasiPassword.setText(null);
  }


}