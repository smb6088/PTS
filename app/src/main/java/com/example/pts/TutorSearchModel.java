package com.example.pts;

public class TutorSearchModel
{
    String Name;
    String Rating;
    String Location;


    public TutorSearchModel(String name, String rating, String location)
    {
        Name = name;
        Rating = rating;
        Location = location;
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
}
