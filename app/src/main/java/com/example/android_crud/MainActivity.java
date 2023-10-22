package com.example.android_crud;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    List<Product> data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.list_view);
        createData();
        DataAdapter arr = new DataAdapter(this, R.layout.data_item, data);
        listView.setAdapter(arr);

    }

    private void createData(){
        data = new ArrayList<Product>();
        data.add(new Product("Java", "Language 1", 50));
        data.add(new Product("JavaScript", "Language 2", 4));
        data.add(new Product("Docker", "Language 31", 2));
        data.add(new Product("ReactJS", "Language 4", 3));
        data.add(new Product("TypeScript", "Language 5", 55));
        data.add(new Product("Kotlin", "Language 6", 13));
        data.add(new Product("Ruby on rail", "Language 6", 13));
    }
}