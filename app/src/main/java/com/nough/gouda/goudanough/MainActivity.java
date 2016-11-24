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
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.nough.gouda.goudanough.fragments.Header;
import com.nough.gouda.goudanough.fragments.Navigation;
import com.nough.gouda.goudanough.fragments.RestaurantListView;

public class MainActivity extends AppCompatActivity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager manager = getFragmentManager();
        FragmentTransaction ft = manager.beginTransaction();
        Header header = new Header();
        Navigation nav = new Navigation();
        RestaurantListView list = new RestaurantListView();
        
        ListView lv = list.getListView();
        ft.add(R.id.mainLayout,header, "xyz");
        ft.add(R.id.mainLayout,nav,"navigation_menu");
        ft.add(R.id.mainLayout, list, "list_view");
        ft.commit();
        // set up a long click listener for the listview
        // launches the implicit intent to call the restaurant using the phones telephony.
//        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
//            @Override
//            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
//                // call the intent
//                Log.d("TAg","on long click");
//                return true;
//            }
//        });


        // Dummy dataset for the list view adapter
        Restaurant[] rs = new Restaurant[2];
        rs[0] = new Restaurant("My resto", "https://google.com", "food",2, 2.2,2.1,"http://i.imgur.com/BTyyfVQ.jpg");
        rs[1] = new Restaurant("My resto2", "https://google.com", "food",2, 2.2,2.1,"http://i.imgur.com/BTyyfVQ.jpg");
        RestaurantListViewAdapter adapter = new RestaurantListViewAdapter(this,R.layout.restaurant_listview,rs);

        list.setAdapter(adapter);

    }


}
