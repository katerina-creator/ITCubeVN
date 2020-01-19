package com.example.it_cube_app;

import androidx.fragment.app.Fragment;

public class ProgrammListActivity extends SingleFragmentActivity {

    //Создаем фрагмент списка программ
    @Override
    protected Fragment createFragment() {
        return new ProgrammListFragment();
    }
}
