package com.example.foods;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class RiderDetails extends AppCompatActivity {


    EditText EditTextridername,EditTextriderid,EditTextcontactnumber;
    Button btnadd,btnupdate,btnshow;
    DatabaseReference dbRef;
    RiderInformation rd;

    private void clearControls() {
        EditTextridername.setText("");
        EditTextriderid.setText("");
        EditTextcontactnumber.setText("");

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        EditTextridername = findViewById(R.id.EditTextOrderNo);
        EditTextriderid = findViewById(R.id.EditTextamount);
        EditTextcontactnumber = findViewById(R.id.EditTextaddress);


        btnadd= findViewById(R.id.btnsubmit);
        btnupdate= findViewById(R.id.btnupdate);
        btnshow=findViewById(R.id.btnshow);

        rd = new RiderInformation();

        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbRef = FirebaseDatabase.getInstance().getReference().child("OrderDetails");
                try {
                    if (TextUtils.isEmpty(EditTextridername.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Please enter an OrderNo", Toast.LENGTH_SHORT).show();
                    else if (TextUtils.isEmpty(EditTextriderid.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Please enter an amount", Toast.LENGTH_SHORT).show();
                    else if (TextUtils.isEmpty(EditTextcontactnumber.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Please enter an address", Toast.LENGTH_SHORT).show();
                    else {
                        rd.setRider_name(EditTextridername.getText().toString().trim());
                        rd.setRider_id(EditTextriderid.getText().toString().trim());
                        rd.setRider_contact_number(EditTextcontactnumber.getText().toString().trim());


                        //dbRef.push().setValue(std);
                        dbRef.child("rd1").setValue(rd);

                        Toast.makeText(getApplicationContext(), "Data saved success", Toast.LENGTH_SHORT).show();
                        Intent i=new Intent(new Intent (RiderDetails.this, Activity3.class));
                        startActivity(i);



                        clearControls();
                    }
                } catch (NumberFormatException e) {
                    Toast.makeText(getApplicationContext(), "Data saved unsuccessfull", Toast.LENGTH_SHORT).show();
                }
            }
        });


        btnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference updRef = FirebaseDatabase.getInstance().getReference().child("Student");
                updRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.hasChild("std1")) {
                            try {
                                rd.setRider_name(EditTextridername.getText().toString().trim());
                                rd.setRider_id(EditTextriderid.getText().toString().trim());
                                rd.setRider_contact_number(EditTextcontactnumber.getText().toString().trim());


                                dbRef = FirebaseDatabase.getInstance().getReference().child("Student").child("std1");
                                dbRef.setValue(rd);
                                clearControls();
                                Toast.makeText(getApplicationContext(), "Data Updated Successfully", Toast.LENGTH_SHORT).show();

                            } catch (NumberFormatException e) {
                                Toast.makeText(getApplicationContext(), "Invalid Contact Number", Toast.LENGTH_SHORT).show();
                            }
                        } else
                            Toast.makeText(getApplicationContext(), "No Source to update", Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }

                });
            }
        });
        btnshow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference readRef = FirebaseDatabase.getInstance().getReference().child("Rider").child("rd1");
                readRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        if (dataSnapshot.hasChildren()) {
                            EditTextridername.setText(dataSnapshot.child("rider_name").getValue().toString());
                            EditTextriderid.setText(dataSnapshot.child("rider_id").getValue().toString());
                            EditTextcontactnumber.setText(dataSnapshot.child("rider_contact_number").getValue().toString());

                        } else
                            Toast.makeText(getApplicationContext(), "No Source to display", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }

                });
            }
        });





    }
}
