package com.master.design.rashnanthi.Fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.master.design.rashnanthi.Activity.MainActivity;
import com.master.design.rashnanthi.Adapter.Adapter_MY_Event_1;
import com.master.design.rashnanthi.Adapter.Adapter_My_Event;
import com.master.design.rashnanthi.Controller.AppController;
import com.master.design.rashnanthi.DataModel.MyEventsRootDM;
import com.master.design.rashnanthi.DataModel.My_Event_DM;
import com.master.design.rashnanthi.Helper.User;
import com.master.design.rashnanthi.R;
import com.master.design.rashnanthi.Utils.ConnectionDetector;
import com.master.design.rashnanthi.Utils.Helper;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import it.sephiroth.android.library.widget.HListView;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class My_Post_1_Fragment extends Fragment {

    private View rootView;
    private Context context;
     User user;
    ImageView back_my_event;
    RecyclerView my_event_Rcv;
    private ArrayList<My_Event_DM> my_event_dmArrayList;

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
        user = new User(getActivity());

        connectionDetector = new ConnectionDetector(getActivity());
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage(getResources().getString(R.string.please_wait));
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);
        ((MainActivity) context).setTitle(getString(R.string.home));


        if (rootView == null) {
            rootView = inflater.inflate(R.layout.my_post_1_fragment_layout, container, false);
            ButterKnife.bind(this, rootView);

            setDetails();
            MyPostAPI();
            back_my_event = rootView.findViewById(R.id.back_my_event);

            my_event_Rcv = rootView.findViewById(R.id.my_event_Rcv);
            back_my_event.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((MainActivity) context).addFragment(new Coach_Account_Fragment(), false);

                }
            });

            my_event_dmArrayList = new ArrayList<>();
            my_event_Rcv = rootView.findViewById(R.id.my_event_Rcv);


//            ArrayList<My_Event_DM> my_event_dmArrayList = new ArrayList<>();
//
//            my_event_dmArrayList.add(new My_Event_DM("3 March 2022", R.drawable.my_event_img_1, R.drawable.my_event_img_2));
//            my_event_dmArrayList.add(new My_Event_DM("6 March 2022", R.drawable.my_event_1_img, R.drawable.my_event_img_2));
//            my_event_dmArrayList.add(new My_Event_DM("9 March 2022", R.drawable.my_event_img_2, R.drawable.my_event_img_1));
//            my_event_dmArrayList.add(new My_Event_DM("12 March 2022", R.drawable.my_event_img_1, R.drawable.my_event_img_2));
//
//
//            my_event_Rcv.setLayoutManager(new LinearLayoutManager((MainActivity) context));
//            my_event_Rcv.setAdapter(new Adapter_My_Event(((MainActivity) context), my_event_dmArrayList));


        }
        return rootView;
    }


    public void MyPostAPI() {
//        if (connectionDetector.isConnectingToInternet()) {
//            //                   String refreshedToken = FirebaseInstanceId.getInstance().getToken();
//            appController.paServices.MyEvents(String.valueOf(user.getId()), "1", new Callback<MyEventsRootDM>() {
//
//                @Override
//
//                public void success(MyEventsRootDM myEventsRootDM, Response response) {
//
//                    if (myEventsRootDM.getOutput().getSuccess().equalsIgnoreCase("1")) {
//
////                        Adapter_MY_Event_1 adapter_my_event_1 = new Adapter_MY_Event_1(getContext(), myEventsRootDM.getOutput().getData());
//                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
//                        my_event_Rcv.setLayoutManager(linearLayoutManager);
//                        my_event_Rcv.setAdapter(adapter_my_event_1);
//
//                    } else
//                        Helper.showToast(getActivity(), "your post does not exist");
//                }
//
//                @Override
//                public void failure(RetrofitError retrofitError) {
//                    Log.e("error", retrofitError.toString());
//
//                }
//            });
//        } else
//            Helper.showToast(getActivity(), getString(R.string.no_internet_connection));
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
