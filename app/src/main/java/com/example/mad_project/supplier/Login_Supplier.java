package com.example.mad_project.supplier;

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
import com.example.mad_project.Models.Supplier;
import com.example.mad_project.R;
import com.example.mad_project.customer.CustomerLogin;
import com.example.mad_project.customer.CustomerOrders;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Login_Supplier extends AppCompatActivity {

    EditText editTexEmail;
    EditText editTextPassword;

    Button SupplierLoginButton;

    DatabaseReference SupplierLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login__supplier);

        editTexEmail = findViewById(R.id.email_edit_view_login);
        editTextPassword = findViewById(R.id.password_edit_view_login);

        SupplierLoginButton = findViewById(R.id.login);

        SupplierLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SupplierLogin = FirebaseDatabase.getInstance().getReference().child("Supplier");
                SupplierLogin.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
//                        Map<String, Map<String, Supplier>> Supplier = (Map<String, Map<String, Supplier>>) snapshot.getValue();
                        String stringEmail = editTexEmail.getText().toString().trim();
                        String stringPassword = editTextPassword.getText().toString();
                        boolean auth = true;
                        for (DataSnapshot ds : snapshot.getChildren()){
                            Supplier supplier = ds.getValue(Supplier.class);
                            Log.d("The ds HERER",supplier.getE_mail()+"   "+supplier.getPassword());
                            if(supplier.getE_mail().equals(stringEmail) && supplier.getPassword().equals(stringPassword)){
                                Toast.makeText(Login_Supplier.this, "You are authenticated", Toast.LENGTH_LONG).show();
                                auth = false;
                                Intent intent = new Intent(Login_Supplier.this, Supplier_page.class);
                                intent.putExtra("supplier_email", stringEmail);
                                startActivity(intent);

                            }
                        }
                        if (auth){
                            Toast.makeText(Login_Supplier.this, "Sorry email or password is wrong", Toast.LENGTH_LONG).show();
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