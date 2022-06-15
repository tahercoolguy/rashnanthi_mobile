package com.master.design.rashnanthi.Adapter;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.solver.SolverVariableValues;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputEditText;
import com.makeramen.roundedimageview.RoundedImageView;
import com.master.design.rashnanthi.Activity.Activity_Add_Event_1;
import com.master.design.rashnanthi.Activity.Add_new_post_1;
import com.master.design.rashnanthi.Activity.ImageActicity;
import com.master.design.rashnanthi.Activity.MainActivity;
import com.master.design.rashnanthi.Activity.SignUpActivity;
import com.master.design.rashnanthi.Controller.AppController;
import com.master.design.rashnanthi.DataModel.DeleteEventRootDM;
import com.master.design.rashnanthi.DataModel.MyEventData;
import com.master.design.rashnanthi.DataModel.MyEventData1;
import com.master.design.rashnanthi.DataModel.MyEventImageData;
import com.master.design.rashnanthi.DataModel.MyEventImageData1;
import com.master.design.rashnanthi.DataModel.MyEventsRootDM;
import com.master.design.rashnanthi.DataModel.My_Event_1DM;
import com.master.design.rashnanthi.Helper.DialogUtil;
import com.master.design.rashnanthi.Helper.User;
import com.master.design.rashnanthi.R;
import com.master.design.rashnanthi.Utils.ConnectionDetector;
import com.master.design.rashnanthi.Utils.Helper;
import com.squareup.picasso.Picasso;


import java.util.ArrayList;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class Adapter_MY_Event_1 extends RecyclerView.Adapter<Adapter_MY_Event_1.ViewHolder> {
    private Context context;
    private ArrayList<MyEventData1> myEventData;
    private ArrayList<MyEventImageData1> myEventImageData1ArrayList;
    Dialog progress;
    DialogUtil dialogUtil;

    AppController appController;
    ConnectionDetector connectionDetector;
    User user;


    int selectedPosition = 0;

    public Adapter_MY_Event_1(Context context, ArrayList<MyEventData1> myEventData, ArrayList<MyEventImageData1> myEventImageData1ArrayList) {
        this.context = context;
        this.myEventData = myEventData;
        this.myEventImageData1ArrayList = myEventImageData1ArrayList;
        dialogUtil = new DialogUtil();

        user = new User(context);
        appController = (AppController) context.getApplicationContext();

        connectionDetector = new ConnectionDetector(context);

    }


    @NonNull
    @Override
    public Adapter_MY_Event_1.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_event_1_recycle_view_item, parent, false);
        // set the view's size, margins, paddings and layout parameters
        Adapter_MY_Event_1.ViewHolder vh = new Adapter_MY_Event_1.ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter_MY_Event_1.ViewHolder holder, int position) {
        setDetails(holder, position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return myEventData.size();
    }


    private void setDetails(Adapter_MY_Event_1.ViewHolder viewHolder, int position) {
        if (user.getCreatorcoach().equalsIgnoreCase("1")) {
            viewHolder.date_time.setText(myEventData.get(position).getEventdate());
        } else {
            viewHolder.date_time.setText(myEventData.get(position).getDate());
        }
        ArrayList<String> img = new ArrayList<>();
        if (myEventData.get(position).getImagedata() != null)
            for (MyEventImageData1 myEventImageData1 : myEventData.get(position).getImagedata()
            ) {
                img.add(myEventImageData1.getImage());
            }


//        if (img.size() >= 2) {
//            Picasso.get().load(AppController.base_image_url + img.get(0)).into(viewHolder.img_1);
//            Picasso.get().load(AppController.base_image_url + img.get(1)).into(viewHolder.img_2);
//        } else if (img.size() == 1) {
//            Picasso.get().load(AppController.base_image_url + img.get(0)).into(viewHolder.img_1);
//        }
//        if(img.get(position)!=null) {
//
//            if(img.get(0).contains(".mp4"))
//            {
//                viewHolder.webview1.setVisibility(View.VISIBLE);
//                viewHolder.img_1.setVisibility(View.GONE);
//                Uri uri = Uri.parse(img.get(0));
//                viewHolder.webview1.loadUrl(AppController.base_image_url +uri);
//
//            }else
//                Picasso.get().load(AppController.base_image_url + img.get(0)).into(viewHolder.img_1);
//        }








        if (img.size() >= 4) {

            if (img.get(0).contains(".mp4")) {
                viewHolder.webview1.setVisibility(View.VISIBLE);
                viewHolder.img_1.setVisibility(View.GONE);
                Uri uri = Uri.parse(img.get(0));
                viewHolder.webview1.loadUrl(AppController.base_image_url + uri);

            } else {
                Picasso.get().load(AppController.base_image_url + img.get(0)).into(viewHolder.img_1);
            }

            if (img.get(1).contains(".mp4")) {
                viewHolder.webview2.setVisibility(View.VISIBLE);
                viewHolder.img_1.setVisibility(View.GONE);
                Uri uri = Uri.parse(img.get(1));
                viewHolder.webview2.loadUrl(AppController.base_image_url + uri);

            } else {
                Picasso.get().load(AppController.base_image_url + img.get(1)).into(viewHolder.img_2);
            }

            if (img.get(2).contains(".mp4")) {
                viewHolder.webview3.setVisibility(View.VISIBLE);
                viewHolder.img_1.setVisibility(View.GONE);
                Uri uri = Uri.parse(img.get(2));
                viewHolder.webview3.loadUrl(AppController.base_image_url + uri);

            } else {
                Picasso.get().load(AppController.base_image_url + img.get(2)).into(viewHolder.img_3);
            }

            if (img.get(3).contains(".mp4")) {
                viewHolder.webview4.setVisibility(View.VISIBLE);
                viewHolder.img_1.setVisibility(View.GONE);
                Uri uri = Uri.parse(img.get(3));
                viewHolder.webview4.loadUrl(AppController.base_image_url + uri);

            } else
                Picasso.get().load(AppController.base_image_url + img.get(3)).into(viewHolder.img_4);


//            Picasso.get().load(AppController.base_image_url + img.get(0)).into(viewHolder.img_1);
//            Picasso.get().load(AppController.base_image_url + img.get(1)).into(viewHolder.img_2);
//            Picasso.get().load(AppController.base_image_url + img.get(2)).into(viewHolder.img_3);
//            Picasso.get().load(AppController.base_image_url + img.get(3)).into(viewHolder.img_4);
        }

        if (img.size() >= 3) {


            if (img.get(0).contains(".mp4")) {
                viewHolder.webview1.setVisibility(View.VISIBLE);
                viewHolder.img_1.setVisibility(View.GONE);
                Uri uri = Uri.parse(img.get(0));
                viewHolder.webview1.loadUrl(AppController.base_image_url + uri);

            } else {
                Picasso.get().load(AppController.base_image_url + img.get(0)).into(viewHolder.img_1);
            }
            if (img.get(1).contains(".mp4")) {
                viewHolder.webview2.setVisibility(View.VISIBLE);
                viewHolder.img_1.setVisibility(View.GONE);
                Uri uri = Uri.parse(img.get(1));
                viewHolder.webview2.loadUrl(AppController.base_image_url + uri);

            } else {
                Picasso.get().load(AppController.base_image_url + img.get(1)).into(viewHolder.img_2);
            }
            if (img.get(2).contains(".mp4")) {
                viewHolder.webview3.setVisibility(View.VISIBLE);
                viewHolder.img_1.setVisibility(View.GONE);
                Uri uri = Uri.parse(img.get(2));
                viewHolder.webview3.loadUrl(AppController.base_image_url + uri);

            } else {
                Picasso.get().load(AppController.base_image_url + img.get(2)).into(viewHolder.img_3);
            }


//            Picasso.get().load(AppController.base_image_url + img.get(0)).into(viewHolder.img_1);
//            Picasso.get().load(AppController.base_image_url + img.get(1)).into(viewHolder.img_2);
//            Picasso.get().load(AppController.base_image_url + img.get(2)).into(viewHolder.img_3);
        }

        if (img.size() >= 2) {
             if (img.get(0).contains(".mp4")) {
                viewHolder.webview1.setVisibility(View.VISIBLE);
                viewHolder.img_1.setVisibility(View.GONE);
                Uri uri = Uri.parse(img.get(0));
                viewHolder.webview1.loadUrl(AppController.base_image_url + uri);

            } else {
                Picasso.get().load(AppController.base_image_url + img.get(0)).into(viewHolder.img_1);
            }
            if (img.get(1).contains(".mp4")) {
                viewHolder.webview2.setVisibility(View.VISIBLE);
                viewHolder.img_1.setVisibility(View.GONE);
                Uri uri = Uri.parse(img.get(1));
                viewHolder.webview2.loadUrl(AppController.base_image_url + uri);

            } else {
                Picasso.get().load(AppController.base_image_url + img.get(1)).into(viewHolder.img_2);
            }
//            Picasso.get().load(AppController.base_image_url + img.get(0)).into(viewHolder.img_1);
//            Picasso.get().load(AppController.base_image_url + img.get(1)).into(viewHolder.img_2);
        }

        if (img.size() == 1) {

            if (img.get(0).contains(".mp4")) {
                viewHolder.webview1.setVisibility(View.VISIBLE);
                viewHolder.img_1.setVisibility(View.GONE);
                Uri uri = Uri.parse(img.get(0));
                viewHolder.webview1.loadUrl(AppController.base_image_url + uri);


            } else {
                Picasso.get().load(AppController.base_image_url + img.get(0)).into(viewHolder.img_1);
            }

//            Picasso.get().load(AppController.base_image_url + img.get(0)).into(viewHolder.img_1);
        }

//            myEventData.get(position).getImagedata().get(position).getImagevideo();

//        viewHolder.delete_Img1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (connectionDetector.isConnectingToInternet()) {
//                    progress = dialogUtil.showProgressDialog(context, context.getString(R.string.please_wait));
//
//                    appController.paServices.DeletEvent(myEventData.get(position).getId(), new Callback<DeleteEventRootDM>() {
//                        @Override
//                        public void success(DeleteEventRootDM deleteEventRootDM, Response response) {
//                            progress.dismiss();
//                            if (deleteEventRootDM.getOutput().getSuccess().equalsIgnoreCase("1")) {
//
//                                myEventData.remove(position);
//                                notifyItemRemoved(position);
//                                notifyDataSetChanged();
//                                Helper.showToast(context, "item deleted succesfully");
//
//
////                                openHelper.deleteProduct(shoppingCart.getId());
////                                shoppingCartList.remove(position);
////                                notifyItemRemoved(position);
//
//                            } else
//                                Helper.showToast(context, "item does not deleted");
//                        }
//
//                        @Override
//                        public void failure(RetrofitError retrofitError) {
//                            progress.dismiss();
//                            Log.e("error", retrofitError.toString());
//
//                        }
//                    });
//                } else
//                    Helper.showToast(context, String.valueOf(R.string.no_internet_connection));
//
//
//            }
//        });


//        viewHolder.delete_Img2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (connectionDetector.isConnectingToInternet()) {
//                    progress = dialogUtil.showProgressDialog(context, context.getString(R.string.please_wait));
//
//                    appController.paServices.DeletEvent(myEventData.get(position).getId(), new Callback<DeleteEventRootDM>() {
//                        @Override
//                        public void success(DeleteEventRootDM deleteEventRootDM, Response response) {
//                            progress.dismiss();
//                            if (deleteEventRootDM.getOutput().getSuccess().equalsIgnoreCase("1")) {
//
//                                myEventData.remove(position);
//                                notifyItemRemoved(position);
//                                notifyDataSetChanged();
//                                Helper.showToast(context, "item deleted succesfully");
//
//
////                                openHelper.deleteProduct(shoppingCart.getId());
////                                shoppingCartList.remove(position);
////                                notifyItemRemoved(position);
//
//                            } else
//                                Helper.showToast(context, "item does not deleted");
//                        }
//
//                        @Override
//                        public void failure(RetrofitError retrofitError) {
//                            progress.dismiss();
//                            Log.e("error", retrofitError.toString());
//
//                        }
//                    });
//                } else
//                    Helper.showToast(context, String.valueOf(R.string.no_internet_connection));
//            }
//
//        });

        viewHolder.delete_Img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (connectionDetector.isConnectingToInternet()) {
                    progress = dialogUtil.showProgressDialog(context, context.getString(R.string.please_wait));

                    appController.paServices.DeletEvent(myEventData.get(position).getId(), new Callback<DeleteEventRootDM>() {
                        @Override
                        public void success(DeleteEventRootDM deleteEventRootDM, Response response) {
                            progress.dismiss();
                            if (deleteEventRootDM.getOutput().getSuccess().equalsIgnoreCase("1")) {

                                myEventData.remove(position);
                                notifyItemRemoved(position);
                                notifyDataSetChanged();
                                Helper.showToast(context, "item deleted succesfully");


//                                openHelper.deleteProduct(shoppingCart.getId());
//                                shoppingCartList.remove(position);
//                                notifyItemRemoved(position);

                            } else
                                Helper.showToast(context, "item does not deleted");
                        }

                        @Override
                        public void failure(RetrofitError retrofitError) {
                            progress.dismiss();
                            Log.e("error", retrofitError.toString());

                        }
                    });
                } else
                    Helper.showToast(context, String.valueOf(R.string.no_internet_connection));
            }
        });


        viewHolder.edit_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                activity_add_event_1.AddEventByCreatorAPI();

//                viewHolder.date_time.setText(myEventData.get(position).getDate());
//                Picasso.get().load(AppController.base_image_url + myEventData.get(0).getImage()).into(viewHolder.img_1);
//                Picasso.get().load(AppController.base_image_url + myEventData.get(1).getImage()).into(viewHolder.img_2);

                if (user.getCreatorcoach().equalsIgnoreCase("1")) {
                    Intent intent = new Intent(context, Activity_Add_Event_1.class);

                    try {
                         intent.putExtra("image1", img.get(0));
                        intent.putExtra("image2", img.get(1));
                        intent.putExtra("image3", img.get(2));
                        intent.putExtra("image4", img.get(3));

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    intent.putExtra("date", myEventData.get(position).getEventdate());

                    intent.putExtra("eventid", myEventData.get(position).getId());
                    intent.putExtra("snapchat", myEventData.get(position).getSnapchat());
                    intent.putExtra("instagram", myEventData.get(position).getInstagram());
                    intent.putExtra("website", myEventData.get(position).getWebsite());
                    intent.putExtra("whatsappcountrycode", myEventData.get(position).getWhatsapcountrycode());
                    intent.putExtra("whatsappnumber", myEventData.get(position).getWhatsapnumber());
                    intent.putExtra("countries", myEventData.get(position).getImpcountries());
                    intent.putExtra("creatorcoach", myEventData.get(position).getCreatorcoach());
                    intent.putExtra("payorfree", myEventData.get(position).getPayorfree());
                    intent.putExtra("status", myEventData.get(position).getStatus());
                    intent.putExtra("postedby", myEventData.get(position).getPostedby());
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                } else {
                    Intent intent = new Intent(context, Add_new_post_1.class);

                    try {
                        intent.putExtra("image1", myEventData.get(position).getImage());
                        intent.putExtra("image2", myEventData.get(position).getImage());
                        intent.putExtra("image3", myEventData.get(position).getImage());
                        intent.putExtra("image4", myEventData.get(position).getImage());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    intent.putExtra("date", myEventData.get(position).getEventdate());
                    intent.putExtra("eventid", myEventData.get(position).getId());
                    intent.putExtra("snapchat", myEventData.get(position).getSnapchat());
                    intent.putExtra("instagram", myEventData.get(position).getInstagram());
                    intent.putExtra("website", myEventData.get(position).getWebsite());
                    intent.putExtra("whatsappcountrycode", myEventData.get(position).getWhatsapcountrycode());
                    intent.putExtra("whatsappnumber", myEventData.get(position).getWhatsapnumber());
                    intent.putExtra("countries", myEventData.get(position).getImpcountries());
                    intent.putExtra("creatorcoach", myEventData.get(position).getCreatorcoach());
                    intent.putExtra("payorfree", myEventData.get(position).getPayorfree());
                    intent.putExtra("status", myEventData.get(position).getStatus());
                    intent.putExtra("postedby", myEventData.get(position).getPostedby());
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                }


            }
        });
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView date_time;
        private ImageView  edit_img, delete_Img, delete_Img1, delete_Img2, delete_Img3, delete_Img4;
        private RoundedImageView img_1, img_2, img_3, img_4;
        private LinearLayout ll;
        private WebView webview1, webview2, webview3, webview4;

        public ViewHolder(View itemView) {
            super(itemView);
            date_time = itemView.findViewById(R.id.date_month_Txt);
            img_1 = itemView.findViewById(R.id.img_1);
            img_2 = itemView.findViewById(R.id.img_2);
            img_3 = itemView.findViewById(R.id.img_3);
            img_4 = itemView.findViewById(R.id.img_4);
            edit_img = itemView.findViewById(R.id.edit_img);
            delete_Img = itemView.findViewById(R.id.delete_Img);
            delete_Img1 = itemView.findViewById(R.id.delete_Img1);
            delete_Img2 = itemView.findViewById(R.id.delete_Img2);
            delete_Img3 = itemView.findViewById(R.id.delete_Img3);
            delete_Img4 = itemView.findViewById(R.id.delete_Img4);
            webview1 = itemView.findViewById(R.id.webview1);
            webview2 = itemView.findViewById(R.id.webview2);
            webview3 = itemView.findViewById(R.id.webview3);
            webview4 = itemView.findViewById(R.id.webview4);
            ll = itemView.findViewById(R.id.LL);

        }
    }
}
