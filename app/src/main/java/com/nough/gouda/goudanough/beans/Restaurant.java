package com.nough.gouda.goudanough.beans;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by 1133611 on 11/21/2016.
 */
public class Restaurant {
    private String name;
    private String url;
    private String notes;
    private int price_range;
    private double latitude;
    private double longitude;
    private String img;
    private List<Comment> comments;
    private Address address;
    private String genre;

    public Restaurant(){
        super();
        name = "";
        url = "";
        notes = "";
        price_range = 0;
        latitude = 0.0;
        longitude = 0.0;
        img = "";
        genre = "";
        comments = new ArrayList<>();
        address = new Address();
    }

    public Restaurant(String name, String url, String notes,
                      int price_range, double latitude, double longitude,
                      String img_src,String genre, List<Comment> comments, Address address){
        this.name = name;
        this.url = url;
        this.notes = notes;
        this.price_range = price_range;
        this.latitude = latitude;
        this.longitude = longitude;
        this.img = img_src;
        this.comments = comments;
        this.address = address;
        this.genre = genre;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public int getPrice_range() {
        return price_range;
    }

    public void setPrice_range(int price_range) {
        this.price_range = price_range;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public List<Comment> getComments() {
        return comments;
    }
    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
}
