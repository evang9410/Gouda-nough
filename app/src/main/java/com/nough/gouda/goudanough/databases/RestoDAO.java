package com.nough.gouda.goudanough.databases;
import android.database.Cursor;
import android.content.Context;

/**
 * Created by 1333612 on 11/24/2016.
 */

public class RestoDAO {

    private Cursor restaurants;
    private Cursor users;
    private Cursor comments;
    private Cursor genre;
    private Cursor addresses;

    private DBHelper database;

    /**
     * Constructor
     */
    public RestoDAO(Context context){

        database = new DBHelper(context);// creates a new instance to the database.

        restaurants = database.getRestaurants();
        users = database.getUsers();
        comments = database.getComments();
        genre = database.getGenre();
        addresses = database.getAddresses();
    }
    


}
