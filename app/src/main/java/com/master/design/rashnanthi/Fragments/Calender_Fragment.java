package com.master.design.rashnanthi.Fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.master.design.rashnanthi.Activity.MainActivity;
import com.master.design.rashnanthi.Adapter.Country_Adapter;
import com.master.design.rashnanthi.Controller.AppController;
import com.master.design.rashnanthi.DataModel.County_Item;
import com.master.design.rashnanthi.R;
import com.master.design.rashnanthi.Utils.ConnectionDetector;

import java.util.ArrayList;

import butterknife.ButterKnife;
import it.sephiroth.android.library.widget.HListView;

public class Calender_Fragment extends Fragment {

    private View rootView;
    private Context context;
    ImageView home_menu;
    private ArrayList<County_Item> county_items;
    private Country_Adapter country_adapter;

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

            home_menu = rootView.findViewById(R.id.home_menu);

            home_menu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ((MainActivity) context).addFragment(new Menu_1_Fragment(), false);

                }
            });
//            idMapping();
            initList();

            Spinner spinnercountries =rootView.findViewById(R.id.country_spinner);

            country_adapter =new Country_Adapter( ((MainActivity) context),county_items);
            spinnercountries.setAdapter(country_adapter);

           spinnercountries.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
               @Override
               public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                   County_Item clickedItem = (County_Item) adapterView.getAdapter();
//                   String clickedCountryName=clickedItem.getMcountryName();
//                   int clickedCountryImage=clickedItem.getMcountryImage();
//                   Toast.makeText((MainActivity)context,clickedCountryName+"Selected",Toast.LENGTH_SHORT);
//                   Toast.makeText((MainActivity)context,clickedCountryImage+"Selected",Toast.LENGTH_SHORT);


               }

               @Override
               public void onNothingSelected(AdapterView<?> adapterView) {

               }
           });
//
//            setClickListeners();
//            setDetails();
        }
        return rootView;
    }

    private void initList() {

        county_items = new ArrayList<>();
        county_items.add(new County_Item("Kuwait", R.drawable.ic_united_arab_emirates));
        county_items.add(new County_Item("Bahrain", R.drawable.ic_bahrain));
        county_items.add(new County_Item("Oman", R.drawable.ic_oman));
        county_items.add(new County_Item("Qatar", R.drawable.ic_qatar));
        county_items.add(new County_Item("Saudi Arabia", R.drawable.ic_saudi_arabia));
        county_items.add(new County_Item("United Arab Emirates", R.drawable.ic_united_arab_emirates));

        county_items.add(new County_Item("Kuwait", R.drawable.ic_united_arab_emirates));
        county_items.add(new County_Item("Bahrain", R.drawable.ic_bahrain));
        county_items.add(new County_Item("Oman", R.drawable.ic_oman));
        county_items.add(new County_Item("Qatar", R.drawable.ic_qatar));
        county_items.add(new County_Item("Saudi Arabia", R.drawable.ic_saudi_arabia));
        county_items.add(new County_Item("United Arab Emirates", R.drawable.ic_united_arab_emirates));

    }
//
//    private void idMapping() {
//
//
//    }
//
//    private void setClickListeners() {
//
//    }
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
