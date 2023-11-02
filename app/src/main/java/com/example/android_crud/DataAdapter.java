package com.example.android_crud;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.w3c.dom.Text;

import java.util.List;

public class DataAdapter extends ArrayAdapter<Product> {
    private List<Product> items;
    int layoutResourceId;
    Context context;

    public DataAdapter(@NonNull Context context, int resource, @NonNull List<Product> objects) {
        super(context, resource, objects);
        this.layoutResourceId = resource;
        this.context = context;
        this.items = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View row = convertView;
        ProductHolder product = null;

        if(row == null){
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);
            product = new ProductHolder();
            product.title = (TextView)row.findViewById(R.id.title);
            product.description = (TextView)row.findViewById(R.id.description);
            product.price = (TextView)row.findViewById(R.id.price);
            product.btnDelete = (Button)row.findViewById(R.id.deleteButton);

            product.btnDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    new AlertDialog.Builder(context)
                            .setTitle("Are you sure?")
                            .setMessage("Do you really want to delete this product?")
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                                public void onClick(DialogInterface dialog, int whichButton) {
                                    DatabaseHandler db = new DatabaseHandler(context);
                                    db.deleteProduct(items.get(position).ID);
                                    items.remove(position);
                                    notifyDataSetChanged();
                                }})
                            .setNegativeButton(android.R.string.no, null).show();


                }
            });

            row.setTag(product);
        }else{
            product = (ProductHolder)row.getTag();
        }

        Product current = items.get(position);
        product.title.setText(current.title);
        product.description.setText(current.description);
        product.price.setText(Double.toString(current.price) + "$");;

        return row;
    }


    class ProductHolder {
        TextView title;
        TextView description;
        TextView price;
        Button btnDelete;

    }
}
