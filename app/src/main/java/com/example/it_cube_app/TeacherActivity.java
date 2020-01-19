package com.example.it_cube_app;

import android.content.Context;
import android.content.Intent;

import androidx.fragment.app.Fragment;

public class TeacherActivity extends SingleFragmentActivity {
    public static final String EXTRA_TEACHER_ID =
            "com.bugnerdranch.android.teacherintent.teacher_id";
    //
    public static Intent newIntent(Context packageContext, int teacherId) {
        Intent intent = new Intent(packageContext, TeacherActivity.class);
        intent.putExtra(EXTRA_TEACHER_ID, teacherId);
        return intent;
    }

    //Создаем фрагмент описания преподавателей
    @Override
    protected Fragment createFragment() {
        return new TeacherFragment();
    }
}
