package com.healthyfood.eatrite.async;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.healthyfood.eatrite.R;
import com.healthyfood.eatrite.data.AllKitchenData;
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
public class FetchAllKitchensAsyncTask extends AsyncTask<String,Void,Boolean> {

    Context context;
    FetchAllKitchensCallback callback;
    InputStream inputStream;
    URL url;
    HttpURLConnection urlConnection;
    String response;
    OutputStream outputStream;
    BufferedWriter bufferedWriter;

    String names[] = {"SAI KRUPA","GURU KRUPA","SAI LEELA","LUCKY","CAFE NEEL"};
    int images[] = {R.drawable.kitchen1,R.drawable.kitchen2,R.drawable.kitchen3,R.drawable.kitchen4,R.drawable.kitchen5};

    public interface FetchAllKitchensCallback {

        public void onStart(boolean status);
        public void onResult(boolean result);
    }

    public FetchAllKitchensAsyncTask(Context context, FetchAllKitchensCallback callback) {
        this.context = context;
        this.callback = callback;
        if(Constants.allKitchenData !=null){
            Constants.allKitchenData.clear();
        }
        else{
            Constants.allKitchenData = new ArrayList<AllKitchenData>();
        }

    }

    @Override
    protected void onPreExecute() {

        super.onPreExecute();
        callback.onStart(true);
    }

    @Override
    protected Boolean doInBackground(String... params) {

        Log.d(Constants.LOG_TAG,Constants.FETCH_ALL_KITCHEN_ASYNC_TASK);
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

            urlConnection.setDoInput(true);
            urlConnection.setDoOutput(true);
            urlConnection.setRequestMethod("POST");


            List<BasicNameValuePair> basicNameValuePair = new ArrayList<BasicNameValuePair>();
            basicNameValuePair.add(new BasicNameValuePair("theme_id",params[1]));

            outputStream = urlConnection.getOutputStream();
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
//            StringBuilder ans = writeToOutputStream(basicNameValuePair);
//            bufferedWriter.write(ans.toString());
//            StringBuilder ans = writeToOutputStream(basicNameValuePair);
            bufferedWriter.write(writeToOutputStream(basicNameValuePair));
            bufferedWriter.flush();

            int statusCode = urlConnection.getResponseCode();

                /* 200 represents HTTP OK */
            if (statusCode == 200) {
                inputStream = new BufferedInputStream(urlConnection.getInputStream());
                response = convertInputStreamToString(inputStream);
                Log.d(Constants.LOG_TAG, " The response is " + response);

                JSONArray jsonArray = new JSONArray(response);
                for (int i=0;i<jsonArray.length();i++){

                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    String kitchenId = jsonObject.getString("kitchen_id");
                    String kitchenName = jsonObject.getString("kitchen_name");
                    String kitchenImage = jsonObject.getString("kitchen_image");

                    Constants.allKitchenData.add(new AllKitchenData(kitchenId,kitchenName,kitchenImage));
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


    public String writeToOutputStream(List<BasicNameValuePair> keyValuePair) throws UnsupportedEncodingException {

        String line;
        String result="";
        boolean firstTime = true;

        for(BasicNameValuePair pair : keyValuePair){


            if(firstTime){

                firstTime = false;
            }
            else{

                result = result.concat("&");

            }

            result = result + URLEncoder.encode(pair.getKey(), "UTF-8");
            result = result + "=";
            result =  result+ URLEncoder.encode(pair.getValue(),"UTF-8");

            Log.d(Constants.LOG_TAG," the result in loop is "+ result);

        }

        Log.d(Constants.LOG_TAG," The result is ");
        return result;

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
