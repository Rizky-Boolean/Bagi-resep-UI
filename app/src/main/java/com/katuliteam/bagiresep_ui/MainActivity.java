package com.katuliteam.bagiresep_ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

  Intent intentDisplay;
  ImageView menu_home, menu_upload, menuAdd, menu_profile, menu_about;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);


    menu_profile = findViewById(R.id.menu_profile);
    menu_home = findViewById(R.id.menu_home);
    menu_upload = findViewById(R.id.menu_upload);
    menu_about = findViewById(R.id.menu_about);
    menuAdd = findViewById(R.id.menu_add);

    getSupportFragmentManager().beginTransaction().replace(R.id.frg_hasil, new HomeFragment()).commit();

    Bundle extras = getIntent().getExtras();
    if( extras != null && extras.getString("notify") != null ){
      Toast.makeText(this, extras.getString("notify"), Toast.LENGTH_SHORT).show();
      setNonActiveMenu();
      menu_upload.setImageResource(R.drawable.ic_uploadt);
      getSupportFragmentManager().beginTransaction().replace(R.id.frg_hasil, new MyResepFragment()).commit();
    }
    if( extras != null && extras.getString("akun") != null ){
      Toast.makeText(this, extras.getString("akun"), Toast.LENGTH_SHORT).show();
      setNonActiveMenu();
      menu_upload.setImageResource(R.drawable.ic_profilt);
      getSupportFragmentManager().beginTransaction().replace(R.id.frg_hasil, new ProfileFragment()).commit();
    }


    menu_home.setOnClickListener(v -> {
      setNonActiveMenu();;
      menu_home.setImageResource(R.drawable.ic_homet);
      getSupportFragmentManager().beginTransaction().replace(R.id.frg_hasil, new HomeFragment()).commit();
    });

    menu_upload.setOnClickListener(v -> {
      setNonActiveMenu();
      menu_upload.setImageResource(R.drawable.ic_uploadt);
      getSupportFragmentManager().beginTransaction().replace(R.id.frg_hasil, new MyResepFragment()).commit();
    });

    menu_profile.setOnClickListener(v -> {
      setNonActiveMenu();
      menu_profile.setImageResource(R.drawable.ic_profilt);
      getSupportFragmentManager().beginTransaction().replace(R.id.frg_hasil, new ProfileFragment()).commit();
    });

    menu_about.setOnClickListener(v -> {
      setNonActiveMenu();
      menu_about.setImageResource(R.drawable.ic_aboutt);
      getSupportFragmentManager().beginTransaction().replace(R.id.frg_hasil, new AboutFragment()).commit();
    });

    menuAdd.setOnClickListener(v -> {
      intentDisplay = new Intent(MainActivity.this, AddActivity.class);
      startActivity(intentDisplay);
    });


  }

  public void setNonActiveMenu()
  {
    menu_home.setImageResource(R.drawable.ic_homef);
    menu_upload.setImageResource(R.drawable.ic_uploadf);
    menu_profile.setImageResource(R.drawable.ic_profilf);
    menu_about.setImageResource(R.drawable.ic_aboutf);
  }




}