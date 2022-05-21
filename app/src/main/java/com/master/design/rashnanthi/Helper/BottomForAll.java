package com.master.design.rashnanthi.Helper;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.master.design.rashnanthi.Adapter.Adapter_Bottom;
import com.master.design.rashnanthi.DataModel.CountryData;
import com.master.design.rashnanthi.Fragments.Coach_Grid_Account_Fragment;
import com.master.design.rashnanthi.R;

import java.util.ArrayList;

public class BottomForAll extends BottomSheetDialogFragment implements View.OnClickListener, View.OnTouchListener {

    private Context context;
    private ListView listview;
//   public EditText search;
    private TextView  txt_error_message;
    private ResponseListener responseListener;
     private BottomSheetBehavior bottomSheetBehavior;
    private ProgressBar progressBar;
    private RelativeLayout layout_top;

    private boolean isTopScroll = false;
    private CountryData selected;
    public ArrayList<CountryData> arrayList=new ArrayList<>(),tmp=new ArrayList<>();
    private Adapter_Bottom adapter;


   public boolean isSort=false;

    private BottomSheetBehavior.BottomSheetCallback mBottomSheetBehaviorCallback = new BottomSheetBehavior.BottomSheetCallback() {

        @Override
        public void onStateChanged(@NonNull View bottomSheet, int newState) {
            if (newState == BottomSheetBehavior.STATE_HIDDEN) {
                dismiss();
            } else if (newState == BottomSheetBehavior.STATE_DRAGGING && Util.canScrollUp(listview)) {
                if (!isTopScroll) {
                    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                }
            }

        }

        @Override
        public void onSlide(@NonNull View bottomSheet, float slideOffset) {
        }
    };

    @SuppressLint("RestrictedApi")
    @Override
    public void setupDialog(Dialog dialog, int style) {
        super.setupDialog(dialog, style);
        this.context = getContext();

        View contentView = View.inflate(context, R.layout.bottom_sheet_for_list_view, null);
        // assert dialog.getWindow() != null;
        //dialog.getWindow().setDimAmount(0f);
        //dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setContentView(contentView);

        CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams) ((View) contentView.getParent()).getLayoutParams();
        CoordinatorLayout.Behavior behavior = params.getBehavior();
        ((View) contentView.getParent()).setBackgroundColor(getResources().getColor(android.R.color.transparent));

        bottomSheetBehavior = (BottomSheetBehavior) behavior;
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);

//        if (behavior != null && behavior instanceof BottomSheetBehavior) {
//            bottomSheetBehavior = (BottomSheetBehavior) behavior;
//            bottomSheetBehavior.setBottomSheetCallback(mBottomSheetBehaviorCallback);
//        }


        mapping(contentView);
        if (arrayList != null && arrayList.size() > 0) {
            setData(arrayList);
            bottomSheetBehavior = (BottomSheetBehavior) behavior;
            bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
        } else {
            getList();
            bottomSheetBehavior = (BottomSheetBehavior) behavior;
            bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
        }
        listview.setAdapter(adapter);
        listview.setVisibility(View.VISIBLE);
    }

    private void getList() {
//        arrayList = new ArrayList<>();
//        arrayList = new DummyData(context).getStringList();
        setData(arrayList);
    }


    public void setData(ArrayList<CountryData> arrayList) {
        if (arrayList != null && arrayList.size() > 0) {
            if (adapter == null) {
                adapter = new Adapter_Bottom(context, arrayList);
                selected = arrayList.get(0);
            }
        }
        this.arrayList=arrayList;
    }


    private void mapping(View view) {

//        btn_done = view.findViewById(R.id.btn_done);
//        btn_cancel = view.findViewById(R.id.btn_cancel);

        listview = view.findViewById(R.id.list_view);
//        search = view.findViewById(R.id.searchET);
        if(isSort)
//           search.setVisibility(View.GONE);
        listview.setVisibility(View.VISIBLE);
        progressBar = view.findViewById(R.id.progressBar);
        layout_top = view.findViewById(R.id.layout_top);
        txt_error_message = view.findViewById(R.id.txt_error_message);

//        btn_done.setOnClickListener(this);
//        btn_cancel.setOnClickListener(this);
        layout_top.setOnTouchListener(this);

//        search.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable editable) {
//                adapter.getFilter().filter(editable.toString());
//            }
//        });
         listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                selected = adapter.searchResults.get(i);
                adapter.setSelected(selected);
                adapter.setPosition(i);
                adapter.notifyDataSetChanged();

                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
                if (responseListener != null && adapter != null) {
                    try{
                        responseListener.response(adapter.getPosition(),adapter.getSelected());
                     }catch (Exception e)
                    {

                    }
                }
            }
        });

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_cancel:
                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
                break;
//            case R.id.btn_done:
//                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
//                if (responseListener != null && adapter != null) {
//                    try{
//                        responseListener.response(adapter.getPosition(),adapter.getSelected());
//                     }catch (Exception e)
//                    {
//
//                    }
//                }
//                break;
        }
    }


    public void setResponseListener(ResponseListener responseListener) {
        this.responseListener = responseListener;
    }



    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            isTopScroll = true;
        } else if (event.getAction() == MotionEvent.ACTION_UP || event.getAction() == MotionEvent.ACTION_CANCEL) {
            isTopScroll = false;
        }
        return true;
    }



}

