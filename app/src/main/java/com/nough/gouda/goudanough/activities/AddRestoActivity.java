package com.nough.gouda.goudanough.activities;

import android.app.Activity;
import android.os.Bundle;

import com.nough.gouda.goudanough.R;
import com.nough.gouda.goudanough.databases.DBHelper;

/**
 * Created by 1333612 on 11/29/2016.
 */

public class AddRestoActivity extends Activity {

    private DBHelper dao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dao = DBHelper.getDBHelper(this);

    }
}
