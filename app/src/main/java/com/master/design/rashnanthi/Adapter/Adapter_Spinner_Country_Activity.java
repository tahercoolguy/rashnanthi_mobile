package com.master.design.rashnanthi.Adapter;

import android.app.Activity;
import android.app.Dialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.master.design.rashnanthi.Activity.Country_Spinner_Activity;
import com.master.design.rashnanthi.Activity.SpinneerActivity;
import com.master.design.rashnanthi.Controller.AppController;
import com.master.design.rashnanthi.DataModel.CountryData;
import com.master.design.rashnanthi.Helper.DialogUtil;
import com.master.design.rashnanthi.Helper.User;
import com.master.design.rashnanthi.R;
import com.master.design.rashnanthi.Utils.ConnectionDetector;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Adapter_Spinner_Country_Activity extends RecyclerView.Adapter<Adapter_Spinner_Country_Activity.ViewHolder> {
    private Activity context;
    private ArrayList<CountryData> countryOutputArrayList;
    Dialog progress;
    DialogUtil dialogUtil;
     AppController appController;
    ConnectionDetector connectionDetector;
    User user;


    int selectedPosition = 0;

    public Adapter_Spinner_Country_Activity(Activity context, ArrayList<CountryData> countryOutputArrayList) {
        this.context = context;
        this.countryOutputArrayList = countryOutputArrayList;
        dialogUtil = new DialogUtil();

        user = new User(context);
        appController = (AppController) context.getApplicationContext();

        connectionDetector = new ConnectionDetector(context);

    }


    @NonNull
    @Override
    public Adapter_Spinner_Country_Activity.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.custum_item_csa, parent, false);
        // set the view's size, margins, paddings and layout parameters
        Adapter_Spinner_Country_Activity.ViewHolder vh = new Adapter_Spinner_Country_Activity.ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter_Spinner_Country_Activity.ViewHolder holder, int position) {
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


    private void setDetails(Adapter_Spinner_Country_Activity.ViewHolder viewHolder, int position) {


        if (user.getLanguageCode().equalsIgnoreCase("en")) {


            viewHolder.nameTxt.setText(countryOutputArrayList.get(position).getTitle());
            viewHolder.codeTxt.setText(countryOutputArrayList.get(position).getCallingcode());
            Picasso.get().load(AppController.base_image_url + countryOutputArrayList.get(position).getImage()).into(viewHolder.img);

        }else{

            viewHolder.nameTxt.setText(countryOutputArrayList.get(position).getTitlear());
            Picasso.get().load(AppController.base_image_url + countryOutputArrayList.get(position).getImage()).into(viewHolder.img);
            viewHolder.codeTxt.setText(countryOutputArrayList.get(position).getCallingcode());


        }


        viewHolder.clickLL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ((Country_Spinner_Activity)  context).countryID = countryOutputArrayList.get(position).getId();
                ((Country_Spinner_Activity)  context).countryImg = countryOutputArrayList.get(position).getImage();
                ((Country_Spinner_Activity)  context).countrycodee = countryOutputArrayList.get(position).getCallingcode();

                if(user.getLanguageCode().equalsIgnoreCase("en")){
                    ((Country_Spinner_Activity)  context).countryName = countryOutputArrayList.get(position).getTitle();
                }else{
                    ((Country_Spinner_Activity)  context).countryName = countryOutputArrayList.get(position).getTitlear();
                }

                ((Country_Spinner_Activity)context).FinishThis();

            }
        });
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView nameTxt,codeTxt;
        private ImageView img;
        LinearLayout clickLL;


        public ViewHolder(View itemView) {
            super(itemView);
            nameTxt = itemView.findViewById(R.id.nameTxt);
            codeTxt = itemView.findViewById(R.id.codeTxt);
            img = itemView.findViewById(R.id.img);
            clickLL = itemView.findViewById(R.id.clickLL);

        }
    }
}
