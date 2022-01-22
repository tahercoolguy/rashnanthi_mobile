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
import androidx.recyclerview.widget.RecyclerView;

import com.master.design.rashnanthi.DataModel.County_Item;
import com.master.design.rashnanthi.DataModel.ShopsResult;
import com.master.design.rashnanthi.Helper.User;
import com.master.design.rashnanthi.R;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class Country_Adapter  extends ArrayAdapter<County_Item> {
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initview(position,convertView,parent);
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return super.getDropDownView(position, convertView, parent);
    }

    public Country_Adapter(Context context, ArrayList<County_Item> county_items){
        super(context,0,county_items);

    }

    private  View initview(int position,View convertView,ViewGroup parent){
        if(convertView==null){
            convertView=LayoutInflater.from(getContext()).inflate(R.layout.country_spinner_calender_row,parent,false);

        }
        ImageView imageviewFlag=convertView.findViewById(R.id.country_flag);
        TextView textCountry=convertView.findViewById(R.id.country_name);

        County_Item county_item =getItem(position);

        if(county_item!=null) {
            imageviewFlag.setImageResource(county_item.getMcountryImage());
            textCountry.setText(county_item.getMcountryName());
        }
        return convertView;

    }
}

//public class Country_Adapter extends RecyclerView.Adapter<Country_Adapter.ViewHolder> {
//    private Context context;
//    private ArrayList<County_Item> arrayList;
//    private ShopsResult selected;
//    User user;
//
//
//    int selectedPosition = 0;
//
//    public Country_Adapter(Context context, ArrayList<County_Item> arrayList) {
//        this.context = context;
//        this.arrayList = arrayList;
//        user = new User(context);
//
//    }
//
//
//    @NonNull
//    @Override
//    public Country_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.country_spinner_calender_row, parent, false);
//        // set the view's size, margins, paddings and layout parameters
//        Country_Adapter.ViewHolder vh = new Country_Adapter.ViewHolder(v);
//        return vh;
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull Country_Adapter.ViewHolder holder, int position) {
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
//        return arrayList.size();
//    }
//
//
//    private void setDetails(Country_Adapter.ViewHolder viewHolder, int position) {
//         viewHolder.companyName.setText(arrayList.get(position).getMcountryName());
//        viewHolder.companyIcon.setImage(arrayList.get(position).getMcountryImage());
//
//    }
//
//    public ShopsResult getSelected() {
//        return selected;
//    }
//
//    public void setSelected(ShopsResult selected) {
//        this.selected = selected;
//    }
//
//    public static class ViewHolder extends RecyclerView.ViewHolder {
//
//        private TextView ,status,location;
//        private ImageView companyIcon,img;
//
//        public ViewHolder(View itemView) {
//            super(itemView);
//            img = itemView.findViewById(R.id.img);
//            companyIcon = itemView.findViewById(R.id.companyIcon);
//            companyName = itemView.findViewById(R.id.companyName);
//            status= itemView.findViewById(R.id.status);
//            location = itemView.findViewById(R.id.location);
//
//            //
//        }
//    }
//}
