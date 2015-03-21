package com.example.ahmadlap.database;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.SimpleCursorAdapter;

import java.util.ArrayList;
import java.util.List;


public class ViewStudentList extends ActionBarActivity {
    DatabaseUtility utility;
    ListView listView;

    Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_student_list);
        utility = new DatabaseUtility(this);
        listView = (ListView) this.findViewById(R.id.listView);

    }

    @Override
    protected void onResume() {
        super.onResume();
        utility.open();
         cursor = utility.getAllStudents();
        final SimpleCursorAdapter cursorAdapter = new SimpleCursorAdapter(this, R.layout.item_list, cursor, new String[]{DatabaseUtility.STUDENT_NAME,DatabaseUtility.STUDENT_AGE},new int[]{R.id.tvName,R.id.tvAge});
        listView.setAdapter(cursorAdapter);
        /*
        listView = (ListView) this.findViewById(R.id.listView);
        adapter= new ListAdapter(this, R.id.list_item, students);
        listView.setAdapter(adapter);
*/
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                PopupMenu menu = new PopupMenu(ViewStudentList.this, view);
                menu.inflate(R.menu.menu_options);
                menu.show();
                menu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        if (item.getItemId() == R.id.popup_delete) {
                            Cursor c = (Cursor) listView.getItemAtPosition(position);
                            int studentId = c.getInt(0);
                            utility.deleteStudent(studentId);
                            cursorAdapter.changeCursor(utility.getAllStudents());
                            return true;
                        }
                        return false;
                    }
                });
                return true;
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        utility.close();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_view_student_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
