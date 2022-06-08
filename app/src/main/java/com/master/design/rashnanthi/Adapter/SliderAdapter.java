package com.master.design.rashnanthi.Adapter;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.net.Uri;
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

import butterknife.BindView;

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

        if(eventsDetailsData.get(position).getImagedata()!=null)
        {
            if(eventsDetailsData.get(position).getImagedata().get(position).getImage()!=null) {
                Picasso.get().load(AppController.base_image_url + eventsDetailsData.get(position).getImagedata().get(position).getEventorstory()).into(viewHolder.imageViewBackground);
            }
        }else{
            Picasso.get().load(AppController.base_image_url + eventsDetailsData.get(position).getImage()).into(viewHolder.imageViewBackground);

//            Helper.showToast(context,"Event Images Does not exist");
        }


        if(!eventsDetailsData.get(position).getInstagram().equalsIgnoreCase(""))
        {
            viewHolder.insta.setVisibility(View.VISIBLE);
            viewHolder.insta.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }

        if(!eventsDetailsData.get(position).getWhatsapnumber().equalsIgnoreCase(""))
        {
            viewHolder.whts.setVisibility(View.VISIBLE);
            viewHolder.whts.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String url = "https://api.whatsapp.com/send?phone=" + eventsDetailsData.get(position).getWhatsapcountrycode()+ eventsDetailsData.get(position).getWhatsapnumber();
                    try {
                        PackageManager pm = context.getPackageManager();
                        pm.getPackageInfo("com.whatsapp", PackageManager.GET_ACTIVITIES);
                        Intent i = new Intent(Intent.ACTION_VIEW);
                        i.setData(Uri.parse(url));
                        context.startActivity(i);
                    } catch (PackageManager.NameNotFoundException e) {
                        Helper.showToast(context, "Whatsapp app not installed in your phone");

                        e.printStackTrace();
                    }                }
            });
        }

        if(!eventsDetailsData.get(position).getSnapchat().equalsIgnoreCase(""))
        {
            viewHolder.snap.setVisibility(View.VISIBLE);
            viewHolder.snap.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://snapchat.com/add/" + eventsDetailsData.get(position).getSnapchat()));
                        intent.setPackage("com.snapchat.android");
                        context.startActivity(intent);
                    } catch (Exception e) {
                        Helper.showToast(context, "Snapchat app not installed in your phone");


                        e.printStackTrace();
                    }
                }
            });
        }

        if(!eventsDetailsData.get(position).getWebsite().equalsIgnoreCase(""))
        {
            viewHolder.web.setVisibility(View.VISIBLE);
            viewHolder.web.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://" + eventsDetailsData.get(position).getWebsite()));
                        context.startActivity(myIntent);
                    } catch (ActivityNotFoundException e) {

                        Helper.showToast(context, "No application can handle this request."
                                + " Please install a web browser");
                        e.printStackTrace();
                    }
                }
            });
        }

        if(!eventsDetailsData.get(position).getInstagram().equalsIgnoreCase(""))
        {
            viewHolder.insta.setVisibility(View.VISIBLE);
            viewHolder.insta.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Uri uri = Uri.parse("http://instagram.com/" + eventsDetailsData.get(position).getInstagram());


                    Intent i = new Intent(Intent.ACTION_VIEW, uri);

                    i.setPackage("com.instagram.android");

                    try {
                        context.startActivity(i);
                    } catch (ActivityNotFoundException e) {

                         context.startActivity(new Intent(Intent.ACTION_VIEW,
                                Uri.parse("http://instagram.com/" + eventsDetailsData.get(position).getInstagram())));
                    }
                }
            });
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
        ImageView imageViewBackground,snap,whts,insta,web ;

        public SliderAdapterViewHolder(View itemView) {
            super(itemView);
            imageViewBackground = itemView.findViewById(R.id.myimage);
            snap = itemView.findViewById(R.id.snapImg);
            whts = itemView.findViewById(R.id.wtsapImg);
            insta = itemView.findViewById(R.id.instaImg);
            web = itemView.findViewById(R.id.webImg);

            this.itemView = itemView;
        }
    }
}
