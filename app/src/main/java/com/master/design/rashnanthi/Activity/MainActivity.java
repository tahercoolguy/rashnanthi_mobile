
package com.master.design.rashnanthi.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.core.view.ViewCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.master.design.rashnanthi.Fragments.Calender_Fragment;
import com.master.design.rashnanthi.Fragments.Coach_Account_Fragment;
import com.master.design.rashnanthi.Fragments.Coach_Fragment;
import com.master.design.rashnanthi.Fragments.My_Account_Fragment;
import com.master.design.rashnanthi.Fragments.Notification_Fragment;
import com.master.design.rashnanthi.Fragments.Social_Media_Fragment;
import com.master.design.rashnanthi.Helper.ContextWrapper;
import com.master.design.rashnanthi.Helper.DialogUtil;
import com.master.design.rashnanthi.Helper.User;
import com.master.design.rashnanthi.R;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private static final int CAMERA_CAPTURE_IMAGE_REQUEST_CODE = 100;
    private static final int CAMERA_CAPTURE_VIDEO_REQUEST_CODE = 200;

    // key to store image path in savedInstance state
    public static final String KEY_IMAGE_STORAGE_PATH = "image_path";

    public static final int MEDIA_TYPE_IMAGE = 1;
    public static final int MEDIA_TYPE_VIDEO = 2;

    // Bitmap sampling size
    public static final int BITMAP_SAMPLE_SIZE = 8;

    // Gallery directory name to store the images or videos
    public static final String GALLERY_DIRECTORY_NAME = "Hello Camera";

    // Image and Video file extensions
    public static final String IMAGE_EXTENSION = "jpg";
    public static final String VIDEO_EXTENSION = "mp4";

    private User user;
    String coachcreator,eventcreator;


    TextView calenderTxt, coachTxt, social_mediaTxt, notificationTxt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        user = new User(this);

        eventcreator = getIntent().getStringExtra("chirag1");
        coachcreator = getIntent().getStringExtra("chirag2");

        addFragment(new Calender_Fragment(), true);

        calenderTxt = findViewById(R.id.calenderTxt);
        coachTxt = findViewById(R.id.coachTxt);
        social_mediaTxt = findViewById(R.id.social_mediaTxt);
        notificationTxt = findViewById(R.id.notificationTxt);

        boolean ifcoachselected = false;


        if (ifcoachselected) {
            Intent intent = getIntent();
            String op = intent.getStringExtra("something");
            if (op != null) {

                getSupportFragmentManager().beginTransaction().replace(R.id.content_frame,
                        new Coach_Account_Fragment()).addToBackStack(null).commit();

            }


        } else {
            Intent intentt = getIntent();
            String opp = intentt.getStringExtra("chirag");
            if (opp != null) {

                getSupportFragmentManager().beginTransaction().replace(R.id.content_frame,
                        new Coach_Account_Fragment()).addToBackStack(null).commit();

            }
        }


        calenderTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ifCalenderClicked = true;

                addFragment(new Calender_Fragment(), true);
                CalenderVisibilityFunction();

            }
        });

        coachTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ifSearchClicked = true;
                SearchVisibilityFunction();
               addFragment(new Coach_Fragment(), true);



            }
        });

        social_mediaTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ifSocialMedia = true;
                SocialVisibilityFunction();
                addFragment(new Social_Media_Fragment(), false);

            }
        });

        notificationTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ifNotification = true;
                NotificationVisibilityFunction();
                addFragment(new Notification_Fragment(), false);

//

            }
        });


    }

    boolean ifCalenderClicked = false;
    boolean ifSearchClicked = false;
    boolean ifSocialMedia = false;
    boolean ifNotification = false;

    public void CalenderVisibilityFunction() {

        if (ifCalenderClicked) {

            calenderTxt.setBackground(getDrawable(R.drawable.ic_calendar_regular_red));
            coachTxt.setBackground(getDrawable(R.drawable.search_black));
            social_mediaTxt.setBackground(getDrawable(R.drawable.social_media_black));
            notificationTxt.setBackground(getDrawable(R.drawable.notification_black));
            addFragment(new Calender_Fragment(), false);


        }

    }

    public void SearchVisibilityFunction() {

        if (ifSearchClicked) {

            calenderTxt.setBackground(getDrawable(R.drawable.ic_calendar_regular_black));
            coachTxt.setBackground(getDrawable(R.drawable.search_red));
            social_mediaTxt.setBackground(getDrawable(R.drawable.social_media_black));
            notificationTxt.setBackground(getDrawable(R.drawable.notification_black));
            addFragment(new Coach_Fragment(), true);


        }

    }

    public void SocialVisibilityFunction() {

        if (ifSocialMedia) {

            calenderTxt.setBackground(getDrawable(R.drawable.ic_calendar_regular_black));
            coachTxt.setBackground(getDrawable(R.drawable.search_black));
            social_mediaTxt.setBackground(getDrawable(R.drawable.social_media_red));
            notificationTxt.setBackground(getDrawable(R.drawable.notification_black));
            addFragment(new Social_Media_Fragment(), false);


        }

    }

    public void NotificationVisibilityFunction() {

        if (ifNotification) {

            calenderTxt.setBackground(getDrawable(R.drawable.ic_calendar_regular_black));
            coachTxt.setBackground(getDrawable(R.drawable.search_black));
            social_mediaTxt.setBackground(getDrawable(R.drawable.social_media_black));
            notificationTxt.setBackground(getDrawable(R.drawable.notification_red));
            addFragment(new Notification_Fragment(), false);


        }

    }


//
//
//    private class DrawerItemClickListener implements ListView.OnItemClickListener {
//        @Override
//        public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
//            Helper.hideSoftKeyboard(MainActivity.this);
//            DrawerMenu menu = (DrawerMenu) adapterView.getItemAtPosition(position);
//            Fragment fragment = null;
//            switch (menu.getId()) {
//                case RECORD:
////                    fragment = new HomeFragment();
//                    break;
//
//                default:
//                    break;
//            }
//            if (fragment != null) {
//                addFragment(fragment, false);
//            }
//            drawer.closeDrawer(GravityCompat.START);
//        }
//    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            int backStackEntryCount = getSupportFragmentManager().getBackStackEntryCount();
            Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.content_frame);
            if (backStackEntryCount == 0) {
                if (fragment != null && fragment instanceof Calender_Fragment) {
                    exitDialog();
                } else {
                    addFragment(new Calender_Fragment(), false);
                }
            } else {
                super.onBackPressed();
            }
        }
    }

    private void exitDialog() {
        DialogUtil.showDialogTwoButton(this, R.drawable.splash_screen_logo, getString(R.string.app_name), getString(R.string.are_you_sure_you_want_to_exit_the_app), getString(R.string.ok), getString(R.string.cancel), new DialogUtil.CallBack() {
            @Override
            public void onDismiss(boolean isPressedOK) {
                if (isPressedOK) {
                    MainActivity.this.finish();
                }
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void addFragment(Fragment fragment, boolean addToStack) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.content_frame, fragment, fragment.getClass().getName());
        if (addToStack) {
            fragmentTransaction.addToBackStack(fragment.getClass().getName());
        }
        fragmentTransaction.commitAllowingStateLoss();
    }


    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(ContextWrapper.wrap(newBase, new Locale(new User(newBase).getLanguageCode())));
    }

    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_back:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void setElevation(boolean isElevate) {
        if (isElevate) {
            ViewCompat.setElevation(findViewById(R.id.app_bar), getResources().getDimension(R.dimen.elevation));
        } else {
            ViewCompat.setElevation(findViewById(R.id.app_bar), 0f);
        }
    }
}
