package com.healthyfood.eatrite.async;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.healthyfood.eatrite.data.AllLocationData;
import com.healthyfood.eatrite.data.BasicNameValuePair;
import com.healthyfood.eatrite.utils.Constants;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by ADMIN on 26-10-2015.
 */
public class FetchAllLocationsAsyncTask extends AsyncTask<String,Void,Boolean> {

    Context context;
    FetchAllLocationsCallback callback;
    InputStream inputStream;

    URL url;
    HttpURLConnection urlConnection;
    String response;

    public interface FetchAllLocationsCallback{

        public void onStart(boolean status);
        public void onResult(boolean result);
    }

    public FetchAllLocationsAsyncTask(Context context, FetchAllLocationsCallback callback) {
        this.context = context;
        this.callback = callback;
        if(Constants.allLocationData != null){

            Constants.allLocationData.clear();
        }
        else{
            Constants.allLocationData = new ArrayList<AllLocationData>();
        }

    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        callback.onStart(true);
    }

    @Override
    protected Boolean doInBackground(String... params) {


        Log.d(Constants.LOG_TAG,Constants.FETCH_ALL_LOCATIONS_ASYNC_TASK);
        Log.d(Constants.LOG_TAG," The url to be fetched "+params[0]);


        try {
            url = new URL(params[0]);
            urlConnection = (HttpURLConnection) url.openConnection();

//                 /* optional request header */
//            urlConnection.setRequestProperty("Content-Type", "application/json");
//
//                /* optional request header */
//            urlConnection.setRequestProperty("Accept", "application/json");

                /* for Get request */
            urlConnection.setRequestMethod("GET");
            int statusCode = urlConnection.getResponseCode();

                /* 200 represents HTTP OK */
            if (statusCode == 200) {
                inputStream = new BufferedInputStream(urlConnection.getInputStream());
                response = convertInputStreamToString(inputStream);
                Log.d(Constants.LOG_TAG, " The response is " + response);

                JSONObject jsonObject = new JSONObject(response);
                JSONArray jsonArray = jsonObject.getJSONArray("Listing");
                for(int i=0;i<jsonArray.length();i++){

                 JSONObject nestedJsonObject = jsonArray.getJSONObject(i);
                 String locationId = nestedJsonObject.getString("location_id");
                 String locationLine1 = nestedJsonObject.getString("location_line1");
                 String locationLine2 = nestedJsonObject.getString("location_line2");
                 String landmark = nestedJsonObject.getString("landmark");
                 String city = nestedJsonObject.getString("city");
                 String state = nestedJsonObject.getString("state");
                 String country = nestedJsonObject.getString("country");
                 String pincode = nestedJsonObject.getString("pincode");

                 Constants.allLocationData.add(new AllLocationData(locationId,locationLine1,locationLine2,landmark,city,state,country,pincode));

                }
                return true;
            }
            else {
                return false;
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            try {
                if(inputStream != null){

                    inputStream.close();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return false;

    }


    public String convertInputStreamToString(InputStream is) throws IOException {

        String line="";
        String result="";
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is));

        while((line = bufferedReader.readLine()) != null){
            result += line;
        }

            /* Close Stream */
        if(null!=inputStream){
            inputStream.close();
        }

        return result;
    }

    @Override
    protected void onPostExecute(Boolean result) {
        super.onPostExecute(result);
        Log.d(Constants.LOG_TAG," The value returned is "+result);
        callback.onResult(result);
    }
}
