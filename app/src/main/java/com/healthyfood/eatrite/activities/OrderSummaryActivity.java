package com.healthyfood.eatrite.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.healthyfood.eatrite.R;
import com.healthyfood.eatrite.custom.CustomButton;
import com.healthyfood.eatrite.custom.CustomTextView;
import com.healthyfood.eatrite.utils.Constants;
//chages made by saeedjassani on line 99 and 100, AddressActivity called and Toast changed accordingly
/**
 * Created by ADMIN on 06-11-2015.
 */
public class OrderSummaryActivity extends AppCompatActivity {

    private LinearLayout orderSummary;
    private CustomButton placeOrder;
    private Toolbar toolbar;
    CustomTextView dish,quantity,price;
    ImageView add,subtract;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_summary);

        Log.d(Constants.LOG_TAG,Constants.YOUR_ITEMS_ACTIVITY);

        getExtras();
        findViews();
        setViews();
        populateLayout();

    }

    public void getExtras(){


    }

    public void findViews(){

        orderSummary = (LinearLayout) findViewById(R.id.order_summary_layout_order_summary_activity);
        placeOrder = (CustomButton) findViewById(R.id.place_order_button_order_summary_activity);
        toolbar = (Toolbar) findViewById(R.id.toolbar_order_summary_activity);

    }
    public void setViews(){

        toolbar.setTitle(Constants.APP_NAME);
        placeOrder.setOnClickListener(listener);

    }

    public void populateLayout(){

        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);

        for (int i =0;i<Constants.order.size();i++){

            View v = inflater.inflate(R.layout.row_your_items,null);
            dish = (CustomTextView) v.findViewById(R.id.dish_text_your_items);
            add = (ImageView)v.findViewById(R.id.add_image_your_items);
            subtract = (ImageView)v.findViewById(R.id.subtract_image_your_items);
            quantity = (CustomTextView) v.findViewById(R.id.quantity_text_your_items);
            price = (CustomTextView) v.findViewById(R.id.total_text_your_items);
            orderSummary.addView(v);

            View line = new View(getApplicationContext());
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT,2);
            line.setBackgroundColor(getResources().getColor(R.color.black));
            orderSummary.addView(line);

        }
    }

    public void placeOrder(){

        SharedPreferences sharedPreferences = getSharedPreferences(Constants.APP_NAME, MODE_PRIVATE);
        String userId = sharedPreferences.getString("user_id", "null");
        if (userId.equalsIgnoreCase("null")) {

            Intent i = new Intent(this, LoginOrRegisterActivity.class);
            startActivity(i);
        } else {

            Toast.makeText(getApplicationContext()," Select order ",5000).show();
            Intent i = new Intent(this,AddressActivity.class);
            startActivity(i);
        }
    }

    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            switch (v.getId()){

                case R.id.place_order_button_order_summary_activity:placeOrder();
                    break;

            }

        }
    };

}
