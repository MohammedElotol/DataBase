package com.example.ahmadlap.database;

import android.database.Cursor;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.FilterQueryProvider;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;


public class SearchName extends ActionBarActivity {
    DatabaseUtility utility;
    ListView listView;
    SimpleCursorAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_name);
        utility = new DatabaseUtility(this);
        utility.open();
        Cursor cursor = utility.getAllStudents();

        listView = (ListView) this.findViewById(R.id.listSearch);
        adapter = new SimpleCursorAdapter(this, R.layout.item_list, cursor, new String[]{DatabaseUtility.STUDENT_NAME,DatabaseUtility.STUDENT_ID},new int[]{R.id.tvName,R.id.tvAge});
        adapter.setFilterQueryProvider(new FilterQueryProvider() {
            @Override
            public Cursor runQuery(CharSequence constraint) {
                return utility.searchStudent(constraint.toString());

            }
        });
        listView.setAdapter(adapter);

        EditText etSearch = (EditText) this.findViewById(R.id.editText3);
        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String filterText = s.toString();
                adapter.getFilter().filter(filterText);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        utility.close();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_search_name, menu);
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
