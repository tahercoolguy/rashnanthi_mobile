package com.master.design.rashnanthi.Fragments;

import android.app.Dialog;
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

import com.bumptech.glide.Glide;
import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import com.github.sundeepk.compactcalendarview.domain.Event;
import com.master.design.rashnanthi.Activity.MainActivity;
import com.master.design.rashnanthi.Activity.SpinneerActivity;
import com.master.design.rashnanthi.Activity.Story_activity;
import com.master.design.rashnanthi.Controller.AppController;
import com.master.design.rashnanthi.DataModel.CountryData;
import com.master.design.rashnanthi.DataModel.CountryRootDM;
import com.master.design.rashnanthi.DataModel.County_ItemDM;
import com.master.design.rashnanthi.DataModel.MyEventData1;
import com.master.design.rashnanthi.DataModel.MyEventRootDM1;
import com.master.design.rashnanthi.Helper.DialogUtil;
import com.master.design.rashnanthi.Helper.User;
import com.master.design.rashnanthi.R;
import com.master.design.rashnanthi.Utils.ConnectionDetector;
import com.master.design.rashnanthi.Utils.Helper;
import com.master.design.rashnanthi.views.RedColorDecorator;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import it.sephiroth.android.library.widget.HListView;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

import org.threeten.bp.LocalDate;

public class Calender_Fragment extends Fragment {


    long EventDate;
    private View rootView;
    private Context context;
    ImageView home_menu;
    DialogUtil dialogUtil;
    Dialog progress;
    ImageView story_viewer;
    private ArrayList<County_ItemDM> county_itemDMS;
    Spinner calender_page_country_spinner;



    @BindView(R.id.txt_error)
    TextView txt_error;

    @BindView(R.id.countryImg)
    ImageView countryImg;

    String countryidMain = "1", storydate;

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
    User user;

    @BindView(R.id.calendarView)
    com.prolificinteractive.materialcalendarview.MaterialCalendarView calendarView;

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
        ((MainActivity) context).setTitle(getString(R.string.calenderr));
        dialogUtil = new DialogUtil();


        if (rootView == null) {
            rootView = inflater.inflate(R.layout.calender_fragment_layout, container, false);
            ButterKnife.bind(this, rootView);



            Binding();
            user = new User(getActivity());
            appController = (AppController) getActivity().getApplicationContext();

            //            moth_year_txt = rootView.findViewById(R.id.moth_year_txt);
            backmonthImg = rootView.findViewById(R.id.backmonthImg);
            aheadamonthImg = rootView.findViewById(R.id.aheadamonthImg);
            story_viewer = rootView.findViewById(R.id.story_viewer);

            Glide.with(context)
                    .asGif()
                    .load(R.raw.icon_2)
                    .into(story_viewer);


            if(countryimg!=null){

                Picasso.get().load(AppController.base_image_url + countryimg).into(countryImg);

            }
            if(countryname!=null){
                country_spinner_Txt.setText(countryname);
            }



//            SetCOuntryNameAndImage();

            backmonthImg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    compactCalendar.scrollLeft();
                    calendarView.goToPrevious();

                }
            });


            aheadamonthImg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    compactCalendar.scrollRight();
                    calendarView.goToNext();

                }
            });

            story_viewer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    Story_activity story_activity = new Story_activity();
//                    story_activity.StoriesByDateAPI(storydate,countryidMain);

                    Calendar calendar = Calendar.getInstance();
                    String date = "", month = "";
                    if ((calendar.get(Calendar.DATE)) <= 9)
                        date = "0" + String.valueOf(calendar.get(Calendar.DATE));
                    else
                        date = String.valueOf(calendar.get(Calendar.DATE));


                    if ((calendar.get(Calendar.MONTH)) <= 9)
                        month = "0" + String.valueOf(calendar.get(Calendar.MONTH) + 1);
                    else
                        month = String.valueOf(calendar.get(Calendar.MONTH));

                    storydate = calendar.get(Calendar.YEAR) + "-" + month + "-" + date;


                    Intent intent = new Intent(getActivity(), Story_activity.class);
                    intent.putExtra("date", storydate);
                    intent.putExtra("countryid", countryidMain);
                    startActivity(intent);
                }
            });

            home_menu = rootView.findViewById(R.id.home_menu);

            home_menu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ((MainActivity) context).addFragment(new Menu_1_Fragment(), true);

                }
            });


            compactCalendar = (CompactCalendarView) rootView.findViewById(R.id.compactcalendar_view);
            compactCalendar.setUseThreeLetterAbbreviation(true);

            compactCalendar.setCurrentSelectedDayBackgroundColor(Color.YELLOW);

            compactCalendar.setEventIndicatorStyle(CompactCalendarView.FILL_LARGE_INDICATOR);

            compactCalendar.displayOtherMonthDays(true);

            SimpleDateFormat formatter = new SimpleDateFormat("MMM yyyy");
            Date date = new Date();
//            moth_year_txt.setText(formatter.format(date));

            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.DATE, 1);
//            calendar.set(Calendar.DATE,);


            compactCalendar.setUseThreeLetterAbbreviation(false);
            compactCalendar.setFirstDayOfWeek(Calendar.SUNDAY);
            compactCalendar.setCurrentSelectedDayBackgroundColor(View.getDefaultSize(10, 2));


            compactCalendar.displayOtherMonthDays(true);


            moth_year_txt = rootView.findViewById(R.id.moth_year_txt);
            Calendar cal = Calendar.getInstance();
            SimpleDateFormat month_date = new SimpleDateFormat("MMMM yyyy");
            String ma = month_date.format(cal.getTime());
            moth_year_txt.setText(ma);

//            String s="1654108200000";
//            long l=Long.parseLong(s);
//
//            Event ev2 = new Event(Color.YELLOW, calendar.getTimeInMillis(), "Event 1");
//            compactCalendar.addEvent(ev2);
//
//            calendar.add(Calendar.DATE, 1);
//            Event ev1 = new Event(Color.RED, calendar.getTimeInMillis(), "Event 1");
//            compactCalendar.addEvent(ev1);
//
            compactCalendar.setTargetHeight(700);


            compactCalendar.setListener(new CompactCalendarView.CompactCalendarViewListener() {
                @Override
                public void onDayClick(Date dateClicked) {


                    if (calendar.getTimeInMillis() != 0) {

                        List<Event> e = compactCalendar.getEvents(dateClicked);
                        if (!e.isEmpty()) {
                            Event_Small_Image_Fragment event_small_image_fragment = new Event_Small_Image_Fragment();
                            Bundle bd = new Bundle();
                            SimpleDateFormat datenew = new SimpleDateFormat("yyyy-MM-dd");

                            bd.putString("date", datenew.format(dateClicked));
                            bd.putString("countryid", countryidMain);
                            event_small_image_fragment.setArguments(bd);
                            ((MainActivity) context).addFragment(event_small_image_fragment, true);
                        } else {
                            Toast.makeText(context, "There is no event ", Toast.LENGTH_SHORT).show();
                        }
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


            calendarView.setOnDateChangedListener(new OnDateSelectedListener() {
                @Override
                public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
                    for (CalendarDay cal : Appointment
                    ) {
                        if (date.getDay() == cal.getDay() && date.getMonth() == cal.getMonth() && date.getYear() == cal.getYear()) {
                            String Date = String.valueOf(date.getDay()), Month = String.valueOf(date.getMonth()), Year = String.valueOf(date.getYear());

                            if (cal.getDay() <= 9)
                                Date = "0" + cal.getDay();

                            if ((cal.getMonth() + 1) <= 9) {
                                Month = "0" + cal.getMonth();
                            }

                            Event_Small_Image_Fragment event_small_image_fragment = new Event_Small_Image_Fragment();
                            Bundle bd = new Bundle();
                            bd.putString("date", Year + "-" + Month + "-" + Date);
                            bd.putString("countryid", countryidMain);
                            event_small_image_fragment.setArguments(bd);
                            ((MainActivity) context).addFragment(event_small_image_fragment, true);
                        }
                    }
                }
            });

//            String[] days = {"","","","","","",""};

            CharSequence[] days = {"", "", "", "", "", "", ""};
            calendarView.setWeekDayLabels(days);
//            compactCalendar.setDayColumnNames(days);
            calendarView.setTopbarVisible(false);


//            try {
//                final ArrayList<CalendarDay> Appointment=new ArrayList<>();
//                Appointment.add(CalendarDay.from(LocalDate.parse("2022-06-09")));
//                Appointment.add(CalendarDay.from(LocalDate.parse("2022-06-10")));
//                Appointment.add(CalendarDay.from(LocalDate.parse("2022-06-11")));
//                RedColorDecorator redColorDecorator = new RedColorDecorator(getActivity(),Appointment);
//                calendarView.addDecorator(redColorDecorator);
//
//            }catch (Exception e)
//            {
//
//            }

//            MySelectorDecorator mySelectorDecorator = new MySelectorDecorator(getActivity());
//            calendarView.addDecorator(mySelectorDecorator);

            myEventsApi(countryidMain);
        }
        return rootView;
    }

    final ArrayList<CalendarDay> Appointment = new ArrayList<>();

    public static Calendar toCalendar(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal;
    }

    public void myEventsApi(String countryid) {
        if (connectionDetector.isConnectingToInternet()) {
            progress = dialogUtil.showProgressDialog(getActivity(), getString(R.string.please_wait));

            appController.paServices.AllEvent(countryid, "2022-01-01", "2040-01-01", new Callback<MyEventRootDM1>() {
                @Override
                public void success(MyEventRootDM1 myEventRootDM1, Response response) {
                    progress.dismiss();

                    Appointment.clear();

                    if (myEventRootDM1.getOutput().getSuccess().equalsIgnoreCase("1")) {
                        for (MyEventData1 dm :
                                myEventRootDM1.getOutput().getData()
                        ) {

                            SimpleDateFormat mdyFormat = new SimpleDateFormat("yyyy-MM-dd");
                            try {
                                Date km = mdyFormat.parse(dm.getEventdate());


                                Event ev2 = new Event(Color.YELLOW, toCalendar(km).getTimeInMillis(), dm.getEventdate());
                                compactCalendar.addEvent(ev2);

                                ///This is mine
                                try {
                                    LocalDate km1 = LocalDate.parse(dm.getEventdate());
                                    Appointment.add(CalendarDay.from(km1));
                                } catch (Exception e) {
                                    e.toString();
                                }


                            } catch (Exception e) {
                                e.printStackTrace();
                            }


                        }

                        //this is mine
                        RedColorDecorator redColorDecorator = new RedColorDecorator(getActivity(), Appointment);
                        calendarView.addDecorator(redColorDecorator);


                    } else {
                        Helper.showToast(getActivity(),  getString(R.string.no_posts));
                        List<Event> events = compactCalendar.getEventsForMonth((Calendar.getInstance()).getTime());
                        for (Event e : events
                        ) {
                            compactCalendar.removeEvent(e, true);
                        }

                        calendarView.removeDecorators();
                    }
                }

                @Override
                public void failure(RetrofitError retrofitError) {
                    progress.dismiss();
                    Log.e("error", retrofitError.toString());

                }
            });
        } else
            Helper.showToast(getActivity(), getString(R.string.no_internet_connection));
    }


//    public void GeteventsbyCountryDateAPI() {
//        if (connectionDetector.isConnectingToInternet()) {
//
//            MultipartTypedOutput multipartTypedOutput = new MultipartTypedOutput();
//            multipartTypedOutput.addPart("countryid", new TypedString("1"));
//            multipartTypedOutput.addPart("date", new TypedString("2022-06-02"));
//
//
//            appController.paServices.GetEventsByCountryDate(multipartTypedOutput, new Callback<GetEventsByCountryDateRootDM>() {
//                @Override
//                public void success(GetEventsByCountryDateRootDM getEventsByCountryDateRootDM, Response response) {
//                    if (getEventsByCountryDateRootDM.getOutput().getSuccess().equalsIgnoreCase("1")) {
//
////                        EventDate = getEventsByCountryDateRootDM.getOutput().getData().get(0).getEventdate();
//
//
//
//                    } else
//                        Helper.showToast(getActivity(), "Some network happened ..");
//                }
//
//                @Override
//                public void failure(RetrofitError error) {
//                    Log.e("String", error.toString());
//                }
//            });
//        }
//    }


    @BindView(R.id.spinnerCountryBottomRL)
    RelativeLayout spinnerCountryBottomRL;



    @OnClick(R.id.spinnerCountryBottomRL)
    public void SpinnerCountryOpenActivity() {

       startActivityForResult(new Intent(context, SpinneerActivity.class),48);
    }

    String countryname  ;
    String countryimg  ;
    String countryid;
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Intent intent=data;
        if(data!=null){
            countryname = data.getStringExtra("countryname");
            countryimg = data.getStringExtra("countryimg");
            countryid = data.getStringExtra("countryid");
            Picasso.get().load(AppController.base_image_url + countryimg).into(countryImg);
            country_spinner_Txt.setText(countryname);

            myEventsApi(countryid);
        }



    }

    //    BottomForAll bottomForAll;

    ArrayList<CountryData> approvalOne = new ArrayList<>();
    ArrayList<String> approvalTwo = new ArrayList<>();


//    @OnClick(R.id.spinnerCountryBottomRL)
//    public void SpinnerCountry() {
//
//        bottomForAll = new BottomForAll();
//        bottomForAll.arrayList = approvalOne;
//
//        bottomForAll.setResponseListener(new ResponseListener() {
//            @Override
//            public void response(int position, Object object) {
//
//                country_spinner_Txt.setText(data.get(position).getTitle());
//                Picasso.get().load(AppController.base_image_url + data.get(position).getImage()).into(countryImg);
//                countryidMain=data.get(position).getId();
//                myEventsApi(countryidMain);
////                countryImg.setImageResource(Integer.parseInt(data.get(position).getImage()));
////                AreaID = data.get(selected).getId();
////                for (CountryData s:data
////                ) {
////                    if(s.getCallingcode().equals((String) object))
////                        AreaID = s.getId();
////                }
//
//
//            }
//        });
//
//
//        bottomForAll.show(getParentFragmentManager(), "bottomSheetCountry");
//    }


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
                        Helper.showToast(getActivity(),  getString(R.string.some_netork_happened));
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
