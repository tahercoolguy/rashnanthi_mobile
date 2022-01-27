package com.master.design.rashnanthi.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.master.design.rashnanthi.DataModel.My_Event_DM;
import com.master.design.rashnanthi.DataModel.NotificationDM;
import com.master.design.rashnanthi.Helper.User;
import com.master.design.rashnanthi.R;

import java.util.ArrayList;

public class Adapter_My_Event extends RecyclerView.Adapter<Adapter_My_Event.ViewHolder> {
    private Context context;
    private ArrayList<My_Event_DM> my_event_dms;
     User user;


    int selectedPosition = 0;

    public Adapter_My_Event(Context context, ArrayList<My_Event_DM> my_event_dms) {
        this.context = context;
        this.my_event_dms = my_event_dms;
        user = new User(context);

    }


    @NonNull
    @Override
    public Adapter_My_Event.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_event_recycle_view_item, parent, false);
        // set the view's size, margins, paddings and layout parameters
        Adapter_My_Event.ViewHolder vh = new Adapter_My_Event.ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter_My_Event.ViewHolder holder, int position) {
        setDetails(holder, position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return my_event_dms.size();
    }


    private void setDetails(Adapter_My_Event.ViewHolder viewHolder, int position) {
//        Picasso.with(context).load(arrayList.get(position).getImage_file()).into(viewHolder.img);
        viewHolder.date_Time.setText(my_event_dms.get(position).getTextview());
        viewHolder.imgview_1.setImageResource(my_event_dms.get(position).getImageview1());

        viewHolder.imageview_2.setImageResource(my_event_dms.get(position).getImageview2());

//        viewHolder.read.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//            }
//        });
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView  date_Time;
        ImageView imgview_1,imageview_2;

        public ViewHolder(View itemView) {
            super(itemView);
            date_Time = itemView.findViewById(R.id.date_month_Txt);
            imgview_1 = itemView.findViewById(R.id.img_1);
            imageview_2 = itemView.findViewById(R.id.img_2);

            //          
        }
    }
}
