package com.dataflair.vehiclehelp.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.dataflair.vehiclehelp.MainActivity;
import com.dataflair.vehiclehelp.R;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.annotations.NotNull;

public class RoleActivity extends AppCompatActivity {

    Button userBtn, adminBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_role);
        String userId = GoogleSignIn.getLastSignedInAccount(getApplicationContext()).getId();

        userBtn = (Button) findViewById(R.id.UserBtn);
        adminBtn = (Button) findViewById(R.id.AdminBtn);

        //Implementing OnClickListener to select role as User
        userBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Storing user role to database
                FirebaseDatabase.getInstance().getReference().child("users")
                        .child(userId)
                        .child("role")
                        .setValue("user").addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull @NotNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            //Navigating to MainActivity
                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    }
                });

            }
        });

        //Implementing OnClickListener to select role as Admin
        adminBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Storing user role to database
                FirebaseDatabase.getInstance().getReference().child("users")
                        .child(userId)
                        .child("role")
                        .setValue("admin").addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull @NotNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            //Navigating to AdminActivity
                            Intent intent = new Intent(getApplicationContext(), AdminActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    }
                });

            }
        });
    }
}