package com.example.pts;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class Registration extends AppCompatActivity {
    private EditText editTextFirstName;
    private EditText editTextLastName;
    private EditText editTextEmailAddress;
    private EditText editTextPhone;
    private EditText editTextPassword;
    private EditText editTextConfirmPassword;
    private EditText editTextPasswordHint;
    private Button buttonCompleteRegistration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        editTextFirstName = findViewById(R.id.editTextFirstName);
        editTextLastName = findViewById(R.id.editTextLastName);
        editTextEmailAddress = findViewById(R.id.editTextEmailAddress);
        editTextPhone = findViewById(R.id.editTextPhone);
        editTextPassword = findViewById(R.id.editTextPassword);
        editTextConfirmPassword = findViewById(R.id.editTextConfirmPassword);
        editTextPasswordHint = findViewById(R.id.editTextPasswordHint);

        String FirstName = editTextFirstName.getText().toString();
        String LastName = editTextLastName.getText().toString();
        String EmailAddress = editTextEmailAddress.getText().toString();
        String Phone = editTextPhone.getText().toString();
        String Password = editTextPassword.getText().toString();
        String ConfirmPassword = editTextConfirmPassword.getText().toString();
        String PasswordHint = editTextPasswordHint.getText().toString();


        System.out.println(FirstName);
        System.out.println(LastName);
        System.out.println(EmailAddress);
        System.out.println(Phone);
        System.out.println(Password);
        System.out.println(ConfirmPassword);
        System.out.println(PasswordHint);

        buttonCompleteRegistration = findViewById(R.id.buttonCompleteRegistration);
    }
}
