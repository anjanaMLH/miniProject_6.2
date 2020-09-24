package com.example.mad_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.mad_project.Models.Products;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;



public class Add_Product_supplier extends AppCompatActivity {

    EditText item_name,old_price,new_price,quantity;
    Button add_item;
    DatabaseReference ref;
    long maxID = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__product_supplier);

        item_name = (EditText) findViewById(R.id.name_item_edit_view);
        old_price = (EditText) findViewById(R.id.price_old_edit_view);
        new_price = (EditText) findViewById(R.id.new_price_edit_view);
        quantity = (EditText) findViewById(R.id.quantity_edit_view);
        final Products product = new Products();

        ref = FirebaseDatabase.getInstance().getReference().child("Products");

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists())
                    maxID = (snapshot.getChildrenCount());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        add_item = findViewById(R.id.add_products);
        add_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                product.setItem_name(item_name.getText().toString().trim());
                product.setOld_price(Double.parseDouble(old_price.getText().toString().trim()));
                product.setNew_price(Double.parseDouble(new_price.getText().toString().trim()));
                product.setQantity(Integer.parseInt(quantity.getText().toString().trim()));


                ref.child(String.valueOf(maxID+1)).setValue(product);










            }
        });



    }
}