package com.nough.gouda.goudanough;

import android.content.Context;
import android.support.v4.app.ListFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.nough.gouda.goudanough.fragments.Navigation;

public class MainActivity extends AppCompatActivity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView lv = (ListView)findViewById(android.R.id.list);

        // Test dataset for the list view adapter
        Restaurant[] rs = new Restaurant[2];
        rs[0] = new Restaurant("My resto", "https://google.com", "food",2, 2.2,2.1,"http://i.imgur.com/BTyyfVQ.jpg");
        rs[1] = new Restaurant("My resto2", "https://google.com", "food",2, 2.2,2.1,"http://i.imgur.com/BTyyfVQ.jpg");
        RestaurantListViewAdapter adapter = new RestaurantListViewAdapter(this,R.layout.restaurant_listview,rs);

        lv.setAdapter(adapter);
    }


}
