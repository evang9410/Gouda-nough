package com.nough.gouda.goudanough.beans;

/**
 * Created by 1333612 on 11/24/2016.
 */

public class Comment {

    private String title;
    private String rating;
    private String content;

    /**
     * Default Constructor
     */
    public Comment() {
        super();
    }

    public Comment(String title, String rating, String content) {
        this.title = title;
        this.rating = rating;
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public String getRating() {
        return rating;
    }

    public String getContent() {
        return content;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString(){
        String returning = "comment: {\n";
        returning += "title: "+title + ",\n";
        returning += "rating: "+rating + ",\n";
        returning += "content: "+content + "\n";
        returning += "}";
        return returning;
    }
}
