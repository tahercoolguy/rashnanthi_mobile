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
import java.util.List;

public class KecamatanAdapter extends ArrayAdapter<CountryData> {

    private ArrayList<CountryData> data = new ArrayList<>();


    public KecamatanAdapter(@NonNull Context context, int resource, int spinnerText, @NonNull ArrayList<CountryData> data) {

        super(context, resource, spinnerText, data);

        this.data = data;

    }


    @Override

    public CountryData getItem(int position) {

        return data.get(position);

    }


    @NonNull

    @Override

    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        return initView(position);


    }


    @Override

    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        return initView(position);

    }

    private View initView(int position) {

        CountryData countryData = getItem(position);

        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View v = inflater.inflate(R.layout.country_spinner_calender_row, null);

        TextView textView = v.findViewById(R.id.country_name);
        ImageView imageView=v.findViewById(R.id.country_flag);

        textView.setText(countryData.getTitle());
        imageView.setImageResource(Integer.parseInt(countryData.getImage()));

        return v;


    }

}