package com.healthyfood.eatrite.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.healthyfood.eatrite.R;
import com.healthyfood.eatrite.utils.Constants;

/**
 * Created by ADMIN on 23-10-2015.
 */
public class ProfileActivity extends AppCompatActivity {

    Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Log.d(Constants.LOG_TAG, Constants.PROFILE_ACTIVITY);

        findViews();
        customActionBar();
        setViews();

    }

    public void findViews() {

        toolbar = (Toolbar) findViewById(R.id.toolbar_profile_activity);

    }

    public void customActionBar() {

        setSupportActionBar(toolbar);
    }

    public void setViews(){


    }

}
