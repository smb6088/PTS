package com.example.pts;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity implements View.OnClickListener {

    private Button buttonLogin;
    private Button buttonRegister;
    private EditText emailText;
    private EditText passwordText;

    @Override
    public void onClick(View view)
    {
        if(view.getId() == R.id.buttonLogin)
        {
            emailText = findViewById(R.id.editEmail);
            passwordText = findViewById(R.id.editPassword);
            if(emailText.getText().toString().equals("barshan@gmail.com") && passwordText.getText().toString().equals("123"))
            {
                Toast.makeText(this, "Logged In", Toast.LENGTH_SHORT).show();
                //Intent intent = new Intent(getApplicationContext(),HomePageActivity.class);
                //startActivity(intent);
                finish();

            }

        }
        if(view.getId() == R.id.buttonRegister)
        {
            //Intent intent = new Intent(getApplicationContext(),RegistrationActivity.class);
            //startActivity(intent);
            finish();
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.setTitle("Login Page");
        buttonLogin = findViewById(R.id.buttonLogin);
        buttonLogin.setOnClickListener(this);

        //buttonRegister = findViewById(R.id.buttonRegister);
        buttonRegister.setOnClickListener(this);
    }

}
