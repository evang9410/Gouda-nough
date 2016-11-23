package com.nough.gouda.goudanough.databases;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

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

    //RESTO TABLE
    public static final String COLUMN_RESTOID = "_id";
    public static final String COLUMN_PRICERANGE = "priceRange";
    public static final String COLUMN_RATING = "rating";
    public static final String COLUMN_NOTES = "notes";
    public static final String COLUMN_RESTO_USERID = "userID";
    public static final String COLUMN_LONG = "longitude";
    public static final String COLUMN_LAT = "_latitude";
    //COMMENT TABLE
    public static final String COLUMN_COMMENTID = "_id";
    public static final String COLUMN_TITLE = "title";
    public static final String COLUMN_COMM_RATING = "rating";
    public static final String COLUMN_CONTENT = "comment";
    public static final String COLUMN_COMM_RESTOID = "restoID";
    public static final String COLUMN_COMM_USERID = "userID";
    //USER TABLE
    public static final String COLUMN_USERID = "_id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_USER_POSTALCODE = "postalCode";
    public static final String COLUMN_PASS = "pass";
    public static final String COLUMN_EMAIL = "email";
    //ADDRESS TABLE
    public static final String COLUMN_ADDRESSID = "_id";
    public static final String COLUMN_STREETNAME = "streetName";
    public static final String COLUMN_STREETNUMBER = "streetNumber";
    public static final String COLUMN_CITY = "city";
    public static final String COLUMN_POSTALCODE = "postalCode";
    public static final String COLUMN_ADDR_RESTOID = "restoID";

    //GENRE TABLE
    public static final String COLUMN_GENREID = "_id";
    public static final String COLUMN_GENRENME = "genreName";
    public static final String COLUMN_GEN_RESTOID = "restoID";


    //Foreign keys
    public static final String FK_RESTO_USER = "FOREIGN KEY (" +COLUMN_RESTO_USERID +" ) REFERENCES " + TABLE_USERS +"("+ COLUMN_USERID + ") ";
    public static final String FK_COMM_RESTO = "FOREIGN KEY (" +COLUMN_COMM_RESTOID +" ) REFERENCES " + TABLE_RESTAURANT +"("+ COLUMN_RESTOID + ") ";
    public static final String FK_COMM_USER = "FOREIGN KEY (" +COLUMN_COMM_USERID +" ) REFERENCES " + TABLE_USERS +"( "+ COLUMN_USERID + ") ";
    public static final String FK_GEN_RESTO = "FOREIGN KEY (" +COLUMN_GEN_RESTOID +" ) REFERENCES " + TABLE_RESTAURANT +"( "+ COLUMN_RESTOID + ") ";
    public static final String FK_ADDR_RESTO = "FOREIGN KEY (" +COLUMN_ADDR_RESTOID +" ) REFERENCES " + TABLE_RESTAURANT +"( "+ COLUMN_RESTOID + ") ";

    //DB NAME
    public static final String DATABASE_NAME = "goudanough.db";
    //DB Version, for when onUpdate is called.
    public static final int DATABASE_VERSION = 1;

    private static final DBHelper dbh = null;
    //Database creation raw SQL statement
    private static final String DATABASE_CREATE_RESTAURANT = "create table "
            + TABLE_RESTAURANT + "( " + COLUMN_RESTOID
            + " integer primary key autoincrement, " + COLUMN_PRICERANGE
            + " text, " + COLUMN_RATING + " text, "
            + COLUMN_NOTES + " text, " + COLUMN_RESTO_USERID
            + " integer, " + FK_RESTO_USER +");";

    private static final String DATABASE_CREATE_USER = "create table "
            + TABLE_USERS + "( " + COLUMN_USERID
            + " integer primary key autoincrement, " + COLUMN_NAME
            + " text, " + COLUMN_USER_POSTALCODE + " text, "
            + COLUMN_PASS + " text, " + COLUMN_EMAIL
            + " text" + ");";

    private static final String DATABASE_CREATE_COMMENTS = "create table "
            + TABLE_COMMENTS + "( " + COLUMN_COMMENTID
            + " integer primary key autoincrement, " + COLUMN_TITLE
            + " text, " + COLUMN_COMM_RATING + " text, "
            + COLUMN_CONTENT + " text, " + COLUMN_COMM_RESTOID
            + " integer, " + COLUMN_COMM_USERID + " integer, "
            + FK_COMM_RESTO + ", " + FK_COMM_USER + ");";

    private static final String DATABASE_CREATE_GENRE = "create table "
            + TABLE_GENRE + "( " + COLUMN_GENREID
            + " integer primary key autoincrement, " + COLUMN_GENRENME
            + " text, " + COLUMN_GEN_RESTOID + " integer, " + FK_GEN_RESTO +");";

    private static final String DATABASE_CREATE_ADDRESS = "create table "
            + TABLE_ADDRESS + "( " + COLUMN_ADDRESSID
            + " integer primary key autoincrement, " + COLUMN_STREETNAME
            + " text, " + COLUMN_STREETNUMBER + " text, "
            + COLUMN_CITY + " text, " + COLUMN_POSTALCODE
            + " text, " + FK_ADDR_RESTO +");";


    /**
     * Default Constructor.
     *
     * @param context
     */
    public DBHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * This method takes care of creating all my tables.
     * @param database
     */
    @Override
    public void onCreate(SQLiteDatabase database) {

        database.execSQL(DATABASE_CREATE_RESTAURANT);
        Log.i(TAG, "CREATE RESTO TBL");
        database.execSQL(DATABASE_CREATE_USER);
        Log.i(TAG, "CREATE USER TBL");
        database.execSQL(DATABASE_CREATE_COMMENTS);
        Log.i(TAG, "CREATE COMMENTS TBL");
        database.execSQL(DATABASE_CREATE_GENRE);
        Log.i(TAG, "CREATE GENRE TBL");
        database.execSQL(DATABASE_CREATE_ADDRESS);
        Log.i(TAG, "CREATE ADDRESS TBL");

    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
        Log.w(TAG, DBHelper.class.getName() + "Upgrading database from version "
                + oldVersion + " to " + newVersion
                + ", which will destroy all old data");
        // Im dropping all the tables in order to create new ones.
        database.execSQL("DROP TABLE IF EXISTS " + TABLE_RESTAURANT);
        database.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        database.execSQL("DROP TABLE IF EXISTS " + TABLE_COMMENTS);
        database.execSQL("DROP TABLE IF EXISTS " + TABLE_GENRE);
        database.execSQL("DROP TABLE IF EXISTS " + TABLE_ADDRESS);
        onCreate(database);
        Log.i(TAG, "UPGRADED");
    }
    @Override
    public void onOpen(SQLiteDatabase database) {
        Log.i(TAG, "OPENED");
    }
}
