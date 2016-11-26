package com.nough.gouda.goudanough;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.nough.gouda.goudanough.databases.DBHelper;
import com.nough.gouda.goudanough.fragments.Navigation;

public class MainActivity extends AppCompatActivity  {
    private DBHelper dbh;

    public static final String TAG = "ActMain";// for logging
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //this.getApplicationContext().deleteDatabase("goudanough.db");
        dbh = DBHelper.getDBHelper(this);
        testGetAllUsers();
        //testInserts();
    }

    private void testInserts(){
        //int b = (int)dbh.insertNewUser("Ryan","H3W1N1","wolrd","railanderson@gmail.com"); //works
        //int a = (int) dbh.insertNewResto("test1","99.99","*****","chinese food",1,8500,8500,new byte[]{},"hello test"); //works
        //int c = (int)dbh.insertNewAddress("Av. Saint-Kevin","4650","Montreal","H3W1N9",1); // works
        //int d = (int)dbh.insertNewComment("Hello Word","testing","**",1,1);// works
        //int e = (int)dbh.insertNewGenre("Chinese Food",1);// works
       // Log.d(TAG,e+"");
    }
    private void testGetAllUsers(){
        Cursor users = dbh.getAllUsers();


    }
}
