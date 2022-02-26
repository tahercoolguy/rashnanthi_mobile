package com.master.design.rashnanthi.Fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.master.design.rashnanthi.Activity.MainActivity;
import com.master.design.rashnanthi.Adapter.Adapter_Coach_Fgmt;
 import com.master.design.rashnanthi.Adapter.Adapter_Coach__grid_Fgmt;
import com.master.design.rashnanthi.Adapter.Adapter_Country_Spinner;
import com.master.design.rashnanthi.Controller.AppController;
import com.master.design.rashnanthi.DataModel.CoachGridDM;
import com.master.design.rashnanthi.DataModel.County_ItemDM;
import com.master.design.rashnanthi.R;
import com.master.design.rashnanthi.Utils.ConnectionDetector;

import java.util.ArrayList;

import it.sephiroth.android.library.widget.HListView;

public class coach_grid_account_Fragment extends Fragment {

    private View rootView;
    private Context context;
     RecyclerView my_account_grid_Rcv;
//     ImageView coach_menu,like_coach_grid;
    private ArrayList<CoachGridDM> coachGridDMArrayList;
    private ArrayList<County_ItemDM> county_itemDMS;
    Spinner calender_page_country_spinner;

    ImageView coach_menu_Back;


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
 //        coach_menu =rootView.findViewById(R.id.coach_menu);
//
//        coach_menu.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                ((MainActivity) context).addFragment(new Menu_1_Fragment() , false);
//
//            }
//        });


//        like_coach_grid=rootView.findViewById(R.id.like_coach_grid);

        if (rootView == null) {
            rootView = inflater.inflate(R.layout.coach_grid_account_fragment_layout, container, false);

             my_account_grid_Rcv = rootView.findViewById(R.id.my_account_grid_Rcv);
            coach_menu_Back=rootView.findViewById(R.id.coach_menu_Back);

            coach_menu_Back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((MainActivity)context).addFragment(new Menu_1_Fragment(),false);
                }
            });



            calender_page_country_spinner = rootView.findViewById(R.id.calender_page_country_spinner);


            ArrayList<County_ItemDM> county_itemDMS;


            county_itemDMS = new ArrayList<>();
            county_itemDMS.add(new County_ItemDM("Kuwait",R.drawable.kuwait_flag));
            county_itemDMS.add(new County_ItemDM("Oman",R.drawable.oman_flag));
            county_itemDMS.add(new County_ItemDM("Saudi Arabia", R.drawable.ic_saudi_arabia));
            county_itemDMS.add(new County_ItemDM("Qatar", R.drawable.ic_qatar));
            county_itemDMS.add(new County_ItemDM("Bahrain", R.drawable.ic_bahrain));
            county_itemDMS.add(new County_ItemDM("United Arab Emirates", R.drawable.ic_united_arab_emirates));
            county_itemDMS.add(new County_ItemDM("Kuwait",R.drawable.kuwait_flag));
            county_itemDMS.add(new County_ItemDM("Oman",R.drawable.oman_flag));
            county_itemDMS.add(new County_ItemDM("Saudi Arabia", R.drawable.ic_saudi_arabia));
            county_itemDMS.add(new County_ItemDM("Qatar", R.drawable.ic_qatar));
            county_itemDMS.add(new County_ItemDM("Bahrain", R.drawable.ic_bahrain));
            county_itemDMS.add(new County_ItemDM("United Arab Emirates", R.drawable.ic_united_arab_emirates));




            Adapter_Country_Spinner adapter_country_spinner;

            adapter_country_spinner = new Adapter_Country_Spinner(context, county_itemDMS);


            calender_page_country_spinner.setAdapter(adapter_country_spinner);




            coachGridDMArrayList = new ArrayList<>();


            ArrayList<CoachGridDM> coachGridDMArrayList = new ArrayList<>();

            coachGridDMArrayList.add(new CoachGridDM(R.drawable.lady,R.drawable.toggle_like_red_black));
            coachGridDMArrayList.add(new CoachGridDM(R.drawable.girl,R.drawable.toggle_like_red_black));
            coachGridDMArrayList.add(new CoachGridDM(R.drawable.boy,R.drawable.toggle_like_red_black));

            coachGridDMArrayList.add(new CoachGridDM(R.drawable.lady,R.drawable.toggle_like_red_black));
            coachGridDMArrayList.add(new CoachGridDM(R.drawable.girl,R.drawable.toggle_like_red_black));
            coachGridDMArrayList.add(new CoachGridDM(R.drawable.boy,R.drawable.toggle_like_red_black));


            coachGridDMArrayList.add(new CoachGridDM(R.drawable.lady,R.drawable.toggle_like_red_black));
            coachGridDMArrayList.add(new CoachGridDM(R.drawable.girl,R.drawable.toggle_like_red_black));
            coachGridDMArrayList.add(new CoachGridDM(R.drawable.boy,R.drawable.toggle_like_red_black));


            coachGridDMArrayList.add(new CoachGridDM(R.drawable.lady,R.drawable.toggle_like_red_black));
            coachGridDMArrayList.add(new CoachGridDM(R.drawable.girl,R.drawable.toggle_like_red_black));
            coachGridDMArrayList.add(new CoachGridDM(R.drawable.boy,R.drawable.toggle_like_red_black));

            coachGridDMArrayList.add(new CoachGridDM(R.drawable.lady,R.drawable.toggle_like_red_black));
            coachGridDMArrayList.add(new CoachGridDM(R.drawable.girl,R.drawable.toggle_like_red_black));
            coachGridDMArrayList.add(new CoachGridDM(R.drawable.boy,R.drawable.toggle_like_red_black));

            coachGridDMArrayList.add(new CoachGridDM(R.drawable.lady,R.drawable.toggle_like_red_black));
            coachGridDMArrayList.add(new CoachGridDM(R.drawable.girl,R.drawable.toggle_like_red_black));
            coachGridDMArrayList.add(new CoachGridDM(R.drawable.boy,R.drawable.toggle_like_red_black));


            coachGridDMArrayList.add(new CoachGridDM(R.drawable.lady,R.drawable.toggle_like_red_black));
            coachGridDMArrayList.add(new CoachGridDM(R.drawable.girl,R.drawable.toggle_like_red_black));
            coachGridDMArrayList.add(new CoachGridDM(R.drawable.boy,R.drawable.toggle_like_red_black));


            coachGridDMArrayList.add(new CoachGridDM(R.drawable.lady,R.drawable.toggle_like_red_black));
            coachGridDMArrayList.add(new CoachGridDM(R.drawable.girl,R.drawable.toggle_like_red_black));
            coachGridDMArrayList.add(new CoachGridDM(R.drawable.boy,R.drawable.toggle_like_red_black));

            int numberOfColumns = 3;
            my_account_grid_Rcv.setLayoutManager(new GridLayoutManager(((MainActivity) context), numberOfColumns));

            my_account_grid_Rcv.setAdapter(new Adapter_Coach__grid_Fgmt(((MainActivity) context), coachGridDMArrayList));


//            my_account_grid_Rcv.setLayoutManager(new LinearLayoutManager((MainActivity) context));
//            my_account_grid_Rcv.setAdapter(new Adapter_Coach_Grid_Fgmt(((MainActivity) context), coachGridDMArrayList));




        }
        return rootView;
    }



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
