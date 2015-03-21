package com.example.ahmadlap.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class MySqLiteHelper extends SQLiteOpenHelper {
    public MySqLiteHelper(Context context, String name, int version) {
        super(context, name,null, version);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table student (_id integer primary key Autoincrement not null, name text not null,age integer not null);");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table student");
        onCreate(db);
    }
}
