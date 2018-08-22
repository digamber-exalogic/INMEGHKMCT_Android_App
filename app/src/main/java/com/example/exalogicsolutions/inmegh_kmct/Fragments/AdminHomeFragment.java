package com.example.exalogicsolutions.inmegh_kmct.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.card.MaterialCardView;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.exalogicsolutions.inmegh_kmct.Activities.AdminMenuCollegeInfo;
import com.example.exalogicsolutions.inmegh_kmct.Activities.AdminMenuExam;
import com.example.exalogicsolutions.inmegh_kmct.Activities.AdminMenuHolidays;
import com.example.exalogicsolutions.inmegh_kmct.Activities.AdminMenuLeave;
import com.example.exalogicsolutions.inmegh_kmct.Activities.AdminMenuMail;
import com.example.exalogicsolutions.inmegh_kmct.Activities.AdminMenuSms;
import com.example.exalogicsolutions.inmegh_kmct.Activities.AdminMenuStudentAttendance;
import com.example.exalogicsolutions.inmegh_kmct.Activities.AdminMenuTimetable;
import com.example.exalogicsolutions.inmegh_kmct.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class AdminHomeFragment extends Fragment {

    private MaterialCardView crdLeave, crdAttendance, crdHolidays, crdTimetable, crdExam, crdEmail, crdSms, crdInfo;

    public AdminHomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_admin_dashboard, container, false);
        crdLeave = view.findViewById(R.id.crdLeave);
        crdAttendance = view.findViewById(R.id.crdAttendance);
        crdHolidays = view.findViewById(R.id.crdHolidays);
        crdTimetable = view.findViewById(R.id.crdTimetable);
        crdExam = view.findViewById(R.id.crdExam);
        crdEmail = view.findViewById(R.id.crdEmail);
        crdSms = view.findViewById(R.id.crdSms);
        crdInfo = view.findViewById(R.id.crdInfo);
        // Inflate the layout for this fragment

        crdLeave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getActivity(), AdminMenuLeave.class);
                startActivity(intent);
            }
        });

        crdAttendance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getActivity(), AdminMenuStudentAttendance.class);
                startActivity(intent);
            }
        });

        crdHolidays.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getActivity(), AdminMenuHolidays.class);
                startActivity(intent);
            }
        });

        crdTimetable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getActivity(), AdminMenuTimetable.class);
                startActivity(intent);
            }
        });

        crdExam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getActivity(), AdminMenuExam.class);
                startActivity(intent);
            }
        });

        crdEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getActivity(), AdminMenuMail.class);
                startActivity(intent);
            }
        });

        crdSms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getActivity(), AdminMenuSms.class);
                startActivity(intent);
            }
        });

        crdInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getActivity(), AdminMenuCollegeInfo.class);
                startActivity(intent);
            }
        });

        return view;
    }

}
