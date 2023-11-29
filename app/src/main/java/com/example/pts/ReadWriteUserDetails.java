package com.example.pts;


import java.util.ArrayList;

public class ReadWriteUserDetails {
    public String FirstName,LastName,EmailAddress,Phone,Password,PasswordHint;
    public ArrayList<String> prevhiretutors;

    public ReadWriteUserDetails(String FirstName, String LastName, String EmailAddress, String Phone, String Password, String PasswordHint, ArrayList prevhiretutors){
        this.FirstName = FirstName;
        this.LastName = LastName;
        this.EmailAddress = EmailAddress;
        this.Phone = Phone;
        this.Password = Password;
        this.PasswordHint = PasswordHint;
        this.prevhiretutors = prevhiretutors;
    }

    public ReadWriteUserDetails() {
        this.FirstName = FirstName;
        this.LastName = LastName;
        this.EmailAddress = EmailAddress;
        this.Phone = Phone;
        this.Password = Password;
        this.PasswordHint = PasswordHint;
        this.prevhiretutors = prevhiretutors;
    }
}
