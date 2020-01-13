package com.example.it_cube_app;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.lang.reflect.Field;

public class TeacherPortfolioActivity  extends Activity{
    int _id;
    int position;
    ImageView imageView;
    TextView txtFIO;
    TextView txtPhone;
    String fullname;
    String lastname;
    String firstname;
    String middlename;
    String education;
    String progress;
    final String LOG_TAG = "Teacher_Log";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_portfolio);

        final Intent intent = getIntent();
        position= intent.getIntExtra("position", 0);
        _id= intent.getIntExtra("_id", 0);

        Log.d(LOG_TAG, "itemSelect: position 2 = " + position + ", id = " + _id);

        String[] arrTeachers = getResources().getStringArray(R.array.teachers);
        txtFIO = (TextView)findViewById(R.id.txt_FIO);
//        Log.d(LOG_TAG, arrTeachers[_id]);
        txtFIO.setText(arrTeachers[position]);

        String[] arrPhone = getResources().getStringArray(R.array.teachers_phone);
        txtPhone = (TextView)findViewById(R.id.txt_PhoneNumber);
        final String phoneNumber = arrPhone[position];
        txtPhone.setText(phoneNumber);

        txtPhone.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" + phoneNumber));
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                }
            }
        });


        imageView = findViewById(R.id.imgv_Photo);
        String filenamePhoto = "drawable/teacher"+position;
        Log.d(LOG_TAG, filenamePhoto);
        int resID = getResources().getIdentifier(filenamePhoto, "drawable", getApplicationContext().getPackageName());
        imageView.setImageResource(resID);


    }

    public static int getResId(String resName, Class<?> c) {

        try {
            Field idField = c.getDeclaredField(resName);
            return idField.getInt(idField);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

}
