package com.master.design.rashnanthi.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.master.design.rashnanthi.DataModel.CoachGridDM;
import com.master.design.rashnanthi.Helper.User;
import com.master.design.rashnanthi.R;

import java.util.ArrayList;

 public class Adapter_Coach__grid_Fgmt extends RecyclerView.Adapter<Adapter_Coach__grid_Fgmt.ViewHolder> {
    private Context context;
    private ArrayList<CoachGridDM> coachGridDMArrayList;
    User user;


    int selectedPosition = 0;

    public Adapter_Coach__grid_Fgmt(Context context, ArrayList<CoachGridDM> coachGridDMArrayList) {
        this.context = context;
        this.coachGridDMArrayList = coachGridDMArrayList;
        user = new User(context);

    }


    @NonNull
    @Override
    public Adapter_Coach__grid_Fgmt.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.coach_grid_account_recycle_view_item, parent, false);
        // set the view's size, margins, paddings and layout parameters
        Adapter_Coach__grid_Fgmt.ViewHolder vh = new Adapter_Coach__grid_Fgmt.ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter_Coach__grid_Fgmt.ViewHolder holder, int position) {
        setDetails(holder, position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return coachGridDMArrayList.size();
    }


    private void setDetails(Adapter_Coach__grid_Fgmt.ViewHolder viewHolder, int position) {


        viewHolder.imgview.setImageResource(coachGridDMArrayList.get(position).getCoach_Image());
        viewHolder.imageView1.setImageResource(coachGridDMArrayList.get(position).getLike_img());


    }


    public static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imgview, imageView1;

        public ViewHolder(View itemView) {
            super(itemView);

            imgview = itemView.findViewById(R.id.coach_grid_img);
            imageView1 = itemView.findViewById(R.id.like_coach_grid);


            //          
        }
    }
}
