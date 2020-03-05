package com.example.it_cube_app;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class TeacherFragment extends Fragment {
    private static final String ARG_TEACHER_ID = "teacher_id";
    private Teacher mTeacher;
    private TextView mFullname;
    private TextView mPhone;
    private TextView mEducation;
    private TextView mProgress;
    private ImageView mPhoto;

    @Override
    public void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
//        mTeacher= new Teacher();
        int teacherId = (int)getActivity().getIntent()
                .getSerializableExtra(TeacherActivity.EXTRA_TEACHER_ID);
        mTeacher = TeacherLab.get(getActivity()).getTeacher(teacherId);
    }

    //Связываем компонеты представления с версткой
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState){
        View v = inflater.inflate(R.layout.fragment_teacher, container, false);

        mPhoto = (ImageView)v.findViewById(R.id.imgvTeacherPhoto);
        int resID = getResources().getIdentifier(mTeacher.getPhoto(), "drawable", getActivity().getPackageName());
        mPhoto.setImageResource(resID);
        mFullname = (TextView)v.findViewById(R.id.txtvFullname);
        mFullname.setText(mTeacher.getFullname());
        mPhone = (TextView)v.findViewById(R.id.txtvPhone);
        mPhone.setText(mTeacher.getPhone());
        mPhone.setOnClickListener(onClickTeacherPhone);
        mEducation = (TextView)v.findViewById(R.id.txtvEducation);
        mEducation.setText(mTeacher.getEducation());
        mProgress = (TextView)v.findViewById(R.id.txtvProgress);
        mProgress.setText(mTeacher.getProgress());
        return v;
    }

    //Метод нажатия на номер телефона
    private final View.OnClickListener onClickTeacherPhone = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.parse("tel:" + mPhone.getText()));
            if (intent.resolveActivity(getActivity().getPackageManager()) != null) {
                startActivity(intent);
            }
        }
    };
}
