package com.master.design.rashnanthi.Adapter;

import static com.master.design.rashnanthi.Controller.AppController.TAG;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.makeramen.roundedimageview.RoundedImageView;
import com.master.design.rashnanthi.Activity.Story_activity;
import com.master.design.rashnanthi.Controller.AppController;
import com.master.design.rashnanthi.DataModel.GetCoachByCountryData;
import com.master.design.rashnanthi.DataModel.GetCoachsByCountryRootDM;
import com.master.design.rashnanthi.DataModel.LikeCoachRootDM;
import com.master.design.rashnanthi.Helper.User;
import com.master.design.rashnanthi.R;
import com.master.design.rashnanthi.Utils.ConnectionDetector;
import com.master.design.rashnanthi.Utils.Helper;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class Adapter_Coach__grid_Fgmt extends RecyclerView.Adapter<Adapter_Coach__grid_Fgmt.ViewHolder> {
    private Context context;
    private ArrayList<GetCoachByCountryData> getCoachsByCountryRootDMArrayList;
    User user;
    ConnectionDetector connectionDetector;
    AppController appController;
    //    String Snapchat, Instagram, Whatsapp, Website, WhatsappCountryCode;
    String likestatus;
    int selectedPosition = 0;

    public Adapter_Coach__grid_Fgmt(Context context, ArrayList<GetCoachByCountryData> GetCoachsByCountryRootDMArrayList) {
        this.context = context;
        this.getCoachsByCountryRootDMArrayList = GetCoachsByCountryRootDMArrayList;
        user = new User(context);
        appController = (AppController) context.getApplicationContext();
        connectionDetector = new ConnectionDetector(context);
    }


    @NonNull
    @Override
    public Adapter_Coach__grid_Fgmt.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.coach_grid_account_recycle_view_item, parent, false);
        // set the view's size, margins, paddings and layout parameters
        Adapter_Coach__grid_Fgmt.ViewHolder vh = new Adapter_Coach__grid_Fgmt.ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter_Coach__grid_Fgmt.ViewHolder holder, int position) {
        setDetails(holder, position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return getCoachsByCountryRootDMArrayList.size();
    }


    private void setDetails(Adapter_Coach__grid_Fgmt.ViewHolder viewHolder, int position) {


//        viewHolder.circle_imgview.setImageResource(Integer.parseInt(GetCoachsByCountryRootDMArrayList.get(0).getOutput().getData().get(0).getProfilepic()));

//        Picasso.get().load(Integer.parseInt(getCoachsByCountryRootDMArrayList.get(position).getProfilepic())).into(viewHolder.circle_imgview);

        Picasso.get().load(AppController.base_image_url + getCoachsByCountryRootDMArrayList.get(position).getProfilepic()).into(viewHolder.circle_imgview);

        String Instagram = getCoachsByCountryRootDMArrayList.get(position).getInstagram();
        String Whatsapp = getCoachsByCountryRootDMArrayList.get(position).getMobile();
        String Snapchat = getCoachsByCountryRootDMArrayList.get(position).getSnapchat();
        String WhatsappCountryCode = getCoachsByCountryRootDMArrayList.get(position).getCountrycode();

        if (Instagram != null || !Instagram.equalsIgnoreCase("")) {
            viewHolder.instaimg.setVisibility(View.VISIBLE);
        } else {
            viewHolder.instaimg.setVisibility(View.GONE);
        }

        if (Whatsapp != null || !Whatsapp.equalsIgnoreCase(""))
//            if (Whatsapp.equalsIgnoreCase("")) {
        {
            viewHolder.whatsappIMg.setVisibility(View.VISIBLE);
        } else {
            viewHolder.whatsappIMg.setVisibility(View.GONE);
        }

        if (Snapchat != null || !Snapchat.equalsIgnoreCase("")) {
            viewHolder.snapchatimg.setVisibility(View.VISIBLE);
        } else {
            viewHolder.snapchatimg.setVisibility(View.GONE);
        }
//            if (Snapchat.equalsIgnoreCase("")) {
//                viewHolder.snapchatimg.setVisibility(View.GONE);
//            } else {
//
//            }


        viewHolder.whatsappIMg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                String url = "https://api.whatsapp.com/send?phone=" + WhatsappCountryCode + Whatsapp;
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

                try {
                    String url = "https://api.whatsapp.com/send?phone=" + WhatsappCountryCode + Whatsapp;
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse(url));
                    context.startActivity(intent);
                } catch (Exception e) {
                    Helper.showToast(context, context.getString(R.string.something_wrong));
                }
            }
        });
        viewHolder.instaimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("http://instagram.com/" + Instagram);


                Intent i = new Intent(Intent.ACTION_VIEW, uri);

                i.setPackage("com.instagram.android");

                try {
                    context.startActivity(i);
                } catch (ActivityNotFoundException e) {

                    context.startActivity(new Intent(Intent.ACTION_VIEW,
                            Uri.parse("http://instagram.com/" + Instagram)));
                }
            }
        });
        viewHolder.snapchatimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://snapchat.com/add/" + Snapchat));
                    intent.setPackage("com.snapchat.android");
                    context.startActivity(intent);
                } catch (Exception e) {
                    Helper.showToast(context, context.getString(R.string.snapchat_not_installed));


                    e.printStackTrace();
                }

            }
        });

        viewHolder.like_coach_grid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                viewHolder.like_coach_grid_red.setVisibility(View.VISIBLE);
                viewHolder.like_coach_grid.setVisibility(View.GONE);
                likestatus = "1";
                liKeAPI();
            }

        });
        viewHolder.like_coach_grid_red.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                viewHolder.like_coach_grid.setVisibility(View.VISIBLE);
                viewHolder.like_coach_grid_red.setVisibility(View.GONE);
                likestatus = "0";
                liKeAPI();
            }

        });


    }


    public void liKeAPI() {
        if (connectionDetector.isConnectingToInternet()) {
//            dialog = dialogUtil.showProgressDialog(getActivity(), getString(R.string.please_wait));

            String userId = String.valueOf(user.getId());
            String coachId = user.getCreatorcoach();

            appController.paServices.LikeCoach(coachId, userId, likestatus, new Callback<LikeCoachRootDM>() {
                @Override
                public void success(LikeCoachRootDM likeCoachRootDM, Response response) {
//                    dialog.dismiss();
                    if (likeCoachRootDM.getOutput().getSuccess().equalsIgnoreCase("1")) {

//                        if(likestatus.equalsIgnoreCase("1")){
//                            Helper.showToast(context,context.getString(R.string.you_liked_it));
//
//                        }else{
//                            Helper.showToast(context,context.getString(R.string.you_unliked_liked_it));
//
//                        }


                    } else {

                    }

                }

                @Override
                public void failure(RetrofitError retrofitError) {
//                    dialog.dismiss();
                    Log.e("error", retrofitError.toString());

                }
            });
        } else
            Helper.showToast(context, context.getString(R.string.no_internet_connection));
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView like_coach_grid, like_coach_grid_red, whatsappIMg, snapchatimg, instaimg;
        private de.hdodenhof.circleimageview.CircleImageView circle_imgview;


        public ViewHolder(View itemView) {
            super(itemView);

            circle_imgview = itemView.findViewById(R.id.coach_grid_img);
            like_coach_grid = itemView.findViewById(R.id.like_coach_grid);
            like_coach_grid_red = itemView.findViewById(R.id.like_coach_grid_red);
            whatsappIMg = itemView.findViewById(R.id.whatsappIMg);
            snapchatimg = itemView.findViewById(R.id.snapchatImg);
            instaimg = itemView.findViewById(R.id.instaImg);


            //          
        }
    }
}
