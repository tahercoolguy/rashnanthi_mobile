package com.master.design.rashnanthi.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.master.design.rashnanthi.DataModel.CoachDM;
import com.master.design.rashnanthi.DataModel.My_Event_DM;
import com.master.design.rashnanthi.Helper.User;
import com.master.design.rashnanthi.R;

import java.util.ArrayList;

public class Adapter_Coach_Fgmt extends RecyclerView.Adapter<Adapter_Coach_Fgmt.ViewHolder> {
    private Context context;
    private ArrayList<CoachDM> coachDMArrayList;
     User user;


    int selectedPosition = 0;

    public Adapter_Coach_Fgmt(Context context, ArrayList<CoachDM> coachDMArrayList) {
        this.context = context;
        this.coachDMArrayList = coachDMArrayList;
        user = new User(context);

    }


    @NonNull
    @Override
    public Adapter_Coach_Fgmt.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.coach_recycle_view_item, parent, false);
        // set the view's size, margins, paddings and layout parameters
        Adapter_Coach_Fgmt.ViewHolder vh = new Adapter_Coach_Fgmt.ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter_Coach_Fgmt.ViewHolder holder, int position) {
        setDetails(holder, position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return coachDMArrayList.size();
    }


    private void setDetails(Adapter_Coach_Fgmt.ViewHolder viewHolder, int position) {



        viewHolder.imgview.setImageResource(coachDMArrayList.get(position).getCoach_Image());

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
