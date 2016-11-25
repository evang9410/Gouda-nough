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
 * Use the {@link Navigation#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Navigation extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String TAG = "Navigation Fragment";
    private Restaurant[] favourites;
    private ImageButton[] nav_buttons = new ImageButton[6];

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    // private OnFragmentInteractionListener mListener;

    public Navigation() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Navigation.
     */
    // TODO: Rename and change types and number of parameters
    public static Navigation newInstance(String param1, String param2) {
        Navigation fragment = new Navigation();
        Bundle args = new Bundle();

        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }


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
