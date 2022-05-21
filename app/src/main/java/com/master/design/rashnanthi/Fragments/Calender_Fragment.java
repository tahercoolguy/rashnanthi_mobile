package com.master.design.rashnanthi.Fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import com.github.sundeepk.compactcalendarview.domain.Event;
import com.master.design.rashnanthi.Activity.MainActivity;
import com.master.design.rashnanthi.Activity.Story_activity;
import com.master.design.rashnanthi.Controller.AppController;
import com.master.design.rashnanthi.DataModel.CountryData;
import com.master.design.rashnanthi.DataModel.CountryRootDM;
import com.master.design.rashnanthi.DataModel.County_ItemDM;
import com.master.design.rashnanthi.Helper.BottomForAll;
import com.master.design.rashnanthi.Helper.ResponseListener;
import com.master.design.rashnanthi.R;
import com.master.design.rashnanthi.Utils.ConnectionDetector;
import com.master.design.rashnanthi.Utils.Helper;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import it.sephiroth.android.library.widget.HListView;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class Calender_Fragment extends Fragment {


    private View rootView;
    private Context context;
    ImageView home_menu;
    ImageView story_viewer;
    private ArrayList<County_ItemDM> county_itemDMS;
    Spinner calender_page_country_spinner;

    @BindView(R.id.spinnerCountryBottomRL)
    RelativeLayout spinnerCountryBottomRL;


    @BindView(R.id.txt_error)
    TextView txt_error;

    @BindView(R.id.countryImg)
    ImageView countryImg;

    @BindView(R.id.country_spinner_Txt)
    TextView country_spinner_Txt;


    CompactCalendarView compactCalendar;
    ImageView backmonthImg, aheadamonthImg;
    TextView moth_year_txt;


    @BindView(R.id.progress_bar)
    ProgressBar progress_bar;


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
        connectionDetector = new ConnectionDetector(getActivity());
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage(getResources().getString(R.string.please_wait));
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);
        ((MainActivity) context).setTitle(getString(R.string.home));


        if (rootView == null) {
            rootView = inflater.inflate(R.layout.calender_fragment_layout, container, false);
            ButterKnife.bind(this, rootView);
            Binding();

             //            moth_year_txt = rootView.findViewById(R.id.moth_year_txt);
            backmonthImg = rootView.findViewById(R.id.backmonthImg);
            aheadamonthImg = rootView.findViewById(R.id.aheadamonthImg);
            story_viewer = rootView.findViewById(R.id.story_viewer);

            backmonthImg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    compactCalendar.scrollLeft();

                }
            });

            aheadamonthImg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    compactCalendar.scrollRight();

                }
            });

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




            ArrayList<County_ItemDM> county_itemDMS;


            county_itemDMS = new ArrayList<>();
            county_itemDMS.add(new County_ItemDM("Bahrain", R.drawable.ic_bahrain));

            county_itemDMS.add(new County_ItemDM("Kuwait", R.drawable.kuwaitttttt));
            county_itemDMS.add(new County_ItemDM("Oman", R.drawable.oman_flag));
            county_itemDMS.add(new County_ItemDM("Saudi Arabia", R.drawable.ic_saudi_arabia));
            county_itemDMS.add(new County_ItemDM("Qatar", R.drawable.ic_qatar));
            county_itemDMS.add(new County_ItemDM("Bahrain", R.drawable.ic_bahrain));
            county_itemDMS.add(new County_ItemDM("United Arab Emirates", R.drawable.ic_united_arab_emirates));
            county_itemDMS.add(new County_ItemDM("Kuwait", R.drawable.kuwaitttttt));
            county_itemDMS.add(new County_ItemDM("Oman", R.drawable.oman_flag));
            county_itemDMS.add(new County_ItemDM("Saudi Arabia", R.drawable.ic_saudi_arabia));
            county_itemDMS.add(new County_ItemDM("Qatar", R.drawable.ic_qatar));
            county_itemDMS.add(new County_ItemDM("Bahrain", R.drawable.ic_bahrain));
            county_itemDMS.add(new County_ItemDM("United Arab Emirates", R.drawable.ic_united_arab_emirates));


//            Adapter_Spin adapter_country_spinner;
//            adapter_country_spinner = new Adapter_Spin(context, county_itemDMS);
//            calender_page_country_spinner.setAdapter(adapter_country_spinner);


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


            compactCalendar = (CompactCalendarView) rootView.findViewById(R.id.compactcalendar_view);
            compactCalendar.setUseThreeLetterAbbreviation(true);

//         compactCalendar.setCurrentSelectedDayBackgroundColor(Color.RED);

            SimpleDateFormat formatter = new SimpleDateFormat("MMM yyyy");
            Date date = new Date();
//            moth_year_txt.setText(formatter.format(date));

            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.DATE, 1);

            compactCalendar.setUseThreeLetterAbbreviation(false);
            compactCalendar.setFirstDayOfWeek(Calendar.SUNDAY);
            compactCalendar.setCurrentSelectedDayBackgroundColor(View.getDefaultSize(10, 2));


            compactCalendar.displayOtherMonthDays(false);


            moth_year_txt = rootView.findViewById(R.id.moth_year_txt);
            Calendar cal = Calendar.getInstance();
            SimpleDateFormat month_date = new SimpleDateFormat("MMMM yyyy");
            String ma = month_date.format(cal.getTime());
            moth_year_txt.setText(ma);


            Event ev1 = new Event(Color.YELLOW, calendar.getTimeInMillis(), "Event 1");
            compactCalendar.addEvent(ev1);

            compactCalendar.setTargetHeight(700);


            compactCalendar.setListener(new CompactCalendarView.CompactCalendarViewListener() {
                @Override
                public void onDayClick(Date dateClicked) {
                    appController = (AppController) getActivity().getApplicationContext();
                    if (calendar.getTimeInMillis() != 0) {

                        ((MainActivity) context).addFragment(new Event_Small_Image_Fragment(), true);

                    } else {
                        Toast.makeText(context, "There is no event ", Toast.LENGTH_SHORT).show();

                    }


                }

                @NonNull
                @Override
                public String toString() {
                    moth_year_txt.setText(formatter.format(compactCalendar));
                    return super.toString();
                }

                @Override
                public void onMonthScroll(Date firstDayOfNewMonth) {
                    moth_year_txt.setText(formatter.format(firstDayOfNewMonth));
                }
            });
            setDetails();
            setClickListeners();


        }
        return rootView;
    }



    BottomForAll bottomForAll;

    ArrayList<CountryData> approvalOne = new ArrayList<>();
    ArrayList<String> approvalTwo = new ArrayList<>();


    @OnClick(R.id.spinnerCountryBottomRL)
    public void SpinnerCountry() {

        bottomForAll = new BottomForAll();
        bottomForAll.arrayList = approvalOne;

        bottomForAll.setResponseListener(new ResponseListener() {
            @Override
            public void response(int position, Object object) {

                country_spinner_Txt.setText(data.get(position).getTitle());
                Picasso.get().load(AppController.base_image_url +data.get(position).getImage()).into(countryImg);

//                countryImg.setImageResource(Integer.parseInt(data.get(position).getImage()));
//                AreaID = data.get(selected).getId();
//                for (CountryData s:data
//                ) {
//                    if(s.getCallingcode().equals((String) object))
//                        AreaID = s.getId();
//                }


            }
        });


        bottomForAll.show(getParentFragmentManager(), "bottomSheetCountry");
    }



    ArrayList<CountryData> data = new ArrayList<>();

    public void Binding() {
        if (connectionDetector.isConnectingToInternet()) {
            appController.paServices.Countries(new Callback<CountryRootDM>() {
                @Override
                public void success(CountryRootDM countryRootDM, Response response) {
                    if (countryRootDM.getOutput().getSuccess().equalsIgnoreCase("1")) {
                        data = countryRootDM.getOutput().getData();
                        for (CountryData area : countryRootDM.getOutput().getData()
                        ) {
                            approvalOne.add(area);
                            if (approvalOne.get(0).getId().equalsIgnoreCase("1")) {
                                country_spinner_Txt.setText(data.get(0).getTitle());
                                Picasso.get().load(AppController.base_image_url + data.get(0).getImage()).into(countryImg);
                            }
                        }
                    } else
                        Helper.showToast(getActivity(), "Some network happened ..");
                }

                @Override
                public void failure(RetrofitError error) {
                    Log.e("String", error.toString());
                }
            });
        }
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
        }, 100);


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
