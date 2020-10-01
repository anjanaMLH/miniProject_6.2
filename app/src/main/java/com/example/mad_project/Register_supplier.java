package com.example.mad_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import com.example.mad_project.Models.Supplier;

public class Register_supplier extends AppCompatActivity {

    EditText full_name,address,e_mail,password;
    String full_name_valid,address_valid,e_mail_valid,password_valid;
    Button button_reg;
    DatabaseReference ref;
    Supplier supplier;
    long maxID = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_supplier);

        full_name = (EditText) findViewById(R.id.name_edit_view);
        address = (EditText) findViewById(R.id.address_edit_view);
        e_mail = (EditText) findViewById(R.id.email_edit_view);
        password = (EditText) findViewById(R.id.password_edit_view);

        supplier = new Supplier();
        ref = FirebaseDatabase.getInstance().getReference().child("Supplier");

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



        button_reg = findViewById(R.id.register);
        button_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                validatSupplier();










            }
        });





    }

    private void validatSupplier() {

            full_name_valid =  full_name.getText().toString();
            address_valid = address.getText().toString();
            e_mail_valid = e_mail.getText().toString();
            password_valid = password.getText().toString();


            if(TextUtils.isEmpty(full_name_valid)){
                Toast.makeText(this, "full name shouldn't be empty", Toast.LENGTH_SHORT).show();
            }
            else if(TextUtils.isEmpty(address_valid)){
                Toast.makeText(this, "Address field shouldn't be empty", Toast.LENGTH_SHORT).show();
            }
            else if(TextUtils.isEmpty(e_mail_valid)){
                Toast.makeText(this, "Email field shouldn't be empty", Toast.LENGTH_SHORT).show();
            }
            else if(TextUtils.isEmpty(password_valid)){
                Toast.makeText(this, "Address field shouldn't be empty", Toast.LENGTH_SHORT).show();
            }
            else{
                saveInformations();
            }

    }

    private void saveInformations() {
        supplier.setFull_name(full_name.getText().toString().trim());
        supplier.setAddress(address.getText().toString().trim());
        supplier.setE_mail(e_mail.getText().toString().trim());
        supplier.setPassword(password.getText().toString().trim());


        ref.child(String.valueOf(maxID+1)).setValue(supplier);

        Intent intent = new Intent(Register_supplier.this, Supplier_page.class);
        startActivity(intent);
    }


}