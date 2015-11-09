package com.healthyfood.eatrite.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import com.healthyfood.eatrite.R;
import com.healthyfood.eatrite.custom.CustomButton;
import com.healthyfood.eatrite.utils.Constants;

/**
 * Created by ADMIN on 06-11-2015.
 */
public class AddressActivity extends AppCompatActivity {

    private CustomButton addNewAddress;
    private CustomButton saveAddress;
    private CustomButton selectAddress;
    private LinearLayout newAddressLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);

        Log.d(Constants.LOG_TAG,Constants.ADDRESS_ACTIVITY);

        getExtras();
        findViews();
        setViews();
    }

    public void getExtras(){


    }

    public void findViews(){
        addNewAddress = (CustomButton) findViewById(R.id.newAddress_button_address_activity);
        newAddressLayout = (LinearLayout) findViewById(R.id.newAddress_layout_address_activity);
        saveAddress = (CustomButton) findViewById(R.id.saveAddress_button_address_activity);
        selectAddress = (CustomButton) findViewById(R.id.selectAddress_button_address_activity);
    }

    public void setViews(){
        addNewAddress.setOnClickListener(listener);
        saveAddress.setOnClickListener(listener);
        selectAddress.setOnClickListener(listener);
    }

    View.OnClickListener listener = new View.OnClickListener(){

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.newAddress_button_address_activity:
                    newAddress();
                    break;
                case R.id.saveAddress_button_address_activity:
                    saveAddress();
                    break;
                case R.id.selectAddress_button_address_activity:
                    selectAddress();
                    break;
            }
        }
    };

    private void selectAddress() {
        Toast.makeText(this, "Order Placed", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, LandingActivity.class);
        startActivity(intent);
    }

    private void saveAddress() {
        Toast.makeText(this, "Address Saved and order placed", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, LandingActivity.class);
        startActivity(intent);
    }

    private void newAddress() {
        if (View.GONE == newAddressLayout.getVisibility())
            newAddressLayout.setVisibility(View.VISIBLE);
        else
            newAddressLayout.setVisibility(View.GONE);
        if (View.VISIBLE == newAddressLayout.getVisibility())
            selectAddress.setVisibility(View.GONE);
        else
            selectAddress.setVisibility(View.VISIBLE);

    }
}
