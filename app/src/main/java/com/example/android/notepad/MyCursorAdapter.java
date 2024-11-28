package com.example.android.notepad;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

public class MyCursorAdapter extends SimpleCursorAdapter {
    public MyCursorAdapter(Context context, int layout, Cursor c,
                           String[] from, int[] to) {
        super(context, layout, c, from, to);
    }
    @Override
    public void bindView(View view, Context context, Cursor cursor){
        super.bindView(view, context, cursor);

        // 获取数据并绑定到视图
        TextView titleView = (TextView) view.findViewById(android.R.id.text1);
        TextView timeView = (TextView) view.findViewById(R.id.timestamp);

        String title = cursor.getString(cursor.getColumnIndex(NotePad.Notes.COLUMN_NAME_TITLE));
        String modificationDate = cursor.getString(cursor.getColumnIndex(NotePad.Notes.COLUMN_NAME_MODIFICATION_DATE));

        titleView.setText(title);
        timeView.setText(modificationDate);

    }
    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        // 创建新的视图
        LayoutInflater inflater = LayoutInflater.from(context);
        return inflater.inflate(R.layout.noteslist_item, parent, false);
    }
}