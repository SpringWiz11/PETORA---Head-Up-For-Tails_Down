package com.example.pawfect_1;

import android.media.Image;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import java.util.ArrayList;
import java.util.List;

public class General_Symptoms extends AppCompatActivity {
    ViewPager2 viewPager;
    List<Image> imageList;
    Handler sliderhandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_general_symptoms);
        viewPager = findViewById(R.id.viewPager);
        imageList = new ArrayList<>();
        List<Integer> imageList = new ArrayList<>();
        List<String> names = new ArrayList<>();
        imageList.add(R.drawable.dog3);
        imageList.add(R.drawable.cat1);
        imageList.add(R.drawable.horse1);
        imageList.add(R.drawable.pigs);
        imageList.add(R.drawable.bird);
        imageList.add(R.drawable.smallmammals);
        imageList.add(R.drawable.reptile);
        imageList.add(R.drawable.ruminants);
        Choose_Animal_Adapter ad = new Choose_Animal_Adapter(imageList, viewPager);
        viewPager.setAdapter(ad);
        viewPager.setOffscreenPageLimit(3);
        viewPager.setClipChildren(false);
        viewPager.setClipToPadding(false);
        viewPager.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);
        CompositePageTransformer transformer = new CompositePageTransformer();
        transformer.addTransformer(new MarginPageTransformer(40));
        transformer.addTransformer(new ViewPager2.PageTransformer() {
            @Override
            public void transformPage(@NonNull View page, float position) {
                float r = 1 - Math.abs(position);
                page.setScaleY(0.85f + r * 0.14f);
            }
        });
        viewPager.setPageTransformer(transformer);
        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                sliderhandler.removeCallbacks(sliderRunnable);
                sliderhandler.postDelayed(sliderRunnable, 3000);
            }
        });

    }
    Runnable sliderRunnable = new Runnable() {
        @Override
        public void run() {
            viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
        }
    };

    @Override
    protected void onPause() {
        super.onPause();
        sliderhandler.removeCallbacks(sliderRunnable);
    }

    @Override
    protected void onResume() {
        super.onResume();
        sliderhandler.postDelayed(sliderRunnable, 3000);
    }
}