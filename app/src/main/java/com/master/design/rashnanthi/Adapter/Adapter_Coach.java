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
import com.master.design.rashnanthi.R;

import java.util.ArrayList;

public class Adapter_Coach extends RecyclerView.Adapter<Adapter_Coach.ViewHolder> {
    private Context context;
    private ArrayList<CoachDM> coachDMS;
    User user;


    int selectedPosition = 0;

    public Adapter_Coach(Context context, ArrayList<CoachDM> coachDMS) {
        this.context = context;
        this.coachDMS = coachDMS;
        user = new User(context);

    }


    @NonNull
    @Override
    public Adapter_Coach.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.coach_recycle_view_item, parent, false);
        // set the view's size, margins, paddings and layout parameters
        Adapter_Coach.ViewHolder vh = new Adapter_Coach.ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter_Coach.ViewHolder holder, int position) {
        setDetails(holder, position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return coachDMS.size();
    }


    private void setDetails(Adapter_Coach.ViewHolder viewHolder, int position) {
//         viewHolder.coach_Img.setImageResource(coachDMS.get(position).get());

        viewHolder.coach_Img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });



    }


    public static class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView coach_Img;

        public ViewHolder(View itemView) {
            super(itemView);
            coach_Img = itemView.findViewById(R.id.coach_img);


        }
    }
}
