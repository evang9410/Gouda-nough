package com.nough.gouda.goudanough.activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.nough.gouda.goudanough.MainActivity;
import com.nough.gouda.goudanough.R;
import com.nough.gouda.goudanough.beans.Restaurant;
import com.nough.gouda.goudanough.databases.DBHelper;

/**
 * Created by 1333612 on 11/29/2016.
 */

public class AddRestoActivity extends Activity {

    private DBHelper dao;
    private EditText resto_name;
    private EditText cuisine;
    private EditText phone_number;
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
        dao = DBHelper.getDBHelper(this);

    }

    public void onSaveClicked(View v){
        if(areAllFieldsEntered())
        {
            Restaurant resto = new Restaurant();
            resto.setCuisine(cuisine.getText().toString());
            resto.setName(resto_name.getText().toString());
            resto.setPhone_numbers(phone_number.getText().toString());
            resto.setRating(rating_spinner.getSelectedItem().toString());
            int price = Integer.parseInt(price_spinner.getSelectedItem().toString());
            resto.setPrice_range(price);
            //resto.setFeatured_image(); // TODO
            //resto.setLatitude();//        TODO
            //resto.setLongitude();//       TODO
            //int userId = getUserID() //   TODO GET THE USER ID FROM THE AUTHENTICATION PAGE.
            dao.insertNewResto(resto,1);// ONE FOR NOW BUT CHANGE LATER!
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
        //redirect to main page
    }

    @Override
    public void finish() {
        super.finish();

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
                        //// TODO: 11/29/2016
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
        if(resto_name == null || cuisine == null || phone_number == null)
            return false;

        String name = resto_name.getText().toString();
        String cuisine = resto_name.getText().toString();
        String phone = phone_number.getText().toString();
        String ratingSelected = rating_spinner.getSelectedItem().toString();
        String priceSelected = price_spinner.getSelectedItem().toString();

        if(name.isEmpty() || cuisine.isEmpty() ||
                phone.isEmpty() || ratingSelected.isEmpty() || priceSelected.isEmpty())
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
    }

    /**
     * This method will set the options inside both spinners.
     */
    private void setSpinners(){

        //set Rating spinner.
        ArrayAdapter<CharSequence> ratingsOptions =
                ArrayAdapter.createFromResource(this,R.array.rating_spin,android.R.layout.simple_spinner_item);
        ratingsOptions.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        rating_spinner.setPrompt("Rate the resto!");
        rating_spinner.setAdapter(ratingsOptions);


        //set price spinner
        ArrayAdapter<CharSequence> pricesOptions =
                ArrayAdapter.createFromResource(this,R.array.price_range_spin,android.R.layout.simple_spinner_item);
        pricesOptions.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        price_spinner.setPrompt("How expensive the food is ?");
        price_spinner.setAdapter(pricesOptions);
    }


}
