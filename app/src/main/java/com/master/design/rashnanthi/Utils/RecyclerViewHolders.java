package com.master.design.rashnanthi.Utils;


import android.view.View;
import android.widget.TextView;

import com.master.design.rashnanthi.R;

import androidx.recyclerview.widget.RecyclerView;

public class RecyclerViewHolders extends RecyclerView.ViewHolder{

    private static final String TAG = RecyclerViewHolders.class.getSimpleName();

    public TextView profileHeader;

    public TextView profileContent;

    public RecyclerViewHolders(final View itemView) {
        super(itemView);
        profileHeader = (TextView)itemView.findViewById(R.id.heading);
        profileContent = (TextView) itemView.findViewById(R.id.profile_content);
    }
}