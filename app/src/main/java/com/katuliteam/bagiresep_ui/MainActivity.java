package com.katuliteam.bagiresep_ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

  ImageView menu_home, menu_upload, menu_profile, menu_about;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    menu_profile = findViewById(R.id.menu_profile);
    menu_home = findViewById(R.id.menu_home);
    menu_upload = findViewById(R.id.menu_upload);
    menu_about = findViewById(R.id.menu_about);

    getSupportFragmentManager().beginTransaction().replace(R.id.frg_hasil, new HomeFragment()).commit();

    Bundle extras = getIntent().getExtras();
    if( extras != null ){
      Toast.makeText(this, extras.getString("notify"), Toast.LENGTH_SHORT).show();
      menu_home.setImageResource(R.drawable.ic_homef);
      menu_upload.setImageResource(R.drawable.ic_uploadt);
      menu_profile.setImageResource(R.drawable.ic_profilf);
      menu_about.setImageResource(R.drawable.ic_aboutf);
      getSupportFragmentManager().beginTransaction().replace(R.id.frg_hasil, new MyResepFragment()).commit();
    }


    menu_home.setOnClickListener(v -> {
      menu_home.setImageResource(R.drawable.ic_homet);
      menu_upload.setImageResource(R.drawable.ic_uploadf);
      menu_profile.setImageResource(R.drawable.ic_profilf);
      menu_about.setImageResource(R.drawable.ic_aboutf);
      getSupportFragmentManager().beginTransaction().replace(R.id.frg_hasil, new HomeFragment()).commit();
    });

    menu_upload.setOnClickListener(v -> {
      menu_home.setImageResource(R.drawable.ic_homef);
      menu_upload.setImageResource(R.drawable.ic_uploadt);
      menu_profile.setImageResource(R.drawable.ic_profilf);
      menu_about.setImageResource(R.drawable.ic_aboutf);
      getSupportFragmentManager().beginTransaction().replace(R.id.frg_hasil, new MyResepFragment()).commit();
    });

    menu_profile.setOnClickListener(v -> {
      menu_home.setImageResource(R.drawable.ic_homef);
      menu_upload.setImageResource(R.drawable.ic_uploadf);
      menu_profile.setImageResource(R.drawable.ic_profilt);
      menu_about.setImageResource(R.drawable.ic_aboutf);
      getSupportFragmentManager().beginTransaction().replace(R.id.frg_hasil, new ProfileFragment()).commit();
    });

    menu_about.setOnClickListener(v -> {
      menu_home.setImageResource(R.drawable.ic_homef);
      menu_upload.setImageResource(R.drawable.ic_uploadf);
      menu_profile.setImageResource(R.drawable.ic_profilf);
      menu_about.setImageResource(R.drawable.ic_aboutt);
      getSupportFragmentManager().beginTransaction().replace(R.id.frg_hasil, new AboutFragment()).commit();
    });


  }




}