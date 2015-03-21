package com.example.ahmadlap.database;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class InsertStudentActivity extends ActionBarActivity {
    DatabaseUtility utility;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_student);
        utility = new DatabaseUtility(this);
        final EditText eTName = (EditText) this.findViewById(R.id.editText);
        final EditText eTAge= (EditText) this.findViewById(R.id.editText2);
        Button btnInsert = (Button) this.findViewById(R.id.btnInsert);

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Student student = new Student();
                student.setName(eTName.getText().toString());
                student.setAge(Integer.parseInt(eTAge.getText().toString()));
                utility.addStudent(student);
                eTName.setText("");
                eTAge.setText("");
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        utility.open();
    }

    @Override
    protected void onPause() {
        super.onPause();
        utility.close();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_insert_student, menu);
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
