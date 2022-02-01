package com.master.design.rashnanthi.Activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.master.design.rashnanthi.R;

public class Activity_Add_Event_1 extends AppCompatActivity {

    LinearLayout ad_more_eventtLL, website_LL;
    Button add_more_eventBtn;
    TextView your_post_will_beTXt;
    RadioButton radioBtn_Term_condition;
    Button pay_now_Btn, continue_Btn, post_for_free_nowBtn;

    RelativeLayout add_img_video_1_RL, add_img_video_2_RL, add_img_video_3_RL, add_img_video_4_RL;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event_1);

        ad_more_eventtLL = findViewById(R.id.ad_more_eventtLL);

        your_post_will_beTXt = findViewById(R.id.your_post_will_beTXt);

        website_LL = findViewById(R.id.website_LL);

        add_more_eventBtn = findViewById(R.id.add_more_eventBtn);

        radioBtn_Term_condition = findViewById(R.id.radioBtn_Term_condition);

        pay_now_Btn = findViewById(R.id.pay_now_Btn);

        continue_Btn = findViewById(R.id.continue_Btn);

        post_for_free_nowBtn = findViewById(R.id.post_for_free_nowBtn);

        add_img_video_3_RL = findViewById(R.id.add_img_video_3_RL);

        add_img_video_1_RL = findViewById(R.id.add_img_video_1_RL);

        add_img_video_2_RL = findViewById(R.id.add_img_video_2_RL);

        add_img_video_4_RL = findViewById(R.id.add_img_video_4_RL);

        add_img_video_1_RL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//                intent.setType("image/* video/*");

                Intent pickIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                pickIntent.setType("image/* video/*");
                startActivityForResult(pickIntent, 3);
            }

        });


        add_img_video_2_RL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                intent.setType("image/* video/*");
            }
        });

        add_img_video_3_RL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                intent.setType("image/* video/*");
            }
        });

        add_img_video_4_RL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                intent.setType("image/* video/*");
            }
        });
        add_more_eventBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ifAddMoreEventClicked1 = true;
                NotificationVisibilityFunction1();
            }
        });

        ad_more_eventtLL.setVisibility(View.GONE);
        your_post_will_beTXt.setVisibility(View.GONE);
        continue_Btn.setVisibility(View.GONE);
        NotificationVisibilityFunction1();
    }


    boolean ifAddMoreEventClicked1 = false;

    public void NotificationVisibilityFunction1() {

        if (ifAddMoreEventClicked1) {

            ad_more_eventtLL.setVisibility(View.VISIBLE);
            add_more_eventBtn.setVisibility(View.VISIBLE);
            your_post_will_beTXt.setVisibility(View.VISIBLE);
            radioBtn_Term_condition.setVisibility(View.VISIBLE);
            continue_Btn.setVisibility(View.VISIBLE);
            post_for_free_nowBtn.setVisibility(View.GONE);
            pay_now_Btn.setVisibility(View.GONE);
            website_LL.setVisibility(View.GONE);

        }


    }

}
