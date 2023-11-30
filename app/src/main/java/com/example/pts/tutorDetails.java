package com.example.pts;
import java.util.ArrayList;
public class tutorDetails
{
    public String tutorid,price, bio;
    public String subject;

    public tutorDetails(String tutorid,String price, String bio, String subject) {

        this.tutorid = tutorid;
        this.price = price;
        this.bio = bio;
        this.subject = subject;
    }

    public String getTutorid()
    {
        return tutorid;
    }
    public String getPrice()
    {
        return price;
    }

    public String getBio() {
        return bio;
    }
    public String getSubject(){
        return subject;
    }
}
