package com.iteso.pdm18_scrollabletabs.tools;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.iteso.pdm18_scrollabletabs.beans.Category;

import java.sql.SQLData;
import java.util.ArrayList;

public class CategoryControl {

    public ArrayList<Category> getCategories(DatabaseHandler databaseHandler){

        ArrayList<Category> categories = new ArrayList<>();

        String SELECT_CATEGORIES = "SELECT " + DatabaseHandler.KEY_CATEGORY_ID +
                "," + DatabaseHandler.KEY_CATEGORY_NAME + " FROM " + DatabaseHandler.TABLE_CATEGORY;

        SQLiteDatabase db = databaseHandler.getReadableDatabase();
        Cursor cursor = db.rawQuery(SELECT_CATEGORIES, null);

        if(cursor.moveToFirst()){
            do{
                categories.add(new Category(cursor.getInt(0), cursor.getString(1)));
            }while(cursor.moveToNext());
        }

        return categories;
    }

}
