package com.katuliteam.bagiresep_ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class AuthActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_auth);

    getSupportFragmentManager().beginTransaction().replace(R.id.layout_auth,new LoginFragment()).commit();

  }
}