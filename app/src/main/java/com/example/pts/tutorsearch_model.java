package com.example.pts;

public class tutorsearch_model {


    static String name;
    static String rating;
    static String location;
    String bio;
    static String subject;


    public tutorsearch_model(String bio,String name, String rating, String location,String subject) {
        this.name = name;
        this.rating = rating;
        this.location = location;
        this.subject = subject;
    }


    public static String getName() {
        return name;
    }
    public static String getSubject() {
        return subject;
    }
    public static String getRating() {
        return rating;
    }

    public static String getLocation() {
        return location;
    }

}
