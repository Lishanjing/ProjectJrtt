package com.bwei.fragment.projectone.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 2017/9/13.
 */

public class MyManager {
    SQLiteDatabase db;
    public MyManager(Context context){
        MyHelper myHelper = new MyHelper(context);
        db = myHelper.getWritableDatabase();
    }
    public void insert(String name,String pwd){
        ContentValues values = new ContentValues();
        values.put("name",name);
        values.put("pwd",pwd);
        db.insert("user",null,values);
    }
    public List<String> query(){
       List<String> list=new ArrayList<>();
        Cursor cursor = db.query("user", null, null, null, null, null, null);
        while (cursor.moveToNext()){
            String name = cursor.getString(cursor.getColumnIndex("name"));
            String pwd = cursor.getString(cursor.getColumnIndex("pwd"));
            list.add(name);
            list.add(pwd);
        }
        return list;
    }
}
