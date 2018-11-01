package com.iteso.pdm18_scrollabletabs;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.iteso.pdm18_scrollabletabs.beans.Category;
import com.iteso.pdm18_scrollabletabs.beans.ItemProduct;
import com.iteso.pdm18_scrollabletabs.beans.Store;
import com.iteso.pdm18_scrollabletabs.tools.CategoryControl;
import com.iteso.pdm18_scrollabletabs.tools.DatabaseHandler;
import com.iteso.pdm18_scrollabletabs.tools.ItemProductControl;
import com.iteso.pdm18_scrollabletabs.tools.StoreControl;

import java.util.ArrayList;

public class ActivityItem extends AppCompatActivity {

    EditText editText;
    Spinner spinner_image;
    Spinner spinner_category;
    Spinner spinner_store;
    Button button_save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);

        editText = findViewById(R.id.activity_item_edittext);

        spinner_image = findViewById(R.id.activity_item_spinner_images);

        spinner_category = findViewById(R.id.activity_item_spinner_category);
        spinner_store = findViewById(R.id.activity_item_spinner_stores);

        final DatabaseHandler databaseHandler = DatabaseHandler.getInstance(this);
        CategoryControl categoryControl = new CategoryControl();

        final ArrayList<Category> categories = categoryControl.getCategories(databaseHandler);
        ArrayList<String> categoryNames = new ArrayList<>();

        for (Category category : categories){
            categoryNames.add(category.getName());
        }

        spinner_category.setAdapter(new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_dropdown_item,
                categoryNames));

        StoreControl storeControl = new StoreControl();

        final ArrayList<Store> stores = storeControl.getStores(databaseHandler);
        ArrayList<String> storeNames = new ArrayList<>();

        for (Store store : stores){
            storeNames.add(store.getName());
        }

        spinner_store.setAdapter(new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_dropdown_item,
                storeNames));

        final ItemProductControl itemProductControl = new ItemProductControl();
        button_save = findViewById(R.id.activity_item_button_save);
        button_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Integer image, Store store, Category category) {
                ItemProduct itemProduct = new ItemProduct(
                        -1,
                        editText.getText().toString(),
                        "Lorem ipsum",
                        spinner_image.getSelectedItemPosition(),
                        stores.get(spinner_store.getSelectedItemPosition()),
                        categories.get(spinner_category.getSelectedItemPosition())
                );
                itemProductControl.addItemProduct(itemProduct, databaseHandler);
                finish();

            }
        });


    }

}