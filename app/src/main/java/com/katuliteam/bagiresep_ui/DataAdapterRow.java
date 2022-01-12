package com.katuliteam.bagiresep_ui;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class DataAdapterRow extends RecyclerView.Adapter<DataAdapterRow.ViewHolder> {

    DBConfig config;
    SQLiteDatabase db;

    Intent intentDisplay;

    private ArrayList keyword_list;
    private ArrayList judul_list;
    private ArrayList deskripsi_list;



    public DataAdapterRow(ArrayList keywordList, ArrayList judulList, ArrayList deskripsiList){
        this.keyword_list = keywordList;
        this.judul_list = judulList;
        this.deskripsi_list = deskripsiList;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View vw = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_item_my_resep, viewGroup, false);
        config = new DBConfig(vw.getContext());
        return new ViewHolder(vw);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        final String keyword = (String) keyword_list.get(i);
        final String judul = (String) judul_list.get(i);
        final String deskripsi = (String) deskripsi_list.get(i);

        viewHolder.keyword.setText(keyword);
        viewHolder.judul.setText(judul);
        viewHolder.deskripsi.setText(deskripsi);

        viewHolder.itemView.setOnClickListener(view -> {
            intentDisplay = new Intent(view.getContext(), DetailActivity.class);
            intentDisplay.putExtra("keyword", keyword);
            view.getContext().startActivity(intentDisplay);
        });

        viewHolder.imgEdit.setOnClickListener(v -> {
            intentDisplay = new Intent(v.getContext(), EditActivity.class);
            intentDisplay.putExtra("keyword", keyword);
            v.getContext().startActivity(intentDisplay);
        });

        viewHolder.imgHapus.setOnClickListener(v -> {
            final CharSequence[] dialogItem = {"Ya, hapus!", "Batal"};
            AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
            builder.setTitle("Yakin menghapus data?");
            builder.setItems(dialogItem, (dialog, which) -> {
                switch ( which ){
                    case 0:
                        // hapus resep
                        db = config.getWritableDatabase();
                        db.execSQL("DELETE FROM tbl_resep WHERE keyword = '" + keyword_list.get(i).toString() + "'");

                        intentDisplay = new Intent(v.getContext(), MainActivity.class);
                        intentDisplay.putExtra("notify", "Data berhasil dihapus.");
                        v.getContext().startActivity(intentDisplay);
                        ((Activity)v.getContext()).finish();

                        break;

                }
            });

            builder.create().show();
        });

    }

    @Override
    public int getItemCount() {
        return judul_list.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView keyword, judul, deskripsi;
        private ImageView imgEdit, imgHapus;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            keyword = itemView.findViewById(R.id.txt_password_baru);
            judul = itemView.findViewById(R.id.txt_judul);
            deskripsi = itemView.findViewById(R.id.txt_deskripsi);
            imgEdit = itemView.findViewById(R.id.img_ubah);
            imgHapus = itemView.findViewById(R.id.img_hapus);
        }

    }
}
