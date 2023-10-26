package com.example.android_crud;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();

        if(itemId == R.id.add){
            Intent addForm = new Intent(this, AddForm.class);
            startActivity(addForm);
        }

        return super.onOptionsItemSelected(item);
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