package com.example.mad_project.customer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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

import java.util.List;
import java.util.Map;

public class CustomerLogin extends AppCompatActivity {

    EditText editTexEmail;
    EditText editTextPassword;

    Button customerLoginButton;

    DatabaseReference customerLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_login);

        editTexEmail = findViewById(R.id.customer_email_login);
        editTextPassword = findViewById(R.id.customer_password_login);

        customerLoginButton = findViewById(R.id.customer_login_button);

        customerLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                customerLogin = FirebaseDatabase.getInstance().getReference("/customers/");
                customerLogin.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
//                        Map<String, Map<String, Customer>> customers = (Map<String, Map<String, Customer>>) snapshot.getValue();
                        String stringEmail = editTexEmail.getText().toString().trim();
                        String stringPassword = editTextPassword.getText().toString();
                        boolean auth = true;
                        for (DataSnapshot ds : snapshot.getChildren()){
                            Customer customer = ds.getValue(Customer.class);
                            Log.d("The ds HERER",customer.getEmail()+"   "+customer.getPassword());
                            if(customer.getEmail().equals(stringEmail) && customer.getPassword().equals(stringPassword)){
                                Toast.makeText(CustomerLogin.this, "You are authenticated", Toast.LENGTH_LONG).show();
                                auth = false;
                                Intent intent = new Intent(CustomerLogin.this, CustomerOrders.class);
                                intent.putExtra("CUSTOMER_EMAIL", stringEmail);
                                startActivity(intent);

                            }
                        }
                        if (auth){
                            Toast.makeText(CustomerLogin.this, "Sorry email or password is wrong", Toast.LENGTH_LONG).show();
                        }

//                        Log.d("THIS IS THE OUTPUT", customers.toString());
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Log.d("THIS IS THE OUTPUT", "SorryErrorError");
                    }
                });

            }
        });
    }
}