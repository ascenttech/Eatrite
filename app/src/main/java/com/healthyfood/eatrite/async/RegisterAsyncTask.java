package com.healthyfood.eatrite.async;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.healthyfood.eatrite.data.BasicNameValuePair;
import com.healthyfood.eatrite.utils.Constants;

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
 * Created by ADMIN on 05-11-2015.
 */
public class RegisterAsyncTask extends AsyncTask<String,Void,Boolean> {

    Context context;
    RegisterAsyncCallback callback;
    InputStream inputStream;
    OutputStream outputStream;
    BufferedWriter bufferedWriter;
    URL url;
    HttpURLConnection httpURLConnection;
    String response;

    public interface RegisterAsyncCallback{

        public void onStart(boolean status);
        public void onResult(boolean result);
    }

    public RegisterAsyncTask(Context context, RegisterAsyncCallback callback) {
        this.context = context;
        this.callback = callback;
        Log.d(Constants.LOG_TAG,Constants.REGISTER_ASYNC_TASK);
    }

    @Override
    protected void onPreExecute(){
        super.onPreExecute();
        callback.onStart(true);

    }

    @Override
    protected Boolean doInBackground(String... params){

        Log.d(Constants.LOG_TAG,Constants.REGISTER_ASYNC_TASK);
        Log.d(Constants.LOG_TAG," The url to be fetched "+params[0]);

        try{

            url = new URL(params[0]);
            httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setDoInput(true);
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setRequestMethod("POST");

            List<BasicNameValuePair> nameValuePair = new ArrayList<BasicNameValuePair>();
            nameValuePair.add(new BasicNameValuePair("first_name",params[1]));
            nameValuePair.add(new BasicNameValuePair("last_name",params[2]));
            nameValuePair.add(new BasicNameValuePair("contact_number",params[3]));
            nameValuePair.add(new BasicNameValuePair("email",params[4]));
            nameValuePair.add(new BasicNameValuePair("password",params[5]));

            outputStream = httpURLConnection.getOutputStream();

            bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
            bufferedWriter.write(writeToOutputStream(nameValuePair));
            bufferedWriter.flush();

            int statusCode = httpURLConnection.getResponseCode();

                /* 200 represents HTTP OK */
            if (statusCode == 200) {
                inputStream = new BufferedInputStream(httpURLConnection.getInputStream());
                response = convertInputStreamToString(inputStream);
                Log.d(Constants.LOG_TAG, " The response is " + response);

                JSONObject jsonObject = new JSONObject(response);
                Constants.userId = jsonObject.getString("userId");


                return true;
            }
            else {
                return false;
            }



        }
        catch (Exception e){

            e.printStackTrace();
        }
        finally{

            try{

                if(inputStream != null){

                    inputStream.close();
                }
                if(outputStream != null){

                    outputStream.close();
                }

            }
            catch(Exception e){


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
