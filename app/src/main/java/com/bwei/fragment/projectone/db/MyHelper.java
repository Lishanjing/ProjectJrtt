package com.bwei.fragment.projectone.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static android.R.attr.version;

/**
 * Created by User on 2017/9/13.
 */

public class MyHelper extends SQLiteOpenHelper{
    public MyHelper(Context context) {
        super(context, "zc", null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table user(id integer primary key autoincrement,name text,pwd text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
