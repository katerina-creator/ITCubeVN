package com.example.it_cube_app;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TeacherListFragment extends Fragment {
    //Переменная списка преподавателей
    private RecyclerView mTeacherRecycleView;
    //Переменная адаптера списка преподавателей
    private TeacherListFragment.TeacherAdapter mAdapter;

    //Создание представления на основе верстки fragment_teacher_list
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_teacher_list, container, false);
        mTeacherRecycleView = (RecyclerView)view.findViewById(R.id.teacher_recycler_view);
        mTeacherRecycleView.setLayoutManager(new LinearLayoutManager(getActivity()));

        //Настройка интерфейса TeacherListFragment
        updateUI();

        return view;
    }

    //Метод настройки пользовательского интерфейса TeacherListFragment
    private void updateUI(){
        //Создание экземпляра хранилища и получение списка преподавателей
        TeacherLab teacherLab = TeacherLab.get(getActivity());
        List<Teacher> teachers = teacherLab.getTeachers();
        Log.d("MyLog", teachers.size()+"");
        //Создание и установка адаптера списка
        mAdapter = new TeacherListFragment.TeacherAdapter(teachers);
        mTeacherRecycleView.setAdapter(mAdapter);
    }

    //Класс для храниения сслыки на представление одного преподавателя
    private class  TeacherHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private Teacher mTeacher;
        private TextView mFullnameTextView;
        private TextView mPhoneTextView;
        private ImageView mPhotoImageView;

        //Собираем наше представление на основе данных из Teacher
        public void bindTeacher(Teacher teacher) {
            mTeacher = teacher;
            mFullnameTextView.setText(teacher.getFullname());
            mPhoneTextView.setText("Телефон: "+teacher.getPhone());
            mPhoneTextView.setOnClickListener(onClickTeacherPhone);
            int resID = getResources().getIdentifier(teacher.getPhoto(), "drawable", getActivity().getPackageName());
            mPhotoImageView.setImageResource(resID);
        }

        //Метод нажатия на номер телефона
        private final View.OnClickListener onClickTeacherPhone = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" + mPhoneTextView.getText()));
                if (intent.resolveActivity(getActivity().getPackageManager()) != null) {
                    startActivity(intent);
                }
            }
        };

        //Связываем представление с разметкой list-item
        public TeacherHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            mFullnameTextView = (TextView)itemView.findViewById(R.id.list_item_txtvFullname);
            mPhoneTextView = (TextView)itemView.findViewById(R.id.list_item_txtvPhone);
            mPhotoImageView = (ImageView)itemView.findViewById(R.id.list_item_imgvPhoto);
        }
        //Обработка нажатия пункта списка преподавателей
        @Override
        public void onClick(View v) {
            Intent intent = TeacherActivity.newIntent(getActivity(), mTeacher.getId());
            startActivity(intent);
        }
    }

    //Класс адаптера списка преподавателей
    private class TeacherAdapter extends RecyclerView.Adapter<TeacherListFragment.TeacherHolder>{
        //Список программ
        private List<Teacher> mTeacher;

        //Конструктор адаптера
        public TeacherAdapter(List<Teacher> teachers){
            mTeacher = teachers;
        }

        //Вызывается виждетом RecyclerView для отображения списка преподавателей
        @NonNull
        @Override
        public TeacherListFragment.TeacherHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(R.layout.list_item_teacher, parent, false);
            return new TeacherListFragment.TeacherHolder(view);
        }

        //Метод, связывающий представление View объекта TeacherHolder с объектом Teacher
        @Override
        public void onBindViewHolder(@NonNull TeacherListFragment.TeacherHolder holder, int position) {
            Teacher teacher = mTeacher.get(position);
            holder.bindTeacher(teacher);
        }
        //Количество записей в списке
        @Override
        public int getItemCount() {
            return mTeacher.size();
        }
    }
}
