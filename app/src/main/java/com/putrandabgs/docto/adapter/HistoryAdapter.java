package com.putrandabgs.docto.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.putrandabgs.docto.ProfileActivity;
import com.putrandabgs.docto.R;
import com.putrandabgs.docto.model.History;

import java.util.List;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder> {

    public List<History> listHistory;

    public HistoryAdapter(List<History> listHistory) {
        this.listHistory = listHistory;
    }

    @NonNull
    @Override
    public HistoryAdapter.HistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.history_card, parent, false);
        return new HistoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryAdapter.HistoryViewHolder holder, int position) {
        holder.namaDokter.setText(listHistory.get(position).getNamaDokter());
        holder.spesialis.setText(listHistory.get(position).getSpesialisasi());
        holder.alamat.setText(listHistory.get(position).getAlamat());
        holder.tanggal.setText(listHistory.get(position).getTanggal());
    }

    @Override
    public int getItemCount() {
        return listHistory.size();
    }

    public class HistoryViewHolder extends RecyclerView.ViewHolder {
        TextView namaDokter, spesialis, alamat, tanggal;

        public HistoryViewHolder(@NonNull View itemView) {
            super(itemView);

            namaDokter = itemView.findViewById(R.id.namaDokterHistory);
            spesialis = itemView.findViewById(R.id.spesialisHistory);
            alamat = itemView.findViewById(R.id.alamatDokterHistory);
            tanggal = itemView.findViewById(R.id.tanggalBookingHistory);
        }
    }
}
