
package com.master.design.rashnanthi.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.master.design.rashnanthi.Fragments.Calender_Fragment;
import com.master.design.rashnanthi.Fragments.Coach_Account_Fragment;
import com.master.design.rashnanthi.Fragments.My_Account_Fragment;
import com.master.design.rashnanthi.Fragments.Social_Media_Fragment;
import com.master.design.rashnanthi.Helper.User;
import com.master.design.rashnanthi.R;

public class MainActivity extends AppCompatActivity {


    private User user;

    View calender;
    View searchh;
    View social_media;
    View notifi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        user = new User(this);
        addFragment(new Calender_Fragment(), false);

        calender = findViewById(R.id.calender);
        searchh = findViewById(R.id.searchh);
        social_media = findViewById(R.id.social_media);
        notifi = findViewById(R.id.notifi);

        boolean ifcoachselected = false;


            if (ifcoachselected) {
                Intent intent = getIntent();
                String op = intent.getStringExtra("something");
                if (op != null) {

                     getSupportFragmentManager().beginTransaction().replace(R.id.content_frame,
                            new My_Account_Fragment()).addToBackStack(null).commit();

                }


            } else {
                Intent intentt = getIntent();
                String opp = intentt.getStringExtra("chirag");
                if (opp != null) {

                     getSupportFragmentManager().beginTransaction().replace(R.id.content_frame,
                            new Coach_Account_Fragment()).addToBackStack(null).commit();

                }
            }





        calender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                ifCalender = true;
                addFragment(new Calender_Fragment(), false);
//                VisibilityFunction();

            }
        });

        searchh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                ifSearch = true;

//                addFragment(new Calender_Fragment(), false);
//                VisibilityFunction();

            }
        });

        social_media.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                ifSocial = true;
                addFragment(new Social_Media_Fragment(), false);
//                VisibilityFunction();

            }
        });

        notifi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                ifNotification = true;
////
////

            }
        });


    }



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
//
//    @Override
//    public void onBackPressed() {
//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        if (drawer.isDrawerOpen(GravityCompat.START)) {
//            drawer.closeDrawer(GravityCompat.START);
//        } else {
//            int backStackEntryCount = getSupportFragmentManager().getBackStackEntryCount();
//            Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.content_frame);
//            if (backStackEntryCount == 0) {
//                if (fragment != null && fragment instanceof Fragment_Default) {
//                    exitDialog();
//                } else {
//                    addFragment(new Fragment_Default(), false);
//                }
//            } else {
//                super.onBackPressed();
//            }
//        }
//    }
//
//    private void exitDialog() {
//        DialogUtil.showDialogTwoButton(this, R.drawable.app_icon, getString(R.string.app_name), getString(R.string.are_you_sure_you_want_to_exit_the_app), getString(R.string.ok), getString(R.string.cancel), new DialogUtil.CallBack() {
//            @Override
//            public void onDismiss(boolean isPressedOK) {
//                if (isPressedOK) {
//                    MainActivity.this.finish();
//                }
//            }
//        });
//    }


    public void addFragment(Fragment fragment, boolean addToStack) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.content_frame, fragment, fragment.getClass().getName());
        if (addToStack) {
            fragmentTransaction.addToBackStack(fragment.getClass().getName());
        }
        fragmentTransaction.commitAllowingStateLoss();
    }

//
//    @Override
//    protected void attachBaseContext(Context newBase) {
//        super.attachBaseContext(ContextWrapper.wrap(newBase, new Locale(new User(newBase).getLanguageCode())));
//    }
//
//    @Override
//    public boolean onCreateOptionsMenu(android.view.Menu menu) {
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()) {
//            case R.id.action_back:
//                onBackPressed();
//                return true;
//            default:
//                return super.onOptionsItemSelected(item);
//        }
//    }
//
//    public void setElevation(boolean isElevate) {
//        if (isElevate) {
//            ViewCompat.setElevation(findViewById(R.id.app_bar), getResources().getDimension(R.dimen.elevation));
//        } else {
//            ViewCompat.setElevation(findViewById(R.id.app_bar), 0f);
//        }
//    }
}
