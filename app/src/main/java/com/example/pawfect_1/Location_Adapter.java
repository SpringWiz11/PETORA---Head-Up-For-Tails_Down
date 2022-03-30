package com.example.pawfect_1;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Location_Adapter extends RecyclerView.Adapter<Location_Adapter.ViewHolder> {
    public static final String TAG = "TAG";
    LayoutInflater inflater;
    List<String> names, address, website, mobno, star;
    public Location_Adapter(Context context, int location_layout, List<String> names, List<String> address, List<String> mobno, List<String> website, List<String> star){
        Log.d(TAG, "Adapter : " + names);
        this.inflater = LayoutInflater.from(context);
        this.names = names;
        this.address = address;
        this.mobno = mobno;
        this.website = website;
        this.star = star;
    }
    @Override
    public int getItemCount() {
        return names.size();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.location_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String name = names.get(position);
        String addrs = address.get(position);
        String web = website.get(position);
        String phone = mobno.get(position);
        String stars = star.get(position);

        holder.names.setText(name);
        holder.address.setText(addrs);
        holder.website.setText(web);
        holder.mobno.setText(phone);
        holder.star.setText(stars);
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView names, address, website, mobno, star;

        public ViewHolder(View view) {
            super(view);
            names = itemView.findViewById(R.id.names);
            address = itemView.findViewById(R.id.address);
            website = itemView.findViewById(R.id.website);
            mobno = itemView.findViewById(R.id.ph_no);
            star = itemView.findViewById(R.id.stars);
        }
    }

}