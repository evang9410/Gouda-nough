package com.nough.gouda.goudanough.databases;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * This class has the purpose of dealing
 * with all the CRUD operations involving our database.
 * Created by Ryan Sena on 11/23/2016.
 */
public class DBHelper extends SQLiteOpenHelper {
    public static final String TAG = "DB";// for logging

    //table names.
    public static final String TABLE_RESTAURANT = "restaurant";
    public static final String TABLE_USERS = "user";
    public static final String TABLE_COMMENTS = "comment";
    public static final String TABLE_GENRE = "genre";
    public static final String TABLE_ADDRESS = "address";

    //database field names
    public static final String COLUMN_RESTOID = "_id";
    public static final String COLUMN_PRICERANGE = "_id";
    public static final String COLUMN_RATING = "_id";
    public static final String COLUMN_NOTES = "_id";
    public static final String COLUMN_RESTO_USERID = "_id";
    public static final String COLUMN_LONG = "_id";
    public static final String COLUMN_LAT = "_id";
    public static final String COLUMN_TITLE = "_id";
    public static final String COLUMN_COMM_RATING = "_id";
    public static final String COLUMN_CONTENT = "_id";
    public static final String COLUMN_COMM_RESTOID = "_id";
    public static final String COLUMN_COMM_USERID = "_id";

    /**
     * Constructor
     *
     * @param context
     * @param name
     * @param factory
     * @param version
     */
    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
