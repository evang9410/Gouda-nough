package com.nough.gouda.goudanough.databases;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.database.Cursor;

import com.nough.gouda.goudanough.beans.Comment;

import java.util.ArrayList;
import java.util.List;

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
    public static final String COLUMN_RESTO_NAME = "name";
    public static final String COLUMN_PRICERANGE = "priceRange";
    public static final String COLUMN_RATING = "rating";
    public static final String COLUMN_NOTES = "notes";
    public static final String COLUMN_RESTO_USERID = "userID";
    public static final String COLUMN_LONG = "longitude";
    public static final String COLUMN_LAT = "latitude";
    public static final String COLUMN_IMG = "image";
    public static final String COLUMN_URL = "url";
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
    private static final String DATABASE_CREATE_RESTAURANT = "create table " + TABLE_RESTAURANT
            + "( "
            + COLUMN_RESTOID + " integer primary key autoincrement, "
            + COLUMN_RESTO_NAME + " text, "
            + COLUMN_PRICERANGE + " text, "
            + COLUMN_RATING + " text, "
            + COLUMN_NOTES + " text, "
            + COLUMN_RESTO_USERID + " integer, "
            + COLUMN_LONG + " integer, "
            + COLUMN_LAT + " integer, "
            + COLUMN_IMG + "blob, "
            + COLUMN_URL + "text, "
            + FK_RESTO_USER
            +");";

    private static final String DATABASE_CREATE_USER = "create table " + TABLE_USERS
            + "( "
            + COLUMN_USERID + " integer primary key autoincrement, "
            + COLUMN_NAME + " text, "
            + COLUMN_USER_POSTALCODE + " text, "
            + COLUMN_PASS + " text, "
            + COLUMN_EMAIL + " text"
            + ");";

    private static final String DATABASE_CREATE_COMMENTS = "create table " + TABLE_COMMENTS
            + "( "
            + COLUMN_COMMENTID + " integer primary key autoincrement, "
            + COLUMN_TITLE + " text, "
            + COLUMN_COMM_RATING + " text, "
            + COLUMN_CONTENT + " text, "
            + COLUMN_COMM_RESTOID + " integer, "
            + COLUMN_COMM_USERID + " integer, "
            + FK_COMM_RESTO + ", "
            + FK_COMM_USER
            + ");";

    private static final String DATABASE_CREATE_GENRE = "create table " + TABLE_GENRE
            + "( "
            + COLUMN_GENREID + " integer primary key autoincrement,"
            + COLUMN_GENRENME + " text, "
            + COLUMN_GEN_RESTOID + " integer, "
            + FK_GEN_RESTO
            + ");";

    private static final String DATABASE_CREATE_ADDRESS = "create table " + TABLE_ADDRESS
            + "( "
            + COLUMN_ADDRESSID + " integer primary key autoincrement, "
            + COLUMN_STREETNAME + " text, "
            + COLUMN_STREETNUMBER + " text, "
            + COLUMN_CITY + " text, "
            + COLUMN_POSTALCODE + " text, "
            + COLUMN_ADDR_RESTOID + "integer, "
            + FK_ADDR_RESTO
            +");";


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

    /**
     * This method will insert a new Restaurant record to the database.
     *
     * @param name name of the restaurant
     * @param price price range
     * @param rating ratings
     * @param notes notes about the restaurant like "Traditional Vietnamese food."
     * @param userID who submitted
     * @param longitude precise location
     * @param latitude precise location
     * @return the number of rows affected.
     */
    public long insertNewResto(String name, String price, String rating,
                               String notes, int userID, int longitude,
                               int latitude, byte[] img, String url)
    {
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_RESTO_NAME,name);
        cv.put(COLUMN_PRICERANGE,price);
        cv.put(COLUMN_RATING,rating);
        cv.put(COLUMN_NOTES,notes);
        cv.put(COLUMN_RESTO_USERID,userID);
        cv.put(COLUMN_LONG,longitude);
        cv.put(COLUMN_LAT,latitude);
        cv.put(COLUMN_IMG,img);
        cv.put(COLUMN_URL, url);

        return getWritableDatabase().insert(TABLE_RESTAURANT, null, cv);


    }

    /**
     * This method will take care of inserting a new user record to the database.
     *
     * @param name name of the person.
     * @param postalCode person's postal code.
     * @param pass user's password.
     * @param email user's email.
     * @return the number of rows affected.
     */
    public long insertNewUser(String name, String postalCode, String pass, String email){

        ContentValues cv = new ContentValues();

        cv.put(COLUMN_NAME,name);
        cv.put(COLUMN_USER_POSTALCODE,postalCode);
        cv.put(COLUMN_PASS,pass);
        cv.put(COLUMN_EMAIL,email);

        return getWritableDatabase().insert(TABLE_USERS, null, cv);
    }

    /**
     * This method will take care of inserting a new comment to the database.
     *
     * @param title the title of the comment.
     * @param content the content of the comment.
     * @param rating the rating of the comment.
     * @param userID who made the comment.
     * @param restoID to which resto this comment belongs to.
     * @return the number of rows affected.
     */
    public long insertNewComment(String title, String content, String rating, int userID, int restoID){

        ContentValues cv = new ContentValues();

        cv.put(COLUMN_TITLE,title);
        cv.put(COLUMN_COMM_RATING,rating);
        cv.put(COLUMN_CONTENT,content);
        cv.put(COLUMN_COMM_RESTOID,restoID);
        cv.put(COLUMN_COMM_USERID,userID);

        return getWritableDatabase().insert(TABLE_COMMENTS, null, cv);
    }

    /**
     * This method will take care of inserting a new Genre to the database.
     * @param genreName the name of the resto genre for example "traditioanl chinese food"
     * @param restoID which resto this genre belongs to.
     * @return number of rows affected.
     */
    public long insertNewGenre(String genreName, int restoID){

        ContentValues cv = new ContentValues();

        cv.put(COLUMN_GENRENME,genreName);
        cv.put(COLUMN_GEN_RESTOID,restoID);

        return getWritableDatabase().insert(TABLE_GENRE, null, cv);
    }

    /**
     * This method will take care of inserting new addresses to the database.
     *
     * @param streetName
     * @param streetNumber
     * @param city
     * @param postalCode
     * @return the number of rows affected.
     */
    public long insertNewAddress(String streetName, String streetNumber, String city, String postalCode, String restoID){

        ContentValues cv = new ContentValues();

        cv.put(COLUMN_STREETNAME,streetName);
        cv.put(COLUMN_STREETNUMBER,streetNumber);
        cv.put(COLUMN_CITY,city);
        cv.put(COLUMN_POSTALCODE,postalCode);
        cv.put(COLUMN_ADDR_RESTOID,restoID);


        return getWritableDatabase().insert(TABLE_ADDRESS, null, cv);
    }

    /**
     * This method will return all the restaurants in the database.
     * @return
     */
    public Cursor geAllRestaurants(){
        return getReadableDatabase().query(TABLE_RESTAURANT, null, null, null,
                null,null,null);
    }

    /**
     * This method will return all the users in the database.
     * @return
     */
    public Cursor getAllUsers(){
        return getReadableDatabase().query(TABLE_USERS, null, null, null,
                null,null,null);
    }

    /**
     * This method will return all comments in the database.
     * @return
     */
    public Cursor getAllComments(){
        return getReadableDatabase().query(TABLE_COMMENTS, null, null, null,
                null,null,null);
    }

    /**
     * This method will return all the genres in the database.
     * @return
     */
    public Cursor getAllGenres(){
        return getReadableDatabase().query(TABLE_GENRE, null, null, null,
                null,null,null);
    }

    /**
     * This method will return all the addresses in the database.
     * @return
     */
    public Cursor getAllAddresses(){
        return getReadableDatabase().query(TABLE_ADDRESS, null, null, null,
                null,null,null);
    }

    /**
     * This method will get all the comments based on the user ID
     * @return
     */
    public List<Comment> getCommentsByUserId(int userID){
        
        List<Comment> listOfComments = new ArrayList<>();
        Comment comment = new Comment();
        String[] tableColumns = new String[]{COLUMN_TITLE,COLUMN_COMM_RATING,COLUMN_CONTENT};
        String whereClause = COLUMN_USERID + " = ?";
        String[] whereArgs = new String[]{userID+""};

        Cursor c = getReadableDatabase().query(TABLE_COMMENTS,tableColumns,whereClause, whereArgs, null,
                null,null);// query and get the results as a cursor.
        if(c != null){
            if(c.moveToFirst()){// move the cursor to the first one
                do{// loop trough each record, getting the values column by column and adding to a
                    // list of comments
                    comment.setTitle(c.getString(c.getColumnIndex(COLUMN_TITLE)));
                    comment.setContent(c.getString(c.getColumnIndex(COLUMN_CONTENT)));
                    comment.setRating(c.getString(c.getColumnIndex(COLUMN_COMM_RATING)));

                    listOfComments.add(comment);
                    comment = new Comment();
                }while(c.moveToNext());
            }
        }
        return listOfComments;// return the list.
    }


}
