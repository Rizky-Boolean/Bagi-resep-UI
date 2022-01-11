package com.katuliteam.bagiresep_ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;

public class SplashActivity extends AppCompatActivity {

  DBConfig config;
  SQLiteDatabase db;
  Cursor cursor;

  Intent intentDisplay;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_splash);

    config = new DBConfig(this);

    db = config.getReadableDatabase();
    cursor = db.rawQuery("SELECT * FROM tbl_login",null);
    cursor.moveToFirst();

    if(cursor.getCount() == 1) {
      intentDisplay = new Intent(SplashActivity.this, MainActivity.class);
    }else{
      intentDisplay = new Intent(SplashActivity.this, AuthActivity.class);
    }

    new Handler().postDelayed(() -> {
      startActivity(intentDisplay);
      finish();
    }, 2500);

  }
}