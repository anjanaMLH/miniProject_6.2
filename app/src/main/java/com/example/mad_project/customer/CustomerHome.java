package com.example.mad_project.customer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.mad_project.R;
import com.example.mad_project.payment_one;

public class CustomerHome extends AppCompatActivity {

    Button loginButton;
    Button registerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_home);

        loginButton = findViewById(R.id.customer_login);
        registerButton = findViewById(R.id.customer_registration);

        loginButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CustomerHome.this, CustomerLogin.class);
                startActivity(intent);
            }
        });

        registerButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CustomerHome.this, CustomerRegistration.class);
                startActivity(intent);
            }
        });

        View view = findViewById(R.id.profile);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CustomerHome.this, payment_one.class);
                startActivity(intent);
            }
        });


    }
}