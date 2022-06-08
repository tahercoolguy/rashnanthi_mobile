package com.master.design.rashnanthi.views;
import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;

import com.master.design.rashnanthi.R;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;

import java.util.ArrayList;


public class RedColorDecorator implements DayViewDecorator {

    private ArrayList<CalendarDay> date;
    Context contextl;
    public RedColorDecorator(Context context, ArrayList<CalendarDay> dates) {

        contextl=context;
        date=dates;
    }

    @Override
    public boolean shouldDecorate(CalendarDay day) {
        return date.contains(day);
    }

    @Override
    public void decorate(DayViewFacade view) {
        try {
            view.setBackgroundDrawable(contextl.getDrawable(R.drawable.background_calendar));
        }catch (Exception e)
        {

        }
    }



}