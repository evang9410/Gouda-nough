package com.nough.gouda.goudanough.beans;

/**
 * Created by 1333612 on 11/24/2016.
 */

public class Address {

    private int id;
    private String streetName;
    private String streetNumber;
    private String city;
    private String postalCode;

    public Address(){
        super();
        streetName = "";
        streetNumber= "";
        city= "";
        postalCode = "";
    }

    public Address(String streetName, String streetNumber, String city, String postalCode) {
        this.streetName = streetName;
        this.streetNumber = streetNumber;
        this.city = city;
        this.postalCode = postalCode;
    }

    public String getStreetName() {
        return streetName;
    }

    public int getId() {
        return id;
    }

    public String getStreetNumber() {
        return streetNumber;
    }

    public String getCity() {
        return city;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public static Address toAddress(String address){

        String[] separateByComa = address.split(",");
        String streetNum = separateByComa[0].substring(0, separateByComa[0].indexOf(" "));
        String streetName = separateByComa[0].substring(separateByComa[0].indexOf(" ")+1);
        String city = separateByComa[1];
        int blank = separateByComa[2].indexOf(" ");
        String postalCode = "";
        if(blank != -1)
            postalCode = separateByComa[2].substring(blank);
        return new Address(streetName,streetNum,city,postalCode);
    }

    @Override
    public String toString(){
        String returnObj = "address : {\n";
        returnObj += "id : " + id +"\n";
        returnObj += "streetName : " + streetName +"\n";
        returnObj += "streetNumber : " + streetNumber +"\n";
        returnObj += "city : " + city +"\n";
        returnObj += "postalCode : " + postalCode +"\n";
        returnObj += "}";
        return returnObj;
    }
}
