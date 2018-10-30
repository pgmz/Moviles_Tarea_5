package com.iteso.pdm18_scrollabletabs.tools;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.iteso.pdm18_scrollabletabs.beans.Category;
import com.iteso.pdm18_scrollabletabs.beans.City;
import com.iteso.pdm18_scrollabletabs.beans.Store;

import java.util.ArrayList;

public class StoreControl {


    public void addStore(Store store, DatabaseHandler databaseHandler){
        String INSERT_STORE = "INSERT INTO " + DatabaseHandler.TABLE_STORE + " (" +
                DatabaseHandler.KEY_STORE_ID + "," + DatabaseHandler.KEY_STORE_NAME + "," +
                DatabaseHandler.KEY_STORE_PHONE + "," + DatabaseHandler.KEY_STORE_ID_CITY + "," +
                DatabaseHandler.KEY_STORE_THUMBNAIL + "," + DatabaseHandler.KEY_STORE_LATITUDE + "," +
                DatabaseHandler.KEY_STORE_LONGITUDE + ") VALUES (" +
                store.getId() + ",'" + store.getName() + "','" + store.getPhone() + "'," +
                store.getCity().getId() + "," + store.getThumbnail() + "," + store.getLatitude() + "," +
                store.getLongitude() + ")";

        SQLiteDatabase sqLiteDatabase = databaseHandler.getWritableDatabase();
        sqLiteDatabase.execSQL(INSERT_STORE);

    }


    public ArrayList<Store> getStores (DatabaseHandler databaseHandler){
        ArrayList<Store> stores = new ArrayList<>();

        String SELECT_STORES = "SELECT S." + DatabaseHandler.KEY_STORE_ID +
                ", S." + DatabaseHandler.KEY_STORE_NAME +
                ", S." + DatabaseHandler.KEY_STORE_PHONE +
                ", S." + DatabaseHandler.KEY_STORE_THUMBNAIL +
                ", S." + DatabaseHandler.KEY_STORE_LATITUDE +
                ", S." + DatabaseHandler.KEY_STORE_LONGITUDE +
                ", C." + DatabaseHandler.KEY_CITY_ID +
                ", C." + DatabaseHandler.KEY_CITY_NAME +
                " FROM " + DatabaseHandler.TABLE_STORE + " S, " +
                DatabaseHandler.TABLE_CITY + " C WHERE S." +
                DatabaseHandler.KEY_STORE_ID + " = C." +
                DatabaseHandler.KEY_CITY_ID;

        SQLiteDatabase db = databaseHandler.getReadableDatabase();
        Cursor cursor = db.rawQuery(SELECT_STORES, null);

        if(cursor.moveToFirst()){
            do{
                stores.add(new Store(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getInt(3),
                        cursor.getDouble(4),
                        cursor.getDouble(5),
                        new City(
                                cursor.getInt(6),
                                cursor.getString(7)
                        ))
                );
            }while(cursor.moveToNext());
        }

        return stores;
    }

    public Store getStoreById(int idStore, DatabaseHandler databaseHandler){

        Store store = new Store();

        String SELECT_STORES = "SELECT S." + DatabaseHandler.KEY_STORE_ID +
                ", S." + DatabaseHandler.KEY_STORE_NAME +
                ", S." + DatabaseHandler.KEY_STORE_PHONE +
                ", S." + DatabaseHandler.KEY_STORE_THUMBNAIL +
                ", S." + DatabaseHandler.KEY_STORE_LATITUDE +
                ", S." + DatabaseHandler.KEY_STORE_LONGITUDE +
                ", C." + DatabaseHandler.KEY_CITY_ID +
                ", C." + DatabaseHandler.KEY_CITY_NAME +
                " FROM " + DatabaseHandler.TABLE_STORE + " S, " +
                DatabaseHandler.TABLE_CITY + " C WHERE S." +
                DatabaseHandler.KEY_STORE_ID + " = " +
                idStore;

        SQLiteDatabase db = databaseHandler.getReadableDatabase();
        Cursor cursor = db.rawQuery(SELECT_STORES, null);

        if(cursor.moveToFirst()){
            store = new Store(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getInt(3),
                        cursor.getDouble(4),
                        cursor.getDouble(5),
                        new City(
                                cursor.getInt(6),
                                cursor.getString(7)
                        ));
        }

        return store;
    }

}
