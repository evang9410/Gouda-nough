package com.nough.gouda.goudanough;


import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.nough.gouda.goudanough.fragments.RestaurantListView;

public class ShowRestaurantActivity extends AppCompatActivity implements RestaurantListView.OnRestaurantListViewListener{
    private Restaurant restaurant;
    private static final String TAG = "Restaurant Info";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_restaurant);

        FragmentManager manager = getFragmentManager();
        FragmentTransaction ft = manager.beginTransaction();

        RestaurantListView list = new RestaurantListView();
        ft.replace(R.id.show_resto_list_container, list);
        ft.commit();
        // Dummy dataset for the list view adapter
        // should display the recent viewed restaurants.
        Restaurant[] rs = new Restaurant[2];
        rs[0] = new Restaurant("My resto", "https://google.com", "food","514-559-7108",2, 2.2,2.1,"http://i.imgur.com/BTyyfVQ.jpg");
        rs[1] = new Restaurant("My resto2", "https://google.com", "food","514-559-7108",2, 2.2,2.1,"http://i.imgur.com/BTyyfVQ.jpg");
        RestaurantListViewAdapter adapter = new RestaurantListViewAdapter(this,R.layout.restaurant_listview,rs);

        list.setAdapter(adapter);
        restaurant = getIntent().getParcelableExtra("selected_restaurant");
        Log.d(TAG,restaurant.getName());
    }

    @Override
    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
       // Log.d(TAG, restaurant.getName());
    }


}
