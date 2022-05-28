package com.master.design.rashnanthi.Adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.master.design.rashnanthi.Controller.AppController;
import com.master.design.rashnanthi.DataModel.CoachDM;
import com.master.design.rashnanthi.DataModel.EventsDetailsData;
import com.master.design.rashnanthi.DataModel.EventsDetailsImageData;
import com.master.design.rashnanthi.DataModel.SliderData;
import com.master.design.rashnanthi.R;
import com.master.design.rashnanthi.Utils.Helper;
import com.smarteist.autoimageslider.SliderViewAdapter;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class SliderAdapter extends SliderViewAdapter<SliderAdapter.SliderAdapterViewHolder> {

    // list for storing urls of images.
//    private final List<SliderData> mSliderItems;
    ArrayList<EventsDetailsData> eventsDetailsData;
    Context context;
    ArrayList<EventsDetailsImageData> eventsDetailsImageData;

    // Constructor
    public SliderAdapter(Context context, ArrayList<EventsDetailsData> eventsDetailsData) {
        this.eventsDetailsData = eventsDetailsData;
        this.context=context;
    }

    // We are inflating the slider_layout
    // inside on Create View Holder method.
    @Override
    public SliderAdapterViewHolder onCreateViewHolder(ViewGroup parent) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.slider_layout, null);
        return new SliderAdapterViewHolder(inflate);
    }

    // Inside on bind view holder we will
    // set data to item of Slider View.
    @Override
    public void onBindViewHolder(SliderAdapterViewHolder viewHolder, int position) {

        if(eventsDetailsData.get(position).getImagedata().get(position).getImage()!=null){
            Picasso.get().load(AppController.base_image_url + eventsDetailsData.get(position).getImagedata().get(position).getImage()).into(viewHolder.imageViewBackground);

        }else{
            Helper.showToast(context,"Event Images Does not exist");
        }




        // Glide is use to load image
        // from url in your imageview.
//        Glide.with(viewHolder.itemView)
//                .load(eventsDetailsData.get(position).getImagedata().get(position).getImage())
//                .fitCenter()
//                .into(viewHolder.imageViewBackground);
    }

    // this method will return
    // the count of our list.
    @Override
    public int getCount() {
        return eventsDetailsData.size();
    }

    static class SliderAdapterViewHolder extends SliderViewAdapter.ViewHolder {
        // Adapter class for initializing
        // the views of our slider view.
        View itemView;
        ImageView imageViewBackground ;

        public SliderAdapterViewHolder(View itemView) {
            super(itemView);
            imageViewBackground = itemView.findViewById(R.id.myimage);

            this.itemView = itemView;
        }
    }
}
