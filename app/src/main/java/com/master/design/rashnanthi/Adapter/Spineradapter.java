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

import com.master.design.rashnanthi.DataModel.CountryData;
import com.master.design.rashnanthi.R;

import java.util.ArrayList;

public class Spineradapter extends ArrayAdapter {

    private ArrayList<CountryData> countryData;
    CountryData countryData1;
    LayoutInflater inflater;

    public Spineradapter(@NonNull Context context, int resourceId, int textviewId, int country_name, ArrayList<CountryData> countryDataArrayList) {
        super(context, resourceId,textviewId, countryDataArrayList);
        countryData = countryDataArrayList;
     }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.country_spinner_calender_row, parent, false);

        }
        TextView name = (TextView) convertView.findViewById(R.id.country_name);
        ImageView img = (ImageView) convertView.findViewById(R.id.country_flag);

        if (countryData.get(position).getTitle() != null || countryData.get(position).getImage() != null) {
            name.setText(countryData.get(position).getTitle());
            img.setImageResource(Integer.parseInt(countryData.get(position).getImage()));
        }


        return convertView;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.country_spinner_calender_row, parent, false);

        }
        TextView name = (TextView) convertView.findViewById(R.id.country_name);
        ImageView img = (ImageView) convertView.findViewById(R.id.country_flag);


        if (countryData.get(position).getTitle() != null || countryData.get(position).getImage() != null) {
            name.setText(countryData.get(position).getTitle());
            img.setImageResource(Integer.parseInt(countryData.get(position).getImage()));
        }

        return convertView;
    }
}
