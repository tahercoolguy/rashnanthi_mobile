package com.master.design.rashnanthi.Activity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.iid.FirebaseInstanceId;
import com.master.design.rashnanthi.Adapter.Adapter_Add_Event_Pay_Now;
import com.master.design.rashnanthi.Controller.AppController;
import com.master.design.rashnanthi.DataModel.SummaryForPaidEventRootDM;
import com.master.design.rashnanthi.Helper.DialogUtil;
import com.master.design.rashnanthi.Helper.User;
import com.master.design.rashnanthi.R;
import com.master.design.rashnanthi.Utils.ConnectionDetector;
import com.master.design.rashnanthi.Utils.Helper;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class Add_Event_Pay_Now extends AppCompatActivity {

    Dialog progress;
    ConnectionDetector connectionDetector;
    User user;
    DialogUtil dialogUtil;
    AppController appController;

    Context context;
    String eventid;

    String EventID;


    Button add_event_pay_now;

    TextView knet_circle, my_fatoorah_circle, visa_mastercard_circle;


    @NotEmpty
    @BindView(R.id.add_event_pay_back)
    ImageView Back;

    @NotEmpty
    @BindView(R.id.post_price_country_Txt)
    TextView post_price_country_Txt;

    @NotEmpty
    @BindView(R.id.post_price_kuwait_Txt)
    TextView post_price_kuwait_Txt;
  @NotEmpty
    @BindView(R.id.qatar_price_Txt)
    TextView qatar_price_Txt;

    @NotEmpty
    @BindView(R.id.kuwait_price__Txt)
    TextView kuwait_price__Txt;

    @NotEmpty
    @BindView(R.id.total_price_kwd)
    TextView total_price_kwd;




    @OnClick(R.id.add_event_pay_back)
    public void Back() {
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_event_pay_now);

        ButterKnife.bind(this);

        dialogUtil = new DialogUtil();
        appController = (AppController) getApplicationContext();
        connectionDetector = new ConnectionDetector(getApplicationContext());
        user = new User(Add_Event_Pay_Now.this);

        SummaryForPaidEvent();

        Intent intent = getIntent();

          eventid = intent.getStringExtra("eventid");


        add_event_pay_now = findViewById(R.id.add_event_pay_now);
        visa_mastercard_circle = findViewById(R.id.visa_mastercard_circle);
        knet_circle = findViewById(R.id.knet_circle);

        my_fatoorah_circle = findViewById(R.id.my_fatoorah_circle);

        visa_mastercard_circle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ifVisa_Mastercard = true;
                ifVisa_MastercardSelected();

            }
        });
        knet_circle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ifKnet = true;
                ifKnetSelected();

            }
        });

        my_fatoorah_circle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ifMY_Fatoora = true;
                ifMY_FatooraSelected();
            }
        });


        add_event_pay_now.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              }
        });


    }


    boolean ifKnet = false;

    public void ifKnetSelected() {
        knet_circle.setBackground(getDrawable(R.drawable.ic_payment_method_selected_circle));
        my_fatoorah_circle.setBackground(getDrawable(R.drawable.ic_unselected_circle));
        visa_mastercard_circle.setBackground(getDrawable(R.drawable.ic_unselected_circle));


    }

    boolean ifMY_Fatoora = false;

    public void ifMY_FatooraSelected() {
        knet_circle.setBackground(getDrawable(R.drawable.ic_unselected_circle));
        my_fatoorah_circle.setBackground(getDrawable(R.drawable.ic_payment_method_selected_circle));
        visa_mastercard_circle.setBackground(getDrawable(R.drawable.ic_unselected_circle));

    }

    boolean ifVisa_Mastercard = false;

    public void ifVisa_MastercardSelected() {

        knet_circle.setBackground(getDrawable(R.drawable.ic_unselected_circle));
        my_fatoorah_circle.setBackground(getDrawable(R.drawable.ic_unselected_circle));
        visa_mastercard_circle.setBackground(getDrawable(R.drawable.ic_payment_method_selected_circle));

    }


    public void SummaryForPaidEvent() {
        if (connectionDetector.isConnectingToInternet()) {
            String refreshedToken = FirebaseInstanceId.getInstance().getToken();

            progress = dialogUtil.showProgressDialog(Add_Event_Pay_Now.this, getString(R.string.please_wait));

            appController.paServices.SummaryForPaidEvent("16", new Callback<SummaryForPaidEventRootDM>() {

                @Override
                public void success(SummaryForPaidEventRootDM summaryForPaidEventRootDM, Response response) {
                    progress.dismiss();
                    if (summaryForPaidEventRootDM.getOutput().getSuccess().equalsIgnoreCase("1")) {
//                        Helper.shwToast(Add_Event_Pay_Now.this,customerRegisterDM.getMessage());
//                        user.setId(Integer.parseInt(addEventByCreatorRootDM.getOutput().getEventid()));

                       //                        rcv_paynow.setAdapter(occasionAdapter);

                        post_price_country_Txt.setText(summaryForPaidEventRootDM.getOutput().getData().getCountry());
                        post_price_kuwait_Txt.setText(summaryForPaidEventRootDM.getOutput().getData().getCountry());
                        qatar_price_Txt.setText(summaryForPaidEventRootDM.getOutput().getData().getCountryvalue());
                        kuwait_price__Txt.setText(summaryForPaidEventRootDM.getOutput().getData().getCountryvalue());
                        total_price_kwd.setText(summaryForPaidEventRootDM.getOutput().getData().getCountryvalue());

//                        startActivity(new Intent(Add_Event_Pay_Now.this,Add_Event_Bill.class));

                    } else
                        Helper.showToast(Add_Event_Pay_Now.this,"something wrong ");

                }

                @Override
                public void failure(RetrofitError retrofitError) {
                    progress.dismiss();
                    Log.e("error", retrofitError.toString());

                }
            });

        } else
            Helper.showToast(Add_Event_Pay_Now.this, getString(R.string.no_internet_connection));

    }


}
