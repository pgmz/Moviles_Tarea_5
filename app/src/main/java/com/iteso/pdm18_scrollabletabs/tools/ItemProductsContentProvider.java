package com.iteso.pdm18_scrollabletabs.tools;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.iteso.pdm18_scrollabletabs.beans.ItemProduct;

import java.util.ArrayList;

public class ItemProductsContentProvider extends ContentProvider {

    static final String PROVIDER_NAME = "com.iteso.pdm18_scrollabletabs.tools.ItemProductsContentProvider";
    static final String URL = "content://" + PROVIDER_NAME + "/";
    static final Uri CONTENT_URI = Uri.parse(URL);


    DatabaseHandler databaseHandler;
    ItemProductControl itemProductControl;

    @Override
    public boolean onCreate() {
        databaseHandler = DatabaseHandler.getInstance(getContext());
        itemProductControl = new ItemProductControl();
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] strings, @Nullable String s,
                        @Nullable String[] strings1, @Nullable String s1) {
        return null;
    }

    @Override
    public Bundle call(String method, String arg, Bundle extras) {
        if(method.equals("getItemProductsByCategory")) {
            Integer category = extras.getInt("CATEGORY");
            ArrayList<String> items = new ArrayList<>();
            ArrayList<ItemProduct> itemProducts = itemProductControl.getItemProductsByCategory(category, databaseHandler);

            for(ItemProduct itemProduct : itemProducts){
                items.add(itemProduct.getTitle());
            }

            Bundle bundle = new Bundle();
            bundle.putStringArrayList("ITEMS", items);

            return bundle;
        }
        return null;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String s, @Nullable String[] strings) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String s, @Nullable String[] strings) {
        return 0;
    }
}
