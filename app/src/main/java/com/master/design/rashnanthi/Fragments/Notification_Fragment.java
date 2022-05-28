package com.master.design.rashnanthi.Fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.master.design.rashnanthi.Activity.MainActivity;
import com.master.design.rashnanthi.Adapter.Adapter_Notification;
import com.master.design.rashnanthi.Controller.AppController;
import com.master.design.rashnanthi.DataModel.NotificationDM;
import com.master.design.rashnanthi.R;
import com.master.design.rashnanthi.Utils.ConnectionDetector;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import it.sephiroth.android.library.widget.HListView;

public class Notification_Fragment extends Fragment {

    private View rootView;
    private Context context;
    RecyclerView notification_Rcv;
    ImageView notification_back;
    LinearLayoutManager layoutManager;

    private ArrayList<NotificationDM> notificationDMArrayList;
     RelativeLayout notificatiom_messege_RL;
    Adapter adapter;

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

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        context = getActivity();
        appController = (AppController) getActivity().getApplicationContext();

        connectionDetector = new ConnectionDetector(getActivity());
//        progressDialog = new ProgressDialog(getActivity());
//        progressDialog.setMessage(getResources().getString(R.string.please_wait));
//        progressDialog.setIndeterminate(true);
//        progressDialog.setCancelable(false);
        ((MainActivity) context).setTitle(getString(R.string.home));


        if (rootView == null) {
            rootView = inflater.inflate(R.layout.notification_fragment_layout, container, false);
            ButterKnife.bind(this,rootView);

            notification_Rcv=rootView.findViewById(R.id.notification_Rcv);
            notification_back=rootView.findViewById(R.id.notification_back);
            notificatiom_messege_RL=rootView.findViewById(R.id.notificatiom_messege_RL);

            notification_back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((MainActivity) context).addFragment(new Menu_1_Fragment(), true);

                }
            });

            notificationDMArrayList = new ArrayList<>();
            notification_Rcv = rootView.findViewById(R.id.notification_Rcv);


            ArrayList<NotificationDM>notificationDMArrayList =new ArrayList<>();

            notificationDMArrayList.add(new NotificationDM("Title","Today 6:00 pm","The Quick, Brown Fox Jumps Over A Lazy Dog. Djs Flock By When MTV Ax Quiz Prog. Junk MTV Quiz Graced By Fox Whelps."));
            notificationDMArrayList.add(new NotificationDM("Title","Today 6:00 pm","The Quick, Brown Fox Jumps Over A Lazy Dog. Djs Flock By When MTV Ax Quiz Prog. Junk MTV Quiz Graced By Fox Whelps."));
            notificationDMArrayList.add(new NotificationDM("Title","Today 6:00 pm","The Quick, Brown Fox Jumps Over A Lazy Dog. Djs Flock By When MTV Ax Quiz Prog. Junk MTV Quiz Graced By Fox Whelps."));
            notificationDMArrayList.add(new NotificationDM("Title","Today 6:00 pm","The Quick, Brown Fox Jumps Over A Lazy Dog. Djs Flock By When MTV Ax Quiz Prog. Junk MTV Quiz Graced By Fox Whelps."));
            notificationDMArrayList.add(new NotificationDM("Title","Today 6:00 pm","The Quick, Brown Fox Jumps Over A Lazy Dog. Djs Flock By When MTV Ax Quiz Prog. Junk MTV Quiz Graced By Fox Whelps."));
            notificationDMArrayList.add(new NotificationDM("Title","Today 6:00 pm","The Quick, Brown Fox Jumps Over A Lazy Dog. Djs Flock By When MTV Ax Quiz Prog. Junk MTV Quiz Graced By Fox Whelps."));
            notificationDMArrayList.add(new NotificationDM("Title","Today 6:00 pm","The Quick, Brown Fox Jumps Over A Lazy Dog. Djs Flock By When MTV Ax Quiz Prog. Junk MTV Quiz Graced By Fox Whelps."));
            notificationDMArrayList.add(new NotificationDM("Title","Today 6:00 pm","The Quick, Brown Fox Jumps Over A Lazy Dog. Djs Flock By When MTV Ax Quiz Prog. Junk MTV Quiz Graced By Fox Whelps."));
            notificationDMArrayList.add(new NotificationDM("Title","Today 6:00 pm","The Quick, Brown Fox Jumps Over A Lazy Dog. Djs Flock By When MTV Ax Quiz Prog. Junk MTV Quiz Graced By Fox Whelps."));
            notificationDMArrayList.add(new NotificationDM("Title","Today 6:00 pm","The Quick, Brown Fox Jumps Over A Lazy Dog. Djs Flock By When MTV Ax Quiz Prog. Junk MTV Quiz Graced By Fox Whelps."));
            notificationDMArrayList.add(new NotificationDM("Title","Today 6:00 pm","The Quick, Brown Fox Jumps Over A Lazy Dog. Djs Flock By When MTV Ax Quiz Prog. Junk MTV Quiz Graced By Fox Whelps."));
            notificationDMArrayList.add(new NotificationDM("Title","Today 6:00 pm","The Quick, Brown Fox Jumps Over A Lazy Dog. Djs Flock By When MTV Ax Quiz Prog. Junk MTV Quiz Graced By Fox Whelps."));
            notificationDMArrayList.add(new NotificationDM("Title","Today 6:00 pm","The Quick, Brown Fox Jumps Over A Lazy Dog. Djs Flock By When MTV Ax Quiz Prog. Junk MTV Quiz Graced By Fox Whelps."));
            notificationDMArrayList.add(new NotificationDM("Title","Today 6:00 pm","The Quick, Brown Fox Jumps Over A Lazy Dog. Djs Flock By When MTV Ax Quiz Prog. Junk MTV Quiz Graced By Fox Whelps."));

            notification_Rcv.setLayoutManager(new LinearLayoutManager((MainActivity) context));
            notification_Rcv.setAdapter(new Adapter_Notification(((MainActivity) context), notificationDMArrayList));

            setDetails();

        }
        return rootView;
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

    public void ShowProgress()
    {
        progress_bar.setVisibility(View.VISIBLE);
        txt_error.setVisibility(View.GONE);
        layout_parent.setVisibility(View.GONE);
    }

    public void DismissProgress()
    {
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
