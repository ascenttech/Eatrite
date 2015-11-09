package com.healthyfood.eatrite.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.healthyfood.eatrite.R;
import com.healthyfood.eatrite.adapters.AllDishesRecyclerAdapter;
import com.healthyfood.eatrite.async.FetchAllDishesAsyncTask;
import com.healthyfood.eatrite.custom.CustomButton;
import com.healthyfood.eatrite.utils.Constants;

/**
 * Created by ADMIN on 23-10-2015.
 */
public class AllDishesActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter adapter;

    private Toolbar toolbar;
    private ProgressDialog progressDialog;

    private int pos;
    private String position;
    private CustomButton placeOrder;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_dishes);

        Log.d(Constants.LOG_TAG,Constants.ALL_DISHES_ACTIVITY);

        getExtras();
        findViews();
        customActionbar();
        settingTheAdapter();
        fetchData();
    }

    public void getExtras(){


        Intent i = getIntent();
        pos = i.getIntExtra("position",0);
        position = String.valueOf(pos+1);
    }

    public void findViews(){

        toolbar = (Toolbar) findViewById(R.id.toolbar_all_dishes_activity);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view_all_dishes_activity);
        placeOrder = (CustomButton) findViewById(R.id.place_order_button_all_dishes_activity);


    }

    public void customActionbar(){

        setSupportActionBar(toolbar);

    }

    public void settingTheAdapter(){

        recyclerView.hasFixedSize();

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);


    }

    public void fetchData(){

        String url = Constants.fetchAllDishes;

        new FetchAllDishesAsyncTask(this,new FetchAllDishesAsyncTask.FetchAllDishesCallback() {
            @Override
            public void onStart(boolean status) {

                progressDialog = new ProgressDialog(AllDishesActivity.this);
                progressDialog.setTitle(Constants.APP_NAME);
                progressDialog.setMessage("Loading...Please wait");
                progressDialog.show();


            }
            @Override
            public void onResult(boolean result) {

                progressDialog.dismiss();
                if(result){

                    adapter = new AllDishesRecyclerAdapter(getApplicationContext(),Constants.allDishesData);
                    recyclerView.setAdapter(adapter);
                    setViews();
                }

            }
        }).execute(url,position);

    }

    public void setViews(){

        placeOrder.setText("CHECK OUT");
        placeOrder.setOnClickListener(listener);
    }

    public void placeOrder(){

        if(Constants.order.size() >0) {
                Intent i = new Intent(this, OrderSummaryActivity.class);
                startActivity(i);

        }
        else{

            Toast.makeText(this," The cart is empty ",3000).show();
        }
    }

    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            placeOrder();

        }
    };
}
