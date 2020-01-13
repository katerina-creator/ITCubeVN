package com.example.it_cube_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class EduProgActivity extends AppCompatActivity {
    ListView programmsList;
//    ListView BaseProgramsList;
//    ListView AdvProgramsList;
    private DataBaseHelper mDBHelper;
    public static SQLiteDatabase mDb;
    List<String> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_programms);

        programmsList = (ListView) findViewById(R.id.list);
//        BaseProgramsList = (ListView) findViewById(R.id.listBaseProgramms);
//        AdvProgramsList = (ListView) findViewById(R.id.listAdvProgramms);

        mDBHelper = new DataBaseHelper(this);
        Log.e("EduListActivity", mDBHelper.getDatabaseName());

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
//        Пробегаем курсором по всем записям таблицы tbl_Programms
        Cursor cursor = mDb.rawQuery("SELECT * FROM tbl_Programms", null);
        Log.e("EduListActivity", cursor.getCount() + "");
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            list.add(cursor.getString(1));
            cursor.moveToNext();
        }
        cursor.close();
        programmsList.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        Log.e("EduListActivity", list.size() + " -");
            Log.e("EduListActivity", adapter.toString());

//        BeginProgramsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            public void onItemClick(AdapterView<?> parent, View view,
//                                    int position, long id) {
//
//                Intent i = new Intent(getBaseContext(), EduProgDescriptionActivity.class);
//                i.putExtra("position", position);
//                startActivity(i);
//            }
//        });
//
//        BaseProgramsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            public void onItemClick(AdapterView<?> parent, View view,
//                                    int position, long id) {
//
//                Intent i = new Intent(getBaseContext(), EduProgDescriptionActivity.class);
//                i.putExtra("position", position);
//                startActivity(i);
//            }
//        });
//
//        AdvProgramsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            public void onItemClick(AdapterView<?> parent, View view,
//                                    int position, long id) {
//
//                Intent i = new Intent(getBaseContext(), EduProgDescriptionActivity.class);
//                i.putExtra("position", position);
//                startActivity(i);
//            }
//        });
    }

}
