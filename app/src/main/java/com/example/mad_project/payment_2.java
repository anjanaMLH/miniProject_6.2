package com.example.mad_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mad_project.Models.payment;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class payment_2 extends AppCompatActivity {

    EditText name,cardnumber,cvcnumber,expiration;
    DatabaseReference dbref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_2);

        name = findViewById(R.id.name_edit_view);
        cardnumber = findViewById(R.id.address_edit_view);
        cvcnumber = findViewById(R.id.cvc_edit_view);
        expiration = findViewById(R.id.expiration_view);

        final payment paymentmodel = new payment();

        Button  register = (Button) findViewById(R.id.register);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dbref = FirebaseDatabase.getInstance().getReference().child("payment");
                try {
                    paymentmodel.setName(name.getText().toString().trim());
                    paymentmodel.setCardnumber(cardnumber.getText().toString().trim());
                    paymentmodel.setCvcnumber(cvcnumber.getText().toString().trim());
                    paymentmodel.setExpiration(expiration.getText().toString().trim());

                    dbref.push().setValue(paymentmodel);

                    dbref.child("lastPaymentData").setValue(paymentmodel);

                    Toast.makeText(getApplicationContext(),"Data saved succesfully",Toast.LENGTH_SHORT).show();
                }catch (NumberFormatException e){

                        }
                Intent intent = new Intent(payment_2.this,payment_view.class);
                startActivity(intent);
                    }

            });




    }
}