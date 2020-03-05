package com.example.it_cube_app;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

//Синглетный класс - хранилище  списка преподавателей
public class TeacherLab {
    final String LOG_TAG = "TeacherLab_Log";
    Context mAppContext;
    private DataBaseHelper mDBHelper;
    private SQLiteDatabase mDb;
    private static TeacherLab sTeacherLab;
    //Упорядоченный список преподавателей
    private List<Teacher> mTeachers;

    //Метод для вызова констуктора
    public static TeacherLab get(Context context) {
        if (sTeacherLab ==null) {
            sTeacherLab = new TeacherLab(context);
        }
        return sTeacherLab;
    }
    //Конструктор списка преподавателей
    private TeacherLab(Context context) {
        mAppContext = context;
        mTeachers = new ArrayList<>();
        //Создаем БД
        mDBHelper = new DataBaseHelper(mAppContext);
        Log.e(LOG_TAG, mDBHelper.getDatabaseName());
        //Обновляем, если нужно
        try {
            mDBHelper.updateDataBase();
        } catch (IOException mIOException) {
            throw new Error("Невозможно обновить базу");
        }
        //Открываем БД
        try {
            mDb = mDBHelper.getWritableDatabase();
        } catch (SQLException mSQLException) {
            throw mSQLException;
        }
        //        Пробегаем курсором по всем записям таблицы tbl_Teachers
        Cursor cursor = mDb.rawQuery("SELECT * FROM tbl_Teachers", null);
        Log.e(LOG_TAG, cursor.getCount() + "");
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Teacher  nTeacher= new Teacher();
            nTeacher.setId(cursor.getInt(0));
            nTeacher.setFullname(cursor.getString(1));
            nTeacher.setPhone(cursor.getString(2));
            nTeacher.setEducation(cursor.getString(3));
            nTeacher.setProgress(cursor.getString(4));
            nTeacher.setPhoto(cursor.getString(5));
            mTeachers.add(nTeacher);
            Log.e(LOG_TAG, nTeacher.getFullname());
            cursor.moveToNext();
        }
        cursor.close();
    }

    public List<Teacher> getTeachers() {
        return mTeachers;
    }

    public Teacher getTeacher(int id) {
        for (Teacher teacher: mTeachers){
            if (teacher.getId() ==id) {
                return teacher;
            }
        }
        return null;
    }
}
