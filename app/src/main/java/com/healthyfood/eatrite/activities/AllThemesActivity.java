package com.healthyfood.eatrite.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.GridView;

import com.healthyfood.eatrite.R;
import com.healthyfood.eatrite.adapters.AllThemesGridAdapter;
import com.healthyfood.eatrite.async.FetchAllThemesAsyncTask;
import com.healthyfood.eatrite.utils.Constants;

/**
 * Created by ADMIN on 31-10-2015.
 */
public class AllThemesActivity extends AppCompatActivity {

    private GridView themesGrid;
    private Toolbar toolbar;
    private ProgressDialog progressDialog;
    private int position;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_themes);



        getExtras();
        findViews();
        setViews();
        fetchAllThemes();


    }

    public void getExtras(){

        Intent i = getIntent();
        position = i.getIntExtra("position",0);

    }

    public void findViews(){

        themesGrid  = (GridView) findViewById(R.id.grid_all_themes_activity);
        toolbar = (Toolbar) findViewById(R.id.toolbar_all_themes_activity);

    }

    public void setViews(){

        setSupportActionBar(toolbar);

    }

    public void fetchAllThemes(){

        String url = Constants.fetchAllThemes;


        new FetchAllThemesAsyncTask(getApplicationContext(),new FetchAllThemesAsyncTask.FetchAllThemesCallback() {
            @Override
            public void onStart(boolean status) {

                progressDialog = new ProgressDialog(AllThemesActivity.this);
                progressDialog.setTitle(Constants.APP_NAME);
                progressDialog.setMessage("Getting Locations");
                progressDialog.show();

            }

            @Override
            public void onResult(boolean result) {

                progressDialog.dismiss();
                if(result){


                    settingTheAdapter();

                }
            }
        }).execute(url,Constants.allLocationData.get(position).getLocationId());

    }

    public void settingTheAdapter(){

        themesGrid.setAdapter(new AllThemesGridAdapter(this));

    }

}

