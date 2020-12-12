package com.putrandabgs.docto;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class DokterAdapter extends RecyclerView.Adapter<DokterAdapter.DokterViewholder> {
    public List<Dokter> listDokter;

    public DokterAdapter(List<Dokter> listDokter) {
        this.listDokter = listDokter;
    }

    @NonNull
    @Override
    public DokterAdapter.DokterViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dokter_card, parent, false);
        return new DokterViewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DokterAdapter.DokterViewholder holder, int position) {
        holder.namaDokter.setText(listDokter.get(position).getNamaDokter());
        holder.spesialis.setText(listDokter.get(position).getSpesialis());
        holder.alamat.setText(listDokter.get(position).getAlamat());
    }

    @Override
    public int getItemCount() {
        return listDokter.size();
    }

    public class DokterViewholder extends RecyclerView.ViewHolder {
        TextView namaDokter, spesialis, alamat;
        Button detailDokter, jadwalDokter;

        public DokterViewholder(@NonNull View itemView) {
            super(itemView);

            namaDokter = itemView.findViewById(R.id.namaDokter);
            spesialis = itemView.findViewById(R.id.spesialis);
            alamat = itemView.findViewById(R.id.alamat);
            detailDokter = itemView.findViewById(R.id.detailDokter);
            jadwalDokter = itemView.findViewById(R.id.jadwalDokter);
        }
    }
}
