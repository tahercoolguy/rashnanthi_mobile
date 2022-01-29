


package com.master.design.rashnanthi.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.master.design.rashnanthi.DataModel.County_ItemDM;
import com.master.design.rashnanthi.R;

import java.util.ArrayList;


public class Adapter_Country_Spinner extends ArrayAdapter<County_ItemDM> {


    public Adapter_Country_Spinner(Context context,
                                   ArrayList<County_ItemDM> county_itemDMS) {
        super(context, 0, county_itemDMS);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable
            View convertView, @NonNull ViewGroup parent) {
        return initView(position, convertView, parent);
    }

    @Override
    public View getDropDownView(int position, @Nullable
            View convertView, @NonNull ViewGroup parent) {
        return initView(position, convertView, parent);
    }

    private View initView(int position, View convertView,
                          ViewGroup parent) {
        // It is used to set our custom view.
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.country_spinner_calender_row, parent, false);
        }

        TextView country_name_Txt = convertView.findViewById(R.id.country_name);

        ImageView country_name_Img = convertView.findViewById(R.id.country_flag);

//        ImageView country_img;
//        TextView country_name_Txt;


        County_ItemDM county_itemDM = getItem(position);


//        AlgorithmItem currentItem = getItem(position);

        // It is used the name to the TextView when the
        // current item is not null.
        if (county_itemDM != null) {
            country_name_Img.setImageResource(county_itemDM.getMcountryImage());
            country_name_Txt.setText(county_itemDM.getMcountryName());


        }
        return convertView;
    }
}


//
//import android.content.Context;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.master.design.rashnanthi.DataModel.County_ItemDM;
//import com.master.design.rashnanthi.Helper.User;
//import com.master.design.rashnanthi.R;
//
//import java.util.ArrayList;
//
//public class Adapter_Country_Spinner extends RecyclerView.Adapter<Adapter_Country_Spinner.ViewHolder> {
//    private Context context;
//    private ArrayList<County_ItemDM> county_itemDMArrayList;
//    User user;
//
//
//    int selectedPosition = 0;
//
//    public Adapter_Country_Spinner(Context context, ArrayList<County_ItemDM> county_itemDMArrayList) {
//        this.context = context;
//        this.county_itemDMArrayList = county_itemDMArrayList;
//        user = new User(context);
//
//    }
//
//
//    @NonNull
//    @Override
//    public Adapter_Country_Spinner.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.country_spinner_calender_row, parent, false);
//        // set the view's size, margins, paddings and layout parameters
//        Adapter_Country_Spinner.ViewHolder vh = new Adapter_Country_Spinner.ViewHolder(v);
//        return vh;
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull Adapter_Country_Spinner.ViewHolder holder, int position) {
//        setDetails(holder, position);
//    }
//
//    @Override
//    public long getItemId(int position) {
//        return position;
//    }
//
//    @Override
//    public int getItemCount() {
//        return county_itemDMArrayList.size();
//    }
//
//
//    private void setDetails(Adapter_Country_Spinner.ViewHolder viewHolder, int position) {
//
//
//        viewHolder.country_img.setImageResource(county_itemDMArrayList.get(position).getMcountryImage());
//        viewHolder.country_name_Txt.setText(county_itemDMArrayList.get(position).getMcountryName());
//
//
//    }
//
//
//    public static class ViewHolder extends RecyclerView.ViewHolder {
//
//        ImageView country_img;
//        TextView country_name_Txt;
//
//        public ViewHolder(View itemView) {
//            super(itemView);
//
//            country_img = itemView.findViewById(R.id.country_flag);
//            country_name_Txt = itemView.findViewById(R.id.country_name);
//
//        }
//    }
//}
