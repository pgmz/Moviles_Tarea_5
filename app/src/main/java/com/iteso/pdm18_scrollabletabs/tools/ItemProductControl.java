package com.iteso.pdm18_scrollabletabs.tools;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.iteso.pdm18_scrollabletabs.beans.Category;
import com.iteso.pdm18_scrollabletabs.beans.ItemProduct;

import java.util.ArrayList;

public class ItemProductControl {

    public static final String KEY_STORE_PRODUCT_ID = "id";
    public static final String KEY_STORE_PRODUCT_ID_PRODUCT = "idProduct";
    public static final String KEY_STORE_PRODUCT_ID_STORE = "idStore";

    public void addItemProduct(ItemProduct itemProduct, DatabaseHandler databaseHandler){
        String INSERT_PRODUCT = "INSERT INTO " + DatabaseHandler.TABLE_PRODUCT + "(" +
                DatabaseHandler.KEY_PRODUCT_ID_PRODUCT + "," + DatabaseHandler.KEY_PRODUCT_TITLE + "," +
                DatabaseHandler.KEY_PRODUCT_DESCRIPTION + "," + DatabaseHandler.KEY_PRODUCT_IMAGE + "," +
                DatabaseHandler.KEY_PRODUCT_ID_CATEGORY + ") VALUES (" +
                itemProduct.getCode() + "," + itemProduct.getTitle() + "," +
                itemProduct.getDescription() + "," + itemProduct.getImage() + "," +
                itemProduct.getCategory().getId() + ")";

        SQLiteDatabase sqLiteDatabase = databaseHandler.getWritableDatabase();
        sqLiteDatabase.execSQL(INSERT_PRODUCT);

        String INSERT_STORE_PRODUCT = "INSERT INTO " + DatabaseHandler.TABLE_STORE_PRODUCT + "(" +
                DatabaseHandler.KEY_STORE_PRODUCT_ID_PRODUCT + "," + DatabaseHandler.KEY_STORE_PRODUCT_ID_STORE +
                ") VALUES (" + itemProduct.getCode() + "," + itemProduct.getCategory().getId() + ")";

        sqLiteDatabase.execSQL(INSERT_STORE_PRODUCT);


    }

    public ArrayList<ItemProduct> getItemProductsByCategory(int idCategory, DatabaseHandler databaseHandler){
        ArrayList<ItemProduct> itemProducts = new ArrayList<>();

        String SELECT_PRODUCTS = "SELECT I." + DatabaseHandler.KEY_PRODUCT_ID_PRODUCT +
                ", I." + DatabaseHandler.KEY_PRODUCT_TITLE +
                ", I." + DatabaseHandler.KEY_PRODUCT_DESCRIPTION +
                ", I." + DatabaseHandler.KEY_PRODUCT_IMAGE +
                ", C." + DatabaseHandler.KEY_CATEGORY_NAME +
                " FROM " + DatabaseHandler.TABLE_PRODUCT + " I, " +
                DatabaseHandler.TABLE_CATEGORY + " C" + " WHERE I." +
                DatabaseHandler.KEY_PRODUCT_ID_CATEGORY + " = " +  idCategory +
                " AND I." + DatabaseHandler.KEY_PRODUCT_ID_CATEGORY + " = C." +
                DatabaseHandler.KEY_CATEGORY_ID;

        SQLiteDatabase db = databaseHandler.getReadableDatabase();
        Cursor cursor = db.rawQuery(SELECT_PRODUCTS, null);

        if(cursor.moveToFirst()){
            do{
                itemProducts.add(new ItemProduct(
                        cursor.getInt(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getInt(4),
                        new Category(
                                idCategory,
                                cursor.getString(5)
                        )
                ));
            }while(cursor.moveToNext());
        }

        StoreControl storeControl = new StoreControl();

        for (ItemProduct itemProduct : itemProducts){
            String SELECT_STORE = "SELECT " + DatabaseHandler.KEY_STORE_PRODUCT_ID_STORE
                    + " FROM " + DatabaseHandler.TABLE_STORE_PRODUCT + " WHERE "
                    + DatabaseHandler.KEY_STORE_PRODUCT_ID_PRODUCT + " = "
                    + itemProduct.getCode();

            cursor = db.rawQuery(SELECT_STORE, null);
            if(cursor.moveToFirst()) {
                itemProduct.setStore(storeControl.getStoreById(cursor.getInt(0), databaseHandler));
            }
        }

        return itemProducts;
    }

}
