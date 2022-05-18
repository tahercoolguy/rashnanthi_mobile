package com.master.design.rashnanthi.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.makeramen.roundedimageview.RoundedImageView;
import com.master.design.rashnanthi.Activity.Story_activity;
import com.master.design.rashnanthi.Controller.AppController;
import com.master.design.rashnanthi.DataModel.GetCoachByCountryData;
import com.master.design.rashnanthi.DataModel.GetCoachsByCountryRootDM;
import com.master.design.rashnanthi.Helper.User;
import com.master.design.rashnanthi.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Adapter_Coach__grid_Fgmt extends RecyclerView.Adapter<Adapter_Coach__grid_Fgmt.ViewHolder> {
    private Context context;
    private ArrayList<GetCoachByCountryData> getCoachsByCountryRootDMArrayList;
    User user;


    int selectedPosition = 0;

    public Adapter_Coach__grid_Fgmt(Context context, ArrayList<GetCoachByCountryData> GetCoachsByCountryRootDMArrayList) {
        this.context = context;
        this.getCoachsByCountryRootDMArrayList = GetCoachsByCountryRootDMArrayList;
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
        return getCoachsByCountryRootDMArrayList.size();
    }


    private void setDetails(Adapter_Coach__grid_Fgmt.ViewHolder viewHolder, int position) {


//        viewHolder.circle_imgview.setImageResource(Integer.parseInt(GetCoachsByCountryRootDMArrayList.get(0).getOutput().getData().get(0).getProfilepic()));

//        Picasso.get().load(Integer.parseInt(getCoachsByCountryRootDMArrayList.get(position).getProfilepic())).into(viewHolder.circle_imgview);

        Picasso.get().load(AppController.base_image_url +getCoachsByCountryRootDMArrayList.get(position).getProfilepic()).into(viewHolder.circle_imgview);

        viewHolder.imageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                viewHolder.imageView1.setImageResource(R.drawable.ic_heart_black);
                liked = true;
                unliked =true;
                LikedUnliked();
 //                Intent i = new Intent(context, Story_activity.class);
//                context.startActivity(i);
            }

            boolean liked = false;
            boolean unliked =false;

            public void LikedUnliked() {

                if (liked) {
                    viewHolder.imageView1.setImageResource(R.drawable.ic_heart_red);
                    viewHolder.imageView1.setImageResource(R.drawable.ic_heart_black);

                }  if (unliked){
                    viewHolder.imageView1.setImageResource(R.drawable.ic_heart_black);
                    viewHolder.imageView1.setImageResource(R.drawable.ic_heart_red);

                }
            }

        });


        viewHolder.circle_imgview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(context, Story_activity.class);
                context.startActivity(i);
            }

        });


    }


    public static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView  imageView1;

        RoundedImageView circle_imgview;

        public ViewHolder(View itemView) {
            super(itemView);

            circle_imgview = itemView.findViewById(R.id.coach_grid_img);
            imageView1 = itemView.findViewById(R.id.like_coach_grid);


            //          
        }
    }
}
