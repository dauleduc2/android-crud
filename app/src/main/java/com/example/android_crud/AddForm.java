package com.example.android_crud;


import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class AddForm extends AppCompatActivity {


    @Override
    public void onBackPressed() {
        return;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_form);
    }


}