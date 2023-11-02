package com.example.android_crud;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

public class AddForm extends AppCompatActivity {
    TextView textHead;
    DatabaseHandler db;
    EditText title;
    EditText description;
    EditText price;
    Button btnAdd;
    int ID;
    String mode;
    Product currentProduct;


    private boolean validate(String titleText, String descriptionText, String priceText){
        boolean isValidForm = true;

        if(titleText.isEmpty()){
            title.setError("Title cannot be empty");
            isValidForm = false;
        }
        if(descriptionText.isEmpty()){
            description.setError("Description cannot be empty");
            isValidForm = false;
        }
        if(priceText.isEmpty()){
            price.setError("Price cannot be empty");
            isValidForm = false;
        }

        try {
            Double.parseDouble(priceText);
        }
        catch(Exception e) {
            price.setError("Price must be a number");
            isValidForm = false;
        }

        return isValidForm;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent currentIntent = getIntent();
        this.mode = currentIntent.getStringExtra("mode");
        this.ID = currentIntent.getIntExtra("id", 0);
        db = new DatabaseHandler(this);

        setContentView(R.layout.add_form);
        title = findViewById(R.id.title);
        description = findViewById(R.id.description);
        price = findViewById(R.id.price);
        btnAdd = findViewById(R.id.submit);
        textHead = findViewById(R.id.head_text);

        if(this.mode.equals("edit")){
            currentProduct = db.getProduct(this.ID);
            btnAdd.setText("Update");
            textHead.setText("Edit Product");

//            set initial value to the form
            title.setText(currentProduct.title);
            description.setText(currentProduct.description);
            price.setText(String.valueOf(currentProduct.price));

        }

        btnAdd.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                String titleText = title.getText().toString();
                String descriptionText = description.getText().toString();
                String priceText = price.getText().toString();
                boolean isValidForm = validate(titleText, descriptionText, priceText);

                if(!isValidForm){
                    return;
                }

                Product product = new Product(titleText, descriptionText, Double.parseDouble(priceText));

                if(mode.equals("edit")){
                    product.ID = ID;
                    db.updateProduct(product);
                }else{
                    db.addProduct(product);
                }

                setResult(Activity.RESULT_OK);
                finish();
            }
        });
    }


}