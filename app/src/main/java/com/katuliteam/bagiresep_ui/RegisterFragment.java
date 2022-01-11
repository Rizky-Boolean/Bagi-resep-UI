package com.katuliteam.bagiresep_ui;

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
 * Use the {@link RegisterFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RegisterFragment extends Fragment {

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

  Button btnRegister;
  LinearLayout linkLogin;

  EditText edtNama, edtEmail, edtPassword, edtKonfirmasiPassword;


  public RegisterFragment() {
    // Required empty public constructor
  }

  /**
   * Use this factory method to create a new instance of
   * this fragment using the provided parameters.
   *
   * @param param1 Parameter 1.
   * @param param2 Parameter 2.
   * @return A new instance of fragment RegisterFragment.
   */
  // TODO: Rename and change types and number of parameters
  public static RegisterFragment newInstance(String param1, String param2) {
    RegisterFragment fragment = new RegisterFragment();
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
    View vw = inflater.inflate(R.layout.fragment_register, container, false);

    config = new DBConfig(getContext());

    edtNama = vw.findViewById(R.id.edt_nama);
    edtEmail = vw.findViewById(R.id.edt_email);
    edtPassword = vw.findViewById(R.id.edt_password);
    edtKonfirmasiPassword = vw.findViewById(R.id.edt_ulangi_password);

    btnRegister = vw.findViewById(R.id.btn_register);

    btnRegister.setOnClickListener(v -> {

      if(edtNama.getText().toString().isEmpty()
              || edtEmail.getText().toString().isEmpty()
              || edtPassword.getText().toString().isEmpty()
              || edtKonfirmasiPassword.getText().toString().isEmpty()){

        Toast.makeText(getActivity(), "Semua data harus diisi!", Toast.LENGTH_SHORT).show();

      }else{

        if( !edtPassword.getText().toString().equals(edtKonfirmasiPassword.getText().toString()) ){

          Toast.makeText(getActivity(), "Konfirmasi password harus sama!", Toast.LENGTH_SHORT).show();

        }else{

          db = config.getReadableDatabase();
          cursor = db.rawQuery("SELECT email FROM tbl_user WHERE email = '" +
                  edtEmail.getText().toString() + "'",null);
          cursor.moveToFirst();

          if(cursor.getCount() > 0) {

            Toast.makeText(getActivity(), "Email sudah ada!", Toast.LENGTH_SHORT).show();

          }else{

            db = config.getWritableDatabase();
            db.execSQL("INSERT INTO tbl_user(email, nama, password) VALUES('" +
                    edtEmail.getText().toString() + "','" +
                    edtNama.getText().toString() + "','" +
                    edtPassword.getText().toString() + "')");

            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.layout_auth,new LoginFragment()).commit();

            Toast.makeText(getActivity(), "Daftar berhasil.", Toast.LENGTH_SHORT).show();

          }

        }

      }

    });


    linkLogin = vw.findViewById(R.id.link_login);
    linkLogin.setOnClickListener(v -> {
      getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.layout_auth,new LoginFragment()).commit();
    });

    return vw;

  }
}