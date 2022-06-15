


package com.master.design.rashnanthi.Adapter;

import static java.security.AccessController.getContext;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;

import com.master.design.rashnanthi.DataModel.CountryData;
import com.master.design.rashnanthi.DataModel.County_ItemDM;
import com.master.design.rashnanthi.DataModel.County_ItemDM;
import com.master.design.rashnanthi.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class Adapter_Country_Spinner extends ArrayAdapter<County_ItemDM> {

    LayoutInflater inflater;
    CountryData data;   public Resources res;

//    public Adapter_Country_Spinner(Context context,
//                                   ArrayList<CountryData> County_ItemDMS) {
//        super(context, 0, County_ItemDMS);
//    }

    public Adapter_Country_Spinner(Context context,ArrayList<CountryData> data,
            FragmentActivity activitySpinner,
            int textViewResourceId,
            ArrayList objects,
            Resources resLocal)
    {
        super(activitySpinner, textViewResourceId, objects);

        objects     = objects;
        data =objects;
        res      = resLocal;
        inflater = (LayoutInflater) activitySpinner.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public Adapter_Country_Spinner(Context context, ArrayList<CountryData> data) {
        super(context,data.size());
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable
            View convertView, @NonNull ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    @Override
    public View getDropDownView(int position, @Nullable
            View convertView, @NonNull ViewGroup parent) {
        return getCustomView(position, convertView, parent);
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


        County_ItemDM County_ItemDM = getItem(position);


//        AlgorithmItem currentItem = getItem(position);

        // It is used the name to the TextView when the
        // current item is not null.
        if (County_ItemDM != null) {
            country_name_Img.setImageResource(Integer.parseInt(String.valueOf(County_ItemDM.getMcountryImage())));
            country_name_Txt.setText(County_ItemDM.getMcountryName());


        }
        return convertView;
    }

    private View getCustomView(int position, View convertView, ViewGroup parent) {

        View row = inflater.inflate(R.layout.country_spinner_calender_row, parent, false);

        CountryData countryData = null;
//        countryData = (CountryData) countryData.get(position);

        TextView name = (TextView) row.findViewById(R.id.country_name);
        ImageView img = (ImageView) row.findViewById(R.id.country_flag);

        if (position == 0) {
            name.setText("Kuwait");
            img.setImageResource(R.drawable.kuwaitt);
//            line.setVisibility(View.GONE);
        } else {
            // Set values for spinner each row
            name.setText(countryData.getTitle());
//            Picasso.with(getContext()).load(countryData.get(position).getImage());
        }


        return row;
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
//    private ArrayList<County_ItemDM> County_ItemDMArrayList;
//    User user;
//
//
//    int selectedPosition = 0;
//
//    public Adapter_Country_Spinner(Context context, ArrayList<County_ItemDM> County_ItemDMArrayList) {
//        this.context = context;
//        this.County_ItemDMArrayList = County_ItemDMArrayList;
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
//        return County_ItemDMArrayList.size();
//    }
//
//
//    private void setDetails(Adapter_Country_Spinner.ViewHolder viewHolder, int position) {
//
//
//        viewHolder.country_img.setImageResource(County_ItemDMArrayList.get(position).getMcountryImage());
//        viewHolder.country_name_Txt.setText(County_ItemDMArrayList.get(position).getMcountryName());
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
