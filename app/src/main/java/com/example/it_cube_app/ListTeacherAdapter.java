package com.example.it_cube_app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

public class ListTeacherAdapter extends BaseAdapter {
    Context cntx;
    ArrayList<Teacher> objects;
    LayoutInflater lInflater;

    public ListTeacherAdapter(Context cntx, ArrayList<Teacher> objects) {
        this.cntx = cntx;
        this.objects = objects;
        lInflater = (LayoutInflater) cntx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    // кол-во элементов
    @Override
    public int getCount() {
        return objects.size();
    }
    // элемент по позиции
    @Override
    public Object getItem(int position) {
        return objects.get(position);
    }

    // id по позиции
    @Override
    public long getItemId(int position) {
        return position;
    }
    // учитель по позиции
    Teacher getTeacher(int position) {
        return ((Teacher) getItem(position));
    }

    // пункт списка
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // используем созданные, но не используемые view
        View view = convertView;
        if (view == null) {
            view = lInflater.inflate(R.layout.activity_teacher_portfolio, parent, false);
        }

        Teacher p = getTeacher(position);

        // заполняем View в пункте списка данными из товаров: наименование, цена
        // и картинка
        ((TextView) view.findViewById(R.id.txt_FIO)).setText(p.fullname);
        ((TextView) view.findViewById(R.id.txt_Phone)).setText(p.phone);
//        ((ImageView) view.findViewById(R.id.ivImage)).setImageResource(p.image);

        return view;
    }

}
