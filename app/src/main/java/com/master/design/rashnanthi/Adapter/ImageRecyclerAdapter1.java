package com.master.design.rashnanthi.Adapter;


import static com.facebook.FacebookSdk.getApplicationContext;

import android.app.Activity;
import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.master.design.rashnanthi.Controller.AppController;
import com.master.design.rashnanthi.DataModel.CoachesWithPostsDatam;
import com.master.design.rashnanthi.DataModel.CoachesWithPostsImageDatam;
import com.master.design.rashnanthi.DataModel.CoachesWithPostsRootDM;
import com.master.design.rashnanthi.DataModel.NewCoachData;
import com.master.design.rashnanthi.DataModel.NewCoachData;
import com.master.design.rashnanthi.DataModel.NewCoachImagData;
import com.master.design.rashnanthi.Helper.DialogUtil;
import com.master.design.rashnanthi.Helper.User;
import com.master.design.rashnanthi.R;
import com.master.design.rashnanthi.Utils.ConnectionDetector;
import com.master.design.rashnanthi.Utils.Helper;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.mime.MultipartTypedOutput;
import retrofit.mime.TypedString;

/**
 * Created by kharag on 19-03-2020.
 */
public class ImageRecyclerAdapter1 extends RecyclerView.Adapter<ImageRecyclerAdapter1.ViewHolder> {
    Context context;
    User user;
    AppController appController;
    ConnectionDetector connectionDetector;
    DialogUtil dialogUtil;
    String whatsapp, whatsappcode, instagram, snapcahat, website;
    Dialog progress;
    ArrayList<NewCoachData> mList;
    String id;

    ArrayList<String> slider_image_list;

    public ImageRecyclerAdapter1(Activity context, ArrayList<NewCoachData> mList) {
        this.mList = mList;
        this.context = context;

//        appController = (AppController) context.getApplicationContext();
        user = new User(this.context);
        appController = (AppController) this.context.getApplicationContext();
        dialogUtil = new DialogUtil();
        connectionDetector = new ConnectionDetector(this.context);

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.single_recycler_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
//        holder.mName.setText(mList.get(position));

        if (mList.get(position).getImagedata() != null && mList.get(position).getImagedata().size()!=0) {
            instagram = mList.get(position).getInstagram();
            whatsapp = mList.get(position).getWhatsapnumber();
            whatsappcode = mList.get(position).getWhatsapcountrycode();
            snapcahat = mList.get(position).getSnapchat();
            website = mList.get(position).getWebsite();
        } else {
            instagram = mList.get(position).getInstagram();
            whatsapp = mList.get(position).getWhatsapnumber();
            whatsappcode = mList.get(position).getWhatsapcountrycode();
            snapcahat = mList.get(position).getSnapchat();
            website = mList.get(position).getWebsite();
        }
        if (instagram != null)
            if (instagram.equalsIgnoreCase("")) {
                holder.insta_img.setVisibility(View.GONE);
            } else {
                holder.insta_img.setVisibility(View.VISIBLE);
            }

        if (whatsapp != null)
            if (whatsapp.equalsIgnoreCase("")) {
                holder.whatsapp_IMg.setVisibility(View.GONE);
            } else {
                holder.whatsapp_IMg.setVisibility(View.VISIBLE);
            }

        if (snapcahat != null)
            if (snapcahat.equalsIgnoreCase("")) {
                holder.snapchaht_Img.setVisibility(View.GONE);
            } else {
                holder.snapchaht_Img.setVisibility(View.VISIBLE);
            }

        if (website != null)
            if (website.equalsIgnoreCase("")) {
                holder.web_Img.setVisibility(View.GONE);
            } else {
                holder.web_Img.setVisibility(View.VISIBLE);
            }

        ArrayList<String> km = new ArrayList<>();

        if (mList.get(position).getImagedata() != null) {
            for (NewCoachImagData s : mList.get(position).getImagedata()
            ) {


                        km.add(s.getImage());


            }

        }else
        {
            km.add(mList.get(position).getImage());
        }
        try {

            holder.sliderPagerAdapter1 = new SliderPagerAdapter1(context, km);
            holder.mViewPager.setAdapter(holder.sliderPagerAdapter1);
            holder.dots = new TextView[km.size()];
            addBottomDots(0, holder.dots, holder);
        } catch (Exception e) {
            e.printStackTrace();
        }


//        if (Whatsapp == null) {
//            wtsapImg.setVisibility(View.GONE);
//        } else {
//            wtsapImg.setVisibility(View.VISIBLE);
//        }
//
//
//        if (Website == null) {
//            webImg.setVisibility(View.GONE);
//        } else {
//            webImg.setVisibility(View.VISIBLE);
//        }
//
//
//        if (Snapchat == null) {
//            snapImg.setVisibility(View.GONE);
//        } else {
//            snapImg.setVisibility(View.VISIBLE);
//        }

//        if (connectionDetector.isConnectingToInternet()) {
//
//            MultipartTypedOutput multipartTypedOutput = new MultipartTypedOutput();
//            multipartTypedOutput.addPart("countryid", new TypedString(id));
//             appController.paServices.GetAllCoachesWithPosts(multipartTypedOutput, new Callback<CoachesWithPostsRootDM>() {
//                @Override
//                public void success(CoachesWithPostsRootDM coachesWithPostsRootDM, Response response) {
//                      if (coachesWithPostsRootDM.getOutput().getSuccess().equalsIgnoreCase("1")) {
//                        slider_image_list = new ArrayList<>();
//
//                        instagram=coachesWithPostsRootDM.getOutput().getData().get(0).getInstagram();
//                        whatsapp=coachesWithPostsRootDM.getOutput().getData().get(0).getWhatsapnumber();
//                        whatsappcode= coachesWithPostsRootDM.getOutput().getData().get(0).getWhatscountrycode();
//                        snapcahat=coachesWithPostsRootDM.getOutput().getData().get(0).getSnapchat();
//                        try{
//
//                             holder.sliderPagerAdapter1 = new SliderPagerAdapter1(context,coachesWithPostsRootDM.getOutput().getData().get(0).getPostsdata().get(0).getImagedata() );
//                            holder.mViewPager.setAdapter(holder.sliderPagerAdapter1);
//                            holder.dots = new TextView[coachesWithPostsRootDM.getOutput().getData().get(0).getPostsdata().size()];
//                            addBottomDots(0, holder.dots, holder);
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                        }
//
//
//                    } else
//                        Helper.showToast(context, "Some network happened ..");
//                }
//
//                @Override
//                public void failure(RetrofitError error) {
//                     Log.e("String", error.toString());
//                }
//            });
//        }


        holder.mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                addBottomDots(position, holder.dots, holder);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });


        holder.whatsapp_IMg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    String url = "https://api.whatsapp.com/send?phone=" + whatsappcode + whatsapp;
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse(url));
                    context.startActivity(intent);
                } catch (Exception e) {
                    Helper.showToast(context,context.getString(R.string.something_wrong));
                }

//                String url = "https://api.whatsapp.com/send?phone=" + whatsappcode + whatsapp;
//                try {
//                    PackageManager pm = context.getPackageManager();
//                    pm.getPackageInfo("com.whatsapp", PackageManager.GET_ACTIVITIES);
//                    Intent i = new Intent(Intent.ACTION_VIEW);
//                    i.setData(Uri.parse(url));
//                    context.startActivity(i);
//                } catch (PackageManager.NameNotFoundException e) {
//                    Helper.showToast(context, "Whatsapp app not installed in your phone");
//
//                    e.printStackTrace();
//                }
            }
        });

        holder.snapchaht_Img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://snapchat.com/add/" + snapcahat));
                    intent.setPackage("com.snapchat.android");
                    context.startActivity(intent);
                } catch (Exception e) {
                    Helper.showToast(context, "Snapchat app not installed in your phone");


                    e.printStackTrace();
                }

            }
        });

        holder.insta_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("http://instagram.com/" + instagram);


                Intent i = new Intent(Intent.ACTION_VIEW, uri);

                i.setPackage("com.instagram.android");

                try {
                    context.startActivity(i);
                } catch (ActivityNotFoundException e) {

                    context.startActivity(new Intent(Intent.ACTION_VIEW,
                            Uri.parse("http://instagram.com/" + instagram)));
                }

            }
        });
        holder.web_Img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://" + website));
                    context.startActivity(myIntent);
                } catch (ActivityNotFoundException e) {

                    Helper.showToast(context, "No application can handle this request."
                            + " Please install a web browser");
                    e.printStackTrace();
                }

            }
        });


    }

    private void addBottomDots(int currentPage, TextView[] dots, ViewHolder holder) {

        holder.ll_dots.removeAllViews();
        for (int i = 0; i < dots.length; i++) {
            dots[i] = new TextView(context);
            dots[i].setText(Html.fromHtml("&#8226;"));
            dots[i].setTextSize(35);
            dots[i].setTextColor(Color.parseColor("#A1A1A1"));
            holder.ll_dots.addView(dots[i]);
        }
        if (dots.length > 0)
            dots[currentPage].setTextColor(Color.parseColor("#CF0010"));
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView mName;
        private ViewPager mViewPager;
        SliderPagerAdapter1 sliderPagerAdapter1;
        SliderPagerAdapter sliderPagerAdapter;
        private TextView[] dots;
        private LinearLayout ll_dots;
        ImageView whatsapp_IMg, insta_img, snapchaht_Img, web_Img;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mViewPager = itemView.findViewById(R.id.vp_slider);
            ll_dots = itemView.findViewById(R.id.ll_dots);
            whatsapp_IMg = itemView.findViewById(R.id.whatsapp_IMg);
            insta_img = itemView.findViewById(R.id.insta_img);
            snapchaht_Img = itemView.findViewById(R.id.snapchaht_Img);
            web_Img = itemView.findViewById(R.id.web_Img);


            //            mName = itemView.findViewById(R.id.list_name);
//            slider_image_list.add("https://oi65.photobucket.com/albums/h214/L_The_Legend/DeathNoteS01E09E.png");
//            slider_image_list.add("https://oi217.photobucket.com/albums/cc312/mastersig/Avitars/For%20Me/DAngel.png");
//            slider_image_list.add("https://oi217.photobucket.com/albums/cc312/mastersig/Avitars/For%20Me/C_D_A.png");
//            slider_image_list.add("https://oi49.photobucket.com/albums/f260/starfoxfan/fursona.jpg");
        }

//        public void GetAllCoachesWithPosts() {
//
//            if (connectionDetector.isConnectingToInternet()) {
//
//                MultipartTypedOutput multipartTypedOutput = new MultipartTypedOutput();
//                multipartTypedOutput.addPart("countryid", new TypedString("1"));
//                progress = dialogUtil.showProgressDialog(context, context.getString(R.string.please_wait));
//
//
//                appController.paServices.GetAllCoachesWithPosts(multipartTypedOutput, new Callback<CoachesWithPostsRootDM>() {
//                    @Override
//                    public void success(CoachesWithPostsRootDM coachesWithPostsRootDM, Response response) {
//                        progress.dismiss();
//                        if (coachesWithPostsRootDM.getOutput().getSuccess().equalsIgnoreCase("1")) {
//                            slider_image_list = new ArrayList<>();
//                            slider_image_list.add(coachesWithPostsRootDM.getOutput().getData().get(0).getPostsdata().get(0).getImagedata().get(0).getImage());
//
//                        } else
//                            Helper.showToast(context, "Some network happened ..");
//                    }
//
//                    @Override
//                    public void failure(RetrofitError error) {
//                        progress.dismiss();
//
//                        Log.e("String", error.toString());
//                    }
//                });
//            }
//        }
    }
}

