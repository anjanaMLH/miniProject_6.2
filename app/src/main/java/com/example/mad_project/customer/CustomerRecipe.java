package com.example.mad_project.customer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

import com.example.mad_project.Models.Order;
import com.example.mad_project.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class CustomerRecipe extends AppCompatActivity {

    AutoCompleteTextView foodType;
    AutoCompleteTextView resturent;

    Button orderButton;

    private DatabaseReference customerOrdersDatabase;

    private static long index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_recipe);

        String[] foods = getResources().getStringArray(R.array.food);
        foodType = findViewById(R.id.food_type);
        ArrayAdapter<String> Foodadapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,foods);

        foodType.setAdapter(Foodadapter);

        String[] resturents = getResources().getStringArray(R.array.restaurent);
        resturent = findViewById(R.id.resturent);
        ArrayAdapter<String> resturentAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, resturents);
        resturent.setAdapter(resturentAdapter);
        customerOrdersDatabase = FirebaseDatabase.getInstance().getReference("food_orders");
        orderButton = findViewById(R.id.order_btn);

        Bundle bundle = getIntent().getExtras();
        if(bundle.getBoolean("UPDATE")){
            foodType.setText(bundle.getString("FOOD"));
            resturent.setText(bundle.getString("RESTURENT"));
        }
        long count;

        orderButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                final String food = foodType.getText().toString();
                final String shop = resturent.getText().toString();
                Bundle bundle = getIntent().getExtras();
                final String email = bundle.getString("CUSTOMER_EMAIL");
                String id = bundle.getString("FOOD_COUNT");
                customerOrdersDatabase.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                            Log.d("COUNT OF THE ORDERS", snapshot.getChildrenCount()+"");
                            index = snapshot.getChildrenCount();
                            Log.d("HERE IS THE INDEX ", index +"");
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
                Log.d("HERE IS THE INDEX OUT ",index+"");
                Order order = new Order(food, shop, email);
                customerOrdersDatabase.child(id).setValue(order);

                Intent intent = new Intent(CustomerRecipe.this, CustomerOrders.class);
                intent.putExtra("CUSTOMER_EMAIL",email);
                startActivity(intent);
            }
        });

    }

    public void changeIndex(long count){
        index+=count;
    }
}