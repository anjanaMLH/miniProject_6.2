package com.example.mad_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.mad_project.customer.CustomerHome;

public class MainActivity_supplier extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_supplier);

        Button button = findViewById(R.id.button_main);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity_supplier.this, Register_supplier.class);
                startActivity(intent);
            }
        });

        Button button2 = findViewById(R.id.button_login);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity_supplier.this,Login_Supplier.class);
                startActivity(intent);
            }
        });

        View view = findViewById(R.id.profile);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity_supplier.this,payment_one.class);
                startActivity(intent);
            }
        });

        //This is the customer part;

        View customerButtom = findViewById(R.id.home);
        customerButtom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent (MainActivity_supplier.this, CustomerHome.class);
                startActivity(intent);
            }
        });

    }
}