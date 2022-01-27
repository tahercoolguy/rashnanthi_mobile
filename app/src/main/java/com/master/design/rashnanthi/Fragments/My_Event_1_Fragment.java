package com.master.design.rashnanthi.Fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.master.design.rashnanthi.Activity.MainActivity;
import com.master.design.rashnanthi.Adapter.Adapter_MY_Event_1;
import com.master.design.rashnanthi.Controller.AppController;
import com.master.design.rashnanthi.DataModel.My_Event_1DM;
import com.master.design.rashnanthi.R;
import com.master.design.rashnanthi.Utils.ConnectionDetector;

import java.util.ArrayList;

import butterknife.ButterKnife;
import it.sephiroth.android.library.widget.HListView;

public class My_Event_1_Fragment extends Fragment {

    private View rootView;
    private Context context;
    ImageView my_event_menu_1, my_event_menu_1_back;
    RecyclerView my_event_Rcv;
    private ArrayList<My_Event_1DM> my_event_1DMArrayList;


//    @BindView(R.id.progress_bar) ProgressBar progress_bar;
//    @BindView(R.id.txt_error) TextView txt_error;

    //    @BindView(R.id.layout_parent) LinearLayout layout_parent;
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
//        my_event_menu_1=rootView.findViewById(R.id.my_event_menu_1);
//
//        my_event_menu_1_back=rootView.findViewById(R.id.my_event_menu_1_back);

        if (rootView == null) {
            rootView = inflater.inflate(R.layout.my_event_1_fragment_layout, container, false);
            ButterKnife.bind(this, rootView);


//
//            my_event_menu_1.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//
//                }
//            });

            my_event_1DMArrayList = new ArrayList<>();
            my_event_Rcv = rootView.findViewById(R.id.my_event_Rcv);


            ArrayList<My_Event_1DM> my_event_1DMArrayList = new ArrayList<>();

            my_event_1DMArrayList.add(new My_Event_1DM("3 March 2022", R.drawable.images, R.drawable.images));
            my_event_1DMArrayList.add(new My_Event_1DM("3 March 2022", R.drawable.images, R.drawable.images));
            my_event_1DMArrayList.add(new My_Event_1DM("3 March 2022", R.drawable.images, R.drawable.images));
            my_event_1DMArrayList.add(new My_Event_1DM("3 March 2022", R.drawable.images, R.drawable.images));
            my_event_1DMArrayList.add(new My_Event_1DM("3 March 2022", R.drawable.images, R.drawable.images));
            my_event_1DMArrayList.add(new My_Event_1DM("3 March 2022", R.drawable.images, R.drawable.images));
            my_event_1DMArrayList.add(new My_Event_1DM("3 March 2022", R.drawable.images, R.drawable.images));
            my_event_1DMArrayList.add(new My_Event_1DM("3 March 2022", R.drawable.images, R.drawable.images));

            my_event_1DMArrayList.add(new My_Event_1DM("3 March 2022", R.drawable.images, R.drawable.images));
            my_event_1DMArrayList.add(new My_Event_1DM("3 March 2022", R.drawable.images, R.drawable.images));
            my_event_1DMArrayList.add(new My_Event_1DM("3 March 2022", R.drawable.images, R.drawable.images));
            my_event_1DMArrayList.add(new My_Event_1DM("3 March 2022", R.drawable.images, R.drawable.images));


            my_event_Rcv.setLayoutManager(new LinearLayoutManager((MainActivity) context));
            my_event_Rcv.setAdapter(new Adapter_MY_Event_1(((MainActivity) context), my_event_1DMArrayList));


        }
        return rootView;
    }

//
//    @Override
//    public void onResume() {
//        super.onResume();
//    }
//
//    private void setDetails() {
//       ShowProgress();
//        rootView.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//               DismissProgress();
//            }
//        }, 1500);
//
//
//

//    }

//    public void ShowProgress()
//    {
//        progress_bar.setVisibility(View.VISIBLE);
//        txt_error.setVisibility(View.GONE);
//        layout_parent.setVisibility(View.GONE);
//    }
//
//    public void DismissProgress()
//    {
//        progress_bar.setVisibility(View.GONE);
//        txt_error.setVisibility(View.GONE);
//        layout_parent.setVisibility(View.VISIBLE);
//    }

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
