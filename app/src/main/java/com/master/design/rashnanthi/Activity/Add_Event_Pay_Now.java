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
import com.master.design.rashnanthi.DataModel.ConfirmEventRootDM;
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
import retrofit.mime.MultipartTypedOutput;
import retrofit.mime.TypedString;

public class Add_Event_Pay_Now extends AppCompatActivity {

    Dialog progress;
    ConnectionDetector connectionDetector;
    User user;
    DialogUtil dialogUtil;
    AppController appController;

    Context context;
    String eventid, invoiceid, paymentlink;


    TextView knet_circle, visa_mastercard_circle;


    @NotEmpty
    @BindView(R.id.add_event_pay_back)
    ImageView Back;

    @NotEmpty
    @BindView(R.id.post_price_country_Txt)
    TextView post_price_country_Txt;


    @NotEmpty
    @BindView(R.id.qatar_price_Txt)
    TextView qatar_price_Txt;


    @NotEmpty
    @BindView(R.id.total_price_kwd)
    TextView total_price_kwd;

    @NotEmpty
    @BindView(R.id.pay_now_Btn)
    Button pay_now_Btn;


    @OnClick(R.id.add_event_pay_back)
    public void Back() {
        finish();
    }


    @OnClick(R.id.pay_now_Btn)
    public void ConfirmEventPayNow() {

        ConfirmEventPayNowAPI();

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

        eventid = getIntent().getStringExtra("eventid");
        SummaryForPaidEvent();


        visa_mastercard_circle = findViewById(R.id.visa_mastercard_circle);
        knet_circle = findViewById(R.id.knet_circle);

//        my_fatoorah_circle = findViewById(R.id.my_fatoorah_circle);

        visa_mastercard_circle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ifVisa_Mastercard = true;
                ifKnet = false;
                ifVisa_MastercardSelected();

            }
        });
        knet_circle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ifKnet = true;
                ifVisa_Mastercard = false;
                ifKnetSelected();

            }
        });

//        my_fatoorah_circle.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                ifMY_Fatoora = true;
//                 ifMY_FatooraSelected();
//            }
//        });


    }


    boolean ifKnet = true;
    String knet, fatoora, visa;

    public void ifKnetSelected() {
        knet_circle.setBackground(getDrawable(R.drawable.ic_payment_method_selected_circle));
//        my_fatoorah_circle.setBackground(getDrawable(R.drawable.ic_unselected_circle));
        visa_mastercard_circle.setBackground(getDrawable(R.drawable.ic_unselected_circle));


    }

    boolean ifMY_Fatoora = false;

    public void ifMY_FatooraSelected() {
        knet_circle.setBackground(getDrawable(R.drawable.ic_unselected_circle));
//        my_fatoorah_circle.setBackground(getDrawable(R.drawable.ic_payment_method_selected_circle));
        visa_mastercard_circle.setBackground(getDrawable(R.drawable.ic_unselected_circle));

    }

    boolean ifVisa_Mastercard = false;

    public void ifVisa_MastercardSelected() {

        knet_circle.setBackground(getDrawable(R.drawable.ic_unselected_circle));
//        my_fatoorah_circle.setBackground(getDrawable(R.drawable.ic_unselected_circle));
        visa_mastercard_circle.setBackground(getDrawable(R.drawable.ic_payment_method_selected_circle));

    }


    public void SummaryForPaidEvent() {
        if (connectionDetector.isConnectingToInternet()) {
            String refreshedToken = FirebaseInstanceId.getInstance().getToken();
//            Intent intent = getIntent();

//            eventid = intent.getStringExtra("eventid");
            MultipartTypedOutput multipartTypedOutput = new MultipartTypedOutput();
            multipartTypedOutput.addPart("eventid", new TypedString(eventid));
            progress = dialogUtil.showProgressDialog(Add_Event_Pay_Now.this, getString(R.string.please_wait));

            appController.paServices.SummaryForPaidEvent(multipartTypedOutput, new Callback<SummaryForPaidEventRootDM>() {

                @Override
                public void success(SummaryForPaidEventRootDM summaryForPaidEventRootDM, Response response) {
                    progress.dismiss();
                    if (summaryForPaidEventRootDM.getOutput().getSuccess().equalsIgnoreCase("1")) {

                        post_price_country_Txt.setText(summaryForPaidEventRootDM.getOutput().getData().getCountry());
                        qatar_price_Txt.setText(summaryForPaidEventRootDM.getOutput().getData().getCountryvalue());
                        total_price_kwd.setText(summaryForPaidEventRootDM.getOutput().getData().getCountryvalue());


                    } else
                        Helper.showToast(Add_Event_Pay_Now.this, "something wrong ");

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

    public void ConfirmEventPayNowAPI() {
        if (connectionDetector.isConnectingToInternet()) {

            MultipartTypedOutput multipartTypedOutput = new MultipartTypedOutput();
            multipartTypedOutput.addPart("eventid", new TypedString(eventid));
            multipartTypedOutput.addPart("paymentmethod", new TypedString("1"));

            if (ifKnet != false) {
                multipartTypedOutput.addPart("paymentmethod", new TypedString("1"));

            }
            if (ifVisa_Mastercard != false) {
                multipartTypedOutput.addPart("paymentmethod", new TypedString("2"));

            }


            progress = dialogUtil.showProgressDialog(Add_Event_Pay_Now.this, getString(R.string.please_wait));

            appController.paServices.ConfirmEvent(multipartTypedOutput, new Callback<ConfirmEventRootDM>() {
                @Override
                public void success(ConfirmEventRootDM confirmEventRootDM, Response response) {
                    progress.dismiss();
                    if (confirmEventRootDM.getOutput().getSuccess().equalsIgnoreCase("1")) {

                        invoiceid = confirmEventRootDM.getOutput().getInvoiceId();
                        paymentlink = confirmEventRootDM.getOutput().getPaymentLink();


                        if (ifKnet != false) {
                            Intent intent = new Intent(Add_Event_Pay_Now.this, PayNowActivity.class);
                            intent.putExtra("invoiceid", invoiceid);
                            intent.putExtra("PaymentUrl", paymentlink);
                            startActivity(intent);
                        } else {
                            Helper.showToast(Add_Event_Pay_Now.this, "kindly select any one  payment method");
                        }

                        if (ifVisa_Mastercard != false) {
                            Intent intent = new Intent(Add_Event_Pay_Now.this, PayNowActivity.class);
                            intent.putExtra("invoiceid", invoiceid);
                            intent.putExtra("PaymentUrl", paymentlink);
                            startActivity(intent);
                        } else {
                            Helper.showToast(Add_Event_Pay_Now.this, "kindly select any one  payment method");
                        }

                    } else
                        Helper.showToast(Add_Event_Pay_Now.this, "Something Wrong");

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
