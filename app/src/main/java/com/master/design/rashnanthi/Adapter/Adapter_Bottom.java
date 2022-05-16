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

import java.util.ArrayList;
import java.util.List;

public class Adapter_Bottom extends BaseAdapter implements Filterable {
    private Context context;
    private ArrayList<CountryData> arrayList;
    private CountryData selected;
    private int position;
    User user;
    public ArrayList<CountryData> searchResults;

    public Adapter_Bottom(Context context, ArrayList<CountryData> arrayList) {
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
            convertView = inflater.inflate(R.layout.country_name_row, parent, false);
            viewHolder = new ViewHolder(convertView);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        setDetails(viewHolder, position);
        return convertView;
    }

    @Override
    public Filter getFilter() {
        Filter filter = new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {

                FilterResults filterResults = new FilterResults();
                if (constraint == null || constraint.length() == 0) {
                    filterResults.count = arrayList.size();
                    filterResults.values = arrayList;

                } else {
                    List<CountryData> resultsModel = new ArrayList<>();
                    String searchStr = constraint.toString().toLowerCase();

                    for (CountryData itemsModel : arrayList) {
                        if (itemsModel.getCurrencycode().toLowerCase().contains(searchStr)) {
                            resultsModel.add(itemsModel);
                        }
                    }
                    filterResults.count = resultsModel.size();
                    filterResults.values = resultsModel;

                }

                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {

                searchResults = (ArrayList<CountryData>) results.values;
                notifyDataSetChanged();

            }
        };
        return filter;
    }

    private void setDetails(ViewHolder viewHolder, int position) {
        CountryData data = searchResults.get(position);
        if (user.getLanguageCode().equalsIgnoreCase("en")) {
            viewHolder.country_code.setText(String.format("%s", data.getCallingcode()));
            viewHolder.CN.setText(arrayList.get(position).getTitle());
        }
        else {
            viewHolder.country_code.setText(String.format("%s", data));
        }
//        Glide.with(context).load(data.getBrandimagefullurl()).apply(new RequestOptions().placeholder(R.drawable.app_icon)).into(viewHolder.imageView);
//        viewHolder.imageView.setVisibility(View.GONE);
        if (selected == data) {
            viewHolder.iv_selected.setVisibility(View.VISIBLE);

        } else {
            viewHolder.iv_selected.setVisibility(View.INVISIBLE);
        }
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

    private static class ViewHolder {
        private TextView country_code,CN;
        private  ImageView  iv_selected;

        private ViewHolder(View view) {
            country_code = view.findViewById(R.id.country_code);
            CN = view.findViewById(R.id.country_name);
            iv_selected = view.findViewById(R.id.iv_selected);
        }
    }
}