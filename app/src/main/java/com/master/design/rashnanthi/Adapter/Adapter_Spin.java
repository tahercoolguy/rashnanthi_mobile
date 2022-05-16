package com.master.design.rashnanthi.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.master.design.rashnanthi.DataModel.CountryData;
import com.master.design.rashnanthi.Helper.User;
import com.master.design.rashnanthi.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class Adapter_Spin extends BaseAdapter {
    private Context context;
    private ArrayList<CountryData> arrayList;
    private CountryData selected;
    private int position;
    User user;
    public ArrayList<CountryData> searchResults;

    public Adapter_Spin(Context context, ArrayList<CountryData> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
        this.searchResults = arrayList;
        user = new User(context);
    }

    @Override
    public int getCount() {
        return searchResults.size();
    }

    @Override
    public Object getItem(int position) {
        return searchResults.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.country_spinner_calender_row, parent, false);
            viewHolder = new ViewHolder(convertView);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        setDetails(viewHolder, position);
        return convertView;
    }
 
    private void setDetails(ViewHolder viewHolder, int position) {
        CountryData data = arrayList.get(position);
        viewHolder.name.setText(data.getTitle());
        viewHolder.img.setImageResource(Integer.parseInt(String.valueOf(data.getImage())));
//        Picasso.with(context).load(data.getImage()).into(viewHolder.img);

     }

    public CountryData getSelected() {
        return selected;
    }

    public void setSelected(CountryData selected) {
        this.selected = selected;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public static class ViewHolder {
          TextView name;
          ImageView img;

        private ViewHolder(View view) {
            name = view.findViewById(R.id.country_name);
            img = view.findViewById(R.id.country_flag);
        }
    }
}