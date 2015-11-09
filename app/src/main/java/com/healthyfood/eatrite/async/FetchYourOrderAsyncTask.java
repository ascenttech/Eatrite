package com.healthyfood.eatrite.async;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

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

/**
 * Created by ADMIN on 23-10-2015.
 */
public class FetchYourOrderAsyncTask extends AsyncTask<String,Void,Boolean> {

    Context context;
    FetchYourOrderCallback callback;
    URL url;
    HttpURLConnection urlConnection;
    InputStream inputStream;
    String response;

    public interface FetchYourOrderCallback {

        public void onStart(boolean status);
        public void onResult(boolean result);
    }

    public FetchYourOrderAsyncTask(Context context, FetchYourOrderCallback callback) {
        this.context = context;
        this.callback = callback;
    }

    @Override
    protected void onPreExecute() {

        super.onPreExecute();
        callback.onStart(true);
    }

    @Override
    protected Boolean doInBackground(String... params) {

        Log.d(Constants.LOG_TAG,Constants.FETCH_YOUR_ORDER_ASYNC_TASK);
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



                return true;
            }
            else {
                return false;
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return false;

    }

    public String convertInputStreamToString(InputStream is) throws IOException{

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
