package com.katuliteam.bagiresep_ui;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

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
  ArrayList penulis_list;

  Intent intentDisplay;
  EditText edtSearch;
  TextView txtResult;
  Button btnReset;



  public HomeFragment() {
    // Required empty public constructor
  }

  /**
   * Use this factory method to create a new instance of
   * this fragment using the provided parameters.
   *
   * @param param1 Parameter 1.
   * @param param2 Parameter 2.
   * @return A new instance of fragment HomeFragment.
   */
  // TODO: Rename and change types and number of parameters
  public static HomeFragment newInstance(String param1, String param2) {
    HomeFragment fragment = new HomeFragment();
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
    View vw = inflater.inflate(R.layout.fragment_home, container, false);

    config = new DBConfig(getContext());

    txtResult = vw.findViewById(R.id.txt_result);
    btnReset = vw.findViewById(R.id.btn_reset);

    edtSearch = vw.findViewById(R.id.edt_search);

    showData(vw, "SELECT * FROM tbl_resep");


    // event kalo search diketik
    edtSearch.addTextChangedListener(new TextWatcher() {
      @Override
      public void onTextChanged(CharSequence s, int start, int before, int count) {
        //here is your code
        if( !edtSearch.getText().toString().isEmpty() ){
          btnReset.setBackgroundResource(R.drawable.ic_reset);
          btnReset.setOnClickListener(v -> {
            edtSearch.setText(null);
          });
        }else{
          btnReset.setBackgroundResource(0);
        }
      }
      @Override
      public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        // TODO Auto-generated method stub
      }
      @Override
      public void afterTextChanged(Editable s) {
        // TODO Auto-generated method stub
      }
    });

    // event kalo tombol search dikeyboard ditekan
    edtSearch.setOnEditorActionListener((v, actionId, event) -> {
      boolean handled = false;
      if (actionId == EditorInfo.IME_ACTION_SEARCH) {
        showData(vw, "SELECT * FROM tbl_resep WHERE judul LIKE '%" + edtSearch.getText().toString() + "%'");
        handled = true;
      }
      return handled;
    });

    return vw;
  }

  // show data function
  public void showData(View vw, String sqlQuery)
  {

    txtResult.setTextSize(TypedValue.COMPLEX_UNIT_SP, 0);

    keyword_list = new ArrayList<>();
    judul_list = new ArrayList<>();
    penulis_list = new ArrayList<>();

    layout = new GridLayoutManager(getContext(), 2);
    adapter = new DataAdapter(keyword_list, judul_list, penulis_list);

    rcv_data = vw.findViewById(R.id.rcv_data);


    rcv_data.setLayoutManager(layout);
    rcv_data.setHasFixedSize(true);
    rcv_data.setAdapter(adapter);



    db = config.getReadableDatabase();
    cursor = db.rawQuery(sqlQuery,null);

    cursor.moveToFirst();

    if( cursor.getCount() > 0 ) {
      for (int count = 0; count < cursor.getCount(); count++) {
        cursor.moveToPosition(count);
        keyword_list.add(cursor.getString(0));
        judul_list.add(cursor.getString(3));
        penulis_list.add(cursor.getString(2));
      }

      rcv_data.addOnItemTouchListener(
              new RecyclerItemClickListener(getContext(), rcv_data, new RecyclerItemClickListener.OnItemClickListener() {
                @Override
                public void onItemClick(View view, int position) {

                  intentDisplay = new Intent(getActivity(), DetailActivity.class);
                  intentDisplay.putExtra("keyword", keyword_list.get(position).toString());
                  startActivity(intentDisplay);

                }
                @Override
                public void onLongItemClick(View view, int position) {};


              })
      );

    }else{
      txtResult.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
    }

    if( !edtSearch.getText().toString().isEmpty() ){
      btnReset.setBackgroundResource(R.drawable.ic_reset);
      btnReset.setOnClickListener(v -> {
        edtSearch.setText(null);
      });
    }else{
      btnReset.setBackgroundResource(0);
    }

    btnReset.setOnClickListener(v -> {
      showData(vw, "SELECT * FROM tbl_resep");
      edtSearch.setText(null);
      btnReset.setBackgroundResource(0);
    });

  }


}