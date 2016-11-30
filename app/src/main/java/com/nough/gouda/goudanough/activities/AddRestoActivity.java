package com.nough.gouda.goudanough.activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.nough.gouda.goudanough.MainActivity;
import com.nough.gouda.goudanough.R;
import com.nough.gouda.goudanough.beans.Address;
import com.nough.gouda.goudanough.beans.Restaurant;
import com.nough.gouda.goudanough.beans.User;
import com.nough.gouda.goudanough.databases.DBHelper;

import java.util.List;

/**
 * Created by 1333612 on 11/29/2016.
 */

public class AddRestoActivity extends Activity {

    public static final String TAG = "AddResto";// for logging
    private DBHelper dao;
    private EditText resto_name;
    private EditText cuisine;
    private EditText phone_number;
    private EditText street_number;
    private EditText street_name;
    private EditText city;
    private EditText postal_code;
    private Spinner price_spinner;
    private Spinner rating_spinner;
    private Button save_button;//prolly not needed.
    private Button cancel_button;//prolly not needed.

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_resto);
        initFields();
        setSpinners();
        
        //String s = getIntent().getStringExtra("activityValue");

        //this.deleteDatabase("goudanough.db");

    }

    public void onSaveClicked(View v){
        if(areAllFieldsEntered())
        {
            dao = DBHelper.getDBHelper(this);
            Restaurant resto = new Restaurant();

            Address addr = new Address();
            addr.setStreetName(street_name.getText().toString());
            addr.setStreetNumber(street_number.getText().toString());
            addr.setCity(city.getText().toString());
            addr.setPostalCode(postal_code.getText().toString());

            resto.setCuisine(cuisine.getText().toString());
            resto.setName(resto_name.getText().toString());
            resto.setPhone_numbers(phone_number.getText().toString());
            resto.setRating(rating_spinner.getSelectedItem().toString());
            int price = Integer.parseInt(price_spinner.getSelectedItem().toString());
            resto.setPrice_range(price);
            //resto.setFeatured_image(); // TODO Must be with zomato.
            //resto.setLatitude();//        TODO Must be with Zomato
            //resto.setLongitude();//       TODO Must be with zomato.
            //int userId = getUserID() //   TODO GET THE USER ID FROM THE AUTHENTICATION PAGE.

            dao.insertNewResto(resto,1);// ONE FOR NOW BUT CHANGE LATER!
            List<Restaurant> restos = dao.getAllRestaurants();
            List<User> users = dao.getAllUsers();
            for(Restaurant r : restos){
                Log.d(TAG,r.getName());
            }
            for(User u : users){
                Log.d(TAG,u.getName());
            }
            //int restoId = dao.getRestoIdByName(resto.getName());// get the resto id based on the name.
            //Log.d(TAG,restoId+"");
            //dao.insertNewAddress(addr,restoId);//insert a new address to this restoID.
            redirectToMain();
        }
        else{
            Toast toast = Toast.makeText(this,"One of your fields was not entered.",Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    public void onCancelClicked(View v){

        resto_name.setText("");
        cuisine.setText("");
        phone_number.setText("");
        //redirectToMain();
    }

    @Override
    public void finish() {
        alertDiscart();
    }

    private void alertDiscart(){
        new AlertDialog.Builder(this)
                .setTitle("Warning")
                .setMessage("Are you sure you wanna discart your changes ?")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog,int which){
                        //// TODO: 11/29/2016
                        redirectToMain();
                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog, int which){
                        //// Nothing should happen.
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

    private void redirectToMain(){
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }
    private boolean areAllFieldsEntered(){
        if(resto_name == null || cuisine == null || phone_number == null || street_number == null
                || street_name== null|| city == null|| postal_code == null)
            return false;

        String name = resto_name.getText().toString();
        String cuisine = resto_name.getText().toString();
        String phone = phone_number.getText().toString();
        String ratingSelected = rating_spinner.getSelectedItem().toString();
        String priceSelected = price_spinner.getSelectedItem().toString();
        String streetNum = street_number.getText().toString();
        String streetName = street_name.getText().toString();
        String citystr = city.getText().toString();
        String postal = postal_code.getText().toString();


        if(name.isEmpty() || cuisine.isEmpty() ||
                phone.isEmpty() || ratingSelected.isEmpty() || priceSelected.isEmpty()
                || streetNum.isEmpty()|| streetName.isEmpty()|| citystr.isEmpty()|| postal.isEmpty())
            return false;

        return true;
    }

    /**
     * This method will initialize all the fields in this class.
     */
    private void initFields(){
        resto_name = (EditText)findViewById(R.id.resto_name);
        cuisine = (EditText)findViewById(R.id.resto_cuisine);
        phone_number = (EditText)findViewById(R.id.resto_phone);
        price_spinner = (Spinner)findViewById(R.id.resto_price_spin);
        rating_spinner = (Spinner)findViewById(R.id.resto_rating_spin);
        save_button = (Button)findViewById(R.id.save_btn);
        cancel_button = (Button)findViewById(R.id.cancel_btn);
        street_number = (EditText)findViewById(R.id.street_number);
        street_name = (EditText)findViewById(R.id.street_name);
        city = (EditText)findViewById(R.id.city);
        postal_code = (EditText)findViewById(R.id.postal_code);

    }

    /**
     * This method will set the options inside both spinners.
     */
    private void setSpinners(){

        //set Rating spinner.
        ArrayAdapter<CharSequence> ratingsOptions =
                ArrayAdapter.createFromResource(this,R.array.rating_spin,android.R.layout.simple_spinner_item);
        ratingsOptions.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        rating_spinner.setPrompt("Give a grade for this resto: ");
        rating_spinner.setAdapter(ratingsOptions);


        //set price spinner
        ArrayAdapter<CharSequence> pricesOptions =
                ArrayAdapter.createFromResource(this,R.array.price_range_spin,android.R.layout.simple_spinner_item);
        pricesOptions.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        price_spinner.setPrompt("Select the price of this resto:");
        price_spinner.setAdapter(pricesOptions);
    }


}
