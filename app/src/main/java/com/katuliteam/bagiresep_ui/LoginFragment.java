package com.katuliteam.bagiresep_ui;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LoginFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LoginFragment extends Fragment {

  // TODO: Rename parameter arguments, choose names that match
  // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
  private static final String ARG_PARAM1 = "param1";
  private static final String ARG_PARAM2 = "param2";

  // TODO: Rename and change types of parameters
  private String mParam1;
  private String mParam2;

  DBConfig config;
  SQLiteDatabase db;
  Cursor cursor;

  EditText edtEmail, edtPassword;

  LinearLayout linkRegister;
  Button btnLogin;
  Intent intentDisplay;

  public LoginFragment() {
    // Required empty public constructor
  }

  /**
   * Use this factory method to create a new instance of
   * this fragment using the provided parameters.
   *
   * @param param1 Parameter 1.
   * @param param2 Parameter 2.
   * @return A new instance of fragment LoginFragment.
   */
  // TODO: Rename and change types and number of parameters
  public static LoginFragment newInstance(String param1, String param2) {
    LoginFragment fragment = new LoginFragment();
    Bundle args = new Bundle();
    args.putString(ARG_PARAM1, param1);
    args.putString(ARG_PARAM2, param2);
    fragment.setArguments(args);
    return fragment;
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    if (getArguments() != null) {
      mParam1 = getArguments().getString(ARG_PARAM1);
      mParam2 = getArguments().getString(ARG_PARAM2);
    }
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    View vw = inflater.inflate(R.layout.fragment_login, container, false);

    config = new DBConfig(getContext());

    edtEmail = vw.findViewById(R.id.edt_email);
    edtPassword = vw.findViewById(R.id.edt_password);
    linkRegister = vw.findViewById(R.id.link_register);
    btnLogin = vw.findViewById(R.id.btn_login);

    btnLogin.setOnClickListener(v -> {

      String email = edtEmail.getText().toString();
      String password = edtPassword.getText().toString();

      if( email.isEmpty() || password.isEmpty() ){
        Toast.makeText(getActivity(), "Isi username dan password!", Toast.LENGTH_SHORT).show();
      }else{

        db = config.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM tbl_user WHERE email = '" + email + "' AND password = '" + password + "'",null);
        cursor.moveToFirst();


        if(cursor.getCount() == 1)
        {

          db = config.getReadableDatabase();
          db.execSQL("INSERT INTO tbl_login(email, nama) VALUES('" +
                  cursor.getString(0) + "','" +
                  cursor.getString(1) + "')");

          intentDisplay = new Intent(getActivity(), MainActivity.class);
          startActivity(intentDisplay);
          getActivity().finish();

        }else{
          Toast.makeText(getActivity(), "Login gagal! Periksa kembali!", Toast.LENGTH_SHORT).show();
        }

      }

    });

    linkRegister.setOnClickListener(v -> {
      getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.layout_auth,new RegisterFragment()).commit();
    });

    return vw;
  }
}