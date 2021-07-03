package com.example.covid.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.covid.R;
import com.example.covid.model.covid.ContentItem;

import java.util.Collections;
import java.util.List;

public class CovidAdapter extends RecyclerView.Adapter<CovidAdapter.ViewHolder> {

    List<ContentItem> item;

    public CovidAdapter(List<ContentItem> item) {
        Collections.reverse(item);
        this.item = item;
    }

    @NonNull
    @Override
    public CovidAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.list_item1, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CovidAdapter.ViewHolder holder, int position) {
        holder.bind(item.get(position));
    }

    @Override
    public int getItemCount() {
        return item.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTanggal, tvKonfirmasi, tvSembuh, tvMeninggal;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTanggal = itemView.findViewById(R.id.tv_tanggal);
            tvKonfirmasi = itemView.findViewById(R.id.tv_konf);
            tvSembuh = itemView.findViewById(R.id.tv_sembuh);
            tvMeninggal = itemView.findViewById(R.id.tv_meninggal);
        }

        @SuppressLint("SetTextI18n")
        public void bind(ContentItem contentItem) {
            tvTanggal.setText(contentItem.getTanggal());
            tvKonfirmasi.setText(String.valueOf(contentItem.getConfirmationDiisolasi()));
            tvSembuh.setText(String.valueOf(contentItem.getConfirmationSelesai()));
            tvMeninggal.setText(String.valueOf(contentItem.getConfirmationMeninggal()));
        }
    }
}
