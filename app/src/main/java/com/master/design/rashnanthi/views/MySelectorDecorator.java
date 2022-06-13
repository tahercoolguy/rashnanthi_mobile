package com.master.design.rashnanthi.views;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;

import androidx.core.content.ContextCompat;

import com.master.design.rashnanthi.R;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;


/**
 * Use a custom selector
 */
public class  MySelectorDecorator implements DayViewDecorator {

    CalendarDay date;
    Context context1;

    public MySelectorDecorator(Activity context) {
        context1=context;
        date = CalendarDay.today();
    }

    @Override
    public boolean shouldDecorate(CalendarDay day) {
        return date != null && day.equals(date);
    }

    @Override
    public void decorate(DayViewFacade view) {
        view.setSelectionDrawable(context1.getDrawable(R.drawable.padding_my_selector));

    }
}