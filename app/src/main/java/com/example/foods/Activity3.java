package com.example.foods;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
//Git commit

public class Activity3 extends AppCompatActivity {

    EditText EditTextcustomername,EditTextlocation,EditTextnumber;
    Button btnsubmit;
    DatabaseReference dbRef;
    CustomerDetails cd;

    private void clearControls() {
        EditTextcustomername.setText("");
        EditTextlocation.setText("");
        EditTextnumber.setText("");

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        EditTextcustomername = findViewById(R.id.EditTextOrderNo);
        EditTextlocation = findViewById(R.id.EditTextamount);
        EditTextnumber = findViewById(R.id.EditTextaddress);


        btnsubmit= findViewById(R.id.btnsubmit);

        cd = new CustomerDetails();

        btnsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbRef = FirebaseDatabase.getInstance().getReference().child("OrderDetails");
                try {
                    if (TextUtils.isEmpty(EditTextcustomername.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Please enter customer name", Toast.LENGTH_SHORT).show();
                    else if (TextUtils.isEmpty(EditTextlocation.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Please enter customer location", Toast.LENGTH_SHORT).show();
                    else if (TextUtils.isEmpty(EditTextnumber.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Please enter customer contact number", Toast.LENGTH_SHORT).show();
                    else {
                        cd.setName(EditTextcustomername.getText().toString().trim());
                        cd.setLocation(EditTextlocation.getText().toString().trim());
                        cd.setContact_number(EditTextnumber.getText().toString().trim());


                        //dbRef.push().setValue(std);
                        dbRef.child("od1").setValue(cd);

                        Toast.makeText(getApplicationContext(), "Data saved success", Toast.LENGTH_SHORT).show();
                        Intent i=new Intent(new Intent (Activity3.this, RiderDetails.class));
                        startActivity(i);



                        clearControls();
                    }
                } catch (NumberFormatException e) {
                    Toast.makeText(getApplicationContext(), "Data saved unsuccessfull", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}