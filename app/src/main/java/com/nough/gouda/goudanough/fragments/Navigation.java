package com.nough.gouda.goudanough.fragments;

import android.Manifest;
import android.app.Fragment;
import android.content.Context;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.nfc.Tag;
import android.os.Build;
import android.os.Bundle;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.nough.gouda.goudanough.CurrentRestaurants;
import com.nough.gouda.goudanough.DownloadWebPageText;
import com.nough.gouda.goudanough.MainActivity;
import com.nough.gouda.goudanough.OnTaskCompleted;
import com.nough.gouda.goudanough.R;
import com.nough.gouda.goudanough.activities.AddRestoActivity;
import com.nough.gouda.goudanough.beans.Address;
import com.nough.gouda.goudanough.beans.Restaurant;
import com.nough.gouda.goudanough.RestaurantInfo;
import com.nough.gouda.goudanough.beans.User;
import com.nough.gouda.goudanough.databases.DBHelper;


import java.util.List;

import static android.R.attr.data;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link //Navigation.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * create an instance of this fragment.
 */
public class Navigation extends Fragment implements
        GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, OnTaskCompleted {

    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String TAG = "Navigation Fragment";
    private Restaurant[] favourites;
    private Restaurant[] nearby_restaurants;
    private ImageButton[] nav_buttons = new ImageButton[6];
    private DBHelper dao;
    private GoogleApiClient mGoogleApiClient;
    private double longitude;
    private double latitude;

    // interface object for communicating with parent activity.
    private OnNavigationListener mListener;
    private OnTaskCompleted tc;
    final int MY_PERMISSIONS_REQUEST_LOCATION_COARSE = 1;


    /**
     * Interface used to communicate with the parent activity.
     * TODO: add methods to the interface that pass required data to the parent activity.
     */
    public interface OnNavigationListener {
        void setFavourites(Restaurant[] favourite_restaurants);
        void setNearby(Restaurant[] nearby_restaurants);
    }

    public Navigation() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //getActivity().deleteDatabase("goudanough.db");
        dao = dao.getDBHelper(getActivity());
        /*  THIS IS DUMMY DATA FOR TESTING, DELETE THIS BEFORE FINAL DEPLOY
        User u1 = new User("Ryan", "world", "railanderson@gmail.com", "H3W1N1", "skorpz");
        User u2 = new User("Evan", "World", "railanderson@gmail.com", "H3W1N1","gliko");
        dao.insertNewUser(u1); //works
        dao.insertNewUser(u2);
        Restaurant resto = new Restaurant("My fav resto", "https://google.com", "food","5145597108",2, 2.2,2.1,"http://i.imgur.com/BTyyfVQ.jpg");
        Restaurant resto2 = new Restaurant("My fav resto2", "https://google.com", "food","5145597109",2, 2.2,2.1,"http://i.imgur.com/BTyyfVQ.jpg");
        resto.setAddress(new Address("Dawson","4568","Montreal","H3W1K6"));
        resto2.setAddress(new Address("College","4689","Montreal","H5L9F1"));
        dao.insertNewResto(resto, 1);
        dao.insertNewResto(resto2,1);
        dao.insertNewAddress(new Address("Dawson","4568","Montreal","H3W1K6"),1);
        dao.insertNewAddress(new Address("College","4689","Montreal","H5L9F1"),2);
         */
//        if (getArguments() != null) {
//
//        }
        mGoogleApiClient = new GoogleApiClient.Builder(getActivity())
                .addApi(LocationServices.API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_navigation, container, false);

        //add click events to all 5 buttons
        createClickEvent(R.id.nav_favourites, view);
        createClickEvent(R.id.nav_add_restaurant, view);
        createClickEvent(R.id.nav_find, view);
        createClickEvent(R.id.nav_nearby, view);
        createClickEvent(R.id.nav_tip_calculator, view);


        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception
        try {
            mListener = (OnNavigationListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement OnNavigationListener");
        }
    }

    @Override
    public void onStart() {
        Log.d(TAG,"onStart");
        mGoogleApiClient.connect();
        super.onStart();
    }

    @Override
    public void onStop() {
        mGoogleApiClient.disconnect();
        super.onStop();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        nav_buttons[0] = (ImageButton) getView().findViewById(R.id.nav_favourites);
        Log.d(TAG, nav_buttons[0].toString());
    }

    private void createClickEvent(int id, View view) {
        ImageButton ib = (ImageButton) view.findViewById(id);
        ib.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.nav_favourites:
                        // fill favourites array.
                        /**
                         * Get the restaurants from the current user, obtain their id from the database
                         * store it in the
                         */
                        List<Restaurant> restaurants = dao.getRestaurantsByUserId(1);
                        Log.d(TAG, dao.getUserIdByUserName("Evan") + "");
//                        Restaurant[] rs = new Restaurant[2];
//                        rs[0] = new Restaurant("My fav resto", "https://google.com", "food","514-559-7108",2, 2.2,2.1,"http://i.imgur.com/BTyyfVQ.jpg");
//                        rs[1] = new Restaurant("My fav resto2", "https://google.com", "food","514-559-7108",2, 2.2,2.1,"http://i.imgur.com/BTyyfVQ.jpg");
//                        favourites = rs;
                        Restaurant[] rs = restaurants.toArray(new Restaurant[restaurants.size()]);
                        Log.d(TAG, "Favourites clicked");
                        // pass the favourites array to the parent activity via the interface.
                        mListener.setFavourites(rs);
                        break;
                    case R.id.nav_add_restaurant:
                        Intent i = new Intent(getActivity(),AddRestoActivity.class);
                        //i.putExtra("activityValue",getActivity().getLocalClassName());
                        startActivity(new Intent(getActivity(), AddRestoActivity.class));// launch add activity
                        Log.d(TAG,"Add restaurant");
                        break;
                    case R.id.nav_find:
                        // launch the find activity, or update view
                        Log.d(TAG, "find called");
                        break;
                    case R.id.nav_nearby:
                        // show the nearby restaurants
                        Log.d(TAG, "nearby called");
                        if(checkStatus()){

                            Uri.Builder uri = new Uri.Builder();
                            uri.scheme("https");
                            uri.authority("developers.zomato.com");
                            uri.appendPath("api").appendPath("v2.1");
                            uri.appendPath("geocode");
                            uri.appendQueryParameter("lat",String.valueOf(latitude)).appendQueryParameter("lon",String.valueOf(longitude));
                            Log.d(TAG,uri.toString());
                            new DownloadWebPageText(Navigation.this).execute(uri.toString());
                        }



                        break;
                    case R.id.nav_tip_calculator:
                        Log.d(TAG, "tip calculator selected");
                        // launch tip activity
                        for (int i = 0; i < CurrentRestaurants.closeByRestaurants.length; i++) {
                            System.out.println(CurrentRestaurants.closeByRestaurants[i].toString());
                        }

                        break;
                }
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private boolean checkStatus() {
        NetworkInfo networkInfo;

        Context context = getActivity(); // changed from getContext to support minimum api.
        boolean networkIsUp = false;
        ConnectivityManager connMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        // getActiveNetworkInfo() each time as the network may swap as the
        // device moves
        if (connMgr != null) {
            networkInfo = connMgr.getActiveNetworkInfo();
            // ALWAYS check isConnected() before initiating network traffic
            if (networkInfo != null && networkInfo.isConnectedOrConnecting()) {
                //  tv2.setText("Network is connected or connecting");
                networkIsUp = true;
            } else {
                // tv2.setText("No network connectivity");
                networkIsUp = false;
            }
        } else {
            //  tv2.setText("No network manager service");
            networkIsUp = false;
        }

        if (!networkIsUp) {
            Log.d(TAG, "false");
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setMessage("No network connectivity")
                    .setPositiveButton("Cancel", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.dismiss();
                        }
                    });

            // Create the AlertDialog object
            builder.create().show();
        } else {
            //if network is conneced
            Log.d(TAG, "Network is connected...");
            networkIsUp = true;

        }
        return networkIsUp;
    }

    /**
     * Gets the location of the devices and setst he log and lat to their respective
     * class variables.
     * @param location
     */
    public void getLongAndLat(Location location) {
        Log.d(TAG, "getLongAndLat Called");
        // Create an instance of GoogleAPIClient.
        if (mGoogleApiClient == null) {
            mGoogleApiClient = new GoogleApiClient.Builder(getActivity())
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API)
                    .build();
        }
        Log.d(TAG,"Lat: " + String.valueOf(location.getLatitude()));
        Log.d(TAG, "Long: " + location.getLongitude());
        longitude = location.getLongitude();
        latitude = location.getLatitude();


    }

    /**
     * Set the class variable of nearby restaurants.
     * Overridden from the OnTaskCompleted interface, to allow the AsyncTask to send back data
     * to the NavigationFragment.
     * @param rs
     */
    @Override
    public void setNearbyRestaurants(Restaurant[] rs){
        this.nearby_restaurants = rs;
        Log.d(TAG, rs.toString());
        mListener.setNearby(rs);
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        Log.d(TAG, "Connected to the Google API");

        // Here, thisActivity is the current activity
        if (ContextCompat.checkSelfPermission(getActivity(),
                Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),
                    Manifest.permission.ACCESS_COARSE_LOCATION)) {

                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

            } else {

                // No explanation needed, we can request the permission.

                ActivityCompat.requestPermissions(getActivity(),
                        new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION_COARSE);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        }

        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Log.w(TAG, "permission denied for location");
            // TODO: Consider calling

            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        Location lastLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
        getLongAndLat(lastLocation); // send the long and lat to a method to define them as class variables.
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        super.onRequestPermissionsResult(requestCode,permissions,grantResults);
        if(requestCode == MY_PERMISSIONS_REQUEST_LOCATION_COARSE){
            for(int i = 0; i < permissions.length; i++){
                String permission = permissions[i];
                int grantResult = grantResults[i];

                if(permission.equals(Manifest.permission.ACCESS_COARSE_LOCATION) | permissions.equals(Manifest.permission.ACCESS_FINE_LOCATION)){
                    grantResult = PackageManager.PERMISSION_GRANTED;

                }else{
                    requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, MY_PERMISSIONS_REQUEST_LOCATION_COARSE);
                }
            }
        }
    }

    @Override
    public void onConnectionSuspended(int i) {
        Log.w(TAG, "Connection suspended");
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Log.w(TAG, "Connection failed");
    }


    /*
    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }
*/
    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    /*public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }*/
}
