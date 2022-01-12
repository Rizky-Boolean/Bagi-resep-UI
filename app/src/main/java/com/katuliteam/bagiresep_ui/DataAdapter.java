package com.katuliteam.bagiresep_ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {

    private ArrayList keyword_list;
    private ArrayList judul_list;
    private ArrayList penulis_list;


    public DataAdapter(ArrayList keywordList, ArrayList judulList, ArrayList penulisList){
        this.keyword_list = keywordList;
        this.judul_list = judulList;
        this.penulis_list = penulisList;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View vw = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_item, viewGroup, false);
        return new ViewHolder(vw);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        final String keyword = (String) keyword_list.get(i);
        final String judul = (String) judul_list.get(i);
        final String penulis = (String) penulis_list.get(i);

        viewHolder.keyword.setText(keyword);
        viewHolder.judul.setText(judul);
        viewHolder.penulis.setText("Penulis : " + penulis);


    }

    @Override
    public int getItemCount() {
        return judul_list.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView keyword, judul, penulis;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            keyword = itemView.findViewById(R.id.txt_password_baru);
            judul = itemView.findViewById(R.id.txt_judul);
            penulis = itemView.findViewById(R.id.txt_penulis);
        }

    }
}
