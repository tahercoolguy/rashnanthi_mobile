


package com.master.design.rashnanthi.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.master.design.rashnanthi.DataModel.Country_CodeDM;
import com.master.design.rashnanthi.DataModel.Country_NameDM;
import com.master.design.rashnanthi.R;

import java.util.ArrayList;


public class Adapter_Country_Code_Only_Spinner extends ArrayAdapter<Country_CodeDM> {


    public Adapter_Country_Code_Only_Spinner(Context context,
                                             ArrayList<Country_CodeDM> country_codeDMS) {
        super(context, 0, country_codeDMS);
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


        Country_CodeDM country_codeDM = getItem(position);


//        AlgorithmItem currentItem = getItem(position);

        // It is used the name to the TextView when the
        // current item is not null.
        if (country_codeDM != null) {
             country_name_Txt.setText(country_codeDM.getCountry_code());


        }
        return convertView;
    }
}

