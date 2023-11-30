package com.example.pts;

import java.util.ArrayList;

public class ReadWriteUserDetails {
    public String FirstName,LastName,EmailAddress,Phone,Password,PasswordHint;
    public boolean isATutor;
    public int Rating;
    public String Location;
    //public ArrayList<String> prevHiredTutors;
    public String prevHiredTutors;
    public String teachingCategories;
    public String teachingStudents;
    public String reviews;


    public ReadWriteUserDetails(String FirstName, String LastName, String EmailAddress, String Phone, String Password, String PasswordHint){
        this.FirstName = FirstName;
        this.LastName = LastName;
        this.EmailAddress = EmailAddress;
        this.Phone = Phone;
        this.Password = Password;
        this.PasswordHint = PasswordHint;
        isATutor = false;
        Rating = 0;
        //prevHiredTutors = new ArrayList<String>();
        prevHiredTutors = "";
        Location = null;
        teachingCategories = "";
        teachingStudents ="";
        reviews = "";
    }

}
