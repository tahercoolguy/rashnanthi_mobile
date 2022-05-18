package com.master.design.rashnanthi.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.master.design.rashnanthi.DataModel.SummaryForPaidData;
import com.master.design.rashnanthi.DataModel.SummaryForPaidEventData;
import com.master.design.rashnanthi.Helper.User;
import com.master.design.rashnanthi.R;

import java.util.ArrayList;


public class Adapter_Add_Event_Pay_Now extends RecyclerView.Adapter<Adapter_Add_Event_Pay_Now.ViewHolder> {
    private Context context;
    private ArrayList<SummaryForPaidData> data;
    User user;


    int selectedPosition = 0;

    public Adapter_Add_Event_Pay_Now(Context context, ArrayList<SummaryForPaidData> data) {
        this.context = context;
        this.data = data;
        user = new User(context);

    }


    @NonNull
    @Override
    public Adapter_Add_Event_Pay_Now.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.custum_item_for_pay_now_activity, parent, false);
        // set the view's size, margins, paddings and layout parameters
        Adapter_Add_Event_Pay_Now.ViewHolder vh = new Adapter_Add_Event_Pay_Now.ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter_Add_Event_Pay_Now.ViewHolder holder, int position) {
        setDetails(holder, position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    private void setDetails(Adapter_Add_Event_Pay_Now.ViewHolder viewHolder, int position) {

        viewHolder.post_price_countryTxt.setText(data.get(position).getCountry());
        viewHolder.priceTxt.setText(data.get(position).getCountryvalue());
        

       
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView post_price_countryTxt,priceTxt;
 

        public ViewHolder(View itemView) {
            super(itemView);
            post_price_countryTxt = itemView.findViewById(R.id.post_priceTxt);
            priceTxt = itemView.findViewById(R.id.priceTxt);
            

            //          
        }
    }
}
