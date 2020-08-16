package com.example.mad_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Supplier_page extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supplier_page);


        View view = findViewById(R.id.delivery);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Supplier_page.this, Add_Product_supplier.class);
                startActivity(intent);
            }
        });

        Button button = findViewById(R.id.button_update);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Supplier_page.this, Udate_food_item_supplier.class);
                startActivity(intent);
            }
        });



    }
}