package com.master.design.rashnanthi.Activity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.master.design.rashnanthi.Adapter.Adapter_Country_Code_Only_Spinner;
import com.master.design.rashnanthi.Adapter.Adapter_Country_Name_Spinner;
import com.master.design.rashnanthi.Controller.AppController;
import com.master.design.rashnanthi.DataModel.Country_CodeDM;
import com.master.design.rashnanthi.DataModel.Country_NameDM;
import com.master.design.rashnanthi.Helper.DialogUtil;
import com.master.design.rashnanthi.Helper.User;
import com.master.design.rashnanthi.R;
import com.master.design.rashnanthi.Utils.ConnectionDetector;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Add_new_post_1 extends AppCompatActivity {


    Dialog progress;
    ConnectionDetector connectionDetector;
    User user;
    DialogUtil dialogUtil;
    AppController appController;

    @NotEmpty
    @BindView(R.id.add_new_post_pay_back)
    ImageView Back;


    @OnClick(R.id.add_new_post_pay_back)
    public void Back() {
        finish();
    }


    RadioButton term_conditionRB;
    Button pay_now_Btn, continue_Btn;
    EditText post_for_free_nowET, add_more_eventET;
    TextView you_will_be_uploaded_withon_24_hours;
    private ArrayList<Country_NameDM> countryNameDMS;
    private ArrayList<Country_CodeDM> country_codeDMS;
    Spinner Mobile_code_Sp, wtsap_code_Sp, country_nname_Sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_post_1);

        ButterKnife.bind(this);

        dialogUtil = new DialogUtil();
        appController = (AppController) getApplicationContext();
        connectionDetector = new ConnectionDetector(getApplicationContext());
        user = new User(Add_new_post_1.this);


        pay_now_Btn = findViewById(R.id.pay_now_Btn);


        Mobile_code_Sp = findViewById(R.id.Mobile_code_Sp);
        wtsap_code_Sp = findViewById(R.id.wtsap_code_Sp);


        country_nname_Sp = findViewById(R.id.country_nname_Sp);

        ArrayList<Country_NameDM> countryNameDMS;


        countryNameDMS = new ArrayList<>();
        countryNameDMS.add(new Country_NameDM("Kuwait"));
        countryNameDMS.add(new Country_NameDM("Oman"));
        countryNameDMS.add(new Country_NameDM("Saudi Arabia"));
        countryNameDMS.add(new Country_NameDM("Qatar"));
        countryNameDMS.add(new Country_NameDM("Bahrain"));


        Adapter_Country_Name_Spinner adapter_country_name_spinner;

        adapter_country_name_spinner = new Adapter_Country_Name_Spinner(this, countryNameDMS);


        country_nname_Sp.setAdapter(adapter_country_name_spinner);


        ArrayList<Country_CodeDM> country_codeDMS;
        country_codeDMS = new ArrayList<>();
        country_codeDMS.add(new Country_CodeDM("+965"));
        country_codeDMS.add(new Country_CodeDM("+968"));
        country_codeDMS.add(new Country_CodeDM("+966"));
        country_codeDMS.add(new Country_CodeDM("+974"));
        country_codeDMS.add(new Country_CodeDM("+973"));

        Adapter_Country_Code_Only_Spinner adapter_country_code_only_spinner;

        adapter_country_code_only_spinner = new Adapter_Country_Code_Only_Spinner(this, country_codeDMS);


        Mobile_code_Sp.setAdapter(adapter_country_code_only_spinner);
        wtsap_code_Sp.setAdapter(adapter_country_code_only_spinner);


        pay_now_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Add_new_post_1.this, Add_Event_Pay_Now.class));
                finish();
            }
        });


    }


}
