package com.example.pts;

public class tutorsearch_model {


    String Name;
    String Rating;
    String Location;
    String Bio;


    public tutorsearch_model(String bio,String name, String rating, String location) {
        Name = name;
        Rating = rating;
        Location = location;
        Bio = bio;
    }

    public String getName() {
        return Name;
    }

    public String getRating() {
        return Rating;
    }

    public String getLocation() {
        return Location;
    }

    public String getBio()
    {
        return Bio;
    }
}
