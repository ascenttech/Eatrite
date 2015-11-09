package com.healthyfood.eatrite.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.healthyfood.eatrite.R;
import com.healthyfood.eatrite.custom.CustomButton;
import com.healthyfood.eatrite.utils.Constants;

/**
 * Created by ADMIN on 05-11-2015.
 */
public class LoginOrRegisterActivity extends Activity {

    CustomButton login,register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_or_register);

        Log.d(Constants.LOG_TAG,Constants.LOGIN_OR_REGISTER_ACTIVITY);

        findViews();
        setViews();
    }

    private void findViews() {

        login = (CustomButton) findViewById(R.id.login_button_login_or_register_activity);
        register = (CustomButton) findViewById(R.id.register_button_login_or_register_activity);

    }


    private void setViews() {

        login.setOnClickListener(listener);
        register.setOnClickListener(listener);

    }

    public void login(){

        Intent i = new Intent(this,LoginActivity.class);
        startActivity(i);

    }

    public void register(){

        Intent i = new Intent(this,RegisterActivity.class);
        startActivity(i);

    }

    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            switch (v.getId()){


                case R.id.login_button_login_or_register_activity:login();
                    break;
                case R.id.register_button_login_or_register_activity: register();
                    break;

            }

        }
    };


}
