package com.nough.gouda.goudanough;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.view.View;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Jesse on 23/11/2016.
 */
public class RestaurantInfo extends Activity {

    //Restaurant restaurentInfo[] =9;
    // public String response = downloadJsonData();
   public String test = "";

    public String downloadJsonData() {
        // Gets the URL from the UI's text field.
        String stringUrl = "www.google.com";
        stringUrl = "http://" + stringUrl;

        // first check to see if we can get on the network
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            // invoke the AsyncTask to do the dirtywork.
             new DownloadWebpageText().execute(stringUrl);
            // text is set in DownloadWebpageText().onPostExecute()
        } else {
            return "No network connection available.";
        }
        return "";
    }


    public class DownloadWebpageText extends AsyncTask<String, Void, String> {


        protected void onPostExecute(String result) {

        }

        @Override
        // runs in background (not in UI thread)
        protected String doInBackground(String... urls) {
            // params comes from the execute() call: params[0] is the url.
            try {
                return downloadUrl(urls[0]);
            } catch (IOException e) {
                return "Unable to retrieve web page. URL may be invalid.";
            }

        } // AsyncTask DownloadWebpageText()


        private String downloadUrl(String myurl) throws IOException {
            InputStream is = null;
            // Only read the first 500 characters of the retrieved
            // web page content.
            // int len = MAXBYTES;
            HttpURLConnection conn = null;
            URL url = new URL(myurl);
            try {
                // create and open the connection
                conn = (HttpURLConnection) url.openConnection();

                conn.setReadTimeout(10000);
                conn.setConnectTimeout(15000 /* milliseconds */);
                conn.setRequestMethod("GET");
                conn.setDoOutput(true);

                // specifies whether this connection allows receiving data
                conn.setDoInput(true);
                // Starts the query
                conn.connect();
                int response = conn.getResponseCode();

                if (response != HttpURLConnection.HTTP_OK)
                    return "Server returned: " + response + " aborting read.";

                // get the stream for the data from the website
                is = conn.getInputStream();
                // read the stream, returns String
                test = is.toString();
                return "";

            } catch (IOException e) {

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

            return new String(byteArrayOutputStream.toString());
        } // readIt()

    }







} // myClickHandler()

