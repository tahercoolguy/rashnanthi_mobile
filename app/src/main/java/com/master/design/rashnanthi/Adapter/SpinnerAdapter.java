package com.master.design.rashnanthi.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.master.design.rashnanthi.DataModel.CountryData;
import com.master.design.rashnanthi.R;

import java.util.ArrayList;

public class SpinnerAdapter extends BaseAdapter {

    private ArrayList<CountryData> data;
    private Context context;
    private LayoutInflater inflater;

    public SpinnerAdapter(Context context, int country_spinner_calender_row, int country_flag, int country_name, ArrayList<CountryData> alCountry) {
        context = context;
        this.data = alCountry;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }



    class Holder {
        private TextView tvCountryName;
        ImageView imgCountry;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        View myView = null;
        try {
            Holder holder;
            myView = convertView;

            if (myView == null) {
                inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                myView = inflater.inflate(R.layout.country_spinner_calender_row, null);

                holder = new Holder();
                holder.tvCountryName = (TextView) myView.findViewById(R.id.country_name);
                myView.setTag(holder);
            } else {
                holder = (Holder) myView.getTag();
            }

            holder.tvCountryName.setText(data.get(i).getTitle());
            holder.imgCountry.setImageResource(Integer.parseInt(data.get(0).getImage()));

        } catch (Exception e) {
            e.printStackTrace();
        }

        return myView;
    }

    // Below code is used to hide first item.

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        View v = null;
        if (position == 0) {
            TextView tv = new TextView(context);
            tv.setVisibility(View.GONE);
            tv.setHeight(0);
            v = tv;
            v.setVisibility(View.GONE);
        } else v = super.getDropDownView(position, null, parent);
        return v;

    }

}
