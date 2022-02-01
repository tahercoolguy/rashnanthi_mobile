package com.master.design.rashnanthi.Fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
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

import com.master.design.rashnanthi.Activity.MainActivity;
import com.master.design.rashnanthi.Activity.Story_activity;
import com.master.design.rashnanthi.Adapter.Adapter_Country_Spinner;
import com.master.design.rashnanthi.Controller.AppController;
import com.master.design.rashnanthi.DataModel.County_ItemDM;
import com.master.design.rashnanthi.R;
import com.master.design.rashnanthi.Utils.ConnectionDetector;

import java.util.ArrayList;

import butterknife.ButterKnife;
import it.sephiroth.android.library.widget.HListView;

public class Calender_Fragment extends Fragment {

    private View rootView;
    private Context context;
    ImageView home_menu;
    ImageView story_viewer;
    private ArrayList<County_ItemDM> county_itemDMS;
    Spinner calender_page_country_spinner;

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
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage(getResources().getString(R.string.please_wait));
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);
        ((MainActivity) context).setTitle(getString(R.string.home));
        if (rootView == null) {
            rootView = inflater.inflate(R.layout.calender_fragment_layout, container, false);
            ButterKnife.bind(this, rootView);

            story_viewer=rootView.findViewById(R.id.story_viewer);

            story_viewer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(((MainActivity) context), Story_activity.class));
                }
            });

            home_menu = rootView.findViewById(R.id.home_menu);

            home_menu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ((MainActivity) context).addFragment(new Menu_1_Fragment(), false);

                }
            });


            calender_page_country_spinner = rootView.findViewById(R.id.calender_page_country_spinner);


            ArrayList<County_ItemDM> county_itemDMS;


            county_itemDMS = new ArrayList<>();
//            county_itemDMS.add(new County_ItemDM("Kuwait",R.drawable.ic_oman));
//            county_itemDMS.add(new County_ItemDM("Oman",R.drawable.ic_oman));
            county_itemDMS.add(new County_ItemDM("Saudi Arabia", R.drawable.ic_saudi_arabia));
            county_itemDMS.add(new County_ItemDM("Qatar", R.drawable.ic_qatar));
            county_itemDMS.add(new County_ItemDM("Bahrain", R.drawable.ic_bahrain));
            county_itemDMS.add(new County_ItemDM("United Arab Emirates", R.drawable.ic_united_arab_emirates));

//            county_itemDMS.add(new County_ItemDM("Kuwait",R.drawable.ic_oman));
//            county_itemDMS.add(new County_ItemDM("Oman",R.drawable.ic_oman));
            county_itemDMS.add(new County_ItemDM("Saudi Arabia", R.drawable.ic_saudi_arabia));
            county_itemDMS.add(new County_ItemDM("Qatar", R.drawable.ic_qatar));
            county_itemDMS.add(new County_ItemDM("Bahrain", R.drawable.ic_bahrain));
            county_itemDMS.add(new County_ItemDM("United Arab Emirates", R.drawable.ic_united_arab_emirates));


            Adapter_Country_Spinner adapter_country_spinner;

            adapter_country_spinner = new Adapter_Country_Spinner(context, county_itemDMS);


            calender_page_country_spinner.setAdapter(adapter_country_spinner);


//            calender_page_country_spinner.setOnItemSelectedListener(
//                    new AdapterView.OnItemSelectedListener() {
//                        @Override
//                        public void onItemSelected(AdapterView<?> parent,
//                                                   View view, int position, long id)
//                        {
//
//                            // It returns the clicked item.
//                            County_ItemDM clickedItem = (County_ItemDM)
//                                    parent.getItemAtPosition(position);
//                            String name = clickedItem.getMcountryName();
////                            Toast.makeText(MainActivity.this, name + " selected", Toast.LENGTH_SHORT).show();
//                        }
//                        @Override
//                        public void onNothingSelected(AdapterView<?> parent)
//                        {
//                        }
//                    });


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
