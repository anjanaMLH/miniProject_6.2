package com.example.mad_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseListAdapter;
import com.firebase.ui.database.FirebaseListOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import com.example.mad_project.Models.Products;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

public class Supplier_page extends AppCompatActivity {

    // private RecyclerView nProductList;
    // private DatabaseReference ref;
    // private productViewHolder productViewHolder;

    ListView lv;
    FirebaseListAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supplier_page);

        lv = (ListView) findViewById(R.id.myListView);

        Query query = FirebaseDatabase.getInstance().getReference().child("Products");
        FirebaseListOptions<Products> options = new FirebaseListOptions.Builder<Products>()
                .setLayout(R.layout.product)
                .setQuery(query,Products.class)
                .build();

        adapter = new FirebaseListAdapter(options) {
            @Override
            protected void populateView(View v, Object model, int position) {
                TextView name_product,old_price,new_price,quantity,discount;
                ImageView image;

                name_product = (TextView) v.findViewById(R.id.name_pizza);
                old_price = (TextView) v.findViewById(R.id.name_pizza_price_old);
                new_price = (TextView) v.findViewById(R.id.name_pizza_price);
                quantity = (TextView) v.findViewById(R.id.quantity_magnitude);
                discount = (TextView)v.findViewById(R.id.discount);
                image = (ImageView) v.findViewById(R.id.imageView_product);




                Products pro = (Products) model;

                float dis =(float) (pro.getNew_price()/pro.getOld_price()) * 100;

                name_product.setText(String.valueOf(pro.getItem_name()));
                old_price.setText(String.valueOf(pro.getOld_price()));
                new_price.setText(String.valueOf(pro.getNew_price()));
                quantity.setText(String.valueOf(pro.getQantity()));
                discount.setText("Discount: " + String.valueOf(dis));
                Picasso
                        .get()
                        .load(pro.getImage_uri())
                        .noFade()
                        .into(image);




            }
        };

        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent update = new Intent(Supplier_page.this,Udate_food_item_supplier.class);
                Products p = (Products) adapterView.getItemAtPosition(i);
                update.putExtra("name",p.getItem_name());
                update.putExtra("old_price", p.getOld_price().toString());
                update.putExtra("new_price", p.getNew_price().toString());
                update.putExtra("quantity", p.getQantity().toString());
                update.putExtra("image", p.getImage_uri());
                update.putExtra("key",p.getKey());
                startActivity(update);
            }
        });


        FloatingActionButton button_add = (FloatingActionButton) findViewById(R.id.button_add);
        button_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Supplier_page.this, Add_Product_supplier.class);
                startActivity(intent);
            }
        });



    }



    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();

    }




    protected void onStop(){
        super.onStop();
        adapter.stopListening();
    }






}