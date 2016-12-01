package com.nough.gouda.goudanough;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;
import com.nough.gouda.goudanough.CurrentRestaurants;
import com.nough.gouda.goudanough.beans.Restaurant;
import com.nough.gouda.goudanough.fragments.Navigation;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by 1432581 on 11/30/2016.
 */

public class DownloadWebPageText extends AsyncTask<String, Void, Restaurant[]> {
    private double longitude;
    private double latitude;
    private Context context;
    private OnTaskCompleted taskCompleted;
    private final String TAG = "Async Task";
    private Restaurant[] restaurants;



    public DownloadWebPageText(double longitude, double latitude, Context c){
        super();
        this.longitude = longitude;
        this.latitude = latitude;
        this.context = c;
        this.restaurants = new Restaurant[0];

    }
    public DownloadWebPageText(){
        super();
    }
    public DownloadWebPageText(OnTaskCompleted taskCompleted){
        super();
        this.taskCompleted = taskCompleted;
        this.restaurants = new Restaurant[0];
    }

    protected void onPostExecute(Restaurant[] rs) {
        Log.d(TAG, "onPostExecute: " + rs);
        Log.d(TAG, "response length: " + rs.length);
        taskCompleted.setNearbyRestaurants(rs);
    }

    @Override
    // runs in background (not in UI thread)
    protected Restaurant[] doInBackground(String... urls) {
        // params comes from the execute() call: params[0] is the url.
        try {
            Restaurant[] temp = downloadUrl(urls[0]);
            return temp;
        } catch (IOException e) {
            Log.e("TAG", "exception" + Log.getStackTraceString(e));
            // return "Unable to retrieve web page. URL may be invalid.";
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return new Restaurant[0];
    } // AsyncTask DownloadWebpageText()


    private Restaurant[] downloadUrl(String myurl) throws IOException, JSONException {
        InputStream is = null;
        // Only read the first 500 characters of the retrieved
        // web page content.
        // int len = MAXBYTES;
        // call request permisson
        HttpURLConnection conn = null;
        URL url = new URL(myurl);
        try {
            // create and open the connection
            conn = (HttpURLConnection) url.openConnection();

            conn.setReadTimeout(10000);    /* milliseconds */
            conn.setConnectTimeout(15000); /* milliseconds */
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Content-Type",
                    "application/json; charset=UTF-8");
            conn.setDoInput(true);
            conn.addRequestProperty("user-key", "70c0881aa31b9b522feff0de9fd16fd8");

            // Starts the query
            conn.connect();
            int response = conn.getResponseCode();

            if (response == HttpURLConnection.HTTP_OK)
                // return "Server returned: " + response + " aborting read.";

                // get the stream for the data from the website
                is = conn.getInputStream();


            // read the stream, returns String
            return convertStreamToJson(is);

        } catch (IOException e) {
            Log.e("TAG", "exception" + Log.getStackTraceString(e));
            throw e;

        } finally {
            /*
             * Make sure that the InputStream is closed after the app is
			 * finished using it.
			 * Make sure the connection is closed after the app is finished using it.
			 */

            if (is != null) {
                try {
                    is.close();
                } catch (IOException ignore) {
                }
                if (conn != null)
                    try {
                        conn.disconnect();
                    } catch (IllegalStateException ignore) {
                    }
            }
        }

    } // downloadUrl()


    public Restaurant[] convertStreamToJson(InputStream inputStream) throws IOException, JSONException {

        String tempData = readIt(inputStream);

        Gson gson = new Gson();
        JSONObject jtemp = new JSONObject();

        JSONObject obj = new JSONObject(tempData);


        return convertJsonIntoObject(obj);


    }

    public Restaurant[] convertJsonIntoObject(JSONObject obj) {

        try {
            JSONArray arr = obj.getJSONArray("nearby_restaurants");
            restaurants = new Restaurant[arr.length()];

            for (int i = 0; i < arr.length(); i++) {
                String name = arr.getJSONObject(i).getJSONObject("restaurant").get("name").toString();
                // String address = arr.getJSONObject(0).getJSONObject("restaurant").getJSONObject("location").get("address").toString();
                String latString = arr.getJSONObject(i).getJSONObject("restaurant").getJSONObject("location").get("latitude").toString();
                double lat = Double.parseDouble(latString);
                String lonString = arr.getJSONObject(i).getJSONObject("restaurant").getJSONObject("location").get("longitude").toString();
                String address = arr.getJSONObject(i).getJSONObject("restaurant").getJSONObject("location").get("address").toString();
                double lon = Double.parseDouble(lonString);
                String url = arr.getJSONObject(i).getJSONObject("restaurant").get("url").toString();
                String cuisines = arr.getJSONObject(i).getJSONObject("restaurant").get("cuisines").toString();
                String image = arr.getJSONObject(i).getJSONObject("restaurant").get("thumb").toString();
                String priceRangeString = arr.getJSONObject(i).getJSONObject("restaurant").get("price_range").toString();
                int price_range = Integer.parseInt(priceRangeString);
                String phoneNumber = "";

                try {
                    phoneNumber = arr.getJSONObject(i).getJSONObject("restaurant").get("phone_numbers").toString();
                } catch (JSONException js) {
                    phoneNumber = "";
                }

                this.restaurants[i] = new Restaurant(name, url, cuisines, phoneNumber, price_range, lat, lon, image);
                CurrentRestaurants.closeByRestaurants[i] = restaurants[i];
                // add to listview in main activity from here.
            }



        } catch (JSONException e) {
            e.printStackTrace();
        }
        return restaurants;


    }

    public String readIt(InputStream stream) throws IOException,
            UnsupportedEncodingException {
        int bytesRead, totalRead = 0;
        byte[] buffer = new byte[1024];

        // for data from the server
        BufferedInputStream bufferedInStream = new BufferedInputStream(stream);
        // to collect data in our output stream
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        DataOutputStream writer = new DataOutputStream(byteArrayOutputStream);

        // read the stream until end
        while ((bytesRead = bufferedInStream.read(buffer)) != -1) {
            writer.write(buffer);
            totalRead += bytesRead;
        }
        writer.flush();
        Log.d("tag", "Bytes read: " + totalRead
                + "(-1 means end of reader so max of)");

        return new String(byteArrayOutputStream.toString());
    } // readIt()

}
