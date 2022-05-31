package com.master.design.rashnanthi.Adapter;


import android.content.Context;
import android.graphics.Color;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.master.design.rashnanthi.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kharag on 19-03-2020.
 */
public class ImageRecyclerAdapter extends RecyclerView.Adapter<ImageRecyclerAdapter.ViewHolder> {
    Context context;
    List<String> mList;
    ArrayList<String> slider_image_list;

    public ImageRecyclerAdapter(List<String> mList, Context context) {
        this.mList = mList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.single_recycler_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
//        holder.mName.setText(mList.get(position));

        holder.sliderPagerAdapter = new SliderPagerAdapter(context, slider_image_list);
        holder.mViewPager.setAdapter(holder.sliderPagerAdapter);
        holder.dots = new TextView[slider_image_list.size()];
        addBottomDots(0, holder.dots, holder);
//        holder.mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//            @Override
//            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//            }
//
//            @Override
//            public void onPageSelected(int position) {
//                addBottomDots(position, holder.dots, holder);
//            }
//
//            @Override
//            public void onPageScrollStateChanged(int state) {
//            }
//        });


        holder.whatsapp_IMg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        holder.snapchaht_Img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        holder.insta_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


    }

    private void addBottomDots(int currentPage, TextView[] dots, ViewHolder holder) {

        holder.ll_dots.removeAllViews();
        for (int i = 0; i < dots.length; i++) {
            dots[i] = new TextView(context);
            dots[i].setText(Html.fromHtml("&#8226;"));
            dots[i].setTextSize(35);
            dots[i].setTextColor(Color.parseColor("#CF0010"));
            holder.ll_dots.addView(dots[i]);
        }
        if (dots.length > 0)
            dots[currentPage].setTextColor(Color.parseColor("#A1A1A1"));
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView mName;
        private ViewPager mViewPager;
        SliderPagerAdapter sliderPagerAdapter;
        private TextView[] dots;
        private LinearLayout ll_dots;
        ImageView whatsapp_IMg,insta_img,snapchaht_Img;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mViewPager = itemView.findViewById(R.id.vp_slider);
            ll_dots = itemView.findViewById(R.id.ll_dots);
            whatsapp_IMg=itemView.findViewById(R.id.whatsapp_IMg);
            insta_img=itemView.findViewById(R.id.insta_img);
            snapchaht_Img=itemView.findViewById(R.id.snapchaht_Img);



//            mName = itemView.findViewById(R.id.list_name);
            slider_image_list = new ArrayList<>();
            slider_image_list.add("https://oi65.photobucket.com/albums/h214/L_The_Legend/DeathNoteS01E09E.png");
            slider_image_list.add("https://oi217.photobucket.com/albums/cc312/mastersig/Avitars/For%20Me/DAngel.png");
            slider_image_list.add("https://oi217.photobucket.com/albums/cc312/mastersig/Avitars/For%20Me/C_D_A.png");
            slider_image_list.add("https://oi49.photobucket.com/albums/f260/starfoxfan/fursona.jpg");
        }
    }
}

