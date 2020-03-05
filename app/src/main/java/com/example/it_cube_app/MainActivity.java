package com.example.it_cube_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


public class MainActivity extends FragmentActivity {
    TextView txt_phone;
    ImageView imgv_Instagram;
    ImageView imgv_VK;
    ImageView imgv_Site;
    Button btn_Teachers;
    Button btn_Programms;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Обработка нажатия кнопки с программами
        btn_Programms = (Button)findViewById(R.id.btn_Programms);
        btn_Programms.setOnClickListener(onProgrammsClickListener);

        // Обработка нажатия кнопки с учителями
        btn_Teachers = (Button)findViewById(R.id.btn_Teachers);
        btn_Teachers.setOnClickListener(onTeachersClickListener);

        // Обработка нажатия телефонного номера
        txt_phone = (TextView)findViewById(R.id.txt_phone);
        txt_phone.setOnClickListener(onPhoneClickListener);

        // Обработка нажатия иконки инстаграмм
        imgv_Instagram = (ImageView)findViewById(R.id.imgvw_Instagram);
        imgv_Instagram.setOnClickListener(onInstaClickListener);

        // Обработка нажатия иконки ВК
        imgv_Site = (ImageView)findViewById(R.id.imgvw_VK);
        imgv_Site.setOnClickListener(onVkClickListener);

        // Обработка нажатия иконки сайта
        imgv_Site = (ImageView)findViewById(R.id.imgvw_Site);
        imgv_Site.setOnClickListener(onSiteClickListener);
    }

    private final View.OnClickListener onProgrammsClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(getApplicationContext(),EduProgActivity.class);
            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivityForResult(intent,1);
            }
        }
    };

    private final View.OnClickListener onTeachersClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(getApplicationContext(),TeachersActivity.class);
            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivityForResult(intent,1);
            }
        }
    };

    private final View.OnClickListener onInstaClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.uriInstagram)));
            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivity(intent);
            }
        }
    };

    private final View.OnClickListener onPhoneClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.parse("tel:" + txt_phone.getText()));
            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivity(intent);
            }
        }
    };

    private final View.OnClickListener onVkClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new  Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.uriVk)));
            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivity(intent);
            }
        }
    };

    private final View.OnClickListener onSiteClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new   Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.uriSchool)));
            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivity(intent);
            }
        }
    };
}
