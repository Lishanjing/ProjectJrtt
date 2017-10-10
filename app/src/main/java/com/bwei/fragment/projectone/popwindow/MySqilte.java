package com.bwei.fragment.projectone.popwindow;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by User on 2017/9/13.
 */

public class MySqilte extends SQLiteOpenHelper{
    public MySqilte(Context context) {
        super(context, "item", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
          sqLiteDatabase.execSQL("create table item(id integer primary key autoincrement,title text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
