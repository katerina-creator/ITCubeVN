package com.example.it_cube_app;

import androidx.fragment.app.Fragment;

public class TeacherListActivity  extends SingleFragmentActivity {

    //Создаем фрагмент списка преподавателей
    @Override
    protected Fragment createFragment() {
        return new TeacherListFragment();
    }
}
