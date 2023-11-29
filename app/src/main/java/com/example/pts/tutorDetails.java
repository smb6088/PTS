package com.example.pts;

import java.util.ArrayList;

public class tutorDetails extends ReadWriteUserDetails {
    public String tutorid,price, location, rating, bio;
    public String subject;

    public tutorDetails(String tutorid,String price, String location, String rating, String bio, String subject) {
        super();
        this.tutorid = tutorid;
        this.price = price;
        this.location = location;
        this.rating = rating;
        this.bio = bio;
        this.subject = subject;
    }
}
