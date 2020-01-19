package com.example.it_cube_app;

import android.content.Intent;
import android.icu.text.UnicodeSetSpanner;
import android.os.Bundle;
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

//Класс для списка программ
public class ProgrammListFragment extends Fragment {
    //Переменная списка программ
    private RecyclerView mProgrammRecycleView;
    //Переменная адаптера списка программ
    private ProgrammAdapter mAdapter;

    //Создание представления на основе верстки fragment_programm_list
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_programm_list, container, false);
        mProgrammRecycleView = (RecyclerView)view.findViewById(R.id.programm_recycler_view);
        mProgrammRecycleView.setLayoutManager(new LinearLayoutManager(getActivity()));

        //Настройка интерфейса ProgrammListFragment
        updateUI();

        return view;
    }

    //Метод настройки пользовательского интерфейса ProgrammListFragment
    private void updateUI(){
        //Создание экземпляра хранилища и получение списка программ
        ProgrammLab programmLab = ProgrammLab.get(getActivity());
        List<Programm> programms = programmLab.getProgramms();
        //Создание и установка адаптера списка
        mAdapter = new ProgrammAdapter(programms);
        mProgrammRecycleView.setAdapter(mAdapter);
    }

    //Класс для храниения сслыки на представление одной программы
    private class  ProgrammHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private Programm mProgram;
        private TextView mTitleTextView;
        private TextView mAgeTextView;
        private ImageView mLogoImageView;

        //Собираем наше представление на основе данных из Programm
        public void bindProgramm(Programm programm) {
            mProgram = programm;
            mTitleTextView.setText(programm.getTitle());
            mAgeTextView.setText("Возраст: "+programm.getAge());
            int resID = getResources().getIdentifier(programm.getLogo(), "drawable", getActivity().getPackageName());
            mLogoImageView.setImageResource(resID);
        }

        //Связываем представление с разметкой list-item
        public ProgrammHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            mTitleTextView = (TextView)itemView.findViewById(R.id.list_item_txtvTitle);
            mAgeTextView = (TextView)itemView.findViewById(R.id.list_item_txtvAge);
            mLogoImageView = (ImageView)itemView.findViewById(R.id.list_item_imgvLogo);
        }
        //Обработка нажатия пункта списка программ
        @Override
        public void onClick(View v) {
            Intent intent = ProgrammActivity.newIntent(getActivity(), mProgram.getId());
            startActivity(intent);
        }
    }

    //Класс адаптера списка программ
    private class ProgrammAdapter extends RecyclerView.Adapter<ProgrammHolder>{
        //Список программ
        private List<Programm> mProgramms;

        //Конструктор адаптера
        public ProgrammAdapter(List<Programm> programms){
            mProgramms = programms;
        }

        //Вызывается виждетом RecyclerView для отображения списка программ
        @NonNull
        @Override
        public ProgrammHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(R.layout.list_item_programm, parent, false);
            return new ProgrammHolder(view);
        }

        //Метод, связывающий представление View объекта ProgrammHolder с объектом Programm
        @Override
        public void onBindViewHolder(@NonNull ProgrammHolder holder, int position) {
            Programm programm = mProgramms.get(position);
            holder.bindProgramm(programm);
        }
        //Количество записей в списке
        @Override
        public int getItemCount() {
            return mProgramms.size();
        }
    }

}
