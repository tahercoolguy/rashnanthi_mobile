package com.master.design.rashnanthi.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.master.design.rashnanthi.Helper.User;
import com.master.design.rashnanthi.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class AdvertiseSelector extends AppCompatActivity {

    User user;
    @OnClick(R.id.englishTxt)
    public void english()
    {
        if(user.getId() == 0){
            startActivity(new Intent(AdvertiseSelector.this,MainActivity.class));
        }
        else{
            startActivity(new Intent(AdvertiseSelector.this,MainActivity.class));
        }
        finish();
    }

    @OnClick(R.id.arabicTxt)
    public void arabic()
    {
        if(user.getId() == 0){
            startActivity(new Intent(AdvertiseSelector.this,MainActivity.class));
        }
        else{
            startActivity(new Intent(AdvertiseSelector.this,MainActivity.class));
        }
        finish();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advertise_selector);
        ButterKnife.bind(this);
        user = new User(AdvertiseSelector.this);

    }
}
