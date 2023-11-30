package com.example.pts;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class Forgotpassword extends AppCompatActivity {

    Button BtnPwdReset;
    EditText EditTextemail;
    ProgressBar progressBar;
    FirebaseAuth authprofile;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgotpassword);

        BtnPwdReset = findViewById(R.id.forgotbtn);
        progressBar = findViewById(R.id.progressBar);
        EditTextemail = findViewById(R.id.edittext_forgotpassword);

        BtnPwdReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = EditTextemail.getText().toString();

                if(TextUtils.isEmpty(email)){
                    Toast.makeText(Forgotpassword.this,"Please Enter Your Registered Email",Toast.LENGTH_SHORT).show();
                    EditTextemail.setError("Email is required");
                    EditTextemail.requestFocus();
                }else if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                    Toast.makeText(Forgotpassword.this,"Please Enter valid Email",Toast.LENGTH_SHORT).show();
                    EditTextemail.setError("Valid Email is required");
                    EditTextemail.requestFocus();
                } else {
                    progressBar.setVisibility(View.VISIBLE);
                    resetpassword(email);
                }
            }
        });
    }

    private void resetpassword(String email) {
        authprofile = FirebaseAuth.getInstance();
        authprofile.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(Forgotpassword.this,"Please check your inbox for password reset link",Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(Forgotpassword.this,"Something went wrong! ",Toast.LENGTH_SHORT).show();
                }
                progressBar.setVisibility(View.GONE);

            }

        });
    }
}