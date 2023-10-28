package com.example.android_crud;


import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddForm extends AppCompatActivity {
    DatabaseHandler db;
    EditText title;
    EditText description;
    EditText price;
    Button btnAdd;



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

        db = new DatabaseHandler(this);

        setContentView(R.layout.add_form);
        title = findViewById(R.id.title);
        description = findViewById(R.id.description);
        price = findViewById(R.id.price);
        btnAdd = findViewById(R.id.submit);

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
                db.addProduct(product);
                setResult(Activity.RESULT_OK);
                finish();
            }
        });
    }


}