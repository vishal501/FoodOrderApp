package com.example.foodorderapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.foodorderapp.Adapters.OrdersAdapter;
import com.example.foodorderapp.Models.OrdersModel;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    final static String DBNAME = "mydatabase.db";
    final static int DBVERSION = 1;

    public DBHelper(@Nullable Context context) {
        super(context, DBNAME, null, DBVERSION);
    }

    /*
    id = 0
    name = 1
    phone = 2
    price = 3
    image = 4
    description = 5
    foodname = 6
    quantity = 7
     */

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create Table users (username TEXT primary key, password TEXT)");

        db.execSQL(
                "create table orders" +
                        "(id integer primary key autoincrement," +
                        "name text," +
                        "phone text," +
                        "price int," +
                        "image int," +
                        "foodname text," +
                        "description text," +
                        "quantity int)"

        );

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP table if exists orders");
        db.execSQL("DROP table if exists users");
        onCreate(db);

    }

    public boolean insertOrder(String name, String phone, int price, int image, String foodName, String desc, int quantity){
        SQLiteDatabase database = getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put("name",name);
        values.put("phone",phone);
        values.put("price",price);
        values.put("image",image);
        values.put("foodname",foodName);
        values.put("description",desc);
        values.put("quantity",quantity);
        long id = database.insert("orders",null,values);
        if (id <= 0) {
            return false;
        } else {
            return true;
        }
    }


    public Boolean insertData(String username, String password) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username);
        contentValues.put("password", password);
        long result = MyDB.insert("users", null, contentValues);
        if (result == -1) return false;
        else
            return true;
    }

    public Boolean checkuserName(String username) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from users where username = ?", new String[]{username});
        if (cursor.getCount() > 0)
            return true;
        else
            return false;

    }

    public Boolean checkusernamepassword(String username, String password) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from users where username = ? and password = ?", new String[]{username, password});
        if (cursor.getCount() > 0)
            return true;
        else
            return false;
    }



















    public ArrayList<OrdersModel> getOrders() {
        ArrayList<OrdersModel> orders = new ArrayList<>();
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery("Select id,foodname,image,price from orders",null);
        if(cursor.moveToFirst()) {
            while(cursor.moveToNext()) {
                OrdersModel model = new OrdersModel();
                model.setOrderNumber(cursor.getInt(0) +"");
                model.setSoldItemName(cursor.getString(1));
                model.setOrderImage(cursor.getInt(2));
                model.setPrice(cursor.getInt(3) +"");
                orders.add(model);
            }
        }
        cursor.close();
        database.close();
        return orders;
    }

    public Cursor getOrderById(int id) {
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery("Select * from orders where id =" +id,null);

        if (cursor != null)
            cursor.moveToFirst();


        return cursor;

    }
    public boolean updateOrder(String name, String phone, int price, int image, String desc, String foodName,  int quantity, int id) {
        SQLiteDatabase database = getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("phone", phone);
        values.put("price", price);
        values.put("image", image);
        values.put("description", desc);
        values.put("foodname", foodName);
        values.put("quantity", quantity);
        long row = database.update("orders", values, "id=" + id, null);
        if(row <= 0) {
            return false;
        } else {
            return true;
        }
    }

        public int deletedOrder(String id) {
           SQLiteDatabase database = this.getWritableDatabase();
           return database.delete("orders","id="+id,null);
        }


}
