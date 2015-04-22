package com.example.ahmadlap.database;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

/**
 * Created by Ahmad lap on 4/21/2015.
 */
public class MyStudentProvider extends ContentProvider {
    public static final int STUDENT_TABLE = 1;
    public static final int STUDENT_RECORD = 2;
    public static final int COURSE_TABLE = 3;
    public static final int COURSE_RECORD = 4;
    SQLiteDatabase db;
    MySqLiteHelper helper;
    UriMatcher uriMatcher;
    @Override
    public boolean onCreate() {
        new MySqLiteHelper(this.getContext(),DatabaseUtility.DB_NAME,DatabaseUtility.DB_VERSION);
        db = helper.getWritableDatabase();
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI("StudentProvider", DatabaseUtility.STUDENT_TABLE, STUDENT_TABLE);
        uriMatcher.addURI("StudentProvider", DatabaseUtility.STUDENT_TABLE+"/#", STUDENT_RECORD);
        uriMatcher.addURI("StudentProvider", "course", COURSE_TABLE);
        uriMatcher.addURI("StudentProvider", "course"+"/#", COURSE_RECORD);
        return false;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        int result = uriMatcher.match(uri);
        if(result == MyStudentProvider.COURSE_TABLE) {
            return db.query(DatabaseUtility.STUDENT_TABLE, projection, selection, selectionArgs, null, null, null);
        }
        return null;
    }

    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        long id = -1;
        int result = uriMatcher.match(uri);
        if ( result == MyStudentProvider.STUDENT_TABLE){

            db.insert(DatabaseUtility.STUDENT_TABLE, null, values);
        }else if (result == MyStudentProvider.STUDENT_RECORD){

        }else if (result == MyStudentProvider.COURSE_TABLE){

        }else if (result == MyStudentProvider.COURSE_RECORD){

        }else {
            throw new IllegalArgumentException("Invalid Uri");
        }



        return Uri.parse(uri.toString()+"/"+id);
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }
}
