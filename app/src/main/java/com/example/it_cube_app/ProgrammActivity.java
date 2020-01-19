package com.example.it_cube_app;


import android.content.Context;
import android.content.Intent;

import androidx.fragment.app.Fragment;

public class ProgrammActivity extends SingleFragmentActivity {
    public static final String EXTRA_PROGRAM_ID =
            "com.bugnerdranch.android.programmintent.programm_id";
//
    public static Intent newIntent(Context packageContext, int programmId) {
        Intent intent = new Intent(packageContext, ProgrammActivity.class);
        intent.putExtra(EXTRA_PROGRAM_ID, programmId);
        return intent;
    }

    //Создаем фрагмент описания программы
    @Override
    protected Fragment createFragment() {
       return new ProgrammFragment();
    }
}
