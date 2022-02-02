


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

import com.master.design.rashnanthi.DataModel.Country_NameDM;
import com.master.design.rashnanthi.DataModel.County_ItemDM;
import com.master.design.rashnanthi.R;

import java.util.ArrayList;


public class Adapter_Country_Name_Spinner extends ArrayAdapter<Country_NameDM> {


    public Adapter_Country_Name_Spinner(Context context,
                                        ArrayList<Country_NameDM> countryNameDMS) {
        super(context, 0, countryNameDMS);
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
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.country_name_row, parent, false);
        }

        TextView country_name_Txt = convertView.findViewById(R.id.country_name);


//        ImageView country_img;
//        TextView country_name_Txt;


        Country_NameDM country_nameDM = getItem(position);


//        AlgorithmItem currentItem = getItem(position);

        // It is used the name to the TextView when the
        // current item is not null.
        if (country_nameDM != null) {
             country_name_Txt.setText(country_nameDM.getCountry_name());


        }
        return convertView;
    }
}

