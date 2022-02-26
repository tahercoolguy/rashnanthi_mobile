package com.master.design.rashnanthi.Activity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.master.design.rashnanthi.Adapter.Adapter_Country_Code_Only_Spinner;
import com.master.design.rashnanthi.Adapter.Adapter_Country_Name_Spinner;
import com.master.design.rashnanthi.Adapter.Adapter_Country_Spinner;
import com.master.design.rashnanthi.Controller.AppController;
import com.master.design.rashnanthi.DataModel.Country_CodeDM;
import com.master.design.rashnanthi.DataModel.Country_NameDM;
import com.master.design.rashnanthi.DataModel.County_ItemDM;
import com.master.design.rashnanthi.Fragments.Coach_Account_Fragment;
import com.master.design.rashnanthi.Fragments.Menu_1_Fragment;
import com.master.design.rashnanthi.Fragments.My_Event_1_Fragment;
import com.master.design.rashnanthi.Helper.DialogUtil;
import com.master.design.rashnanthi.Helper.User;
import com.master.design.rashnanthi.R;
import com.master.design.rashnanthi.Utils.ConnectionDetector;

import java.util.ArrayList;

public class SignUpActivity extends AppCompatActivity {
    AppController appController;
    Context context;
    private static final int IMAGE_PICKER_SELECT = 1;
    private static final int IMAGE_PICKER_SELECT1 = 2;
    private static final int FILE_PICKER_SELECT = 3;
    private static final int IMAGE_VIDEO_ACTIVITY_PICKER = 4;

    Dialog progress;
    ConnectionDetector connectionDetector;
    User user;
    DialogUtil dialogUtil;
    private ArrayList<Country_NameDM> countryNameDMS;
    private ArrayList<Country_CodeDM> country_codeDMS;

     Spinner country_name_spinner,mobile_countrycode_Sp,wtsap_countrycode_Sp;



    ImageView profileImg,cameraImg,back_from_register_page;

//    ImageView back_from_register_page;

    LinearLayout testing, testing1;
    TextView event, coach,nameET,emailET,passwordET,confirm_passwordET;
    Button register_NowBtn;
    EditText snap_id_ET,insta_id_ET;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        country_name_spinner=findViewById(R.id.country_name_spinner);

        back_from_register_page=findViewById(R.id.back_from_register_page);

        back_from_register_page.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();

                }
        });





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

        mobile_countrycode_Sp=findViewById(R.id.mobile_countrycode_Sp);
        wtsap_countrycode_Sp=findViewById(R.id.wtsap_countrycode_Sp);

        ArrayList<Country_CodeDM> country_codeDMS;
        country_codeDMS = new ArrayList<>();
        country_codeDMS.add(new Country_CodeDM("+965"));
        country_codeDMS.add(new Country_CodeDM("+968"));
        country_codeDMS.add(new Country_CodeDM("+966"));
        country_codeDMS.add(new Country_CodeDM("+974"));
        country_codeDMS.add(new Country_CodeDM("+973"));

        Adapter_Country_Code_Only_Spinner adapter_country_code_only_spinner;

        adapter_country_code_only_spinner = new Adapter_Country_Code_Only_Spinner( this,country_codeDMS);


        mobile_countrycode_Sp.setAdapter(adapter_country_code_only_spinner);
        wtsap_countrycode_Sp.setAdapter(adapter_country_code_only_spinner);





        ArrayList<Country_NameDM> countryNameDMS;


        countryNameDMS = new ArrayList<>();
        countryNameDMS.add(new Country_NameDM("Kuwait"));
        countryNameDMS.add(new Country_NameDM("Oman"));
        countryNameDMS.add(new Country_NameDM("Saudi Arabia"));
        countryNameDMS.add(new Country_NameDM("Qatar"));
        countryNameDMS.add(new Country_NameDM("Bahrain"));


        Adapter_Country_Name_Spinner adapter_country_name_spinner;

        adapter_country_name_spinner = new Adapter_Country_Name_Spinner( this,countryNameDMS);


        country_name_spinner.setAdapter(adapter_country_name_spinner);


        country_name_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        VisibilityFunction();



//                Intent i = new Intent(getApplicationContext(),Menu_1_Fragment.class);
//
//        ButterKnife.bind(this);
        dialogUtil = new DialogUtil();
        appController = (AppController) getApplicationContext();
        connectionDetector = new ConnectionDetector(getApplicationContext());
        user = new User(SignUpActivity.this);


 //        progressDialog = new ProgressDialog(getActivity());
//        progressDialog.setMessage(getResources().getString(R.string.please_wait));
//        progressDialog.setIndeterminate(true);
//        progressDialog.setCancelable(false);

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






