package com.example.teamcapstonercr.usersinfo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.teamcapstonercr.HomeScreen;
import com.example.teamcapstonercr.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Register extends AppCompatActivity {
EditText rFullname,rEmail,rPassword;
Button rRegister;
TextView rlinktologin;
FirebaseAuth mAuth;
ProgressBar rProgressbar;
FirebaseFirestore mStore;
String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        rFullname = findViewById(R.id.RName);
        rEmail = findViewById(R.id.REmailId);
        rPassword = findViewById(R.id.RPassword);
        rRegister = findViewById(R.id.Rbtn);
        rlinktologin = findViewById(R.id.rlinktologin);
        rProgressbar = findViewById(R.id.rprogressbar);

        mAuth = FirebaseAuth.getInstance();
        mStore = FirebaseFirestore.getInstance();

        //already logged in
        if (mAuth.getCurrentUser() != null)
        {
            startActivity(new Intent(getApplicationContext(),HomeScreen.class));
            finish();
        }


        //Function of register button
        rRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              final String email = rEmail.getText().toString().trim();
              String password = rPassword.getText().toString().trim();
              final String fullname = rFullname.getText().toString();


              //Fields should not be empty
              if (TextUtils.isEmpty(email))
              {
                  rEmail.setError("Email is Required");
                  return;
              }
              if (TextUtils.isEmpty(password))
              {
                  rPassword.setError("Password is Required");
              }

              //condition of password
              if (password.length() < 6)
              {
                  rPassword.setError("Password must be greater or equals to 6 characters");
              }

              //display of spinner to show registeration is started
                rProgressbar.setVisibility(View.VISIBLE);

              //Register the user in firebase

                mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                       if (task.isSuccessful())
                       {
                           //send verification link
                           FirebaseUser fuser = mAuth.getCurrentUser();
                           fuser.sendEmailVerification().addOnSuccessListener(new OnSuccessListener<Void>() {
                               @Override
                               public void onSuccess(Void aVoid) {
                                   Toast.makeText(Register.this, "Verification Email Has Been Sent", Toast.LENGTH_SHORT).show();
                               }
                           }).addOnFailureListener(new OnFailureListener() {
                               @Override
                               public void onFailure(@NonNull Exception e) {
                                   Log.d("TAG","onFailure: " + e.getMessage());
                               }
                           });

                           Toast.makeText(Register.this, "User Created", Toast.LENGTH_SHORT).show();
                           userID = mAuth.getCurrentUser().getUid();
                           DocumentReference documentReference = mStore.collection("users").document(userID);
                           Map<String,Object> user = new HashMap<>();
                           user.put("user_fullname",fullname);
                           user.put("user_email",email);
                           documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                               @Override
                               public void onSuccess(Void aVoid) {
                                   Log.d("TAG","onSuccess: User Profile is created for :" + userID);
                               }
                           }).addOnFailureListener(new OnFailureListener() {
                               @Override
                               public void onFailure(@NonNull Exception e) {
                                   Log.d("TAG","onFailure: " + e.toString());
                               }
                           });
                           startActivity(new Intent(getApplicationContext(),HomeScreen.class));
                       }
                       else
                       {
                           Toast.makeText(Register.this, "Error ! " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                           rProgressbar.setVisibility(View.GONE);
                       }
                    }
                });
            }
        });

        rlinktologin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Login.class));
            }
        });
    }

}
