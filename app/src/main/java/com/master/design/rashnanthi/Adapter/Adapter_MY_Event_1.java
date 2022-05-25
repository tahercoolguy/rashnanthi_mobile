package com.master.design.rashnanthi.Adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.solver.SolverVariableValues;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.github.siyamed.shapeimageview.RoundedImageView;
import com.google.android.material.textfield.TextInputEditText;
import com.master.design.rashnanthi.Activity.Activity_Add_Event_1;
import com.master.design.rashnanthi.Activity.ImageActicity;
import com.master.design.rashnanthi.Activity.MainActivity;
import com.master.design.rashnanthi.Activity.SignUpActivity;
import com.master.design.rashnanthi.Controller.AppController;
import com.master.design.rashnanthi.DataModel.DeleteEventRootDM;
import com.master.design.rashnanthi.DataModel.MyEventData;
import com.master.design.rashnanthi.DataModel.MyEventImageData;
import com.master.design.rashnanthi.DataModel.MyEventsRootDM;
import com.master.design.rashnanthi.DataModel.My_Event_1DM;
import com.master.design.rashnanthi.Helper.User;
import com.master.design.rashnanthi.R;
import com.master.design.rashnanthi.Utils.ConnectionDetector;
import com.master.design.rashnanthi.Utils.Helper;
import com.squareup.picasso.Picasso;


import java.util.ArrayList;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class Adapter_MY_Event_1 extends RecyclerView.Adapter<Adapter_MY_Event_1.ViewHolder> {
    private Context context;
    private ArrayList<MyEventImageData> myEventData;
    Activity_Add_Event_1 activity_add_event_1;

    AppController appController;
    ConnectionDetector connectionDetector;
    User user;


    int selectedPosition = 0;

    public Adapter_MY_Event_1(Context context, ArrayList<MyEventImageData> myEventData) {
        this.context = context;
        this.myEventData = myEventData;
        user = new User(context);
        appController = (AppController) context.getApplicationContext();

        connectionDetector = new ConnectionDetector(context);

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
        return myEventData.size();
    }


    private void setDetails(Adapter_MY_Event_1.ViewHolder viewHolder, int position) {
        viewHolder.date_time.setText(myEventData.get(position).getDate());

        Picasso.get().load(AppController.base_image_url + myEventData.get(position).getImage()).into(viewHolder.img_1);
        Picasso.get().load(AppController.base_image_url + myEventData.get(position).getImage()).into(viewHolder.img_2);


        viewHolder.delete_Img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (connectionDetector.isConnectingToInternet()) {

                    appController.paServices.DeletEvent(myEventData.get(position).getId(), new Callback<DeleteEventRootDM>() {
                        @Override
                        public void success(DeleteEventRootDM deleteEventRootDM, Response response) {
                            if (deleteEventRootDM.getOutput().getSuccess().equalsIgnoreCase("1")) {

                                myEventData.remove(selectedPosition);
                                notifyItemRemoved(selectedPosition);
                                Helper.showToast(context, "item deleted succesfully");

                            } else
                                Helper.showToast(context, "item does not deleted");
                        }

                        @Override
                        public void failure(RetrofitError retrofitError) {
                            Log.e("error", retrofitError.toString());

                        }
                    });
                } else
                    Helper.showToast(context, String.valueOf(R.string.no_internet_connection));
            }
        });


        viewHolder.edit_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                activity_add_event_1.AddEventByCreatorAPI();

                viewHolder.date_time.setText(myEventData.get(position).getDate());
                Picasso.get().load(AppController.base_image_url + myEventData.get(position).getImage()).into(viewHolder.img_1);
                Picasso.get().load(AppController.base_image_url + myEventData.get(position).getImage()).into(viewHolder.img_2);
                MyEventData myEventData1 = new MyEventData();

                Intent intent = new Intent(context, Activity_Add_Event_1.class);
                intent.putExtra("image1", myEventData.get(position).getImage());
                intent.putExtra("date", myEventData.get(position).getDate());
                intent.putExtra("image2", myEventData.get(position).getImage());
                intent.putExtra("eventid", myEventData.get(position).getId());
                context.startActivity(intent);

            }
        });


    }


    public void Edit_Post_API() {

    }

    public void Delete_Post_API() {

    }


    public static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView date_time;
        private ImageView img_1, img_2, edit_img, delete_Img;


        public ViewHolder(View itemView) {
            super(itemView);
            date_time = itemView.findViewById(R.id.date_month_Txt);
            img_1 = itemView.findViewById(R.id.img_1);
            img_2 = itemView.findViewById(R.id.img_2);
            edit_img = itemView.findViewById(R.id.edit_img);
            delete_Img = itemView.findViewById(R.id.delete_Img);

        }
    }
}
