package com.example.exalogicsolutions.inmegh_kmct.Fragments.EmployeeFragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.exalogicsolutions.inmegh_kmct.Activities.EmployeeActivities.EmployeeMenuAssignment;
import com.example.exalogicsolutions.inmegh_kmct.Activities.EmployeeActivities.EmployeeMenuAttendance;
import com.example.exalogicsolutions.inmegh_kmct.Activities.EmployeeActivities.EmployeeMenuCoursePlan;
import com.example.exalogicsolutions.inmegh_kmct.Activities.EmployeeActivities.EmployeeMenuEmail;
import com.example.exalogicsolutions.inmegh_kmct.Activities.EmployeeActivities.EmployeeMenuExam;
import com.example.exalogicsolutions.inmegh_kmct.Activities.EmployeeActivities.EmployeeMenuLeave;
import com.example.exalogicsolutions.inmegh_kmct.Activities.EmployeeActivities.EmployeeMenuSeminar;
import com.example.exalogicsolutions.inmegh_kmct.Activities.EmployeeActivities.EmployeeMenuSms;
import com.example.exalogicsolutions.inmegh_kmct.Activities.EmployeeActivities.EmployeeMenuTimetable;
import com.example.exalogicsolutions.inmegh_kmct.Adapters.MenuAdapter;
import com.example.exalogicsolutions.inmegh_kmct.Models.Menu;
import com.example.exalogicsolutions.inmegh_kmct.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class EmployeeMenuFragment extends Fragment {
    MenuAdapter menuAdapter;
    ListView myModule_list;
    ArrayList<Menu> items;

    public EmployeeMenuFragment() {
        super();
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_employee_menu, container, false);
        // Inflate the layout for this fragment
        myModule_list = view.findViewById(R.id.list_id);

        items = new ArrayList<Menu>();

        items.add(new Menu("Attendance", R.drawable.ic_attendance));
        items.add(new Menu("Leave", R.drawable.ic_leave));
        items.add(new Menu("Assignment", R.drawable.ic_assignment));
        items.add(new Menu("Timetable", R.drawable.ic_alarm_black_24dp));
        items.add(new Menu("Exam", R.drawable.ic_exam_black_24dp));
        items.add(new Menu("Course Plan", R.drawable.ic_course_plan));
        items.add(new Menu("Seminar", R.drawable.ic_seminar));
        items.add(new Menu("Email", R.drawable.ic_email1_black_24dp));
        items.add(new Menu("Sms", R.drawable.ic_sms_black_24dp));

        menuAdapter = new MenuAdapter(getActivity(), items);
        /*Log.i("myModule_list3", String.valueOf(myModule_list));*/
        myModule_list.setAdapter(menuAdapter);

        myModule_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                if (position == 0) {

                    Intent intent = new Intent(getActivity(), EmployeeMenuAttendance.class);
                    startActivity(intent);
                } else if (position == 1) {

                    Intent intent = new Intent(getActivity(), EmployeeMenuLeave.class);
                    startActivity(intent);
                } else if (position == 2) {

                    Intent intent = new Intent(getActivity(), EmployeeMenuAssignment.class);
                    startActivity(intent);
                } else if (position == 3) {

                    Intent intent = new Intent(getActivity(), EmployeeMenuTimetable.class);
                    startActivity(intent);
                } else if (position == 4) {

                    Intent intent = new Intent(getActivity(), EmployeeMenuExam.class);
                    startActivity(intent);
                } else if (position == 5) {

                    Intent intent = new Intent(getActivity(), EmployeeMenuCoursePlan.class);
                    startActivity(intent);
                } else if (position == 6) {

                    Intent intent = new Intent(getActivity(), EmployeeMenuSeminar.class);
                    startActivity(intent);
                } else if (position == 7) {

                    Intent intent = new Intent(getActivity(), EmployeeMenuEmail.class);
                    startActivity(intent);
                } else if (position == 8) {

                    Intent intent = new Intent(getActivity(), EmployeeMenuSms.class);
                    startActivity(intent);
                }
            }
        });

        return view;
    }

}
