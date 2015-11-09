package com.healthyfood.eatrite.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.healthyfood.eatrite.R;
import com.healthyfood.eatrite.async.FetchAllLocationsAsyncTask;
import com.healthyfood.eatrite.async.FetchAllThemesAsyncTask;
import com.healthyfood.eatrite.utils.Constants;

import java.util.ArrayList;
import java.util.List;


public class LandingActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private Spinner allLocations;
    private Toolbar toolbar;
    private int backCounter;
    private ProgressDialog progressDialog;
    private List<String> locations;
    private String supportsMaterialDesign;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);

        Log.d(Constants.LOG_TAG, Constants.LANDING_ACTIVITY);

        enableMaterialDesign();
        findViews();
        customActionBar();
        fetchAllLocations();

    }

    public void enableMaterialDesign(){

        SharedPreferences sharedPreferences = getSharedPreferences(Constants.APP_NAME,MODE_PRIVATE);
        supportsMaterialDesign = sharedPreferences.getString("materialDesign","null");

    }


    public void findViews(){

        toolbar = (Toolbar)findViewById(R.id.toolbar_landing_activity);
        allLocations = (Spinner) findViewById(R.id.location_spinner_landing_activity);

    }

    public void customActionBar(){

        setSupportActionBar(toolbar);
    }

    public void fetchAllLocations(){

        String url = Constants.fetchAllLocations;

        new FetchAllLocationsAsyncTask(getApplicationContext(),new FetchAllLocationsAsyncTask.FetchAllLocationsCallback() {
            @Override
            public void onStart(boolean status) {

                progressDialog = new ProgressDialog(LandingActivity.this);
                progressDialog.setTitle(Constants.APP_NAME);
                progressDialog.setMessage("Getting Locations");
                progressDialog.show();
            }

            @Override
            public void onResult(boolean result) {

                progressDialog.dismiss();
                if(result){

                    setSpinner();
                    setViews();

                }
            }
        }).execute(url);

    }


    public void setViews(){

        allLocations.setOnItemSelectedListener(this);

    }

    public void setSpinner(){

        locations = new ArrayList<String>();
        for(int i =0;i<Constants.allLocationData.size();i++){

            String location = Constants.allLocationData.get(i).getLandmark()+", "+Constants.allLocationData.get(i).getCity();
            locations.add(location);

        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.layout_spinner,locations);

//        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.locations,R.layout.layout_spinner);
        adapter.setDropDownViewResource(R.layout.layout_spinner);
        allLocations.setAdapter(adapter);

    }

    @Override
    public void onBackPressed() {

        backCounter++;
        if(backCounter%2 == 0){

            finish();
        }
        else{
            Toast.makeText(this,"Press Back Again to exit",5000).show();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_landing, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId()){

            case R.id.action_settings:
                break;
            case R.id.action_about_us: Intent i = new Intent(LandingActivity.this,AboutUsActivity.class);
                    startActivity(i);
                break;

        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        if(position !=0){

            Intent i = new Intent(LandingActivity.this,AllThemesActivity.class);
            i.putExtra("position", position);
            startActivity(i);
        }




    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
