package com.example.mad_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;


public class MainActivity_payment extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_payment);


        View view = findViewById(R.id.add_cart);
        view.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity_payment.this,payment_one.class);
                startActivity(intent);
            }

        });


        ImageView Iview =(ImageView) findViewById(R.id.supplier);
        Iview.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity_payment.this,MainActivity_supplier.class);
                startActivity(intent);
            }

        });





    }
}