package com.example.police_app2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    Button btnCHart,btnView;//variable
    EditText name,LicenseNumber, Details,Money;//variable
    DatabaseReference databaseUsers;//variable

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //getSupportActionBar().hide();//this line hides the action bar


        setContentView(R.layout.activity_main);
        btnCHart =  findViewById(R.id.btnChart);
        btnView = findViewById(R.id.btnView);
        databaseUsers = FirebaseDatabase.getInstance().getReference();



        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,userlist.class));
                finish();
            }
        });
        btnCHart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,BarchartActivity.class));
            }
        });

    }

    private void InsertData() {
        String username = name.getText().toString();
        String userLicenseNumber = LicenseNumber.getText().toString();
        String userDetails = Details.getText().toString();
        String userMoney = Money.getText().toString();
        String id = databaseUsers.push().getKey();

        User user = new User(username,userLicenseNumber,userDetails,userMoney);
        databaseUsers.child("users").child(id).setValue(user)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(MainActivity.this, "User Details Inserted", Toast.LENGTH_SHORT).show();
                        }
                    }
                });


    }
}