package com.example.practiceiii;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "products_database";
    private static final int DATABASE_VERSION = 1;

    // Here we are declaring the table and columns
    public static final String TABLE_PRODUCTS = "products";
    public static final String KEY_ID = "id";
    public static final String KEY_NAME = "name";
    public static final String KEY_DESCRIPTION = "description";
    public static final String KEY_SELLER = "seller";
    public static final String KEY_PRICE = "price";
    public static final String KEY_IMAGE_RESOURCE = "imageResource";

    private static final String CREATE_TABLE_PRODUCTS = "CREATE TABLE " + TABLE_PRODUCTS + " (" +
            KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            KEY_NAME + " TEXT NOT NULL, " +
            KEY_DESCRIPTION + " TEXT, " +
            KEY_SELLER + " TEXT, " +
            KEY_PRICE + " REAL, " +
            KEY_IMAGE_RESOURCE + " INTEGER);";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_PRODUCTS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRODUCTS);
        onCreate(db);
    }

    public void insertProduct(Product product){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, product.name);
        values.put(KEY_DESCRIPTION, product.description);
        values.put(KEY_SELLER, product.seller);
        values.put(KEY_PRICE, product.price);
        values.put(KEY_IMAGE_RESOURCE, product.imageResource);
        db.insert(TABLE_PRODUCTS, null, values);
        db.close();
    }

    public List<Product> getAllProducts() {
        List<Product> productList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_PRODUCTS, null);

        if (cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") int id = cursor.getInt(cursor.getColumnIndex(KEY_ID));
                @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex(KEY_NAME));
                @SuppressLint("Range") String description = cursor.getString(cursor.getColumnIndex(KEY_DESCRIPTION));
                @SuppressLint("Range") String seller = cursor.getString(cursor.getColumnIndex(KEY_SELLER));
                @SuppressLint("Range") float price = cursor.getFloat(cursor.getColumnIndex(KEY_PRICE));
                @SuppressLint("Range") int imageResource = cursor.getInt(cursor.getColumnIndex(KEY_IMAGE_RESOURCE));
                productList.add(new Product(id, name, description, seller, price, imageResource));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return productList;
    }
}
