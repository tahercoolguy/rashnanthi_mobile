package com.master.design.rashnanthi.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;

import com.master.design.rashnanthi.Adapter.Adapter_Spinner_Country;
import com.master.design.rashnanthi.Controller.AppController;
import com.master.design.rashnanthi.DataModel.CountryRootDM;
import com.master.design.rashnanthi.Helper.DialogUtil;
import com.master.design.rashnanthi.Helper.User;
import com.master.design.rashnanthi.R;
import com.master.design.rashnanthi.Utils.ConnectionDetector;
import com.master.design.rashnanthi.Utils.Helper;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class SpinneerActivity extends AppCompatActivity {

    Dialog progress;
    ConnectionDetector connectionDetector;
    Context context;
    User user;
    DialogUtil dialogUtil;
    AppController appController;


    @BindView(R.id.backLL)
    LinearLayout backLL; @BindView(R.id.back)
    LinearLayout back;

    @BindView(R.id.countryRcv)
    RecyclerView countryRcv;

    @OnClick(R.id.backLL)
    public void Back() {

//        ((MainActivity)context).addFragment(new Calender_Fragment(),false);
        finish();
    } @OnClick(R.id.back)
    public void BackK() {

//        ((MainActivity)context).addFragment(new Calender_Fragment(),false);
        finish();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spinneer);
        ButterKnife.bind(this);
        dialogUtil = new DialogUtil();
        appController = (AppController) getApplicationContext();
        connectionDetector = new ConnectionDetector(getApplicationContext());
        user = new User(SpinneerActivity.this);
        Binding();

    }

    public String countryID="1";
    public String countryName="Kuwait";
    public String countryImg ;

    public void FinishThis()
    {
        Intent intent=new Intent();
        intent.putExtra("countryid",countryID);
        intent.putExtra("countryname",countryName);
        intent.putExtra("countryimg",countryImg);

        setResult(89,intent);
        finish();
    }


    public void Binding() {
        if (connectionDetector.isConnectingToInternet()) {
            progress = dialogUtil.showProgressDialog(SpinneerActivity.this, getString(R.string.please_wait));

            appController.paServices.Countries(new Callback<CountryRootDM>() {
                @Override
                public void success(CountryRootDM countryRootDM, Response response) {
                    progress.dismiss();
                    if (countryRootDM.getOutput().getSuccess().equalsIgnoreCase("1")) {
                        context = getApplicationContext();
                        Adapter_Spinner_Country adapter_spinner_country = new Adapter_Spinner_Country(SpinneerActivity.this, countryRootDM.getOutput().getData());
                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context, RecyclerView.VERTICAL, false);
                        countryRcv.setLayoutManager(linearLayoutManager);
                        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_anim);
                        countryRcv.startAnimation(animation);
                        countryRcv.setAdapter(adapter_spinner_country);

                    } else
                        Helper.showToast(SpinneerActivity.this, "Some network happened ..");
                }

                @Override
                public void failure(RetrofitError error) {
                    progress.dismiss();
                    Log.e("String", error.toString());
                }
            });
        }
    }

}