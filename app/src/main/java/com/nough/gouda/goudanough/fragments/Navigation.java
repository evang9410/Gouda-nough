package com.nough.gouda.goudanough.fragments;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.nough.gouda.goudanough.MainActivity;
import com.nough.gouda.goudanough.R;
import com.nough.gouda.goudanough.Restaurant;
import com.nough.gouda.goudanough.RestaurantListViewAdapter;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link //Navigation.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * create an instance of this fragment.
 */
public class Navigation extends Fragment {

    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String TAG = "Navigation Fragment";
    private Restaurant[] favourites;
    private ImageButton[] nav_buttons = new ImageButton[6];

    // interface object for communicating with parent activity.
    private OnNavigationListener mListener;

    /**
     * Interface used to communicate with the parent activity.
     */
    public interface OnNavigationListener{
        void setFavourites(Restaurant[] favourite_restaurants);
    }

    public Navigation() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//
//        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_navigation, container, false);

        //add click events to all 5 buttons
        createClickEvent(R.id.nav_favourites,view);
        createClickEvent(R.id.nav_add_restaurant,view);
        createClickEvent(R.id.nav_find,view);
        createClickEvent(R.id.nav_nearby,view);
        createClickEvent(R.id.nav_tip_calculator,view);


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
                    + " must implement OnHeadlineSelectedListener");
        }
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        nav_buttons[0] = (ImageButton)getView().findViewById(R.id.nav_favourites);
        Log.d(TAG,nav_buttons[0].toString());
    }

    public ImageButton[] getButtons(){
        return nav_buttons;
    }

    private void createClickEvent(int id, View view){
        ImageButton ib = (ImageButton)view.findViewById(id);
        ib.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch(view.getId()){
                    case R.id.nav_favourites:
                        // fill favourites array.
                        Restaurant[] rs = new Restaurant[2];
                        rs[0] = new Restaurant("My fav resto", "https://google.com", "food","514-559-7108",2, 2.2,2.1,"http://i.imgur.com/BTyyfVQ.jpg");
                        rs[1] = new Restaurant("My fav resto2", "https://google.com", "food","514-559-7108",2, 2.2,2.1,"http://i.imgur.com/BTyyfVQ.jpg");
                        favourites = rs;
                        Log.d(TAG,"Favourites clicked");
                        // get a handle to the adapter to update the listview in the main activity.
                        //RestaurantListViewAdapter adapter = ((MainActivity)getActivity()).getRestaurantAdapter();
                        //adapter.setDataset(rs);
                        mListener.setFavourites(rs);
                        break;
                    case R.id.nav_add_restaurant:
                        // launch the add resto activity
                        Log.d(TAG,"Add restaurant");
                        break;
                    case R.id.nav_find:
                        // launch the find activity, or update view
                        Log.d(TAG,"find called");
                        break;
                    case R.id.nav_nearby:
                        // show the nearby restaurants
                        Log.d(TAG,"nearby called");
                        break;
                    case R.id.nav_tip_calculator:
                        Log.d(TAG, "tip caluulator selected");
                        // launch tip activity
                        break;
                }
            }
        });
    }

    public Restaurant[] getFavourites(){
        return favourites;
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
