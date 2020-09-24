package com.example.mad_project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Udate_food_item_supplier extends AppCompatActivity {

    EditText pName,oldPrice,newPrice,quantity;
    DatabaseReference reference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_udate_food_item_supplier);

        pName = (EditText) findViewById(R.id.name_item_edit_view);
        oldPrice = (EditText) findViewById(R.id.price_old_edit_view);
        newPrice = (EditText) findViewById(R.id.new_price_edit_view);
        quantity = (EditText) findViewById(R.id.quantity_edit_view);
        String key = getIntent().getStringExtra("name").toString();
        reference = FirebaseDatabase.getInstance().getReference().child(key);

        pName.setText(getIntent().getStringExtra("name"));
        oldPrice.setText(getIntent().getStringExtra("old_price"));
        newPrice.setText(getIntent().getStringExtra("new_price"));
        quantity.setText(getIntent().getStringExtra("quantity"));

    }

    public void btnUpdate(View view){

    }
}