package com.example.it_cube_app;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.service.autofill.LuhnChecksumValidator;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

//Синглетный класс - хранилище  списка программ
public class ProgrammLab {
    final String LOG_TAG = "ProgrammLab_Log";
    Context mAppContext;
    private DataBaseHelper mDBHelper;
    private SQLiteDatabase mDb;
    private static ProgrammLab sProgrammLab;
    //Упорядоченный список программ
    private List<Programm> mProgramms;

    //Метод для вызова констуктора
    public static ProgrammLab get(Context context) {
        if (sProgrammLab ==null) {
            sProgrammLab = new ProgrammLab(context);
        }
        return sProgrammLab;
    }
    //Конструктор списка программ
    private ProgrammLab(Context context) {
        mAppContext = context;
        mProgramms = new ArrayList<>();
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
        //        Пробегаем курсором по всем записям таблицы tbl_Programms
        Cursor cursor = mDb.rawQuery("SELECT * FROM tbl_Programms", null);
        Log.e(LOG_TAG, cursor.getCount() + "");
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Programm  nProgramm = new Programm();
            nProgramm.setId(cursor.getInt(0));
            nProgramm.setTitle(cursor.getString(1));
            nProgramm.setAge(cursor.getString(2));
            nProgramm.setDuration(cursor.getString(3));
            nProgramm.setDescription(cursor.getString(4));
            nProgramm.setLogo(cursor.getString(5));
            mProgramms.add(nProgramm);
            Log.e(LOG_TAG, nProgramm.getTitle());
            cursor.moveToNext();
        }
        cursor.close();
    }

    public List<Programm> getProgramms() {
        return mProgramms;
    }

    public Programm getProgramm(int id) {
        for (Programm programm: mProgramms){
            if (programm.getId() ==id) {
                return programm;
            }
        }
        return null;
    }
}
