package com.example.ahmadlap.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

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
    public DatabaseUtility(Context context) {
        helper = new MySqLiteHelper(context,DB_NAME,DB_VERSION);
    }

    public void open() {
        db = helper.getWritableDatabase();
    }

    public void close() {
        if (db != null && db.isOpen()) {
            db.close();
            helper.close();
        }
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
}
