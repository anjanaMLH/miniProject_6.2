package com.example.mad_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mad_project.Models.payment;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class payment_view extends AppCompatActivity {

    Button updatebtn;
    Button cancelbtn;
    Button proceedbtn;
    EditText name,cardnumber,cvcnumber,expiration;
    DatabaseReference dbref;
    String lastKey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_view);

        name = findViewById(R.id.name_edit_viewv);
        cardnumber = findViewById(R.id.address_edit_viewv);
        cvcnumber = findViewById(R.id.cvc_edit_viewv);
        expiration = findViewById(R.id.expiration_viewv);

        final payment paymentmodel = new payment();
        updatebtn = (Button) findViewById(R.id.pymntubtn);
        cancelbtn = (Button) findViewById(R.id.pymntcbtn);
        proceedbtn = (Button) findViewById(R.id.pymntpbtn);

        dbref = FirebaseDatabase.getInstance().getReference().child("payment").child("lastPaymentData");
        dbref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.hasChildren()){
                    name.setText(snapshot.child("name").getValue().toString());
                    cardnumber.setText(snapshot.child("cardnumber").getValue().toString());
                    cvcnumber.setText(snapshot.child("cvcnumber").getValue().toString());
                    expiration.setText(snapshot.child("expiration").getValue().toString());
                }
                else{
                    Toast.makeText(getApplicationContext(),"No  data to display",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        updatebtn.setOnClickListener(new View.OnClickListener() {
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

                    Toast.makeText(getApplicationContext(),"Data Changed Successfully",Toast.LENGTH_SHORT).show();
                }catch (NumberFormatException e){

                }
                Intent intent = new Intent(payment_view.this,payment_view.class);
                startActivity(intent);
            }

        });

        proceedbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(getApplicationContext(),"Data Changed Successfully",Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(payment_view.this,MainActivity_payment.class);
                startActivity(intent);
            }
        });

        cancelbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference delref = FirebaseDatabase.getInstance().getReference().child("Payment");
                delref.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        ArrayList<String> tableDataKeys = new ArrayList<>();
                        for(DataSnapshot data : snapshot.getChildren()){
                            tableDataKeys.add(data.getKey());
                        }
                        lastKey = tableDataKeys.get(tableDataKeys.size()-2);

                        if(snapshot.hasChild(lastKey)){
                            dbref = FirebaseDatabase.getInstance().getReference().child("Payment").child(lastKey);
                            dbref.removeValue();
                        }

                        if(snapshot.hasChild("lastPaymentData")){
                            dbref = FirebaseDatabase.getInstance().getReference().child("Payment").child("lastPaymentData");
                            dbref.removeValue();
                            Toast.makeText(getApplicationContext(), "Deleted Successfully", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(getApplicationContext(), "No data to delete.", Toast.LENGTH_SHORT).show();
                        }


                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });



    }
}