package com.healthyfood.eatrite.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.healthyfood.eatrite.R;
import com.healthyfood.eatrite.adapters.AllKitchenRecyclerAdapter;
import com.healthyfood.eatrite.async.FetchAllKitchensAsyncTask;
import com.healthyfood.eatrite.async.FetchAllThemesAsyncTask;
import com.healthyfood.eatrite.utils.Constants;

/**
 * Created by ADMIN on 23-10-2015.
 */
public class AllKitchenActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter adapter;

    private Toolbar toolbar;
    private ProgressDialog progressDialog;
    private String pos;
    private int position;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_kitchen);

        Log.d(Constants.LOG_TAG, Constants.ALL_KITCHEN_ACTIVITY);

        getExtras();
        findViews();
        customActionbar();
        settingTheAdapter();
        fetchAllKitchens();
    }

    public void getExtras(){

        Intent  i = getIntent();
        pos = i.getStringExtra("position");
        position = Integer.parseInt(pos)+1;

    }

    public void findViews(){

        toolbar = (Toolbar) findViewById(R.id.toolbar_all_kitchen_activity);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view_all_kitchen_activity);

    }

    public void customActionbar(){

        setSupportActionBar(toolbar);
    }

    public void settingTheAdapter(){

        recyclerView.hasFixedSize();

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);


    }

    public void fetchAllKitchens(){

        String url = Constants.fetchAllKitchens;

        new FetchAllKitchensAsyncTask(getApplicationContext(),new FetchAllKitchensAsyncTask.FetchAllKitchensCallback() {
            @Override
            public void onStart(boolean status) {

                progressDialog = new ProgressDialog(AllKitchenActivity.this);
                progressDialog.setTitle(Constants.APP_NAME);
                progressDialog.setMessage("Getting Locations");
                progressDialog.show();

            }

            @Override
            public void onResult(boolean result) {

                progressDialog.dismiss();
                adapter = new AllKitchenRecyclerAdapter(getApplicationContext(),Constants.allKitchenData);
                recyclerView.setAdapter(adapter);

            }
        }).execute(url,String.valueOf(position));


    }

}
