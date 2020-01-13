package com.example.it_cube_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class TeachersActivity extends AppCompatActivity {
    final String LOG_TAG = "Teacher_Log";
    private DataBaseHelper mDBHelper;
    public static SQLiteDatabase mDb;
    List<String> list;
    ListView teacherList;
//    ListTeacherAdapter adapter;
//    Image photo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teachers);

        teacherList = (ListView)findViewById(R.id.list);

        mDBHelper = new DataBaseHelper(this);
        Log.e(LOG_TAG, mDBHelper.getDatabaseName());

        try {
            mDBHelper.updateDataBase();
        } catch (IOException mIOException) {
            throw new Error("Невозможно обновить базу");
        }

        try {
            mDb = mDBHelper.getWritableDatabase();
        } catch (SQLException mSQLException) {
            throw mSQLException;
        }

        list = new ArrayList<String>();
        ArrayAdapter<String> adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, list);
//        adapter = new ListTeacherAdapter(this, list);
//        Пробегаем курсором по всем записям таблицы tbl_Teacher
        Cursor cursor = mDb.rawQuery("SELECT * FROM tbl_Teacher", null);
        Log.e(LOG_TAG, cursor.getCount() + "");
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
//            int _id = cursor.getInt(0);
//            String fullname = cursor.getString(1);
//            String phone = cursor.getString(2);
//            String filenamePhoto = "drawable/teacher"+_id;
//            photo =
//            list.add(new Teacher(_id, fullname, phone,  photo));
            list.add(cursor.getString(1));
            Log.e(LOG_TAG, cursor.getColumnCount() + "");
            cursor.moveToNext();
        }
        cursor.close();
        teacherList.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        Log.e(LOG_TAG, list.size() + " -");
        Log.e(LOG_TAG, adapter.toString());

        teacherList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Log.d(LOG_TAG, "itemSelect: position = " + position + ", id = " + id);

                Intent i = new Intent(getBaseContext(), TeacherPortfolioActivity.class);
                i.putExtra("position", position);
                i.putExtra("_id", id);
                startActivity(i);
            }
        });
    }

}
