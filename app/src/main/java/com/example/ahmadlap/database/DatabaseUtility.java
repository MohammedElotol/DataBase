package com.example.ahmadlap.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Ahmad lap on 3/16/2015.
 */
public class DatabaseUtility {
    public static final String DB_NAME = "name";
    public static final int DB_VERSION = 4;
    public static  String STUDENT_TABLE = "student";
    public static final String STUDENT_ID = "_id";
    public static final String STUDENT_NAME = "name";
    public static final String STUDENT_AGE = "age";
    SQLiteDatabase db;
    MySqLiteHelper helper;
    String destPath;
    public DatabaseUtility(Context context) {
        destPath = "/data/data/" + context.getPackageName() + "/databases";
        try {
        File f = new File(destPath);
        if (!f.exists()) {
            f.mkdirs();
            f.createNewFile();
           copyDB(context.getAssets().open("studentDB"), new FileOutputStream(destPath + "/studentDB"));
        }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void open() {

        db = SQLiteDatabase.openDatabase(destPath + "/studentDB", null, SQLiteDatabase.OPEN_READWRITE);
    }

    public void close() {
        if (db.isOpen())
            db.close();
    }
public int getDate(){
    Calendar calendar = Calendar.getInstance();
    calendar.get(Calendar.DATE);
    calendar.get(Calendar.MONTH);
    calendar.get(Calendar.DAY_OF_MONTH);
    calendar.get(Calendar.DAY_OF_WEEK);
    calendar.get(Calendar.HOUR_OF_DAY);
    calendar.get(Calendar.HOUR);
    calendar.set(Calendar.YEAR,1990);

    long time = calendar.getTimeInMillis();

    Calendar c2 = Calendar.getInstance();
    c2.setTimeInMillis(time);


    return 0;
}

    public void setTime(long time) {
        Calendar c2 = Calendar.getInstance();
        c2.setTimeInMillis(time);
    }

    public Cursor searchStudent(String filterName) {
        return db.query(STUDENT_TABLE, new String[]{STUDENT_NAME,STUDENT_ID}, STUDENT_NAME+" like ? ",new String[]{"%"+filterName+"%"}, null, null, null);
//        return db.rawQuery("select " + STUDENT_ID + "," + STUDENT_NAME + " from " + STUDENT_TABLE + " where " + STUDENT_NAME + " like '%" + filterName + "%'",null);
    }

    public long addStudent(Student student) {
        ContentValues values = new ContentValues();
        values.put(STUDENT_NAME,student.getName());
        values.put(STUDENT_AGE,student.getAge());
        return db.insert(STUDENT_TABLE, null, values);
    }

    public long deleteStudent(int id) {
        return db.delete(STUDENT_TABLE, STUDENT_ID + "="+id+"",null);


    }

    public Cursor getAllStudents(){
        List<Student> students = new ArrayList<Student>();
//        String[] allColumns = {STUDENT_ID,STUDENT_NAME,STUDENT_AGE};
         return db.query(STUDENT_TABLE, null, null, null, null, null,null);
        /*cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            Student std = new Student();
            std.setId(cursor.getInt(0));
            std.setName(cursor.getString(1));
            std.setAge(cursor.getInt(2));
            students.add(std);
            cursor.moveToNext();
        }
        cursor.close();
        return students;*/
    }

    public void copyDB(InputStream inputStream, OutputStream outputStream) throws IOException {
        //---copy 1K bytes at a time---
        byte[] buffer = new byte[1024];
        int length;
        while ((length = inputStream.read(buffer)) > 0) {
            outputStream.write(buffer, 0, length);
        }
        inputStream.close();
        outputStream.close();

    }
}
