package com.master.design.rashnanthi.Activity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.master.design.rashnanthi.Controller.AppController;
import com.master.design.rashnanthi.Helper.DialogUtil;
import com.master.design.rashnanthi.Helper.User;
import com.master.design.rashnanthi.R;
import com.master.design.rashnanthi.Utils.ConnectionDetector;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Add_Event_Bill extends AppCompatActivity {



    AppController appController;
    Dialog progress;
    ConnectionDetector connectionDetector;
    User user;
    DialogUtil dialogUtil;
    ImageView bill;
    LinearLayout layout_parent;


    @NotEmpty
    @BindView(R.id.bill)
    ImageView Bill;

    @OnClick(R.id.bill)
    public void Bill() {

        finish();
     }

    @NotEmpty
    @BindView(R.id.layout_parent)
    LinearLayout Layout;

    @OnClick(R.id.layout_parent)
    public void Layout() {
         startActivity(new Intent(Add_Event_Bill.this,Thank_you.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event_bill);

        ButterKnife.bind(this);
        dialogUtil = new DialogUtil();
        appController = (AppController) getApplicationContext();
        connectionDetector = new ConnectionDetector(getApplicationContext());
        user = new User(Add_Event_Bill.this);
    }
}
