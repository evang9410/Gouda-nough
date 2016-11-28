package com.nough.gouda.goudanough;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.nough.gouda.goudanough.beans.Address;
import com.nough.gouda.goudanough.beans.Comment;
import com.nough.gouda.goudanough.beans.Restaurant;
import com.nough.gouda.goudanough.beans.User;
import com.nough.gouda.goudanough.databases.DBHelper;

import java.util.List;

public class MainActivity extends AppCompatActivity  {
    public static final String TAG = "ActMain";// for logging

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

}
