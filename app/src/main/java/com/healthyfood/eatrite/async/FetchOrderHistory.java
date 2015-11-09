package com.healthyfood.eatrite.async;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.healthyfood.eatrite.data.AllOrdersData;
import com.healthyfood.eatrite.data.BasicNameValuePair;
import com.healthyfood.eatrite.utils.Constants;

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
 * Created by ADMIN on 05-11-2015.
 */
public class FetchOrderHistory extends AsyncTask<String,Void,Boolean> {

    private Context context;
    private FetchOrderCallback callback;
    private InputStream inputStream;
    private OutputStream outputStream;
    private BufferedWriter bufferedWriter;
    private HttpURLConnection urlConnection;


    public interface FetchOrderCallback{

        public void onStart(boolean status);
        public void onResult(boolean result);
    }

    public FetchOrderHistory(Context context, FetchOrderCallback callback) {
        this.context = context;
        this.callback = callback;
        if(Constants.allOrdersData !=null){
            Constants.allOrdersData.clear();
        }
        else{

            Constants.allOrdersData = new ArrayList<AllOrdersData>();
        }

    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        callback.onStart(true);
    }

    @Override
    protected Boolean doInBackground(String... params) {

        Log.d(Constants.LOG_TAG,Constants.FETCH_ORDER_HISTORY);
        Log.d(Constants.LOG_TAG," The url to be fetched is "+params[0]);
        try{

            URL url = new URL(params[0]);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setDoInput(true);
            urlConnection.setDoOutput(true);

            List<BasicNameValuePair> nameValuePair = new ArrayList<BasicNameValuePair>();
//            nameValuePair.add(new BasicNameValuePair("user_id",1));

            outputStream = urlConnection.getOutputStream();
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
            bufferedWriter.write(writeToOutputStream(nameValuePair));
            bufferedWriter.flush();

            int statusCode = urlConnection.getResponseCode();

            if(statusCode == 200){

                inputStream = urlConnection.getInputStream();
                String response = convertInputStreamToString(inputStream);




                return true;
            }
            return false;
        }
        catch(Exception e){

           e.printStackTrace();
        }
        finally {

            try {
                if (inputStream != null) {
                    inputStream.close();
                }
                if (outputStream != null) {
                    outputStream.close();
                }
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }

        return false;

    }

    private String convertInputStreamToString(InputStream inputStream) throws IOException {

        String line="";
        String result = "";

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

        while((line = bufferedReader.readLine()) != null){
            result += line;
        }

            /* Close Stream */
        if(null!=inputStream){
            inputStream.close();
        }

        return result;
    }

    private String writeToOutputStream(List<BasicNameValuePair> nameValuePair) throws UnsupportedEncodingException {

        String result="";
        boolean firstTime = false;

        for(BasicNameValuePair pair : nameValuePair) {

            if (firstTime == false) {

                firstTime = true;
            } else {

                result += "&";
            }

            result += URLEncoder.encode(pair.getKey(),"UTF-8");
            result += "=";
            result += URLEncoder.encode(pair.getValue(),"UTF-8");

        }

        Log.d(Constants.LOG_TAG," The result is ");
        return result;
    }

    @Override
    protected void onPostExecute(Boolean result) {
        super.onPostExecute(result);
        Log.d(Constants.LOG_TAG," The value returned is "+result);
        callback.onResult(result);
    }
}
