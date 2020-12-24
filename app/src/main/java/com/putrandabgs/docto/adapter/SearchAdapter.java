package com.putrandabgs.docto.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.putrandabgs.docto.ConfirmActivity;
import com.putrandabgs.docto.DetailActivity;
import com.putrandabgs.docto.R;
import com.putrandabgs.docto.model.Dokter;

import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.DokterViewholder> {

    Context context;
    public List<Dokter> listDokter;

    public SearchAdapter(List<Dokter> listDokter, Context context) {
        this.listDokter = listDokter;
        this.context = context;
    }

    @NonNull
    @Override
    public SearchAdapter.DokterViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dokter_card, parent, false);
        return new DokterViewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchAdapter.DokterViewholder holder, int position) {
        holder.idDokter.setText(listDokter.get(position).getIdDokter());
        holder.namaDokter.setText(listDokter.get(position).getNamaDokter());
        holder.spesialis.setText(listDokter.get(position).getSpesialisasi());
        holder.alamat.setText(listDokter.get(position).getAlamat());
        holder.detailDokter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sendIdDokter = listDokter.get(position).getIdDokter();
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra("id_dokter", sendIdDokter);
                context.startActivity(intent);
            }
        });

        holder.jadwalDokter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sendIdDokter = listDokter.get(position).getIdDokter();
                Intent intent = new Intent(context, ConfirmActivity.class);
                intent.putExtra("id_dokter", sendIdDokter);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listDokter.size();
    }

    public class DokterViewholder extends RecyclerView.ViewHolder {
        TextView namaDokter, spesialis, alamat, idDokter;
        Button detailDokter, jadwalDokter;

        public DokterViewholder(@NonNull View itemView) {
            super(itemView);

            idDokter = itemView.findViewById(R.id.idDokter);
            namaDokter = itemView.findViewById(R.id.namaDokter);
            spesialis = itemView.findViewById(R.id.spesialis);
            alamat = itemView.findViewById(R.id.alamat);
            detailDokter = itemView.findViewById(R.id.detailDokter);
            jadwalDokter = itemView.findViewById(R.id.jadwalDokter);
        }
    }

    public void clear() {
        int size = listDokter.size();
        listDokter.clear();
        notifyItemRangeRemoved(0, size);
    }
}
