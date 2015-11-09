package com.healthyfood.eatrite.async;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.healthyfood.eatrite.R;
import com.healthyfood.eatrite.data.AllKitchenData;
import com.healthyfood.eatrite.data.AllThemesData;
import com.healthyfood.eatrite.data.BasicNameValuePair;
import com.healthyfood.eatrite.utils.Constants;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ADMIN on 23-10-2015.
 */
public class FetchAllThemesAsyncTask extends AsyncTask<String,Void,Boolean> {

    Context context;
    FetchAllThemesCallback callback;

    URL url;
    HttpURLConnection urlConnection;
    String response;
    InputStream inputStream;
    OutputStream outputStream;

    public interface FetchAllThemesCallback {

        public void onStart(boolean status);
        public void onResult(boolean result);
    }

    public FetchAllThemesAsyncTask(Context context, FetchAllThemesCallback callback) {
        this.context = context;
        this.callback = callback;
        if(Constants.allThemesData != null){
            Constants.allThemesData.clear();
        }
        else{
            Constants.allThemesData = new ArrayList<AllThemesData>();
        }
    }

    @Override
    protected void onPreExecute() {

        super.onPreExecute();
        callback.onStart(true);
    }

    @Override
    protected Boolean doInBackground(String... params) {

        Log.d(Constants.LOG_TAG,Constants.FETCH_ALL_THEMES_ASYNC_TASK);
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
//            urlConnection.setChunkedStreamingMode(0);
            urlConnection.setDoOutput(true);
            urlConnection.setDoInput(true);
            urlConnection.setRequestMethod("POST");

            List<BasicNameValuePair> nameValuePairs = new ArrayList<BasicNameValuePair>();
            nameValuePairs.add(new BasicNameValuePair("location_id",params[1]));

            outputStream = urlConnection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));

            String ans = writeToOutputStream(nameValuePairs).toString();
            bufferedWriter.write(ans);
            bufferedWriter.flush();


            int statusCode = urlConnection.getResponseCode();

                /* 200 represents HTTP OK */
            if (statusCode == 200) {
                inputStream = new BufferedInputStream(urlConnection.getInputStream());
                response = convertInputStreamToString(inputStream);

                Log.d(Constants.LOG_TAG, " The response is " + response);

                JSONArray jsonArray = new JSONArray(response);
                for(int i=0;i<jsonArray.length();i++){

                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    String themeId = jsonObject.getString("theme_id");
                    String themeName = jsonObject.getString("theme_name");
                    String themeImage = jsonObject.getString("theme_image");

                    Constants.allThemesData.add(new AllThemesData(themeId,themeName,themeImage));

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
                if(outputStream != null){
                    outputStream.close();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return false;

    }

//    public String writeToOutputStream(List<BasicNameValuePair> keyValuePair) throws UnsupportedEncodingException{
    public StringBuilder writeToOutputStream(List<BasicNameValuePair> keyValuePair) throws UnsupportedEncodingException{

//        String result="";
        StringBuilder result=new StringBuilder();
        boolean firstTime = true;

        for(BasicNameValuePair pair : keyValuePair){


            if(firstTime){

                firstTime = false;
            }
            else{

//                result = result.concat("&");
                result.append("&");

            }

            result.append(URLEncoder.encode(pair.getKey(), "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(pair.getValue(),"UTF-8"));

//            result = result + URLEncoder.encode(pair.getKey(), "UTF-8");
//            result = result + "=";
//            result =  result+ URLEncoder.encode(pair.getValue(),"UTF-8");

        }

        Log.d(Constants.LOG_TAG," The result is "+result);
        return result;

    }



    public String convertInputStreamToString(InputStream is) throws IOException {

        String line="";
        String result="";
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is));

        while((line = bufferedReader.readLine()) != null){

            Log.d(Constants.LOG_TAG," The line value is "+line);
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
