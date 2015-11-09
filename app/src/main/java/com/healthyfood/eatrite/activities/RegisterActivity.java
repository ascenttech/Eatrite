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
import com.healthyfood.eatrite.async.RegisterAsyncTask;
import com.healthyfood.eatrite.custom.CustomButton;
import com.healthyfood.eatrite.custom.CustomEditText;
import com.healthyfood.eatrite.utils.Constants;

import java.net.URLEncoder;
//chages made by saeedjassani on line 99 and 102, AddressActivity called and Toast changed accordingly
/**
 * Created by ADMIN on 05-11-2015.
 */
public class RegisterActivity extends Activity {

    private CustomEditText firstName,lastName,phoneNumber,emailId,password;
    private String tempFirstName,tempLastName,tempPhoneNumber,tempEmailId,tempPassword;
    private CustomButton register;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Log.d(Constants.LOG_TAG,Constants.REGISTER_ACTIVITY);

        findViews();
        setViews();

    }

    public void findViews(){

        firstName = (CustomEditText) findViewById(R.id.first_name_edit_register_activity);
        lastName = (CustomEditText) findViewById(R.id.last_name_edit_register_activity);
        phoneNumber = (CustomEditText) findViewById(R.id.phone_number_edit_register_activity);
        emailId = (CustomEditText) findViewById(R.id.email_id_edit_register_activity);
        password = (CustomEditText) findViewById(R.id.password_edit_register_activity);
        register = (CustomButton) findViewById(R.id.register_button_register_activity);


    }

    public void setViews(){

        register.setOnClickListener(listener);

    }

    public void register(){

        try{

            tempFirstName = URLEncoder.encode(firstName.getText().toString(),"UTF-8");
            tempLastName = URLEncoder.encode(lastName.getText().toString(),"UTF-8");
            tempPhoneNumber = URLEncoder.encode(phoneNumber.getText().toString(),"UTF-8");
            tempEmailId = URLEncoder.encode(emailId.getText().toString(),"UTF-8");
            tempPassword = URLEncoder.encode(password.getText().toString(),"UTF-8");


        }
        catch(Exception e){

            e.printStackTrace();
        }

        String url = Constants.register;
        new RegisterAsyncTask(getApplicationContext(),new RegisterAsyncTask.RegisterAsyncCallback() {
            @Override
            public void onStart(boolean status) {

                progressDialog = new ProgressDialog(RegisterActivity.this);
                progressDialog.setTitle(Constants.APP_NAME);
                progressDialog.setMessage("Loading...Please Wait");
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

                    Toast.makeText(getApplicationContext()," Select Address ",5000).show();
//                    Intent i = new Intent(RegisterActivity.this,AddressActivity.class);

                    Intent i = new Intent(RegisterActivity.this,AddressActivity.class);
                    startActivity(i);
                }
                else{

                    Toast.makeText(getApplicationContext(),"Couldn't register",5000).show();
                }
            }
        }).execute(url,tempFirstName,tempLastName,tempPhoneNumber,tempEmailId,tempPassword);



    }

    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            switch (v.getId()){


                case R.id.register_button_register_activity: register();
                    break;

            }

        }
    };
}
