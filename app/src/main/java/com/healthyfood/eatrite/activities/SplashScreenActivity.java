package com.healthyfood.eatrite.activities;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.healthyfood.eatrite.R;
import com.healthyfood.eatrite.custom.CustomTextView;
import com.healthyfood.eatrite.utils.Constants;

import java.util.HashMap;

/**
 * Created by ADMIN on 23-10-2015.
 */
public class SplashScreenActivity extends Activity {

    private ImageView logo;
    private CustomTextView appName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        Log.d(Constants.LOG_TAG, Constants.SPLASH_SCREEN_ACTIVITY);

        checkAndroidVersion();
        initializeHashMaps();
        findViews();
        setViews();
        loadAnimation();


    }

    public void checkAndroidVersion(){

        SharedPreferences sharedPreferences = getSharedPreferences(Constants.APP_NAME,MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){

            editor.putString("materialDesign","true");
            editor.commit();
        }
        else{

            editor.putString("materialDesign","false");
            editor.commit();

        }

    }

    public void initializeHashMaps(){

        Constants.order = new HashMap<String,String>();

    }

    public void findViews(){

        logo = (ImageView) findViewById(R.id.logo_image_splash_screen_activity);
        appName = (CustomTextView) findViewById(R.id.app_name_text_splash_screen_activity);

    }

    public void setViews(){



    }

    public void loadAnimation(){

        Animation logoAnimation = AnimationUtils.loadAnimation(this, R.anim.zoom_out);
        logoAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

                Animation appNameAnimation = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.right_in);
                appNameAnimation.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {

                        appName.setVisibility(View.VISIBLE);
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {

                                Intent i = new Intent(SplashScreenActivity.this, LandingActivity.class);
                                startActivity(i);

                            }
                        },2000);

                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
                appName.startAnimation(appNameAnimation);

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        logo.startAnimation(logoAnimation);

    }


}
