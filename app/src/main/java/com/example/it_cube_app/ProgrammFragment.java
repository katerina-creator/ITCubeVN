package com.example.it_cube_app;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.fragment.app.Fragment;

public class ProgrammFragment extends Fragment{
    private static final String ARG_PROGRAM_ID = "programm_id";
    private Programm mProgramm;
    private TextView mTitle;
    private TextView mAge;
    private TextView mDuration;
    private TextView mDescription;
    private ImageView mProgLogo;

//    public static ProgrammFragment newInstance(int programmId) {
//        Bundle args = new Bundle();
//        args.putSerializable(ARG_PROGRAM_ID, programmId);
//
//        ProgrammFragment fragment = new ProgrammFragment();
//        fragment.setArguments(args);
//        return fragment;
//    }

    @Override
    public void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
//        mProgramm = new Programm();
        int programId = (int)getActivity().getIntent()
                .getSerializableExtra(ProgrammActivity.EXTRA_PROGRAM_ID);
        mProgramm = ProgrammLab.get(getActivity()).getProgramm(programId);
    }

    //Связываем компонеты представления с версткой
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState){
        View v = inflater.inflate(R.layout.fragment_programm, container, false);

        mTitle = (TextView)v.findViewById(R.id.txtvTitle);
        mTitle.setText(mProgramm.getTitle());
        mProgLogo = (ImageView)v.findViewById(R.id.imgvProrammLogo);
        int resID = getResources().getIdentifier(mProgramm.getLogo(), "drawable", getActivity().getPackageName());
        mProgLogo.setImageResource(resID);
        mAge = (TextView)v.findViewById(R.id.txtvAge);
        mAge.setText(mProgramm.getAge());
        mDuration = (TextView)v.findViewById(R.id.txtvDuration);
        mDuration.setText(mProgramm.getDuration());
        mDescription = (TextView)v.findViewById(R.id.txtvDescription);
        mDescription.setText(mProgramm.getDescription());
        return v;
    }
}
