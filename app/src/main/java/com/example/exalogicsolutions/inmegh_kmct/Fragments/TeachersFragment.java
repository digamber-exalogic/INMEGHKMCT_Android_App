package com.example.exalogicsolutions.inmegh_kmct.Fragments;


import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.card.MaterialCardView;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.exalogicsolutions.inmegh_kmct.Adapters.SmsAdapters.TeachersAdapter;
import com.example.exalogicsolutions.inmegh_kmct.Api.RetrofitAPI;
import com.example.exalogicsolutions.inmegh_kmct.Models.EmailResponse.MailResponse;
import com.example.exalogicsolutions.inmegh_kmct.Models.SmsResponse.CourseResponse.Course;
import com.example.exalogicsolutions.inmegh_kmct.Models.SmsResponse.CourseResponse.CourseResponse;
import com.example.exalogicsolutions.inmegh_kmct.Models.SmsResponse.DepartmentResponse.Department;
import com.example.exalogicsolutions.inmegh_kmct.Models.SmsResponse.DepartmentResponse.DepartmentResponse;
import com.example.exalogicsolutions.inmegh_kmct.Models.SmsResponse.TeachingResponse.TeacherSentSmsResponse;
import com.example.exalogicsolutions.inmegh_kmct.Models.SmsResponse.TeachingResponse.Teaching;
import com.example.exalogicsolutions.inmegh_kmct.Models.SmsResponse.TeachingResponse.TeachingResponse;
import com.example.exalogicsolutions.inmegh_kmct.R;
import com.example.exalogicsolutions.inmegh_kmct.Utilities.Constants;
import com.example.exalogicsolutions.inmegh_kmct.Utilities.UIUtil;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class TeachersFragment extends Fragment {
    private AppCompatSpinner spinCourse, spinDepartment;
    /*private ArrayList<Teaching> employeeDetailArrayList;*/
    private ArrayList<Course> courseResponseArrayList;
    private ArrayList<Department> departmentResponseArrayList;
    private ArrayList<String> courseArraylist, departmentArrayList;
    private AppCompatCheckBox chkAll;
    private RecyclerView rcvSmsTeacher;
    private TeachersAdapter teachersAdapter;
    private ArrayList<Teaching> teacherArraylist, searchArraylist;
    private FloatingActionButton flbCreateSms;
    private TextInputEditText etSmsCompose;
    /*private ArrayList<String> items, number;
    private int spinpos;*/
    private ArrayAdapter<String> departmentAdapter, batchAdapter;
    /*private ArrayList<String> departmentArraylist;
    private ArrayList<String> batchArraylist;*/
    /*private int editpos;*/
    private MaterialCardView llAllcheck;
    private JsonArray presentItem;
    private ArrayAdapter<String> courseAdapter;
    private String depart = "", course, batc = "", department;


    public TeachersFragment() {
        super();
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_teachers, container, false);

        spinCourse = view.findViewById(R.id.spinCourse);
        spinDepartment = view.findViewById(R.id.spinDepartment);
        chkAll = view.findViewById(R.id.chkAll);
        rcvSmsTeacher = view.findViewById(R.id.rcvSmsTeacher);
        flbCreateSms = view.findViewById(R.id.flbCreateSms);
        llAllcheck = view.findViewById(R.id.llAllcheck);

        rcvSmsTeacher.setHasFixedSize(true);
        rcvSmsTeacher.setLayoutManager(new LinearLayoutManager(getActivity()));

        courseArraylist = new ArrayList<>();
        departmentArrayList = new ArrayList<>();
        teacherArraylist = new ArrayList<>();
        presentItem = new JsonArray();

        courseResponseArrayList = new ArrayList<>();
        departmentResponseArrayList = new ArrayList<>();

        teachersAdapter = new TeachersAdapter(getActivity(), teacherArraylist);
        rcvSmsTeacher.setAdapter(teachersAdapter);

        courseArraylist.clear();
        courseArraylist.add("Select Course");
        courseAdapter = new ArrayAdapter<String>(Objects.requireNonNull(getActivity()), R.layout.spinner_text_color, courseArraylist);
        courseAdapter.setDropDownViewResource(R.layout.spinnertext);
        spinCourse.setAdapter(courseAdapter);
        courseAdapter.notifyDataSetChanged();
        /*teachersAdapter.notifyDataSetChanged();*/

        departmentArrayList.clear();
        departmentArrayList.add("Select Department");
        departmentAdapter = new ArrayAdapter<String>(getActivity(), R.layout.spinner_text_color, departmentArrayList);
        departmentAdapter.setDropDownViewResource(R.layout.spinnertext);
        spinDepartment.setAdapter(departmentAdapter);
        departmentAdapter.notifyDataSetChanged();

        getCourse();

        flbCreateSms.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View view) {

                sendSms();
            }
        });

        chkAll.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                presentItem = new JsonArray();

                if (isChecked) {

                    for (int i = 0; i < teacherArraylist.size(); i++) {
                        teacherArraylist.get(i).setCheck_box(1);
                        presentItem.add(new JsonPrimitive(teacherArraylist.get(i).getId().toString()));
                        Log.e("data3", "===" + presentItem);
                    }
                    teachersAdapter.notifyDataSetChanged();
                } else {
                    for (int i = 0; i < teacherArraylist.size(); i++) {
                        teacherArraylist.get(i).setCheck_box(2);
                        presentItem.remove(new JsonPrimitive(teacherArraylist.get(i).getId().toString()));
                        Log.e("data4", "===" + presentItem);

                    }
                    teachersAdapter.notifyDataSetChanged();
                }
                if (presentItem.size() <= 0) {
                    flbCreateSms.setVisibility(View.GONE);
                } else {
                    flbCreateSms.setVisibility(View.VISIBLE);
                }
            }
        });

        teachersAdapter.SetOnItemClickListener(new TeachersAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

            }

            @Override
            public void oncheakboxClick(View view, int position) {

                if (teacherArraylist.get(position).getCheck_box() == 2) {
                    teacherArraylist.get(position).setCheck_box(1);

                    presentItem.add(new JsonPrimitive(teacherArraylist.get(position).getId().toString()));

                    teachersAdapter.notifyDataSetChanged();

                    Log.e("data1", "===" + presentItem);

                } else {
                    teacherArraylist.get(position).setCheck_box(2);

                    presentItem.remove(new JsonPrimitive(teacherArraylist.get(position).getId().toString()));
                    teachersAdapter.notifyDataSetChanged();

                    Log.e("data2", "===" + presentItem);
                }
                if (presentItem.size() <= 0) {
                    flbCreateSms.setVisibility(View.GONE);
                } else {
                    flbCreateSms.setVisibility(View.VISIBLE);
                }
            }
        });

        return view;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private AlertDialog sendSms() {


        AlertDialog.Builder builder = new AlertDialog.Builder(Objects.requireNonNull(getActivity()));
        builder.setCancelable(false);
        LayoutInflater inflater = getActivity().getLayoutInflater();
        @SuppressLint("InflateParams") View promptView = inflater.inflate(R.layout.sms_alert_compose_box, null);
        etSmsCompose = promptView.findViewById(R.id.etSmsCompose);


        builder.setView(promptView)
                .setPositiveButton("Send", new DialogInterface.OnClickListener() {
                    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
                    public void onClick(DialogInterface dialog, int id) {
                        // editMessage();
                        if (TextUtils.isEmpty(Objects.requireNonNull(etSmsCompose.getText()).toString())) {

                            Toast.makeText(getActivity(), "Enter Message", Toast.LENGTH_SHORT).show();

                        } else {

                            sendEmployeeSms();
                        }
                    }
                })
                .setNegativeButton("Close", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
        return builder.create();
    }

    private void getCourse() {
        try {

            RetrofitAPI.getInstance(getContext()).getApi().getCourses(new Callback<CourseResponse>() {
                @Override
                public void success(CourseResponse courseResponse, Response response) {
                    try {
                        if (courseResponse.getStatus() == Constants.SUCCESS) {
                            courseResponseArrayList.clear();
                            courseResponseArrayList.addAll(courseResponse.getResponse());
                            updateCourseToSpinner();

                        } else if (courseResponse.getStatus() == Constants.SWR) {
                            Toast.makeText(getActivity(), "Something went wrong redirect to back ", Toast.LENGTH_SHORT).show();
                        } else if (courseResponse.getStatus() == Constants.NDF) {
                            Toast.makeText(getActivity(), " NO Datafound ", Toast.LENGTH_SHORT).show();
                        } else if (courseResponse.getStatus() == Constants.CMF) {
                            Toast.makeText(getActivity(), "Check all the mandatory fields", Toast.LENGTH_SHORT).show();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void failure(RetrofitError error) {

                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void updateCourseToSpinner() {
        courseArraylist.clear();

        courseArraylist.add("Select Course");
        for (int i = 0; i < courseResponseArrayList.size(); i++) {
            Course course = courseResponseArrayList.get(i);
            courseArraylist.add(course.getCourseName());
        }

        courseAdapter = new ArrayAdapter<String>(Objects.requireNonNull(getContext()), R.layout.spinner_text_color, courseArraylist);
        courseAdapter.setDropDownViewResource(R.layout.spinnertext);

        spinCourse.setAdapter(courseAdapter);
        courseAdapter.notifyDataSetChanged();

        spinCourse.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                try {

                    if (position == 0) {
                        depart = "";
                        rcvSmsTeacher.setVisibility(View.GONE);
                        llAllcheck.setVisibility(View.GONE);
                    } else {
                        depart = courseArraylist.get(position);
                    }
                    Log.e("department_id", "-------" + getCourseCategory(depart));
                    if (position == 0) {
                        departmentArrayList.clear();
                        spinDepartment.setClickable(false);
                        rcvSmsTeacher.setVisibility(View.GONE);
                        llAllcheck.setVisibility(View.GONE);
                        course = courseArraylist.get(position);
                    } else {
                        departmentArrayList.clear();
                        spinDepartment.setClickable(true);
                        course = courseArraylist.get(position);
                        rcvSmsTeacher.setVisibility(View.GONE);
                        llAllcheck.setVisibility(View.GONE);
                        getDepartment();

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    private int getCourseCategory(String cat) {
        for (int i = 0; i < courseResponseArrayList.size(); i++) {
            if (cat.equalsIgnoreCase(courseResponseArrayList.get(i).getCourseName())) {
                return courseResponseArrayList.get(i).getId();
            }
        }
        return 0;
    }

    private void getDepartment() {
        try {
            Map<String, String> params = new HashMap<String, String>();
            params.put("id", String.valueOf(getCourseCategory(depart)));

            RetrofitAPI.getInstance(getContext()).getApi().getDepartments(params, new Callback<DepartmentResponse>() {
                @Override
                public void success(DepartmentResponse departmentResponse, Response response) {
                    try {
                        if (departmentResponse.getStatus() == Constants.SUCCESS) {
                            departmentResponseArrayList.clear();
                            departmentResponseArrayList.addAll(departmentResponse.getDepartments());
                            updateDepartmentToSpinner();

                        } else if (departmentResponse.getStatus() == Constants.SWR) {
                            Toast.makeText(getActivity(), "Something went wrong redirect to back ", Toast.LENGTH_SHORT).show();
                        } else if (departmentResponse.getStatus() == Constants.NDF) {
                            Toast.makeText(getActivity(), " NO Datafound ", Toast.LENGTH_SHORT).show();
                        } else if (departmentResponse.getStatus() == Constants.CMF) {
                            Toast.makeText(getActivity(), "Check all the mandatory fields", Toast.LENGTH_SHORT).show();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void failure(RetrofitError error) {

                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void updateDepartmentToSpinner() {
        departmentArrayList.clear();

        departmentArrayList.add("Select Department");
        for (int i = 0; i < departmentResponseArrayList.size(); i++) {
            Department department = departmentResponseArrayList.get(i);
            departmentArrayList.add(department.getName());
        }

        departmentAdapter = new ArrayAdapter<String>(Objects.requireNonNull(getContext()), R.layout.spinner_text_color, departmentArrayList);
        departmentAdapter.setDropDownViewResource(R.layout.spinnertext);
        spinDepartment.setAdapter(departmentAdapter);
        departmentAdapter.notifyDataSetChanged();

        spinDepartment.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                try {
                    if (position == 0) {
                        batc = "";
                        rcvSmsTeacher.setVisibility(View.GONE);
                        llAllcheck.setVisibility(View.GONE);
                    } else {
                        batc = departmentArrayList.get(position);
                    }
                    Log.e("department_id", "-------" + getDepartmentCategory(batc));
                    if (position == 0) {

                        rcvSmsTeacher.setVisibility(View.GONE);
                        llAllcheck.setVisibility(View.GONE);
                        department = departmentArrayList.get(position);
                        //batch = batchArraylist.get(position);
                    } else {

                        rcvSmsTeacher.setVisibility(View.VISIBLE);
                        llAllcheck.setVisibility(View.VISIBLE);
                        getEmployeeDetails();

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                // ondepartmentSelect();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    private int getDepartmentCategory(String cat) {
        for (int i = 0; i < departmentResponseArrayList.size(); i++) {
            if (cat.equalsIgnoreCase(departmentResponseArrayList.get(i).getName())) {
                return departmentResponseArrayList.get(i).getId();
            }
        }
        return 0;
    }

    private void getEmployeeDetails() {
        try {

            if (UIUtil.isInternetAvailable(Objects.requireNonNull(getContext()))) {

                UIUtil.startProgressDialog(getContext(), "Please Wait.. Getting Details");
                JsonObject jsonObject = new JsonObject();
                jsonObject.addProperty("course_id", getCourseCategory(depart));
                jsonObject.addProperty("department_id", getDepartmentCategory(batc));

                RetrofitAPI.getInstance(getContext()).getApi().getEmployeeSmsDetails(jsonObject, new Callback<TeachingResponse>() {
                    @Override
                    public void success(TeachingResponse teachingResponse, Response response) {
                        UIUtil.stopProgressDialog(getActivity());

                        try {
                            if (Constants.SUCCESS == teachingResponse.getStatus()) {
                                teacherArraylist.clear();
                                //tutorArraylist.clear();
                                teacherArraylist.addAll(teachingResponse.getEmployees());
                                if (teacherArraylist.size() <= 0) {
                                    rcvSmsTeacher.setVisibility(View.GONE);
                                    llAllcheck.setVisibility(View.GONE);
                                    /*noData.setVisibility(View.VISIBLE);*/

                                } else {
                                    rcvSmsTeacher.setVisibility(View.VISIBLE);
                                    llAllcheck.setVisibility(View.VISIBLE);
                                    /*noData.setVisibility(View.GONE);*/
                                }
                                //searchArrayList.addAll(studentListResponse.getStudents());
                                teachersAdapter.notifyDataSetChanged();
                            }
                            if (Constants.SWR == teachingResponse.getStatus()) {
                                /*noData.setVisibility(View.VISIBLE);*/
                                rcvSmsTeacher.setVisibility(View.GONE);
                                llAllcheck.setVisibility(View.GONE);
                                Toast.makeText(getActivity(), "Something went wrong redirect to back", Toast.LENGTH_SHORT).show();
                            } else if (Constants.NDF == teachingResponse.getStatus()) {
                                /*noData.setVisibility(View.VISIBLE);*/
                                rcvSmsTeacher.setVisibility(View.GONE);
                                llAllcheck.setVisibility(View.GONE);
                                Toast.makeText(getActivity(), "NO Datafound", Toast.LENGTH_SHORT).show();
                            } else if (Constants.CMF == teachingResponse.getStatus()) {
                                /*noData.setVisibility(View.VISIBLE);*/
                                rcvSmsTeacher.setVisibility(View.GONE);
                                llAllcheck.setVisibility(View.GONE);
                                Toast.makeText(getActivity(), " Check all the mandatory fields", Toast.LENGTH_SHORT).show();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        UIUtil.stopProgressDialog(getActivity());
                        Toast.makeText(getActivity(), "Something went wrong, try again.", Toast.LENGTH_SHORT).show();
                    }
                });
            } else {
                Toast.makeText(getActivity(), "Please Connect to Internet", Toast.LENGTH_SHORT).show();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void sendEmployeeSms() {
        try {

            if (UIUtil.isInternetAvailable(Objects.requireNonNull(getContext()))) {

                UIUtil.startProgressDialog(getContext(), "Please Wait.. Getting Details");
                JsonObject jsonObject = new JsonObject();
                jsonObject.addProperty("body", Objects.requireNonNull(etSmsCompose.getText()).toString());
                jsonObject.add("employee_ids", presentItem);


                RetrofitAPI.getInstance(getContext()).getApi().getEmployeeSendSms(jsonObject, new Callback<TeacherSentSmsResponse>() {
                    @Override
                    public void success(TeacherSentSmsResponse teacherSentSmsResponse, Response response) {
                        UIUtil.stopProgressDialog(getActivity());

                        //Toast.makeText(getActivity(), "Messages sent successfully.", Toast.LENGTH_SHORT).show();

                        if (teacherSentSmsResponse.getStatus() == Constants.SUCCESS) {
                            getEmployeeDetails();
                            Toast.makeText(getActivity(), teacherSentSmsResponse.getMessage(), Toast.LENGTH_SHORT).show();
                        } else if (teacherSentSmsResponse.getStatus() == Constants.SWR) {
                            Toast.makeText(getActivity(), teacherSentSmsResponse.getMessage(), Toast.LENGTH_SHORT).show();
                        } else if (teacherSentSmsResponse.getStatus() == Constants.NDF) {
                            Toast.makeText(getActivity(), teacherSentSmsResponse.getMessage(), Toast.LENGTH_SHORT).show();
                        } else if (teacherSentSmsResponse.getStatus() == Constants.CMF) {
                            Toast.makeText(getActivity(), teacherSentSmsResponse.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        UIUtil.stopProgressDialog(getActivity());
                        Toast.makeText(getActivity(), "Something went wrong, try again.", Toast.LENGTH_SHORT).show();
                    }
                });
            } else {
                Toast.makeText(getActivity(), "Please Connect to Internet", Toast.LENGTH_SHORT).show();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

