package com.iteso.pdm18_scrollabletabs.tools;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHandler extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "ItesoStore.db";
    private static final int DATABASE_VERSION = 1;

    //Tables
    public static final String TABLE_CITY = "City";
    public static final String TABLE_CATEGORY = "Category";
    public static final String TABLE_STORE = "Store";
    public static final String TABLE_PRODUCT = "Product";
    public static final String TABLE_STORE_PRODUCT = "StoreProduct";

    //Columns City
    public static final String KEY_CITY_ID = "id";
    public static final String KEY_CITY_NAME = "name";

    //Columns Category
    public static final String KEY_CATEGORY_ID = "id";
    public static final String KEY_CATEGORY_NAME = "name";

    //Columns Store
    public static final String KEY_STORE_ID = "id";
    public static final String KEY_STORE_NAME = "name";
    public static final String KEY_STORE_PHONE = "phone";
    public static final String KEY_STORE_ID_CITY = "idCity";
    public static final String KEY_STORE_THUMBNAIL = "thumbnail";
    public static final String KEY_STORE_LATITUDE = "latitude";
    public static final String KEY_STORE_LONGITUDE = "longitude";

    //Columns Product
    public static final String KEY_PRODUCT_ID_PRODUCT = "idProduct";
    public static final String KEY_PRODUCT_TITLE = "title";
    public static final String KEY_PRODUCT_DESCRIPTION = "description";
    public static final String KEY_PRODUCT_IMAGE = "image";
    public static final String KEY_PRODUCT_ID_CATEGORY = "idCategory";

    //Columns StoreProduct
    public static final String KEY_STORE_PRODUCT_ID = "id";
    public static final String KEY_STORE_PRODUCT_ID_PRODUCT = "idProduct";
    public static final String KEY_STORE_PRODUCT_ID_STORE = "idStore";

    private static DatabaseHandler databaseHandler;

    private DatabaseHandler(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static DatabaseHandler getInstance(Context context){
        if(databaseHandler == null){
            databaseHandler = new DatabaseHandler(context);
        }
        return databaseHandler;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String CREATE_TABLE_CITY = "CREATE TABLE "+ TABLE_CITY + "(" + KEY_CITY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_CITY_NAME + " TEXT)";
        String CREATE_TABLE_CATEGORY = "CREATE TABLE "+ TABLE_CATEGORY + "(" + KEY_CATEGORY_ID + " INTEGER PRIMARY KEY," + KEY_CATEGORY_NAME + " TEXT)";
        String CREATE_TABLE_STORE = "CREATE TABLE "+ TABLE_STORE + "(" + KEY_STORE_ID + " INTEGER PRIMARY KEY,"
                + KEY_STORE_NAME + " TEXT," + KEY_STORE_PHONE + " TEXT," + KEY_STORE_ID_CITY + " INTEGER," + KEY_STORE_THUMBNAIL + " INTEGER,"
                + KEY_STORE_LATITUDE + " DOUBLE," + KEY_STORE_LONGITUDE + " DOUBLE)";

        String CREATE_TABLE_PRODUCT = "CREATE TABLE " + TABLE_PRODUCT + "(" + KEY_PRODUCT_ID_PRODUCT + " INTEGER," + KEY_PRODUCT_TITLE + " TEXT," +
                KEY_PRODUCT_DESCRIPTION + " TEXT," + KEY_PRODUCT_IMAGE + " INTEGER," +
                KEY_PRODUCT_ID_CATEGORY + " INTEGER)";

        String CREATE_TABLE_STORE_PRODUCT = "CREATE TABLE " + TABLE_STORE_PRODUCT + "(" + KEY_STORE_PRODUCT_ID
                + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_STORE_PRODUCT_ID_PRODUCT +
                " INTEGER," + KEY_STORE_PRODUCT_ID_STORE + " INTEGER)";

        sqLiteDatabase.execSQL(CREATE_TABLE_CITY);
        sqLiteDatabase.execSQL(CREATE_TABLE_CATEGORY);
        sqLiteDatabase.execSQL(CREATE_TABLE_STORE);
        sqLiteDatabase.execSQL(CREATE_TABLE_PRODUCT);
        sqLiteDatabase.execSQL(CREATE_TABLE_STORE_PRODUCT);

        String ADD_CATEGORY_TECHNOLOGY = "INSERT INTO " + TABLE_CATEGORY + "(" + KEY_CATEGORY_NAME + ") VALUES ('Technology')";
        String ADD_CATEGORY_HOME = "INSERT INTO " + TABLE_CATEGORY + "(" + KEY_CATEGORY_NAME + ") VALUES ('Home')";
        String ADD_CATEGORY_ELECTRONICS = "INSERT INTO " + TABLE_CATEGORY + "(" + KEY_CATEGORY_NAME + ") VALUES ('Electronics')";

        sqLiteDatabase.execSQL(ADD_CATEGORY_TECHNOLOGY);
        sqLiteDatabase.execSQL(ADD_CATEGORY_HOME);
        sqLiteDatabase.execSQL(ADD_CATEGORY_ELECTRONICS);

        String ADD_CITY_GUADALAJARA = "INSERT INTO " + TABLE_CITY + "(" + KEY_CITY_ID + "," + KEY_CITY_NAME + ") VALUES (1,'Guadalajara')";
        String ADD_CITY_MONTERREY = "INSERT INTO " + TABLE_CITY + "(" + KEY_CITY_ID + "," + KEY_CITY_NAME + ") VALUES (2,'Monterrey')";
        String ADD_CITY_CDMX = "INSERT INTO " + TABLE_CITY + "(" + KEY_CITY_ID + "," + KEY_CITY_NAME + ") VALUES (3,'Ciudad de México')";

        sqLiteDatabase.execSQL(ADD_CITY_GUADALAJARA);
        sqLiteDatabase.execSQL(ADD_CITY_MONTERREY);
        sqLiteDatabase.execSQL(ADD_CITY_CDMX);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
