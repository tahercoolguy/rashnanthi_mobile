package com.master.design.rashnanthi.Fragments;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.master.design.rashnanthi.Activity.MainActivity;
import com.master.design.rashnanthi.Controller.AppController;
import com.master.design.rashnanthi.DataModel.AboutUsDM;
import com.master.design.rashnanthi.DataModel.SocialMediaDM;
import com.master.design.rashnanthi.Helper.DialogUtil;
import com.master.design.rashnanthi.R;
import com.master.design.rashnanthi.Utils.ConnectionDetector;
import com.master.design.rashnanthi.Utils.Helper;

import butterknife.BindView;
import butterknife.ButterKnife;
import it.sephiroth.android.library.widget.HListView;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class Social_Media_Fragment extends Fragment {

    private View rootView;
    private Context context;
    DialogUtil dialogUtil;
    Dialog progress;

    ImageView social_media_menu;
    RelativeLayout instaRL;
    RelativeLayout whatsappRl;
    RelativeLayout twitterRL;
    RelativeLayout snapchatRl;
    RelativeLayout youtubeRL;
    RelativeLayout tiktokRl;
    RelativeLayout telegramRL;RelativeLayout facbookRL;


    @BindView(R.id.progress_bar)
    ProgressBar progress_bar;
    @BindView(R.id.txt_error)
    TextView txt_error;

    @BindView(R.id.layout_parent)
    LinearLayout layout_parent;

    private HListView lst_latest_profiles, lst_latest_news, lst_featured_video;
    AppController appController;
    ConnectionDetector connectionDetector;
    ProgressDialog progressDialog;

    boolean ifinsta = false;
    boolean ifwhatsapp = false;
    boolean iftwitter = false;
    boolean ifsnapchat = false;
    boolean ifyoutube = false;
    boolean iftiktok = false;
    boolean iftelegram = false;
    boolean iffacebook = false;


    public void SocialMediaLink() {

        if (connectionDetector.isConnectingToInternet()) {
            progress = dialogUtil.showProgressDialog(context,getString(R.string.please_wait));
            appController.paServices.SocialMedia(new Callback<SocialMediaDM>() {
                @Override

                public void success(SocialMediaDM socialMediaDM, Response response) {
                    progress.dismiss();
                    if (socialMediaDM.getOutput().getSuccess().equalsIgnoreCase("1")) {

                        if (ifinsta!=false) {
                             if (socialMediaDM.getOutput().getData().get(0).getInstagram() != null) {
//                                String url = socialMediaDM.getOutput().getData().get(0).getInstagram();
//                                Intent intent = new Intent(Intent.ACTION_VIEW);
//                                intent.setData(Uri.parse(url));
//                                startActivity(intent);
//                                Helper.showToast(context, String.valueOf(socialMediaDM.getOutput().getData().get(0).getInstagram()));

                                 String url = socialMediaDM.getOutput().getData().get(0).getInstagram();
                                Intent intent = new Intent(Intent.ACTION_VIEW);
                                intent.setData(Uri.parse(url));
                                startActivity(intent);

                            }

                        }     if (ifwhatsapp!=false) {

                            if (socialMediaDM.getOutput().getData().get(0).getWhatsapp() != null) {
//                                String url = socialMediaDM.getOutput().getData().get(0).getWhatsapp();
//                                Intent intent =!=false new Intent(Intent.ACTION_VIEW);
//                                intent.setData(Uri.parse(url));
//                                startActivity(intent);
//                                Helper.showToast(context, String.valueOf(socialMediaDM.getOutput().getData().get(0).getInstagram()));

                                String url = socialMediaDM.getOutput().getData().get(0).getWhatsapp();
                                Intent intent = new Intent(Intent.ACTION_VIEW);
                                intent.setData(Uri.parse(url));
                                startActivity(intent);

                            }
//


                        }    if (iftwitter!=false) {

                            if (socialMediaDM.getOutput().getData().get(0).getTwitter() != null) {
//                                String url = socialMediaDM.getOutput().getData().get(0).getTwitter() ;
//                                Intent intent = new Intent(Intent.ACTION_VIEW);
//                                intent.setData(Uri.parse(url));
//                                startActivity(intent);
//                                Helper.showToast(context, String.valueOf(socialMediaDM.getOutput().getData().get(0).getTwitter()));

                                String url = socialMediaDM.getOutput().getData().get(0).getTwitter();
                                Intent intent = new Intent(Intent.ACTION_VIEW);
                                intent.setData(Uri.parse(url));
                                startActivity(intent);

                            }


                        }    if (ifsnapchat!=false) {
                            if (socialMediaDM.getOutput().getData().get(0).getSnapchat() != null) {
//                                String url = socialMediaDM.getOutput().getData().get(0).getSnapchat();
//                                Intent intent = new Intent(Intent.ACTION_VIEW);
//                                intent.setData(Uri.parse(url));
//                                startActivity(intent);
//                                Helper.showToast(context, String.valueOf(socialMediaDM.getOutput().getData().get(0).getSnapchat()));

                                String url = socialMediaDM.getOutput().getData().get(0).getSnapchat();
                                Intent intent = new Intent(Intent.ACTION_VIEW);
                                intent.setData(Uri.parse(url));
                                startActivity(intent);

                            }


                        }    if (ifyoutube!=false) {
                            if (socialMediaDM.getOutput().getData().get(0).getYoutube() != null) {
//                                String url = socialMediaDM.getOutput().getData().get(0).getYoutube();
//                                Intent intent = new Intent(Intent.ACTION_VIEW);
//                                intent.setData(Uri.parse(url));
//                                startActivity(intent);
//                              Helper.showToast(context, String.valueOf(socialMediaDM.getOutput().getData().get(0).getYoutube()));

                                String url = socialMediaDM.getOutput().getData().get(0).getYoutube();
                                Intent intent = new Intent(Intent.ACTION_VIEW);
                                intent.setData(Uri.parse(url));
                                startActivity(intent);

                            }

                        }    if (iftiktok!=false) {

                            if (socialMediaDM.getOutput().getData().get(0).getTiktok() != null) {
//                                String url = socialMediaDM.getOutput().getData().get(0).getTiktok();
//                                Intent intent = new Intent(Intent.ACTION_VIEW);
//                                intent.setData(Uri.parse(url));
//                                startActivity(intent);
//                               Helper.showToast(context, String.valueOf(socialMediaDM.getOutput().getData().get(0).getTiktok()));

                                String url = socialMediaDM.getOutput().getData().get(0).getTiktok();
                                Intent intent = new Intent(Intent.ACTION_VIEW);
                                intent.setData(Uri.parse(url));
                                startActivity(intent);

                            }




                        }
                        if (iftelegram!=false) {

                            if (socialMediaDM.getOutput().getData().get(0).getTelegram() != null) {
//                                String url = socialMediaDM.getOutput().getData().get(0).getTelegram();
//                                Intent intent = new Intent(Intent.ACTION_VIEW);
//                                intent.setData(Uri.parse(url));
//                                startActivity(intent);
//                               Helper.showToast(context, String.valueOf(socialMediaDM.getOutput().getData().get(0).getTelegram()));

                                String url = socialMediaDM.getOutput().getData().get(0).getTelegram();
                                Intent intent = new Intent(Intent.ACTION_VIEW);
                                intent.setData(Uri.parse(url));
                                startActivity(intent);

                            }

                        }if (iffacebook!=false) {

                            if (socialMediaDM.getOutput().getData().get(0).getFacebook() != null) {
//                                String url = socialMediaDM.getOutput().getData().get(0).getTelegram();
//                                Intent intent = new Intent(Intent.ACTION_VIEW);
//                                intent.setData(Uri.parse(url));
//                                startActivity(intent);
//                               Helper.showToast(context, String.valueOf(socialMediaDM.getOutput().getData().get(0).getTelegram()));

                                String url = socialMediaDM.getOutput().getData().get(0).getFacebook();
                                Intent intent = new Intent(Intent.ACTION_VIEW);
                                intent.setData(Uri.parse(url));
                                startActivity(intent);

                            }

                        }


                    } else
                        Helper.showToast(context, "something wrong");
                }

                @Override
                public void failure(RetrofitError retrofitError) {
                    progress.dismiss();
                    Log.e("error", retrofitError.toString());

                }
            });
        } else
            Helper.showToast(context,

                    getString(R.string.no_internet_connection));
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        context = getActivity();
        appController = (AppController) getActivity().getApplicationContext();
        dialogUtil = new DialogUtil();
        connectionDetector = new ConnectionDetector(getActivity());
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage(getResources().getString(R.string.please_wait));
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);
        ((MainActivity) context).setTitle(getString(R.string.home));


        if (rootView == null) {
            rootView = inflater.inflate(R.layout.social_media_fragment_layout, container, false);
            ButterKnife.bind(this, rootView);
            idMapping();

            setClickListeners();
            setDetails();
            social_media_menu = rootView.findViewById(R.id.social_media_menu);
            instaRL = rootView.findViewById(R.id.instaRL);
            whatsappRl = rootView.findViewById(R.id.whatsappRl);
            twitterRL = rootView.findViewById(R.id.twitterRL);
            snapchatRl = rootView.findViewById(R.id.snapchatRl);
            youtubeRL = rootView.findViewById(R.id.youtubeRL);
            tiktokRl = rootView.findViewById(R.id.tiktokRl);
            telegramRL = rootView.findViewById(R.id.telegramRL);
            facbookRL = rootView.findViewById(R.id.facbookRL);

            social_media_menu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ((MainActivity) context).addFragment(new Menu_1_Fragment(), true);
                }
            });

            instaRL.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ifinsta = true;
                       ifwhatsapp = false;
                      iftwitter = false;
                      ifsnapchat = false;
                      ifyoutube = false;
                      iftiktok = false;
                      iftelegram = false;iffacebook = false;
                    SocialMediaLink();

                }
            });
            whatsappRl.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    ifwhatsapp = true;
                    ifinsta = false;
                     iftwitter = false;
                    ifsnapchat = false;
                    ifyoutube = false;
                    iftiktok = false;
                    iftelegram = false;iffacebook = false;
                    SocialMediaLink();



                }
            });
            twitterRL.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    iftwitter = true;
                    ifinsta = false;
                    ifwhatsapp = false;
                     ifsnapchat = false;
                    ifyoutube = false;
                    iftiktok = false;
                    iftelegram = false;iffacebook = false;
                    SocialMediaLink();

                }
            });
            snapchatRl.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    ifsnapchat = true;
                    ifinsta = false;
                    ifwhatsapp = false;
                    iftwitter = false;
                     ifyoutube = false;
                    iftiktok = false;
                    iftelegram = false;iffacebook = false;
                    SocialMediaLink();


                }
            });
            youtubeRL.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {


                    ifyoutube = true;
                    ifinsta = false;
                    ifwhatsapp = false;
                    iftwitter = false;
                    ifsnapchat = false;
                     iftiktok = false;
                    iftelegram = false;iffacebook = false;
                    SocialMediaLink();

                }
            });
            tiktokRl.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {


                    iftiktok = true;
                    ifinsta = false;
                    ifwhatsapp = false;
                    iftwitter = false;
                    ifsnapchat = false;
                    ifyoutube = false;
                     iftelegram = false;iffacebook = false;
                    SocialMediaLink();

                }
            });
            telegramRL.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    iftelegram = true;
                    ifinsta = false;
                    ifwhatsapp = false;
                    iftwitter = false;
                    ifsnapchat = false;
                    ifyoutube = false;
                    iftiktok = false;
                    iffacebook = false;


                    SocialMediaLink();

                }
            });  facbookRL.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    iffacebook = true;
                    iftelegram = false; ifinsta = false;
                    ifwhatsapp = false;
                    iftwitter = false;
                    ifsnapchat = false;
                    ifyoutube = false;
                    iftiktok = false;
                    SocialMediaLink();

                }
            });


        }


        return rootView;

    }

    private void idMapping() {


    }

    private void setClickListeners() {

    }

    @Override
    public void onResume() {
        super.onResume();
    }

    private void setDetails() {
        ShowProgress();
        rootView.postDelayed(new Runnable() {
            @Override
            public void run() {
                DismissProgress();
            }
        }, 1500);


    }

    public void ShowProgress() {
        progress_bar.setVisibility(View.VISIBLE);
        txt_error.setVisibility(View.GONE);
        layout_parent.setVisibility(View.GONE);
    }

    public void DismissProgress() {
        progress_bar.setVisibility(View.GONE);
        txt_error.setVisibility(View.GONE);
        layout_parent.setVisibility(View.VISIBLE);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        menu.findItem(R.id.action_back).setVisible(false);
    }
}
