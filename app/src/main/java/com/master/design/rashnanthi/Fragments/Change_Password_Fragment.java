package com.master.design.rashnanthi.Fragments;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.iid.FirebaseInstanceId;
import com.master.design.rashnanthi.Activity.Activity_Add_Event_1;
import com.master.design.rashnanthi.Activity.MainActivity;
import com.master.design.rashnanthi.Activity.SignUpActivity;
import com.master.design.rashnanthi.Controller.AppController;
import com.master.design.rashnanthi.DataModel.ChangePasswordRootDM;
import com.master.design.rashnanthi.Helper.DialogUtil;
import com.master.design.rashnanthi.Helper.User;
import com.master.design.rashnanthi.R;
import com.master.design.rashnanthi.Utils.ConnectionDetector;
import com.master.design.rashnanthi.Utils.Helper;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import it.sephiroth.android.library.widget.HListView;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class Change_Password_Fragment extends Fragment {
    private HListView lst_latest_profiles, lst_latest_news, lst_featured_video;
    AppController appController;
    ConnectionDetector connectionDetector;
    ProgressDialog progressDialog;
    User user;
    DialogUtil dialogUtil;
    Dialog progress;

    private View rootView;
    private Context context;
    ImageView my_accountImg;
    RelativeLayout add_new_event_RL, view_event_RL, change_password_RL, edit_profile_RL;

    @BindView(R.id.progress_bar)
    ProgressBar progress_bar;

    @BindView(R.id.txt_error)
    TextView txt_error;

    @BindView(R.id.layout_parent)
    LinearLayout layout_parent;

    @BindView(R.id.old_password_ET)
    EditText old_password_ET;

    @BindView(R.id.new_password_ET)
    EditText new_password_ET;

    @BindView(R.id.confirm_password_ET)
    EditText confirm_password_ET;

    @BindView(R.id.change_password_Btn)
    Button change_password_Btn;

    @BindView(R.id.backImg)
    ImageView backImg;


    @OnClick(R.id.backImg)
    public void Back() {
        ((MainActivity) context).addFragment(new My_Account_Fragment(), false);

    }

    @OnClick(R.id.change_password_Btn)
    public void ChangePasswordBtn() {

        if (connectionDetector.isConnectingToInternet()) {

//            progress = dialogUtil.showProgressDialog(context,getString(R.string.please_wait));

            String refreshedToken = FirebaseInstanceId.getInstance().getToken();

            boolean correct = true;
            if (old_password_ET.getText().toString().equalsIgnoreCase("")) {
                correct = false;
                Helper.showToast(getActivity(), getString(R.string.enter_old_password));
            }   else if (new_password_ET.getText().toString().equalsIgnoreCase("")) {
                correct = false;
                Helper.showToast(getActivity(), getString(R.string.enter_new_password));
            }else if (confirm_password_ET.getText().toString().equalsIgnoreCase("")) {
                correct = false;
                Helper.showToast(getActivity(), getString(R.string.enter_confirm_password));
            } else if (correct) {

                appController.paServices.ChangePassword(String.valueOf(user.getId()), old_password_ET.getText().toString(), new_password_ET.getText().toString(), confirm_password_ET.getText().toString(), new Callback<ChangePasswordRootDM>() {
                    @Override

                    public void success(ChangePasswordRootDM changePasswordRootDM, Response response) {
//                    progress.dismiss();
                        if (changePasswordRootDM.getOutput().getSuccess().equalsIgnoreCase("1")) {
                            Helper.showToast(getActivity(), getString(R.string.password_successfully_changed));

                        } else {
                            Helper.showToast(getActivity(), getString(R.string.something_wrong));

                        }
                    }

                    @Override
                    public void failure(RetrofitError retrofitError) {
//                    progress.dismiss();
                        Log.e("error", retrofitError.toString());

                    }
                });
            }
        } else
            Helper.showToast(context, getString(R.string.no_internet_connection));
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if (rootView == null) {
            rootView = inflater.inflate(R.layout.change_password_fragment_layout, container, false);
            ButterKnife.bind(this, rootView);
            context = getActivity();
            appController = (AppController) getActivity().getApplicationContext();
            user = new User(getActivity());

            connectionDetector = new ConnectionDetector(getActivity());
//            progressDialog = new ProgressDialog(getActivity());
//            progressDialog.setMessage(getResources().getString(R.string.please_wait));
//            progressDialog.setIndeterminate(true);
//            progressDialog.setCancelable(false);
            ((MainActivity) context).setTitle(getString(R.string.change_password));

            idMapping();

            setClickListeners();
            setDetails();


        }
        return rootView;


    }


    private void idMapping() {


    }

    private void setClickListeners() {

    }

    @Override
    public void onResume() {
        super.onResume();
    }

    private void setDetails() {
        ShowProgress();
        rootView.postDelayed(new Runnable() {
            @Override
            public void run() {
                DismissProgress();
            }
        }, 1500);
    }

    public void ShowProgress() {
        progress_bar.setVisibility(View.VISIBLE);
        txt_error.setVisibility(View.GONE);
        layout_parent.setVisibility(View.GONE);
    }

    public void DismissProgress() {
        progress_bar.setVisibility(View.GONE);
        txt_error.setVisibility(View.GONE);
        layout_parent.setVisibility(View.VISIBLE);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        menu.findItem(R.id.action_back).setVisible(false);
    }

}

