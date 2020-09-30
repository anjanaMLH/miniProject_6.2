package com.example.mad_project.customer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mad_project.Models.Customer;
import com.example.mad_project.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class CustomerRegistration extends AppCompatActivity {

    private EditText editTextFirstName;
    private EditText editTextAddress;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private EditText editTextRePassword;

    private Button  registration;

    private DatabaseReference customerDatabase;


    private long index;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_registration);

        editTextFirstName = findViewById(R.id.customer_name_edit_view);
        editTextAddress = findViewById(R.id.customer_address_edit_view);
        editTextEmail = findViewById(R.id.customer_email_edit_view);
        editTextPassword = findViewById(R.id.customer_password_edit_view);
        editTextRePassword = findViewById(R.id.customer_new_password_edit_view);

        customerDatabase = FirebaseDatabase.getInstance().getReference();

        registration = findViewById(R.id.customer_register);

        registration.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {


                String stringFirstName = editTextFirstName.getText().toString();
                String stringAddress = editTextAddress.getText().toString();
                String stringEmail = editTextEmail.getText().toString();
                String stringPassword = editTextPassword.getText().toString();
                String stringRePassword = editTextRePassword.getText().toString();



                if(!stringPassword.equals(stringRePassword)){
                    Toast.makeText(CustomerRegistration.this, "Sorry two password fields are not matching", Toast.LENGTH_LONG).show();
                }
                else if(stringFirstName.equals("") || stringAddress.equals("") || stringEmail.equals("")){
                    Toast.makeText(CustomerRegistration.this, "Sorry all the fields are required", Toast.LENGTH_LONG).show();
                }
                else{
                    //Make the firebase registration

//                    customerDatabase.addValueEventListener(new ValueEventListener() {
//                        @Override
//                        public void onDataChange(@NonNull DataSnapshot snapshot) {
//                            if(snapshot.exists()){
//                                index = (snapshot.getChildrenCount());
//                            }
//                        }
//
//                        @Override
//                        public void onCancelled(@NonNull DatabaseError error) {
//
//                        }
//                    });
                    Customer customer = new Customer(stringFirstName, stringAddress, stringEmail, stringPassword);
                    customerDatabase.child("customers").child(1+"").setValue(customer);
                    Intent intent = new Intent(CustomerRegistration.this, CustomerLogin.class);
                    startActivity(intent);
                }
            }
        });

    }
}