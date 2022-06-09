
package com.master.design.rashnanthi.Activity;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.ImageView;


import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.firebase.iid.FirebaseInstanceId;
import com.master.design.rashnanthi.Controller.AppController;
import com.master.design.rashnanthi.DataModel.AllStoryImage;
import com.master.design.rashnanthi.DataModel.CountryData;
import com.master.design.rashnanthi.DataModel.StoriesByDateData;
import com.master.design.rashnanthi.DataModel.StoriesByDateRootDM;
import com.master.design.rashnanthi.DataModel.StoriesByImageData;
import com.master.design.rashnanthi.Helper.DialogUtil;
import com.master.design.rashnanthi.Helper.User;
import com.master.design.rashnanthi.R;
import com.master.design.rashnanthi.Utils.ConnectionDetector;
import com.master.design.rashnanthi.Utils.Helper;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import jp.shts.android.storiesprogressview.StoriesProgressView;
import jp.wasabeef.picasso.transformations.BlurTransformation;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.mime.MultipartTypedOutput;
import retrofit.mime.TypedString;


public class Story_activity extends AppCompatActivity implements StoriesProgressView.StoriesListener {
    User user;
    AppController appController;
    ConnectionDetector connectionDetector;
    DialogUtil dialogUtil;
    Dialog progress;
    ArrayList<String> array_image = new ArrayList<>();
    ArrayList<String> array_image_count = new ArrayList<>();
    ArrayList<Integer> mL;
    String date, countryid;

//    @BindView(R.id.webView)
//    webView webView;


    // on below line we are creating a int array
    // in which we are storing all our image ids.
    private final int[] resources = new int[]{
            R.drawable.story_img_1,
            R.drawable.story_img_2,
            R.drawable.story_img_3,
            R.drawable.story_img_1,
            R.drawable.story_img_2,
            R.drawable.story_img_3
    };


    // on below line we are creating variable for
    // our press time and time limit to display a story.
    long pressTime = 0L;
    long limit = 500L;

    // on below line we are creating variables for
    // our progress bar view and image view .
    private StoriesProgressView storiesProgressView;
    ImageView image, story_back_btn, backgrd;

    // on below line we are creating a counter
    // for keeping count of our stories.
    private int counter = 0;

    // on below line we are creating a new method for adding touch listener
    private View.OnTouchListener onTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            // inside on touch method we are
            // getting action on below line.
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:

                    // on action down when we press our screen
                    // the story will pause for specific time.
                    pressTime = System.currentTimeMillis();

                    // on below line we are pausing our indicator.
                    storiesProgressView.pause();
                    return false;
                case MotionEvent.ACTION_UP:

                    // in action up case when user do not touches
                    // screen this method will skip to next image.
                    long now = System.currentTimeMillis();

                    // on below line we are resuming our progress bar for status.
                    storiesProgressView.resume();

                    // on below line we are returning if the limit < now - presstime
                    return limit < now - pressTime;
            }
            return false;
        }
    };

    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.story_activity);
        user = new User(this);
        ButterKnife.bind(Story_activity.this);
        webView = (WebView) findViewById(R.id.webView);
        appController = (AppController) getApplicationContext();
        connectionDetector = new ConnectionDetector(Story_activity.this);
        dialogUtil = new DialogUtil();
        mL = new ArrayList<Integer>();

        story_back_btn = findViewById(R.id.story_back_btn);
        backgrd =(ImageView) findViewById(R.id.bckgrd);


        story_back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // inside in create method below line is use to make a full screen.
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.story_activity);

        // on below line we are initializing our variables.
        storiesProgressView = (StoriesProgressView) findViewById(R.id.stories);


        if (getIntent() != null)
            storiesByDateAPI(getIntent().getStringExtra("date"), getIntent().getStringExtra("countryid"));
    }

    @Override
    public void onNext() {
        // this method is called when we move
        // to next progress view of story.

        if ((counter + 1) < 0) return;

        counter = counter + 1;
        if (array_image.get(counter) != null) {
           String new1= array_image.get(counter);
            if (array_image.get(counter).contains(".mp4")) {
                webView.setVisibility(View.VISIBLE);
                image.setVisibility(View.GONE);
//                webView.setVideoURI(Uri.parse(AppController.base_image_url + array_image.get(counter))); //the string of the URL mentioned above
//                webView.requestFocus();
//                webView.start();
                webView.loadUrl(AppController.base_image_url + array_image.get(counter));

            } else {
                webView.setVisibility(View.GONE);
                image.setVisibility(View.VISIBLE);
                Picasso.get().load(AppController.base_image_url + array_image.get(counter)).into(image);
                Picasso.get()
                        .load(AppController.base_image_url + array_image.get(counter))
                        .transform(new BlurTransformation(Story_activity.this, 25, 1))
                        .into(backgrd);
            }
        }


//        image.setImageResource(resources[++counter]);
    }

    @Override
    public void onPrev() {

        // this method id called when we move to previous story.
        // on below line we are decreasing our counter
        if ((counter - 1) < 0) return;
        // on below line we are setting image to image view
//        image.setImageResource(resources[--counter]);
        counter = counter - 1;

        if (array_image.get(counter) != null)
            if (array_image.get(counter).contains(".mp4")) {
                webView.setVisibility(View.VISIBLE);
                image.setVisibility(View.GONE);
//                webView.setVideoURI(Uri.parse("http://kuwaitgate.com/rasnanthi/uploads/eventimages/rasnanthi10111654689005.mp4")); //the string of the URL mentioned above
//                webView.requestFocus();
//                webView.start();
                webView.loadUrl(AppController.base_image_url + array_image.get(counter));

            } else {
                webView.setVisibility(View.GONE);
                image.setVisibility(View.VISIBLE);
                Picasso.get().load(AppController.base_image_url + array_image.get(counter)).into(image);
                Picasso.get()
                        .load(AppController.base_image_url + array_image.get(counter))
                        .transform(new BlurTransformation(Story_activity.this, 25, 1))
                        .into(backgrd);
            }


    }

    @Override
    public void onComplete() {
        // when the stories are completed this method is called.
        // in this method we are moving back to initial main activity.
//        Intent i = new Intent(Story_activity.this, MainActivity.class);
//        startActivity(i);
        finish();
    }

    @Override
    protected void onDestroy() {
        // in on destroy method we are destroying
        // our stories progress view.
        storiesProgressView.destroy();
        super.onDestroy();
    }


    public void storiesByDateAPI(String date, String countryid) {

        if (connectionDetector.isConnectingToInternet()) {

            progress = dialogUtil.showProgressDialog(Story_activity.this, getString(R.string.please_wait));

            MultipartTypedOutput multipartTypedOutput = new MultipartTypedOutput();
            multipartTypedOutput.addPart("date", new TypedString(date));
            multipartTypedOutput.addPart("countryid", new TypedString(countryid));

            appController.paServices.StoriesByDate(multipartTypedOutput, new Callback<StoriesByDateRootDM>() {
                @Override

                public void success(StoriesByDateRootDM storiesByDateRootDM, Response response) {
                    progress.dismiss();
                    if (storiesByDateRootDM.getOutput().getSuccess().equalsIgnoreCase("1")) {

                        for (AllStoryImage story : storiesByDateRootDM.getOutput().getAllimagedata()
                        ) {
                            array_image.add(story.getImage());
                        }


//                        array_image_count.add(String.valueOf(storiesByDateRootDM.getOutput().getData().get(0).getImagedata().size()));

                        // on below line we are setting the total count for our stories.
                        storiesProgressView.setStoriesCount(array_image.size());

                        // on below line we are setting story duration for each story.
                        storiesProgressView.setStoryDuration(3000L);

                        // on below line we are calling a method for set
                        // on story listener and passing context to it.
                        storiesProgressView.setStoriesListener(Story_activity.this);

                        // below line is use to start stories progress bar.
                        storiesProgressView.startStories(counter);

                        // initializing our image view.
                        image = (ImageView) findViewById(R.id.image);
                        backgrd = (ImageView) findViewById(R.id.bckgrd);
                        // on below line we are setting image to our image view.
//                        image.setImageResource(resources[counter]);

                        if (array_image.get(0).contains(".mp4")) {
                            webView.setVisibility(View.VISIBLE);
                            image.setVisibility(View.GONE);
//                            webView.setVideoURI(Uri.parse(AppController.base_image_url + array_image.get(counter))); //the string of the URL mentioned above
//                            webView.requestFocus();
//                            webView.start();

                            webView.loadUrl(AppController.base_image_url + array_image.get(counter));
                        } else {
                            webView.setVisibility(View.GONE);
                            image.setVisibility(View.VISIBLE);
                            Picasso.get().load(AppController.base_image_url + array_image.get(0)).into(image);
                            Picasso.get()
                                    .load(AppController.base_image_url + array_image.get(0))
                                    .transform(new BlurTransformation(Story_activity.this, 25, 1))
                                    .into(backgrd);
                        }
                        // below is the view for going to the previous story.
                        // initializing our previous view.
                        View reverse = findViewById(R.id.reverse);

                        // adding on click listener for our reverse view.
                        reverse.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                // inside on click we are
                                // reversing our progress view.
                                storiesProgressView.reverse();
                            }
                        });

                        // on below line we are calling a set on touch
                        // listener method to move towards previous image.
                        reverse.setOnTouchListener(onTouchListener);

                        // on below line we are initializing
                        // view to skip a specific story.
                        View skip = findViewById(R.id.skip);
                        skip.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                // inside on click we are
                                // skipping the story progress view.
                                storiesProgressView.skip();
                            }
                        });
                        // on below line we are calling a set on touch
                        // listener method to move to next story.
                        skip.setOnTouchListener(onTouchListener);

//                        array_image.add(storiesByDateRootDM.getOutput().getData().get(0).getImagedata().get(0).getStoryimage());
//                        mL.add(Integer.valueOf(storiesByDateRootDM.getOutput().getData().get(0).getImagedata().get(0).getStoryimage()));

                    } else
                        Helper.showToast(Story_activity.this, "no stories present");
                }

                @Override
                public void failure(RetrofitError retrofitError) {
                    progress.dismiss();
                    Log.e("error", retrofitError.toString());

                }
            });

        } else
            Helper.showToast(Story_activity.this, getString(R.string.no_internet_connection));


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
