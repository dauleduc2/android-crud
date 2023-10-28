package com.example.android_crud;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
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
    DatabaseHandler db;


    private void getData(){
        data = db.getAllProduct();
        listView = (ListView) findViewById(R.id.list_view);

        DataAdapter arr = new DataAdapter(this, R.layout.data_item, data);
        listView.setAdapter(arr);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = new DatabaseHandler(this);
        getData();
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
            goToAddForm.launch(addForm);
        }

        return super.onOptionsItemSelected(item);
    }

    ActivityResultLauncher<Intent> goToAddForm = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        // There are no request codes
                        Toast.makeText(MainActivity.this, "Data added", Toast.LENGTH_SHORT).show();
                        getData();
                    }
                }
            });

    @Override
    public void onRestart() {
        super.onRestart();
        getData();
    }

    private void createData(){
        data = new ArrayList<Product>();
        data.add(new Product(1,"Java", "Language 1", 50));
        data.add(new Product(2,"JavaScript", "Language 2", 4));
        data.add(new Product(3,"Docker", "Language 31", 2));
        data.add(new Product(4,"ReactJS", "Language 4", 3));
        data.add(new Product(5,"TypeScript", "Language 5", 55));
        data.add(new Product(6,"Kotlin", "Language 6", 13));
        data.add(new Product(7,"Ruby on rail", "Language 6", 13));
    }
}