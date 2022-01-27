package com.master.design.rashnanthi.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.master.design.rashnanthi.DataModel.My_Event_1DM;
import com.master.design.rashnanthi.Helper.User;
import com.master.design.rashnanthi.R;

import java.util.ArrayList;

public class Adapter_MY_Event_1 extends RecyclerView.Adapter<Adapter_MY_Event_1.ViewHolder> {
    private Context context;
    private ArrayList<My_Event_1DM> my_event_1DMS;
    User user;


    int selectedPosition = 0;

    public Adapter_MY_Event_1(Context context, ArrayList<My_Event_1DM> my_event_1DMS) {
        this.context = context;
        this.my_event_1DMS = my_event_1DMS;
        user = new User(context);

    }


    @NonNull
    @Override
    public Adapter_MY_Event_1.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_event_1_recycle_view_item, parent, false);
        // set the view's size, margins, paddings and layout parameters
        Adapter_MY_Event_1.ViewHolder vh = new Adapter_MY_Event_1.ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter_MY_Event_1.ViewHolder holder, int position) {
        setDetails(holder, position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return my_event_1DMS.size();
    }


    private void setDetails(Adapter_MY_Event_1.ViewHolder viewHolder, int position) {
//        Picasso.with(context).load(arrayList.get(position).getImage_file()).into(viewHolder.img);
        viewHolder.date_time.setText(my_event_1DMS.get(position).getTextview());
        viewHolder.img_1.setImageResource(my_event_1DMS.get(position).getImageview1());

        viewHolder.img_2.setImageResource(my_event_1DMS.get(position).getImageview2());


        viewHolder.img_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        viewHolder.img_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView date_time;
        private ImageView img_1, img_2;

        public ViewHolder(View itemView) {
            super(itemView);
            date_time = itemView.findViewById(R.id.date_month_Txt);
            img_1 = itemView.findViewById(R.id.img_1);
            img_2 = itemView.findViewById(R.id.img_2);

            //          
        }
    }
}
