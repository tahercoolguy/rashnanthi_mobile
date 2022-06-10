package com.master.design.rashnanthi.Adapter;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.master.design.rashnanthi.Activity.Activity_Add_Event_1;
import com.master.design.rashnanthi.Activity.Add_new_post_1;
import com.master.design.rashnanthi.Activity.MainActivity;
import com.master.design.rashnanthi.Activity.SpinneerActivity;
import com.master.design.rashnanthi.Controller.AppController;
import com.master.design.rashnanthi.DataModel.CountryData;
import com.master.design.rashnanthi.DataModel.CountryOutput;
import com.master.design.rashnanthi.DataModel.DeleteEventRootDM;
import com.master.design.rashnanthi.DataModel.MyEventData1;
import com.master.design.rashnanthi.DataModel.MyEventImageData1;
import com.master.design.rashnanthi.Fragments.Calender_Fragment;
import com.master.design.rashnanthi.Helper.DialogUtil;
import com.master.design.rashnanthi.Helper.ResponseListener;
import com.master.design.rashnanthi.Helper.ResponseListener1;
import com.master.design.rashnanthi.Helper.User;
import com.master.design.rashnanthi.R;
import com.master.design.rashnanthi.Utils.ConnectionDetector;
import com.master.design.rashnanthi.Utils.Helper;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class Adapter_Spinner_Country extends RecyclerView.Adapter<Adapter_Spinner_Country.ViewHolder> {
    private Activity context;
    private ArrayList<CountryData> countryOutputArrayList;
    Dialog progress;
    DialogUtil dialogUtil;
    ResponseListener1 responseListener;
    AppController appController;
    ConnectionDetector connectionDetector;
    User user;


    int selectedPosition = 0;

    public Adapter_Spinner_Country(Activity context, ArrayList<CountryData> countryOutputArrayList) {
        this.context = context;
        this.countryOutputArrayList = countryOutputArrayList;
        dialogUtil = new DialogUtil();

        user = new User(context);
        appController = (AppController) context.getApplicationContext();

        connectionDetector = new ConnectionDetector(context);

    }


    @NonNull
    @Override
    public Adapter_Spinner_Country.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.csa, parent, false);
        // set the view's size, margins, paddings and layout parameters
        Adapter_Spinner_Country.ViewHolder vh = new Adapter_Spinner_Country.ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter_Spinner_Country.ViewHolder holder,int position) {
        setDetails(holder, position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return countryOutputArrayList.size();
    }


    private void setDetails(Adapter_Spinner_Country.ViewHolder viewHolder, int position) {
        viewHolder.country_name.setText(countryOutputArrayList.get(position).getTitle());
        Picasso.get().load(AppController.base_image_url + countryOutputArrayList.get(position).getImage()).into(viewHolder.country_flag);


        viewHolder.clickLL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ((SpinneerActivity)  context).countryID = countryOutputArrayList.get(position).getId();
                ((SpinneerActivity)  context).countryImg = countryOutputArrayList.get(position).getImage();
                ((SpinneerActivity)  context).countryName = countryOutputArrayList.get(position).getTitle();
                ((SpinneerActivity)context).FinishThis();

            }
        });
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView country_name;
        private ImageView country_flag;
        LinearLayout clickLL;


        public ViewHolder(View itemView) {
            super(itemView);
            country_name = itemView.findViewById(R.id.country_name);
            country_flag = itemView.findViewById(R.id.country_flag);
            clickLL = itemView.findViewById(R.id.clickLL);

        }
    }
}
