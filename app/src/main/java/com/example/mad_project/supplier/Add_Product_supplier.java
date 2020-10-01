package com.example.mad_project.supplier;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.mad_project.Models.Products;
import com.example.mad_project.R;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.text.SimpleDateFormat;
import java.util.Calendar;


public class Add_Product_supplier extends AppCompatActivity {

    private String iname,oldP,newP,Quanty,saveCurrentDate,saveCurrentTime;
    EditText item_name,old_price,new_price,quantity;
    ImageView upload_image;
    Button add_item;
    DatabaseReference ref;
    private static final int GALLARYPICK = 1;
    long maxID = 0;
    private Uri imageUri;
    private StorageReference productImageRef;
    private String productRandomKey, downloadImageUrl;
    final Products product = new Products();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__product_supplier);

        item_name = (EditText) findViewById(R.id.name_item_edit_view);
        old_price = (EditText) findViewById(R.id.price_old_edit_view);
        new_price = (EditText) findViewById(R.id.new_price_edit_view);
        quantity = (EditText) findViewById(R.id.quantity_edit_view);
        upload_image = (ImageView) findViewById(R.id.imageToUpload);
        productImageRef = FirebaseStorage.getInstance().getReference().child("product image");









        upload_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openGallary();
            }


        });

        add_item = findViewById(R.id.add_products);
        add_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                validateProductData();




            }
        });



    }

    private void openGallary() {
        Intent gallaryIntent = new Intent();
        gallaryIntent.setAction(Intent.ACTION_GET_CONTENT);
        gallaryIntent.setType("image/*");
        startActivityForResult(gallaryIntent,GALLARYPICK);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == GALLARYPICK && resultCode == RESULT_OK && data != null){

            imageUri = data.getData();
            upload_image.setImageURI(imageUri);


        }

        }

        private void validateProductData(){

            iname = item_name.getText().toString();
            oldP = old_price.getText().toString();
            newP = new_price.getText().toString();
            Quanty = quantity.getText().toString();

            if(imageUri == null){
                Toast.makeText(this, "Product image is mandatory", Toast.LENGTH_SHORT).show();
            }
            else if(TextUtils.isEmpty(iname)){
                Toast.makeText(this, "product name shouldnt be empty", Toast.LENGTH_SHORT).show();
            }
            else if(TextUtils.isEmpty(oldP)){
                Toast.makeText(this, "old price field shouldn't be empty", Toast.LENGTH_SHORT).show();
            }
            else if(TextUtils.isEmpty(newP)){
                Toast.makeText(this, "new price field shouldn't be empty", Toast.LENGTH_SHORT).show();
            }
            else if(TextUtils.isEmpty(Quanty)){
                Toast.makeText(this, "quantity field shouldn't be empty", Toast.LENGTH_SHORT).show();
            }
            else {
                storeProductInformation();
            }

        }

        private void storeProductInformation(){

            Calendar calendar = Calendar.getInstance();

            SimpleDateFormat currentDate = new SimpleDateFormat("MMM DD, YYYY");
            saveCurrentDate = currentDate.format(calendar.getTime());

            SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss a");
            saveCurrentTime = currentTime.format(calendar.getTime());

            productRandomKey = saveCurrentDate + saveCurrentTime;


                final StorageReference filepath = productImageRef.child(imageUri.getLastPathSegment()+ productRandomKey +".jpg");

                final UploadTask task = filepath.putFile(imageUri);

                task.addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        String message = e.toString();
                        Toast.makeText(Add_Product_supplier.this, "Error: " + message, Toast.LENGTH_SHORT).show();
                    }
                }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        Toast.makeText(Add_Product_supplier.this, "image uploaded succesfully", Toast.LENGTH_SHORT).show();

                        Task<Uri> uriTask = task.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                            @Override
                            public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task2) throws Exception {
                                if(!task2.isSuccessful()){
                                    throw task2.getException();
                                }

                                downloadImageUrl = filepath.getDownloadUrl().toString();
                                return filepath.getDownloadUrl();
                            }
                        }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                            @Override
                            public void onComplete(@NonNull Task<Uri> task) {
                                    if(task.isSuccessful()){
                                        downloadImageUrl = task.getResult().toString();
                                        Toast.makeText(Add_Product_supplier.this, "getting product image url is succesfull", Toast.LENGTH_SHORT).show();
                                        saveProductInfoToDatebase();
                                    }
                            }
                        });
                    }
                });



            }

    private void saveProductInfoToDatebase() {

        product.setItem_name(item_name.getText().toString().trim());
        product.setOld_price(Double.parseDouble(old_price.getText().toString().trim()));
        product.setNew_price(Double.parseDouble(new_price.getText().toString().trim()));
        product.setQantity(Integer.parseInt(quantity.getText().toString().trim()));
        product.setImage_uri(downloadImageUrl);
        product.setKey(productRandomKey);


        ref = FirebaseDatabase.getInstance().getReference().child("Products");

        ref.child(productRandomKey).setValue(product).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){

                    Toast.makeText(Add_Product_supplier.this, "Product added successfully", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Add_Product_supplier.this, Supplier_page.class);
                    startActivity(intent);
                }
                else {
                    String message = task.getException().toString();
                    Toast.makeText(Add_Product_supplier.this, " Error : " + message, Toast.LENGTH_SHORT).show();
                }
            }
        });



    }
}
