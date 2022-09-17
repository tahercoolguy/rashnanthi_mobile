package com.master.design.rashnanthi.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.master.design.rashnanthi.Activity.ImageActicity;
import com.master.design.rashnanthi.Controller.AppController;
import com.master.design.rashnanthi.DataModel.CoachesWithPostsDatam;
import com.master.design.rashnanthi.DataModel.CoachesWithPostsImageDatam;
import com.master.design.rashnanthi.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by kharag on 18-03-2020.
 */
public class SliderPagerAdapter1 extends PagerAdapter {
    private LayoutInflater layoutInflater;
    private Context context;
    private ArrayList<String> image_arraylist;
    private ArrayList<String> coachesWithPostsImageDatamArrayList;

    public SliderPagerAdapter1(Context context,ArrayList<String> coachesWithPostsImageDatamArrayList) {
        this.context = context;
         this.coachesWithPostsImageDatamArrayList = coachesWithPostsImageDatamArrayList;
    }

    @Override
    public Object instantiateItem(ViewGroup container,   int position) {

        View view = LayoutInflater.from(context).inflate(R.layout.layout_slider, container, false);
        ImageView im_slider = view.findViewById(R.id.im_slider);
        WebView webView = view.findViewById(R.id.webView);

        if(coachesWithPostsImageDatamArrayList.get(position).contains("mp4")){
            webView.setVisibility(View.VISIBLE);
            im_slider.setVisibility(View.GONE);
            webView.getSettings().setJavaScriptEnabled(true);
            webView.getSettings().setPluginState(WebSettings.PluginState.ON);
            webView.getSettings().setBuiltInZoomControls(true);
            webView.getSettings().setDisplayZoomControls(true);
            webView.getSettings().setUseWideViewPort(true);
            webView.getSettings().setLoadWithOverviewMode(true);
            webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(false);
//            webView.addJavascriptInterface(this, "FBDownloader");


            CookieSyncManager.createInstance(context.getApplicationContext());
            CookieManager cookieManager = CookieManager.getInstance();
            cookieManager.setAcceptCookie(true);
            CookieSyncManager.getInstance().startSync();

            webView.getSettings().setMediaPlaybackRequiresUserGesture(false);
            webView.loadUrl(AppController.base_image_url +coachesWithPostsImageDatamArrayList.get(position));

        }else{
            webView.setVisibility(View.GONE);
            im_slider.setVisibility(View.VISIBLE);
            Picasso.get().load(AppController.base_image_url + coachesWithPostsImageDatamArrayList.get(position)).into(im_slider);

        }

//        Glide.with(context).load(image_arraylist.get(position)).into(im_slider);
        //For Picasso user
      /*  Picasso.with(context.getApplicationContext())
                .load(image_arraylist.get(position))
                .placeholder(R.mipmap.ic_launcher) // optional
                .error(R.mipmap.ic_launcher)         // optional
                .into(im_slider);*/
        container.addView(view);
//        im_slider.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                 Intent intent = new Intent(context, ImageActicity.class);
//                intent.putExtra("url", AppController.base_image_url+coachesWithPostsImageDatamArrayList.get(position));
//                context.startActivity(intent);
//            }
//        });
        return view;
    }

    @Override
    public int getCount() {
        return coachesWithPostsImageDatamArrayList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object obj) {
        return view == obj;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        View view = (View) object;
        container.removeView(view);
    }
}
