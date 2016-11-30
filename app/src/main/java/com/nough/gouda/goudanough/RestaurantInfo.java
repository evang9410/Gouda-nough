package com.nough.gouda.goudanough;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.renderscript.ScriptGroup;
import android.support.v7.app.AlertDialog;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;

import com.google.gson.Gson;

/**
 * Created by Jesse on 23/11/2016.
 */
public class RestaurantInfo extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    public void downloadJsonData() {
        // Gets the URL from the UI's text field.
        String stringUrl = "https://developers.zomato.com/api/v2.1/geocode?lat=45.4897&lon=-73.5878";
        new DownloadWebpageText().execute(stringUrl);
    }


    public class DownloadWebpageText extends AsyncTask<String, Void, Restaurant[]> {

        protected void onPostExecute(String result) {
            Log.d("tag", "onPostExecute: " + result);
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
            Restaurant restaurants[] = new Restaurant[0];
            try {
                JSONArray arr = obj.getJSONArray("nearby_restaurants");
                restaurants = new Restaurant[arr.length()];

                for (int i = 0; i < arr.length(); i++) {
                    String name = arr.getJSONObject(i).getJSONObject("restaurant").get("name").toString();
                    // String address = arr.getJSONObject(0).getJSONObject("restaurant").getJSONObject("location").get("address").toString();
                    String latString = arr.getJSONObject(i).getJSONObject("restaurant").getJSONObject("location").get("latitude").toString();
                    double lat = Double.parseDouble(latString);
                    String lonString = arr.getJSONObject(i).getJSONObject("restaurant").getJSONObject("location").get("longitude").toString();
                    double lon = Double.parseDouble(lonString);
                    String url = arr.getJSONObject(i).getJSONObject("restaurant").get("url").toString();
                    String cuisines = arr.getJSONObject(i).getJSONObject("restaurant").get("cuisines").toString();
                    String image = arr.getJSONObject(i).getJSONObject("restaurant").get("thumb").toString();
                    String priceRangeString = arr.getJSONObject(i).getJSONObject("restaurant").get("price_range").toString();
                    int price_range = Integer.parseInt(priceRangeString);
                    String phoneNumber = "";
                        try {
                            phoneNumber = arr.getJSONObject(i).getJSONObject("restaurant").get("phone_numbers").toString();
                        }
                        catch(JSONException js){
                            phoneNumber = "";
                        }

                    restaurants[i] = new Restaurant(name, url, cuisines, phoneNumber, price_range, lat, lon, image);
                    CurrentRestaurants.closeByRestaurants[i] = restaurants[i];
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


} // myClickHandler()

