package com.healthyfood.eatrite.activities;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.healthyfood.eatrite.R;
import com.healthyfood.eatrite.async.LoginAsyncTask;
import com.healthyfood.eatrite.custom.CustomButton;
import com.healthyfood.eatrite.custom.CustomEditText;
import com.healthyfood.eatrite.utils.Constants;

import java.net.URLEncoder;

//chages made by saeedjassani on line 89 and 80, AddressActivity called and Toast changed accordingly
/**
 * Created by ADMIN on 05-11-2015.
 */
public class LoginActivity extends Activity {

    private CustomEditText emailId,password;
    private CustomButton login;
    private String tempEmail,tempPwd;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Log.d(Constants.LOG_TAG,Constants.LOGIN_ACTIVITY);

        findViews();
        setViews();

    }

    public void findViews(){

        emailId = (CustomEditText) findViewById(R.id.email_id_edit_login_activity);
        password = (CustomEditText) findViewById(R.id.password_edit_login_activity);
        login = (CustomButton) findViewById(R.id.login_button_login_activity);


    }

    public void setViews(){

        login.setOnClickListener(listener);

    }

    public void login(){

        String url = Constants.login;

        try {

            tempEmail = URLEncoder.encode(emailId.getText().toString(), "UTF-8");
            tempPwd = URLEncoder.encode(password.getText().toString(), "UTF-8");
        }
        catch(Exception e){
            e.printStackTrace();
        }
        new LoginAsyncTask(getApplicationContext(),new LoginAsyncTask.LoginAsyncCallback() {
            @Override
            public void onStart(boolean status) {

                progressDialog = new ProgressDialog(LoginActivity.this);
                progressDialog.setTitle(Constants.APP_NAME);
                progressDialog.setMessage("Please Wait...");
                progressDialog.show();
            }

            @Override
            public void onResult(boolean result) {

                progressDialog.dismiss();
                if(result){

                    SharedPreferences sharedPreferences = getSharedPreferences(Constants.APP_NAME,MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("user_id",Constants.userId);
                    editor.commit();
                    Toast.makeText(getApplicationContext()," Select the address ",5000).show();
                    Intent i = new Intent(LoginActivity.this,AddressActivity.class);
                    startActivity(i);

                }
                else{
                    Toast.makeText(getApplicationContext()," Invalid Login ",5000).show();
                }

            }
        }).execute(url,tempEmail,tempPwd);


    }

    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            switch (v.getId()){

                case R.id.login_button_login_activity: login();
                    break;

            }

        }
    };
}
