package com.example.pts;

public class ReadWriteUserDetails {
    public String FirstName,LastName,EmailAddress,Phone,Password,PasswordHint;


    public ReadWriteUserDetails(String FirstName, String LastName, String EmailAddress, String Phone, String Password, String PasswordHint){
        this.FirstName = FirstName;
        this.LastName = LastName;
        this.EmailAddress = EmailAddress;
        this.Phone = Phone;
        this.Password = Password;
        this.PasswordHint = PasswordHint;
    }

}
