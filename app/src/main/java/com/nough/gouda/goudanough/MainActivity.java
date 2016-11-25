package com.nough.gouda.goudanough;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.nough.gouda.goudanough.fragments.Header;
import com.nough.gouda.goudanough.fragments.Navigation;
import com.nough.gouda.goudanough.fragments.RestaurantListView;

public class MainActivity extends AppCompatActivity  {
    private static final String TAG = "Main Activity";
    private RestaurantListViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager manager = getFragmentManager();
        FragmentTransaction ft = manager.beginTransaction();

        // create the fragments to be used in this layout
        Header header = new Header();
        Navigation nav = new Navigation();
        RestaurantListView list = new RestaurantListView();
        if(savedInstanceState == null){
            // add the fragments to the linear layout
            ft.add(R.id.mainLayout,header, "header");
            ft.add(R.id.mainLayout,nav,"navigation_menu");
            ft.add(R.id.mainLayout, list, "list_view");
            ft.commit();
        }


        // Dummy dataset for the list view adapter
        Restaurant[] rs = new Restaurant[2];
        rs[0] = new Restaurant("My resto", "https://google.com", "food","514-559-7108",2, 2.2,2.1,"http://i.imgur.com/BTyyfVQ.jpg");
        rs[1] = new Restaurant("My resto2", "https://google.com", "food","514-559-7108",2, 2.2,2.1,"http://i.imgur.com/BTyyfVQ.jpg");
        adapter = new RestaurantListViewAdapter(this,R.layout.restaurant_listview,rs);

        list.setAdapter(adapter);
        //setUpNavButtons();
    }

    public RestaurantListViewAdapter getRestaurantAdapter(){
        return adapter;
    }

    private void setUpNavButtons() {
        //add click events to all 5 buttons
        createClickEvent(R.id.nav_favourites);
        createClickEvent(R.id.nav_add_restaurant);
        createClickEvent(R.id.nav_find);
        createClickEvent(R.id.nav_nearby);
        createClickEvent(R.id.nav_tip_calculator);
    }

    private void createClickEvent(int id){
        ImageButton ib = (ImageButton)findViewById(id);
        ib.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch(view.getId()){
                    case R.id.nav_favourites:
                        // fill favourites array.
                        // dummy data for testing favourites click to change ListView DataSet.
                        Restaurant[] rs = new Restaurant[2];
                        rs[0] = new Restaurant("My fav resto", "https://google.com", "food","514-559-7108",2, 2.2,2.1,"http://i.imgur.com/BTyyfVQ.jpg");
                        rs[1] = new Restaurant("My fav resto2", "https://google.com", "food","514-559-7108",2, 2.2,2.1,"http://i.imgur.com/BTyyfVQ.jpg");
                        Log.d(TAG,"Favourites clicked");
                        adapter.setDataset(rs); // changes the dataset of the ListView
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


}
