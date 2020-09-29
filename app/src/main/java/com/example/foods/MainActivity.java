package com.example.foods;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {


    EditText EditTextOrderNo,EditTextamount,EditTextaddress;
    Button btnsubmit;
    DatabaseReference dbRef;
    OrderDetails od;

    private void clearControls() {
        EditTextOrderNo.setText("");
        EditTextamount.setText("");
        EditTextaddress.setText("");

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        EditTextOrderNo = findViewById(R.id.EditTextOrderNo);
        EditTextamount = findViewById(R.id.EditTextamount);
        EditTextaddress = findViewById(R.id.EditTextaddress);


        btnsubmit= findViewById(R.id.btnsubmit);

        od = new OrderDetails();

        btnsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbRef = FirebaseDatabase.getInstance().getReference().child("OrderDetails");
                try {
                    if (TextUtils.isEmpty(EditTextOrderNo.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Please enter an OrderNo", Toast.LENGTH_SHORT).show();
                    else if (TextUtils.isEmpty(EditTextamount.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Please enter an amount", Toast.LENGTH_SHORT).show();
                    else if (TextUtils.isEmpty(EditTextaddress.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Please enter an address", Toast.LENGTH_SHORT).show();
                    else {
                        od.setOrderNo(EditTextOrderNo.getText().toString().trim());
                        od.setAmount(EditTextamount.getText().toString().trim());
                        od.setAddress(EditTextaddress.getText().toString().trim());


                        //dbRef.push().setValue(std);
                        dbRef.child("od1").setValue(od);

                        Toast.makeText(getApplicationContext(), "Data saved success", Toast.LENGTH_SHORT).show();
                        Intent i=new Intent(new Intent (MainActivity.this, Activity3.class));
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