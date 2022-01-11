package com.katuliteam.bagiresep_ui;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MyResepFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MyResepFragment extends Fragment {

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

  RecyclerView rcv_data;
  RecyclerView.Adapter adapter;
  RecyclerView.LayoutManager layout;

  ArrayList keyword_list;
  ArrayList judul_list;
  ArrayList deskripsi_list;

  Intent intentDisplay;
  ImageView imgTambah;
  TextView txtResult;


  public MyResepFragment() {
    // Required empty public constructor
  }

  /**
   * Use this factory method to create a new instance of
   * this fragment using the provided parameters.
   *
   * @param param1 Parameter 1.
   * @param param2 Parameter 2.
   * @return A new instance of fragment MyResepFragment.
   */
  // TODO: Rename and change types and number of parameters
  public static MyResepFragment newInstance(String param1, String param2) {
    MyResepFragment fragment = new MyResepFragment();
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
    View vw = inflater.inflate(R.layout.fragment_my_resep, container, false);

    config = new DBConfig(getContext());

    txtResult = vw.findViewById(R.id.txt_result);

    keyword_list = new ArrayList<>();
    judul_list = new ArrayList<>();
    deskripsi_list = new ArrayList<>();

    layout = new LinearLayoutManager(getContext());
    adapter = new DataAdapterRow(keyword_list, judul_list, deskripsi_list);

    rcv_data = vw.findViewById(R.id.rcv_data);

    rcv_data.setLayoutManager(layout);
    rcv_data.setHasFixedSize(true);
    rcv_data.setAdapter(adapter);

    db = config.getReadableDatabase();
    cursor = db.rawQuery("SELECT * FROM tbl_login",null);
    cursor.moveToFirst();

    String emailPenulis = cursor.getString(0);

    db = config.getReadableDatabase();
    cursor = db.rawQuery("SELECT * FROM tbl_resep WHERE emailPenulis = '" + emailPenulis + "'",null);
    cursor.moveToFirst();

    if( cursor.getCount() > 0 ) {

      for (int count = 0; count < cursor.getCount(); count++) {
        cursor.moveToPosition(count);

        String cutDeskripsi = cursor.getString(4);
        if (cursor.getString(4).length() > 50) {
          cutDeskripsi = cutDeskripsi.replaceAll("\n", " ").substring(0, 50) + "...";
        }

        String cutJudul = cursor.getString(3);
        if (cursor.getString(3).length() > 15) {
          cutJudul = cutJudul.substring(0, 15) + "...";
        }

        keyword_list.add(cursor.getString(0));
        judul_list.add(cutJudul);
        deskripsi_list.add(cutDeskripsi);
      }


    }else{
      txtResult.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
    }

    imgTambah = vw.findViewById(R.id.img_tambah_resep);
    imgTambah.setOnClickListener(v -> {
      intentDisplay = new Intent(getActivity(), AddActivity.class);
      startActivity(intentDisplay);
    });

    return vw;
  }
}