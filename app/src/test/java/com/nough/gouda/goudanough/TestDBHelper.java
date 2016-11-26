package com.nough.gouda.goudanough;
import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.test.AndroidTestCase;

import com.nough.gouda.goudanough.databases.DBHelper;

import org.junit.*;
import static org.junit.Assert.*;

/**
 * Created by Railanderson Sena on 2016-11-25.
 */

public class TestDBHelper extends Activity {

    private static DBHelper dbh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbh = DBHelper.getDBHelper(this);
    }

    @Test
    public void testInsertNewResto(){

        int a = (int) dbh.insertNewResto("test1","99.99","*****","chinese food",1,8500,8500,new byte[]{},"hello test");
        assertEquals(1,a);
    }
}
