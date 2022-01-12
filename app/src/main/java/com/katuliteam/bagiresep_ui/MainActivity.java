package com.katuliteam.bagiresep_ui;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

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
      setActiveMenu(menu_upload, R.drawable.ic_uploadt, new MyResepFragment());
    }
    if( extras != null && extras.getString("akun") != null && !extras.getString("akun").equals("")){
      Toast.makeText(this, extras.getString("akun"), Toast.LENGTH_SHORT).show();
      setNonActiveMenu();
      setActiveMenu(menu_profile, R.drawable.ic_profilt, new ProfileFragment());
    }
    if( extras != null && extras.getString("akun") != null && extras.getString("akun").equals("") ){
      setNonActiveMenu();
      setActiveMenu(menu_profile, R.drawable.ic_profilt, new ProfileFragment());
    }


    menu_home.setOnClickListener(v -> {
      setNonActiveMenu();
      setActiveMenu(menu_home, R.drawable.ic_homet, new HomeFragment());
    });

    menu_upload.setOnClickListener(v -> {
      setNonActiveMenu();
      setActiveMenu(menu_upload, R.drawable.ic_uploadt, new MyResepFragment());
    });

    menu_profile.setOnClickListener(v -> {
      setNonActiveMenu();
      menu_profile.setImageResource(R.drawable.ic_profilt);
      setActiveMenu(menu_profile, R.drawable.ic_profilt, new ProfileFragment());
    });

    menu_about.setOnClickListener(v -> {
      setNonActiveMenu();
      setActiveMenu(menu_about, R.drawable.ic_aboutt, new AboutFragment());
    });

    menuAdd.setOnClickListener(v -> {
      intentDisplay = new Intent(MainActivity.this, AddActivity.class);
      startActivity(intentDisplay);
    });

  }


  private void setNonActiveMenu()
  {
    menu_home.setImageResource(R.drawable.ic_homef);
    menu_upload.setImageResource(R.drawable.ic_uploadf);
    menu_profile.setImageResource(R.drawable.ic_profilf);
    menu_about.setImageResource(R.drawable.ic_aboutf);
  }

  public void setActiveMenu(ImageView menu, @DrawableRes int source, @NonNull Fragment fragment)
  {
    menu.setImageResource(source);
    getSupportFragmentManager().beginTransaction().replace(R.id.frg_hasil, fragment).commit();
  }




}