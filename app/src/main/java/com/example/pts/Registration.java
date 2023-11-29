package com.example.pts;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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
import com.google.firebase.Firebase;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class Registration extends AppCompatActivity {
    EditText editTextFirstName;
    EditText editTextLastName;
    EditText editTextEmailAddress;
    EditText editTextPhone;
    EditText editTextPassword;
    EditText editTextConfirmPassword;
    EditText editTextPasswordHint;
    Button buttonCompleteRegistration;
    FirebaseDatabase database;
    ProgressBar progressBar;
    DatabaseReference reference;



    static final String TAG = "Registration";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        progressBar = findViewById(R.id.progressBar);
        editTextFirstName = findViewById(R.id.editTextFirstName);
        editTextLastName = findViewById(R.id.editTextLastName);
        editTextEmailAddress = findViewById(R.id.editTextEmailAddress);
        editTextPhone = findViewById(R.id.editTextPhone);
        editTextPassword = findViewById(R.id.editTextPassword);
        editTextConfirmPassword = findViewById(R.id.editTextConfirmPassword);
        editTextPasswordHint = findViewById(R.id.editTextPasswordHint);
        buttonCompleteRegistration = findViewById(R.id.buttonCompleteRegistration);


        buttonCompleteRegistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                database = FirebaseDatabase.getInstance();
                reference = database.getReference("users");
                String FirstName = editTextFirstName.getText().toString();
                String LastName = editTextLastName.getText().toString();
                String EmailAddress = editTextEmailAddress.getText().toString();
                String Phone = editTextPhone.getText().toString();
                String Password = editTextPassword.getText().toString();
                ArrayList<String> prevtutor = new ArrayList<>();
                String ConfirmPassword = editTextConfirmPassword.getText().toString();
                String PasswordHint = editTextPasswordHint.getText().toString();

                if(TextUtils.isEmpty(FirstName)){
                    Toast.makeText(Registration.this,"Please enter your First Name",Toast.LENGTH_LONG).show();
                    editTextFirstName.setError("First Name is required");
                    editTextFirstName.requestFocus();
                }else if(TextUtils.isEmpty(LastName)){
                    Toast.makeText(Registration.this,"Please enter your Last Name",Toast.LENGTH_LONG).show();
                    editTextLastName.setError("First Last is required");
                    editTextLastName.requestFocus();
                }else if(TextUtils.isEmpty(EmailAddress)){
                    Toast.makeText(Registration.this,"Please enter your EmailAddress",Toast.LENGTH_LONG).show();
                    editTextEmailAddress.setError("EmailAddress is required");
                    editTextEmailAddress.requestFocus();
                }else if(!Patterns.EMAIL_ADDRESS.matcher(EmailAddress).matches()){
                    Toast.makeText(Registration.this,"Please re-enter your EmailAddress",Toast.LENGTH_LONG).show();
                    editTextEmailAddress.setError("Valid EmailAddress is required");
                    editTextEmailAddress.requestFocus();
                } else if(TextUtils.isEmpty(Phone)){
                    Toast.makeText(Registration.this,"Please enter your Phone No.",Toast.LENGTH_LONG).show();
                    editTextPhone.setError("Phone No. is required");
                    editTextPhone.requestFocus();
                } else if(Phone.length()!=10){
                    Toast.makeText(Registration.this,"Please re-enter your Phone No.",Toast.LENGTH_LONG).show();
                    editTextPhone.setError("10 digit Phone No. is required");
                    editTextPhone.requestFocus();
                }else if(TextUtils.isEmpty(Password)){
                    Toast.makeText(Registration.this,"Please enter your Password",Toast.LENGTH_LONG).show();
                    editTextPassword.setError("Password is required");
                    editTextPassword.requestFocus();
                }else if(Password.length() < 6){
                    Toast.makeText(Registration.this,"Password should be at least 6 digits",Toast.LENGTH_LONG).show();
                    editTextPassword.setError("Password too weak");
                    editTextPassword.requestFocus();
                }else if(TextUtils.isEmpty(ConfirmPassword)){
                    Toast.makeText(Registration.this,"Please confirm your Password",Toast.LENGTH_LONG).show();
                    editTextConfirmPassword.setError("Password Confirmation is required");
                    editTextConfirmPassword.requestFocus();
                }else if(!Password.equals(ConfirmPassword)){
                    Toast.makeText(Registration.this,"Please enter the same password",Toast.LENGTH_LONG).show();
                    editTextConfirmPassword.setError("Password Confirmation is required");
                    editTextConfirmPassword.requestFocus();
                    editTextPassword.clearComposingText();
                    editTextConfirmPassword.clearComposingText();
                }else if(TextUtils.isEmpty(PasswordHint)){
                    Toast.makeText(Registration.this,"Please enter your Password Hint",Toast.LENGTH_LONG).show();
                    editTextFirstName.setError("Password Hint is required");
                    editTextFirstName.requestFocus();
                }else{
                    progressBar.setVisibility(View.VISIBLE);
                    registeruser(FirstName,LastName,EmailAddress,Phone,Password,ConfirmPassword,PasswordHint,prevtutor);
                }


            }
        });
    }
    private void registeruser(String FirstName,String LastName,String EmailAddress,String Phone,String Password,String ConfirmPassword,String PasswordHint, ArrayList prevtutor){
        FirebaseAuth auth = FirebaseAuth.getInstance();
        auth.createUserWithEmailAndPassword(EmailAddress,Password).addOnCompleteListener(Registration.this,
                new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){

                            FirebaseUser firebaseUser = auth.getCurrentUser();
                            //UserProfileChangeRequest profileChangeRequest = new UserProfileChangeRequest.Builder().setDisplayName(FirstName).build();
                            //firebaseUser.updateProfile(profileChangeRequest);
                            ReadWriteUserDetails writeUserDetails = new ReadWriteUserDetails(FirstName,LastName,EmailAddress,Phone,Password,PasswordHint,prevtutor);
                            DatabaseReference referenceProfile = FirebaseDatabase.getInstance().getReference("Registered Users");
                            referenceProfile.child(firebaseUser.getUid()).setValue(prevtutor);
                            referenceProfile.child(firebaseUser.getUid()).setValue(writeUserDetails).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()){
                                        Toast.makeText(Registration.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(Registration.this, Dashboard.class);
                                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK
                                                | Intent.FLAG_ACTIVITY_NEW_TASK);
                                        intent.putExtra("name", FirstName);
                                        startActivity(intent);
                                        finish();
                                    } else {
                                        Toast.makeText(Registration.this, "Registration Failed", Toast.LENGTH_SHORT).show();

                                    }
                                    progressBar.setVisibility(View.GONE);
                                }

                            });
                        }else{
                            try {
                                throw task.getException();
                            }catch (FirebaseAuthWeakPasswordException e){
                                editTextPassword.setError("your password is too weak. kindly use a mix of alphabets,number and special character");
                                editTextPassword.requestFocus();
                            }catch (FirebaseAuthInvalidCredentialsException e){
                                editTextPassword.setError("your email is invalid or already in use");
                                editTextPassword.requestFocus();
                            } catch (FirebaseAuthUserCollisionException e){
                                editTextPassword.setError("User is already registered with this email.");
                                editTextPassword.requestFocus();
                            } catch (Exception e){
                                Log.e(TAG,e.getMessage());
                                Toast.makeText(Registration.this,e.getMessage(), Toast.LENGTH_SHORT).show();

                            }
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                });
    }
}
