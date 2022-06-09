package com.master.design.rashnanthi.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.master.design.rashnanthi.DataModel.MyNotificationOutput;
import com.master.design.rashnanthi.DataModel.MyNotificationOutputData;
import com.master.design.rashnanthi.DataModel.NotificationDM;
import com.master.design.rashnanthi.Helper.User;
import com.master.design.rashnanthi.R;

import java.util.ArrayList;

public class Adapter_Notification extends RecyclerView.Adapter<Adapter_Notification.ViewHolder> {
    private Context context;
    private ArrayList<MyNotificationOutputData> myNotificationOutputDataArrayList;
    private ArrayList<MyNotificationOutput> myNotificationOutputs;
     User user;


    int selectedPosition = 0;

    public Adapter_Notification(Context context, ArrayList<MyNotificationOutputData> myNotificationOutputDataArrayList ) {
        this.context = context;
        this.myNotificationOutputDataArrayList = myNotificationOutputDataArrayList;
         user = new User(context);

    }


    @NonNull
    @Override
    public Adapter_Notification.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.notification_recycle_view_item, parent, false);
        // set the view's size, margins, paddings and layout parameters
        Adapter_Notification.ViewHolder vh = new Adapter_Notification.ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter_Notification.ViewHolder holder, int position) {
        setDetails(holder, position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return myNotificationOutputDataArrayList.size();
    }


    private void setDetails(Adapter_Notification.ViewHolder viewHolder, int position) {
//        Picasso.with(context).load(arrayList.get(position).getImage_file()).into(viewHolder.img);
        viewHolder.time.setText(myNotificationOutputDataArrayList.get(position).getDate());
        viewHolder.tittle.setText("");

        viewHolder.messege.setText(myNotificationOutputDataArrayList.get(position).getMessage());

//        viewHolder.read.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//            }
//        });
    }

//    public ArrayList<NotificationDM> getSelected() {
//        return notificationDMS;
//    }
//
//    public void setSelected(NotificationDM selected) {
//        this.notificationDMS = notificationDMS;
//    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView  tittle,time,messege;

        public ViewHolder(View itemView) {
            super(itemView);
            tittle = itemView.findViewById(R.id.notification_title_Txt);
            time = itemView.findViewById(R.id.notification_time_Txt);
            messege = itemView.findViewById(R.id.notificatiom_messege_Txt);

            //          
        }
    }
}
