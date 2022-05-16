package com.master.design.rashnanthi.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.master.design.rashnanthi.Activity.MainActivity;
import com.master.design.rashnanthi.DataModel.CountryData;
import com.master.design.rashnanthi.Helper.User;
import com.master.design.rashnanthi.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Adapter_Spinner extends BaseAdapter {
    private Context context;
    private ArrayList<CountryData> data;
    User user;


    public Adapter_Spinner(Context context, ArrayList<CountryData> data) {
        this.context = context;
        this.data = data;
        user = new User(context);

    }


    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return data.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        TextView countryTxt;
        if (convertView != null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.country_spinner_calender_row, null, false);
            countryTxt = convertView.findViewById(R.id.country_name);
            ImageView countryImg = convertView.findViewById(R.id.country_flag);

            countryTxt.setText(data.get(position).getTitle());
            countryImg.setImageResource(Integer.parseInt(data.get(position).getImage()));
        }
        return convertView;


    }
}
