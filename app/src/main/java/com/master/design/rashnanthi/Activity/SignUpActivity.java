package com.master.design.rashnanthi.Activity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.master.design.rashnanthi.Controller.AppController;
import com.master.design.rashnanthi.Helper.DialogUtil;
import com.master.design.rashnanthi.Helper.User;
import com.master.design.rashnanthi.R;
import com.master.design.rashnanthi.Utils.ConnectionDetector;

public class SignUpActivity extends AppCompatActivity {
    AppController appController;
    private static final int IMAGE_PICKER_SELECT = 1;
    private static final int IMAGE_PICKER_SELECT1 = 2;
    private static final int FILE_PICKER_SELECT = 3;
    private static final int IMAGE_VIDEO_ACTIVITY_PICKER = 4;

    Dialog progress;
    ConnectionDetector connectionDetector;
    User user;
    DialogUtil dialogUtil;


    ImageView backlogin,profileImg,cameraImg;

    LinearLayout testing, testing1;
    TextView event, coach,nameET,emailET,passwordET,confirm_passwordET;
    Button register_NowBtn;
    EditText snap_id_ET,insta_id_ET;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        backlogin = findViewById(R.id.backlogin);
        testing = findViewById(R.id.testing);
        testing1 = findViewById(R.id.testing1);
        event = findViewById(R.id.eventTxt);
        coach = findViewById(R.id.coachTxt);
        register_NowBtn = findViewById(R.id.register_NowBtn);
        profileImg=findViewById(R.id.profileImg);
        cameraImg=findViewById(R.id.cameraImg);
        nameET=findViewById(R.id.nameET);
        emailET=findViewById(R.id.emailET);
        passwordET=findViewById(R.id.passwordET);
        confirm_passwordET=findViewById(R.id.confirm_passwordET);
        snap_id_ET=findViewById(R.id.snap_id_ET);
        insta_id_ET=findViewById(R.id.insta_id_ET);


        VisibilityFunction();

        backlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                Intent i = new Intent(getApplicationContext(),Menu_1_Fragment.class);
//                startActivity(i);
            }
        });


//        ButterKnife.bind(this);
        dialogUtil = new DialogUtil();
        appController = (AppController) getApplicationContext();
        connectionDetector = new ConnectionDetector(getApplicationContext());
        user = new User(SignUpActivity.this);

        coach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ifCoach = true;

                VisibilityFunction();
            }
        });

        event.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ifCoach = false;

                VisibilityFunction();
            }
        });

        register_NowBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SignUpActivity.this, VerifyActivity.class));
            }
        });

    }

    boolean ifCoach = false;

    public void VisibilityFunction() {
        if (ifCoach) {
            coach.setBackground(getDrawable(R.drawable.rounded_corner_black_border));
            event.setBackground(getDrawable(R.drawable.rounded_corner_white));

            testing.setVisibility(View.VISIBLE);
            testing1.setVisibility(View.VISIBLE);
        } else {
            event.setBackground(getDrawable(R.drawable.rounded_corner_black_border));
            coach.setBackground(getDrawable(R.drawable.rounded_corner_white));

            testing.setVisibility(View.GONE);
            testing1.setVisibility(View.GONE);
        }
    }
}






