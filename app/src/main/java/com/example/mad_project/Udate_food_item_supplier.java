package com.example.mad_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Udate_food_item_supplier extends AppCompatActivity {

    EditText pName,oldPrice,newPrice,quantity;
    DatabaseReference reference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_udate_food_item_supplier);

        pName = (EditText) findViewById(R.id.name_item_edit_view_update);
        oldPrice = (EditText) findViewById(R.id.price_old_edit_view_update);
        newPrice = (EditText) findViewById(R.id.new_price_edit_view_update);
        quantity = (EditText) findViewById(R.id.quantity_edit_view_update);

        String key = getIntent().getStringExtra("name").toString();

        reference = FirebaseDatabase.getInstance().getReference().child("Product").child(key);





        pName.setText(getIntent().getStringExtra("name"));
        oldPrice.setText(getIntent().getStringExtra("old_price"));
        newPrice.setText(getIntent().getStringExtra("new_price"));
        quantity.setText(getIntent().getStringExtra("quantity"));

    }

    public void btnUpdate(View view){

        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                snapshot.getRef().child("item_name").setValue(pName.getText().toString());
                snapshot.getRef().child("new_price").setValue(newPrice.getText().toString());
                snapshot.getRef().child("old_price").setValue(oldPrice.getText().toString());
                snapshot.getRef().child("quantity").setValue(quantity.getText().toString());
                Udate_food_item_supplier.this.finish();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}