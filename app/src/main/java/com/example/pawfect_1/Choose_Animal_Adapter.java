package com.example.pawfect_1;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.makeramen.roundedimageview.RoundedImageView;

import java.util.List;


public class Choose_Animal_Adapter extends RecyclerView.Adapter<Choose_Animal_Adapter.ImageViewHolder> {
    List<Integer> imageList;
    ViewPager2 viewPager2;

    public Choose_Animal_Adapter(List<Integer> imageList, ViewPager2 viewPager2){
        this.imageList = imageList;
        this.viewPager2 = viewPager2;
    }

    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.choose_animal_layout, parent, false);
        return new ImageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.imageView.setImageResource(imageList.get(position));


        if(position == imageList.size() - 2){
            viewPager2.post(runnable);
        }
        holder.itemView.setOnClickListener(new OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View view) {
                if(position == 0){
                    Intent intent = new Intent(view.getContext(),Dogs_Diseases.class);
                    Context context = view.getContext();
                    viewPager2 = view.findViewById(R.id.viewPager);
                    context.startActivity(intent);
                }
                if(position == 1){
                    Intent intent = new Intent(view.getContext(),Cats_Diseases.class);
                    Context context = view.getContext();
                    viewPager2 = view.findViewById(R.id.viewPager);
                    context.startActivity(intent);
                }
                if(position == 2){
                    Intent intent = new Intent(view.getContext(),Horses_Diseases.class);
                    Context context = view.getContext();
                    viewPager2 = view.findViewById(R.id.viewPager);
                    context.startActivity(intent);
                }
                if(position == 3){
                    Intent intent = new Intent(view.getContext(),Pigs_Diseases.class);
                    Context context = view.getContext();
                    viewPager2 = view.findViewById(R.id.viewPager);
                    context.startActivity(intent);
                }
                if(position == 4){
                    Intent intent = new Intent(view.getContext(),Birds_Diseases.class);
                    Context context = view.getContext();
                    viewPager2 = view.findViewById(R.id.viewPager);
                    context.startActivity(intent);
                }
                if(position == 5){
                    Intent intent = new Intent(view.getContext(),SmallMammals_Diseases.class);
                    Context context = view.getContext();
                    viewPager2 = view.findViewById(R.id.viewPager);
                    context.startActivity(intent);
                }
                if(position == 6){
                    Intent intent = new Intent(view.getContext(),Reptiles_Diseases.class);
                    Context context = view.getContext();
                    viewPager2 = view.findViewById(R.id.viewPager);
                    context.startActivity(intent);
                }
                if(position == 7){
                    Intent intent = new Intent(view.getContext(),Ruminants_Diseases.class);
                    Context context = view.getContext();
                    viewPager2 = view.findViewById(R.id.viewPager);
                    context.startActivity(intent);
                }

            }
        });
    }
    @Override
    public int getItemCount() {

        return imageList.size();
    }

    public static class ImageViewHolder extends RecyclerView.ViewHolder{
        RoundedImageView imageView;

        public ImageViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
        }
    }



    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            imageList.addAll(imageList);
            notifyDataSetChanged();
        }
    };
}
