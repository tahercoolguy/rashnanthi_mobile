package com.master.design.rashnanthi.Activity;

import static android.view.View.GONE;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.iid.FirebaseInstanceId;
import com.master.design.rashnanthi.Helper.DialogUtil;
import com.master.design.rashnanthi.Models.User;
import com.master.design.rashnanthi.R;
import com.master.design.rashnanthi.Utils.ConnectionDetector;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SplashScreen extends AppCompatActivity {
    User user;
    ConnectionDetector connectionDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        user = new User(SplashScreen.this);

//
//        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
//        Log.d("Android Token", "Refreshed token: " + refreshedToken);
//        try {
//            PackageInfo info = getPackageManager().getPackageInfo(
//                    getPackageName(),
//                    PackageManager.GET_SIGNATURES);
//            for (Signature signature : info.signatures) {
//                MessageDigest messageDigest = MessageDigest.getInstance("SHA");
//                messageDigest.update(signature.toByteArray());
//                Log.d("KeyHash:", Base64.encodeToString(messageDigest.digest(), Base64.DEFAULT));
//            }
//
////             info = getPackageManager().getPackageInfo(
////                    "44:64:0F:46:C1:E8:29:E9:C7:F3:FF:50:5C:EA:80:86:46:0B:67:4C",
////                    PackageManager.GET_SIGNATURES);
////            for (Signature signature : info.signatures) {
////                MessageDigest md = MessageDigest.getInstance("SHA");
////                md.update(signature.toByteArray());
////                Log.i("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
////                Log.println(1,"Base64", Base64.encodeToString(md.digest(),Base64.NO_WRAP));
////                Log.println(1,"Base64", Base64.encodeToString(md.digest(),Base64.NO_WRAP));
////                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
////                Log.e("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
////                Log.v("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
////                Log.w("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
////
////            }
//        } catch (PackageManager.NameNotFoundException e) {
//            Log.d("Exception NameNotFound", "hurray");
//        } catch (NoSuchAlgorithmException e) {
//            Log.d("Exception NoSuction", "hurray");
//
//        }
//        int secondsDelayed = 2;
//        new Handler().postDelayed(new Runnable() {
//            public void run() {
//                String Id=user.getCountryId();
////                if(Id.equalsIgnoreCase("0"))
////                {
////                     startActivity(new Intent(SplashScreen.this,MainActivity.class));
////                     finish();
////                }else {
////                    startActivity(new Intent(SplashScreen.this,MainActivity.class));
////                    finish();
////                }
////                startActivity(new Intent(SplashScreen.this, LoginActivity.class));
////                finish();
//
//
//                if(user.getId() == 0){
//
//                    startActivity(new Intent(SplashScreen.this,MainActivity.class));
//
//                }
//                else{
//                    startActivity(new Intent(SplashScreen.this,MainActivity.class));
//                }
//                finish();
//
//            }
//        }, secondsDelayed * 1000);
//
//
//    }
//
//
//}
        setSystemUIFlags(GONE);
        user = new User(SplashScreen.this);
        view = (VideoView) findViewById(R.id.video_view);
        String path = "android.resource://" + getPackageName() + "/" + R.raw.splash_screen;
        view.setVideoURI(Uri.parse(path));
        view.start();
        connectionDetector = new ConnectionDetector(SplashScreen.this);
//        if (connectionDetector.isConnectingToInternet()) {
//
//        } else {
////            Toast.makeText(this, getString(R.string.no_internet_connection), Toast.LENGTH_SHORT).show();
//            DialogUtil.showDialogSingleButton(getApplicationContext(), R.drawable.splash_screen_logo, getString(R.string.are_you_sure_you_want_to_exit_the_app), getString(R.string.no_internet_connection), getString(R.string.ok), new DialogUtil.CallBack() {
//                @Override
//                public void onDismiss(boolean isPressedOK) {
//
//                }
//            });
//
//        }
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.d("Android Token", "Refreshed token: " + refreshedToken);
        try {
            PackageInfo info = getPackageManager().getPackageInfo(
                    getPackageName(),
                    PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest messageDigest = MessageDigest.getInstance("SHA");
                messageDigest.update(signature.toByteArray());
                Log.d("KeyHash:", Base64.encodeToString(messageDigest.digest(), Base64.DEFAULT));
            }

//             info = getPackageManager().getPackageInfo(
//                    "44:64:0F:46:C1:E8:29:E9:C7:F3:FF:50:5C:EA:80:86:46:0B:67:4C",
//                    PackageManager.GET_SIGNATURES);
//            for (Signature signature : info.signatures) {
//                MessageDigest md = MessageDigest.getInstance("SHA");
//                md.update(signature.toByteArray());
//                Log.i("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
//                Log.println(1,"Base64", Base64.encodeToString(md.digest(),Base64.NO_WRAP));
//                Log.println(1,"Base64", Base64.encodeToString(md.digest(),Base64.NO_WRAP));
//                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
//                Log.e("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
//                Log.v("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
//                Log.w("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
//
//            }
        } catch (PackageManager.NameNotFoundException e) {
            Log.d("Exception NameNotFound", "hurray");
        } catch (NoSuchAlgorithmException e) {
            Log.d("Exception NoSuction", "hurray");

        }

        view.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                String Id = String.valueOf(user.getId());
                if (Id.equalsIgnoreCase("0")) {
                    startActivity(new Intent(SplashScreen.this, MainActivity.class));
                    finish();
                } else {
                    startActivity(new Intent(SplashScreen.this, MainActivity.class));
                    finish();
                }
            }
        });
//        int secondsDelayed = 13;
//        new Handler().postDelayed(new Runnable() {
//            public void run() {
//                String Id=user.getCountryId();
//                if(Id.equalsIgnoreCase("0"))
//                {
//                    startActivity(new Intent(SplashScreen.this, AddressSelector.class));
//                    finish();
//                }else {
//                    startActivity(new Intent(SplashScreen.this, AdvertiseSelector.class));
//                    finish();
//                }
//            }
//        }, secondsDelayed * 1000);
    }

    private void setSystemUIFlags(int visibility) {
        // here we get ourCurrentActivity
        // from it, we grab the window
        // and from that we grab the decorView for our current activity
        View decorView = getWindow().getDecorView();
        // think of the decorView as the eldest ancestor of the UI class familly tree.
        // it contains not only your views, but all current system views
        // eg system alerts and *navigation elements*
        // it contains a method for manipulating some of those system views
        decorView.setSystemUiVisibility(visibility);
    }

    @Override
    protected void onPause() {
        super.onPause();
        view.pause();
    }

    VideoView view;

    @Override
    protected void onResume() {
        super.onResume();

//        String path = "android.resource://" + getPackageName() + "/" + R.raw.video_splash;
//        view.setVideoURI(Uri.parse(path));
        String path = "android.resource://" + getPackageName() + "/" + R.raw.splash_screen;
        view.setVideoURI(Uri.parse(path));
        view.start();
    }
}