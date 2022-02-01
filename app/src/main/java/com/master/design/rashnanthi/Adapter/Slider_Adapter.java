package com.master.design.rashnanthi.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.master.design.rashnanthi.DataModel.CoachDM;
import com.master.design.rashnanthi.Helper.User;
import com.master.design.rashnanthi.Models.SliderModel;
import com.master.design.rashnanthi.R;

import java.util.ArrayList;
import java.util.List;

public class Slider_Adapter extends RecyclerView.Adapter<Slider_Adapter.ViewHolder> {
    private Context context;
    private ArrayList<SliderModel> sliderModels;
     User user;


    int selectedPosition = 0;

    public Slider_Adapter(Context context, ArrayList<SliderModel> sliderModels) {
        this.context = context;
        this.sliderModels = sliderModels;
        user = new User(context);

    }

    public ArrayList<SliderModel> getSliderModels() {
        return sliderModels;
    }

    public void setSliderModels(ArrayList<SliderModel> sliderModels) {
        this.sliderModels = sliderModels;
    }

    public Slider_Adapter(List<Integer> integers) {

    }


    @NonNull
    @Override
    public Slider_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.coach_recycle_view_item, parent, false);
        // set the view's size, margins, paddings and layout parameters
        Slider_Adapter.ViewHolder vh = new Slider_Adapter.ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull Slider_Adapter.ViewHolder holder, int position) {
        setDetails(holder, position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return sliderModels.size();
    }


    private void setDetails(Slider_Adapter.ViewHolder viewHolder, int position) {



        viewHolder.imgview.setImageResource(Integer.parseInt(sliderModels.get(position).getImage()));

        viewHolder.imgview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {

         ImageView imgview;

        public ViewHolder(View itemView) {
            super(itemView);

            imgview = itemView.findViewById(R.id.coach_img);


            //          
        }
    }
}
