package com.example.pts;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Login extends AppCompatActivity {

    Button buttonLogin;
    ProgressBar progressBar;
    FirebaseAuth authProfile;
    Button buttonRegister;
    EditText emailText;
    EditText passwordText;
    static final String TAG = "Login";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        this.setTitle("Login Page");
        emailText = findViewById(R.id.editEmail);
        passwordText = findViewById(R.id.editPassword);
        progressBar = findViewById(R.id.progressBar);
        buttonLogin = findViewById(R.id.buttonLogin);
        buttonRegister = findViewById(R.id.buttonRegister);

        authProfile = FirebaseAuth.getInstance();

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String textemail = emailText.getText().toString();
                String textpassword = passwordText.getText().toString();
                if(TextUtils.isEmpty(textemail)){
                    Toast.makeText(Login.this,"Please enter your email",Toast.LENGTH_SHORT).show();
                    emailText.setError("Email is Required");
                    emailText.requestFocus();
                }else if(!Patterns.EMAIL_ADDRESS.matcher(textemail).matches()){
                    Toast.makeText(Login.this,"Please re-enter your email",Toast.LENGTH_SHORT).show();
                    emailText.setError("Valid Email is Required");
                    emailText.requestFocus();
                }else if(TextUtils.isEmpty(textpassword)){
                    Toast.makeText(Login.this,"Please enter your password",Toast.LENGTH_SHORT).show();
                    passwordText.setError("password is Required");
                    passwordText.requestFocus();
                }else{
                    progressBar.setVisibility(View.VISIBLE);
                    loginUser(textemail,textpassword);
                }
            }
        });

        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this, Registration.class);
                startActivity(intent);
            }
        });
    }

    private void loginUser(String email, String password) {
        authProfile.signInWithEmailAndPassword(email,password).addOnCompleteListener(Login.this,new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    FirebaseUser firebaseUser = authProfile.getCurrentUser();
                    Toast.makeText(Login.this,"You are Logged in",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Login.this, Dashboard.class);
                    startActivity(intent);
                    finish();
                } else {
                    try{
                        throw task.getException();
                    } catch(FirebaseAuthInvalidUserException e){
                        emailText.setError("User does not exists or is no longer valid. please register again");
                        emailText.requestFocus();
                    } catch(FirebaseAuthInvalidCredentialsException e){
                        emailText.setError("Invalid credentials. Kindly, check and re-enter.");
                        emailText.requestFocus();
                    } catch (Exception e)
                    {
                        Log.e(TAG,e.getMessage());
                        Toast.makeText(Login.this,"Something went wrong!",Toast.LENGTH_SHORT).show();
                    }

                }
                progressBar.setVisibility(View.GONE);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(authProfile.getCurrentUser()!=null){
            Toast.makeText(Login.this,"Already Logged in",Toast.LENGTH_SHORT).show();
            startActivity(new Intent(Login.this, Dashboard.class));
            finish();
        }
        else{
            Toast.makeText(Login.this,"you can Login now",Toast.LENGTH_SHORT).show();

        }
    }


}
