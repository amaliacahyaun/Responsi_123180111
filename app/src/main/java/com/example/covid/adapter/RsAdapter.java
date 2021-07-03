package com.example.covid.adapter;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.covid.R;
import com.example.covid.model.rs.DataItem;

import java.util.List;

public class RsAdapter extends RecyclerView.Adapter<RsAdapter.ViewHolder> {
    List<DataItem> item;

    public RsAdapter(List<DataItem> item) {
        this.item = item;
    }


    @NonNull
    @Override
    public RsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.list_item2, parent, false);

        return new RsAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RsAdapter.ViewHolder holder, int position) {
        holder.bind(item.get(position));
    }

    @Override
    public int getItemCount() {
        return item.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvNama, tvAlamat;
        Button Maps;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNama = itemView.findViewById(R.id.tv_namars);
            tvAlamat = itemView.findViewById(R.id.tv_alamat);
            Maps = itemView.findViewById(R.id.buttonmap);
        }

        @SuppressLint("SetTextI18n")
        public void bind(DataItem dataItem) {
            tvNama.setText(dataItem.getNama());
            tvAlamat.setText(dataItem.getAlamat());
            Maps.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Uri Intent = Uri.parse("geo: " + dataItem.getLatitude() + "," + dataItem.getLongitude() + "?q=" + Uri.encode(dataItem.getNama()));
                    Intent map = new Intent(android.content.Intent.ACTION_VIEW, Intent);
                    map.setPackage("com.google.android.apps.maps");
                    itemView.getContext().startActivity(map);
                }
            });
        }
    }
}
