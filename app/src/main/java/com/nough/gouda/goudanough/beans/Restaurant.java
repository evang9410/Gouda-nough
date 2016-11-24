package com.nough.gouda.goudanough.beans;


/**
 * Created by 1133611 on 11/21/2016.
 */
public class Restaurant {
    private String name;
    private String url;
    private String cuisine;
    private int price_range;
    private double latitude;
    private double longitude;
    private String featured_image;

    public Restaurant(String name, String url, String cuisine, int price_range, double latitude, double longitude, String img_src){
        this.name = name;
        this.url = url;
        this.cuisine = cuisine;
        this.price_range = price_range;
        this.latitude = latitude;
        this.longitude = longitude;
        this.featured_image = img_src;
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

    public String getCuisine() {
        return cuisine;
    }

    public void setCuisine(String cuisine) {
        this.cuisine = cuisine;
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

    public String getFeatured_image() {
        return featured_image;
    }

    public void setFeatured_image(String featured_image) {
        this.featured_image = featured_image;
    }
}
