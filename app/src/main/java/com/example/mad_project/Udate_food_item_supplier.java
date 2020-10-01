package com.example.mad_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class Udate_food_item_supplier extends AppCompatActivity {

    EditText pName,oldPrice,newPrice,quantity;
    DatabaseReference reference;
    ImageView image;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_udate_food_item_supplier);

        pName = (EditText) findViewById(R.id.name_item_edit_view_update);
        oldPrice = (EditText) findViewById(R.id.price_old_edit_view_update);
        newPrice = (EditText) findViewById(R.id.new_price_edit_view_update);
        quantity = (EditText) findViewById(R.id.quantity_edit_view_update);
        image = (ImageView) findViewById(R.id.image_update);







        pName.setText(getIntent().getStringExtra("name"));
        oldPrice.setText(getIntent().getStringExtra("old_price"));
        newPrice.setText(getIntent().getStringExtra("new_price"));
        quantity.setText(getIntent().getStringExtra("quantity"));
        Picasso
                .get()
                .load(getIntent().getStringExtra("image"))
                .noFade()
                .into(image);

        String key = getIntent().getStringExtra("key").toString();

        reference = FirebaseDatabase.getInstance().getReference().child("Products").child(key);


        Button button_up = (Button) findViewById(R.id.update);
        button_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                reference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        snapshot.getRef().child("item_name").setValue(pName.getText().toString());
                        snapshot.getRef().child("new_price").setValue(Double.parseDouble(newPrice.getText().toString()));
                        snapshot.getRef().child("old_price").setValue(Double.parseDouble(oldPrice.getText().toString()));
                        snapshot.getRef().child("quantity").setValue(Integer.parseInt(quantity.getText().toString()));


                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });



                Intent intent = new Intent(Udate_food_item_supplier.this, Supplier_page.class);
                startActivity(intent);

            }
        });


        Button button_del = (Button) findViewById(R.id.delete);
        button_del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reference.removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(Udate_food_item_supplier.this, "the item deleted successfully", Toast.LENGTH_SHORT).show();
                            Udate_food_item_supplier.this.finish();

                        }
                        else{
                            Toast.makeText(Udate_food_item_supplier.this, "delete unsuccessful", Toast.LENGTH_SHORT).show();

                        }
                    }
                });
            }
        });


    }





}