package com.example.it_cube_app;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

//Абстрактный класс активности
public abstract class SingleFragmentActivity extends FragmentActivity {
    //Абстрактный метод для создания экземпляра фрагмента
    protected  abstract Fragment createFragment();

    @Override
    public void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_fragment);
        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.fragmentContainer);

        if (fragment ==null) {
            //Вызов абстрактного метода createFragment(), который объявлен выше
            fragment = createFragment();
            fm.beginTransaction().add(R.id.fragmentContainer, fragment).commit();
        }
    }
}
