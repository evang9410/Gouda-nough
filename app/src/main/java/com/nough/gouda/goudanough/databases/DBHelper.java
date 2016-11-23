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
    public static final String COLUMN_PRICERANGE = "priceRange";
    public static final String COLUMN_RATING = "rating";
    public static final String COLUMN_NOTES = "notes";
    public static final String COLUMN_RESTO_USERID = "userID";
    public static final String COLUMN_LONG = "longitude";
    public static final String COLUMN_LAT = "_latitude";
    public static final String COLUMN_COMMENTID = "_id";
    public static final String COLUMN_TITLE = "title";
    public static final String COLUMN_COMM_RATING = "rating";
    public static final String COLUMN_CONTENT = "comment";
    public static final String COLUMN_COMM_RESTOID = "restoID";
    public static final String COLUMN_COMM_USERID = "userID";
    public static final String COLUMN_USERID = "_id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_USER_POSTALCODE = "postalCode";
    public static final String COLUMN_PASS = "pass";
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_STREETNAME = "streetName";
    public static final String COLUMN_STREETNUMBER = "streetNumber";
    public static final String COLUMN_CITY = "city";
    public static final String COLUMN_POSTALCODE = "postalCode";
    public static final String COLUMN_ADDRESSID = "_id";
    public static final String COLUMN_GENREID = "_id";
    public static final String COLUMN_GENRENME = "genreName";

    //DB NAME
    public static final String DATABASE_NAME = "goudanough.db";
    //DB Version, for when onUpdate is called.
    public static final int DATABASE_VERSION = 1;


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
