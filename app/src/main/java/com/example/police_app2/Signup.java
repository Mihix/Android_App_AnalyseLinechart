package com.example.police_app2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Signup extends AppCompatActivity {
    private FirebaseAuth auth;
    private EditText SignUp_password, Email_username;
    private Button SignUp_button;
    private TextView loginRedirectText;
    //mata mekaa denna


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        auth = FirebaseAuth.getInstance();
        Email_username = findViewById(R.id.Email_username);
        SignUp_password =findViewById(R.id.SignUp_password);
        SignUp_button = findViewById(R.id.SignUp_button);
        loginRedirectText = findViewById(R.id.signupRedirectText);

        SignUp_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = Email_username.getText().toString().trim();
                String pass = SignUp_password.getText().toString().trim();

                if (user.isEmpty()){
                    Email_username.setError("Email cannot be empty");
                }
                if (pass.isEmpty()){
                    SignUp_password.setError("Password cannot be empty");
                }else {
                    auth.createUserWithEmailAndPassword(user,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(Signup.this, "SignUp Successfull", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(Signup.this,LoginActivity.class));
                            }else {
                                Toast.makeText(Signup.this, "SignUp Failed" +task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                }
            }

        });

        loginRedirectText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Signup.this,LoginActivity.class));
            }
        });
    }
}