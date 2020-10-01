package com.example.mad_project.customer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mad_project.Models.Order;
import com.example.mad_project.R;
import com.example.mad_project.adapter.OrderAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomerOrders extends AppCompatActivity {

    DatabaseReference orderDatabase;

    FloatingActionButton fab;

    String email;

    long index;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    private Map<String, Order> orderMap = new HashMap<>();
    private List<Order> orders = new ArrayList<>();

    private TextView logOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_orders);

        this.logOut = findViewById(R.id.log_out);
        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CustomerOrders.this, CustomerHome.class);
                startActivity(intent);
            }
        });

        this.recyclerView = findViewById(R.id.food_list);
        this.recyclerView.setHasFixedSize(true);

        this.layoutManager = new LinearLayoutManager(this);
        this.recyclerView.setLayoutManager(layoutManager);

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);

        Bundle bundle = getIntent().getExtras();
        email = bundle.getString("CUSTOMER_EMAIL");

        fab = findViewById(R.id.floatingActionButton);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CustomerOrders.this, CustomerRecipe.class);
                intent.putExtra("CUSTOMER_EMAIL",email);
                intent.putExtra("FOOD_COUNT",index+"");
                Log.d("INDEX OF ORDERS", index+"");
                startActivity(intent);
            }
        });


        Toast.makeText(CustomerOrders.this, email, Toast.LENGTH_LONG).show();

        orderDatabase = FirebaseDatabase.getInstance().getReference("food_orders");

        orderDatabase.addValueEventListener(new ValueEventListener() {


            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot data: snapshot.getChildren()){
                    Order order = data.getValue(Order.class);
                    String key = data.getKey();

                    orderMap.put(key, order);

                    orders.add(order);
                    Log.d("THIS IS THE LAST",data.toString());
                }
                index = snapshot.getChildrenCount()+2;


                adapter = new OrderAdapter(orders);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }

    ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {

        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

            int position = viewHolder.getAdapterPosition();
            Order order = orders.get(position);

            String key = "";
            for (Map.Entry<String, Order> entry : orderMap.entrySet()){

                if(entry.getValue().getFood().equals(order.getFood()) && entry.getValue().getResturent().equals(order.getResturent())){
                    key = entry.getKey();
                    break;
                }

            }
            switch (direction){
                case ItemTouchHelper.LEFT:

                    Toast.makeText(CustomerOrders.this,key, Toast.LENGTH_LONG).show();
                    orderDatabase.child(key).setValue(null);
                    orders.clear();
                    break;
        //            orderDatabase.child(key).removeValue();
        //
        //            orders.remove(position);
                case ItemTouchHelper.RIGHT:
                    Intent intent = new Intent(CustomerOrders.this, CustomerRecipe.class);
                    intent.putExtra("FOOD_COUNT", key);
                    intent.putExtra("UPDATE", true);
                    intent.putExtra("FOOD",order.getFood());
                    intent.putExtra("RESTURENT",order.getResturent());
                    startActivity(intent);
                    break;

            }



        }
    };
}