package com.example.ahmadlap.database;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ahmad lap on 3/17/2015.
 */
public class ListAdapter extends BaseAdapter implements Serializable {
    Context context;
    int layoutId;
    List<Student> students;

    public ListAdapter(Context context, int layoutId, List<Student> student) {
        this.context = context;
        this.layoutId = layoutId;
        this.students = student;
    }

    @Override
    public int getCount() {
        return students.size();
    }

    @Override
    public Object getItem(int position) {
        return students.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layoutId, null);

            holder = new ViewHolder();
            holder.holdedName= (TextView) convertView.findViewById(R.id.tvName);
            holder.holdedAge = (TextView) convertView.findViewById(R.id.tvAge);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        holder.holdedName.setText(students.get(position).getName());
        holder.holdedAge.setText(students.get(position).getAge());

        return convertView;
    }

    static class ViewHolder{
        TextView holdedName;
        TextView holdedAge;
    }
}
