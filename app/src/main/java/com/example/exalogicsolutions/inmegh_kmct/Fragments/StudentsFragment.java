package com.example.exalogicsolutions.inmegh_kmct.Fragments;


import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
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
import android.widget.CompoundButton;
import android.widget.Toast;

import com.example.exalogicsolutions.inmegh_kmct.Adapters.SmsAdapters.StudentsAdapter;
import com.example.exalogicsolutions.inmegh_kmct.Api.RetrofitAPI;
import com.example.exalogicsolutions.inmegh_kmct.Models.SmsResponse.BatchResponse.Batch;
import com.example.exalogicsolutions.inmegh_kmct.Models.SmsResponse.BatchResponse.BatchResponse;
import com.example.exalogicsolutions.inmegh_kmct.Models.SmsResponse.CourseResponse.Course;
import com.example.exalogicsolutions.inmegh_kmct.Models.SmsResponse.CourseResponse.CourseResponse;
import com.example.exalogicsolutions.inmegh_kmct.Models.SmsResponse.SectionResponse.Section;
import com.example.exalogicsolutions.inmegh_kmct.Models.SmsResponse.SectionResponse.SectionResponse;
import com.example.exalogicsolutions.inmegh_kmct.Models.SmsResponse.StudentResponse.Student;
import com.example.exalogicsolutions.inmegh_kmct.Models.SmsResponse.StudentResponse.StudentResponse;
import com.example.exalogicsolutions.inmegh_kmct.Models.SmsResponse.StudentResponse.StudentSentSmsResponse;
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
public class StudentsFragment extends Fragment {
    private AppCompatSpinner spinCourse, spinBatch, spinSection;
    /*private ArrayList<Teaching> employeeDetailArrayList;*/
    private ArrayList<Course> courseResponseArrayList;
    private ArrayList<Batch> batchResponseArrayList;
    private ArrayList<Section> sectionResponseArrayList;
    private ArrayList<String> courseArraylist, batchArrayList, sectionArrayList;
    private AppCompatCheckBox chkAll;
    private RecyclerView rcvSmsStudent;
    private StudentsAdapter studentAdapter;
    private ArrayList<Student> studentArraylist, searchArraylist;
    private FloatingActionButton flbCreateSms;
    private TextInputEditText etSmsCompose;
    /*private ArrayList<String> items, number;
    private int spinpos;*/
    private ArrayAdapter<String> courseAdapter, sectionAdapter, batchAdapter;
    /*private ArrayList<String> departmentArraylist;
    private ArrayList<String> batchArraylist;*/
    /*private int editpos;*/
    private MaterialCardView llAllcheck;
    private JsonArray presentItem;
    private String depart = "", course, batc = "", department, batch, sec = "", section;

    public StudentsFragment() {
        super();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_students, container, false);
        // Inflate the layout for this fragment
        spinCourse = view.findViewById(R.id.spinCourse);
        spinBatch = view.findViewById(R.id.spinBatch);
        spinSection = view.findViewById(R.id.spinSection);
        chkAll = view.findViewById(R.id.chkAll);
        rcvSmsStudent = view.findViewById(R.id.rcvSmsStudent);
        flbCreateSms = view.findViewById(R.id.flbCreateSms);
        llAllcheck = view.findViewById(R.id.llAllcheck);

        rcvSmsStudent.setHasFixedSize(true);
        rcvSmsStudent.setLayoutManager(new LinearLayoutManager(getActivity()));

        courseArraylist = new ArrayList<>();
        batchArrayList = new ArrayList<>();
        sectionArrayList = new ArrayList<>();
        studentArraylist = new ArrayList<>();
        presentItem = new JsonArray();

        courseResponseArrayList = new ArrayList<>();
        batchResponseArrayList = new ArrayList<>();
        sectionResponseArrayList = new ArrayList<>();

        studentAdapter = new StudentsAdapter(getActivity(), studentArraylist);
        rcvSmsStudent.setAdapter(studentAdapter);

        courseArraylist.clear();
        courseArraylist.add("Select Course");
        courseAdapter = new ArrayAdapter<String>(Objects.requireNonNull(getActivity()), R.layout.spinner_text_color, courseArraylist);
        courseAdapter.setDropDownViewResource(R.layout.spinnertext);
        spinCourse.setAdapter(courseAdapter);
        courseAdapter.notifyDataSetChanged();

        batchArrayList.clear();
        batchArrayList.add("Select Batch");
        batchAdapter = new ArrayAdapter<String>(getActivity(), R.layout.spinner_text_color, batchArrayList);
        batchAdapter.setDropDownViewResource(R.layout.spinnertext);
        spinBatch.setAdapter(batchAdapter);
        batchAdapter.notifyDataSetChanged();

        sectionArrayList.clear();
        sectionArrayList.add("Select Section");
        sectionAdapter = new ArrayAdapter<String>(getActivity(), R.layout.spinner_text_color, sectionArrayList);
        sectionAdapter.setDropDownViewResource(R.layout.spinnertext);
        spinSection.setAdapter(sectionAdapter);
        sectionAdapter.notifyDataSetChanged();

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

                    for (int i = 0; i < studentArraylist.size(); i++) {
                        studentArraylist.get(i).setCheck_box(1);
                        presentItem.add(new JsonPrimitive(studentArraylist.get(i).getId().toString()));
                        Log.e("data3", "===" + presentItem);
                    }
                    studentAdapter.notifyDataSetChanged();
                } else {
                    for (int i = 0; i < studentArraylist.size(); i++) {
                        studentArraylist.get(i).setCheck_box(2);
                        presentItem.remove(new JsonPrimitive(studentArraylist.get(i).getId().toString()));
                        Log.e("data4", "===" + presentItem);

                    }
                    studentAdapter.notifyDataSetChanged();
                }
                if (presentItem.size() <= 0) {
                    flbCreateSms.setVisibility(View.GONE);
                } else {
                    flbCreateSms.setVisibility(View.VISIBLE);
                }
            }
        });

        studentAdapter.SetOnItemClickListener(new StudentsAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

            }

            @Override
            public void oncheakboxClick(View view, int position) {

                if (studentArraylist.get(position).getCheck_box() == 2) {
                    studentArraylist.get(position).setCheck_box(1);

                    presentItem.add(new JsonPrimitive(studentArraylist.get(position).getId().toString()));

                    studentAdapter.notifyDataSetChanged();

                    Log.e("data1", "===" + presentItem);

                } else {
                    studentArraylist.get(position).setCheck_box(2);

                    presentItem.remove(new JsonPrimitive(studentArraylist.get(position).getId().toString()));
                    studentAdapter.notifyDataSetChanged();

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
                        rcvSmsStudent.setVisibility(View.GONE);
                        llAllcheck.setVisibility(View.GONE);
                    } else {
                        depart = courseArraylist.get(position);
                    }
                    Log.e("department_id", "-------" + getCourseCategory(depart));
                    if (position == 0) {
                        batchArrayList.clear();
                        spinBatch.setClickable(false);
                        rcvSmsStudent.setVisibility(View.GONE);
                        llAllcheck.setVisibility(View.GONE);
                        course = courseArraylist.get(position);
                    } else {
                        batchArrayList.clear();
                        spinBatch.setClickable(true);
                        course = courseArraylist.get(position);
                        rcvSmsStudent.setVisibility(View.GONE);
                        llAllcheck.setVisibility(View.GONE);
                        getBatch();

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

    private void getBatch() {
        try {
            Map<String, String> params = new HashMap<String, String>();
            params.put("department_id", String.valueOf(getCourseCategory(depart)));

            RetrofitAPI.getInstance(getContext()).getApi().getBatches(params, new Callback<BatchResponse>() {
                @Override
                public void success(BatchResponse batchResponse, Response response) {
                    try {
                        if (batchResponse.getStatus() == Constants.SUCCESS) {
                            batchResponseArrayList.clear();
                            batchResponseArrayList.addAll(batchResponse.getBatches());
                            updateBatchToSpinner();

                        } else if (batchResponse.getStatus() == Constants.SWR) {
                            Toast.makeText(getActivity(), "Something went wrong redirect to back ", Toast.LENGTH_SHORT).show();
                        } else if (batchResponse.getStatus() == Constants.NDF) {
                            Toast.makeText(getActivity(), " NO Datafound ", Toast.LENGTH_SHORT).show();
                        } else if (batchResponse.getStatus() == Constants.CMF) {
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

    private void updateBatchToSpinner() {
        batchArrayList.clear();

        batchArrayList.add("Select Batch");
        for (int i = 0; i < batchResponseArrayList.size(); i++) {
            Batch batch = batchResponseArrayList.get(i);
            batchArrayList.add(batch.getName());
        }

        batchAdapter = new ArrayAdapter<String>(Objects.requireNonNull(getContext()), R.layout.spinner_text_color, batchArrayList);
        batchAdapter.setDropDownViewResource(R.layout.spinnertext);
        spinBatch.setAdapter(batchAdapter);
        batchAdapter.notifyDataSetChanged();

        spinBatch.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                try {
                    if (position == 0) {
                        batc = "";
                        rcvSmsStudent.setVisibility(View.GONE);
                        llAllcheck.setVisibility(View.GONE);
                    } else {
                        batc = batchArrayList.get(position);
                    }
                    Log.e("department_id", "-------" + getBatchCategory(batc));
                    if (position == 0) {
                        sectionArrayList.clear();
                        spinSection.setClickable(false);
                        rcvSmsStudent.setVisibility(View.GONE);
                        llAllcheck.setVisibility(View.GONE);
                        /*department = batchArrayList.get(position);*/
                        //batch = batchArraylist.get(position);
                    } else {
                        sectionArrayList.clear();
                        spinSection.setClickable(true);
                        batch = batchArrayList.get(position);
                        rcvSmsStudent.setVisibility(View.GONE);
                        llAllcheck.setVisibility(View.GONE);
                        getSection();

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

    private int getBatchCategory(String cat) {
        for (int i = 0; i < batchResponseArrayList.size(); i++) {
            if (cat.equalsIgnoreCase(batchResponseArrayList.get(i).getName())) {
                return batchResponseArrayList.get(i).getId();
            }
        }
        return 0;
    }

    private void getSection() {
        try {
            Map<String, String> params = new HashMap<String, String>();
            //params.put("department_id", String.valueOf(getdepartCategory(depart)));
            params.put("batch_id", String.valueOf(getBatchCategory(batc)));

            RetrofitAPI.getInstance(getContext()).getApi().getSections(params, new Callback<SectionResponse>() {
                @Override
                public void success(SectionResponse sectionResponse, Response response) {
                    try {
                        if (sectionResponse.getStatus() == Constants.SUCCESS) {
                            sectionResponseArrayList.clear();
                            sectionResponseArrayList.addAll(sectionResponse.getSections());
                            updateSectionToSpinner();

                        } else if (sectionResponse.getStatus() == Constants.SWR) {
                            Toast.makeText(getActivity(), "Something went wrong redirect to back ", Toast.LENGTH_SHORT).show();
                        } else if (sectionResponse.getStatus() == Constants.NDF) {
                            Toast.makeText(getActivity(), " NO Datafound ", Toast.LENGTH_SHORT).show();
                        } else if (sectionResponse.getStatus() == Constants.CMF) {
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

    private void updateSectionToSpinner() {
        sectionArrayList.clear();

        sectionArrayList.add("Select Section");
        for (int i = 0; i < sectionResponseArrayList.size(); i++) {
            Section section = sectionResponseArrayList.get(i);
            sectionArrayList.add(section.getName());
        }

        sectionAdapter = new ArrayAdapter<String>(getContext(), R.layout.spinner_text_color, sectionArrayList);
        sectionAdapter.setDropDownViewResource(R.layout.spinnertext);
        spinSection.setAdapter(sectionAdapter);
        sectionAdapter.notifyDataSetChanged();

        spinSection.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                try {

                    if (position == 0) {
                        sec = "";
                        rcvSmsStudent.setVisibility(View.GONE);
                        llAllcheck.setVisibility(View.GONE);
                    } else {
                        sec = sectionArrayList.get(position);
                    }
                    Log.e("department_id", "-------" + getsectionCategory(sec));
                    if (position == 0) {
                        rcvSmsStudent.setVisibility(View.GONE);
                        llAllcheck.setVisibility(View.GONE);
                        section = sectionArrayList.get(position);
                    } else {
                        rcvSmsStudent.setVisibility(View.VISIBLE);
                        llAllcheck.setVisibility(View.VISIBLE);
                        section = sectionArrayList.get(position);
                        getStudentDetails();
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

    private int getsectionCategory(String cat) {
        for (int i = 0; i < sectionResponseArrayList.size(); i++) {
            if (cat.equalsIgnoreCase(sectionResponseArrayList.get(i).getName())) {
                return sectionResponseArrayList.get(i).getId();
            }
        }
        return 0;
    }

    private void getStudentDetails() {
        try {

            if (UIUtil.isInternetAvailable(Objects.requireNonNull(getContext()))) {

                UIUtil.startProgressDialog(getContext(), "Please Wait.. Getting Details");
                JsonObject jsonObject = new JsonObject();
                jsonObject.addProperty("course_id", getCourseCategory(depart));
                jsonObject.addProperty("batch_id", getBatchCategory(batc));

                RetrofitAPI.getInstance(getContext()).getApi().getStudentSmsDetails(jsonObject, new Callback<StudentResponse>() {
                    @Override
                    public void success(StudentResponse studentResponse, Response response) {
                        UIUtil.stopProgressDialog(getActivity());

                        try {
                            if (Constants.SUCCESS == studentResponse.getStatus()) {
                                studentArraylist.clear();
                                //tutorArraylist.clear();
                                studentArraylist.addAll(studentResponse.getStudents());
                                if (studentArraylist.size() <= 0) {
                                    rcvSmsStudent.setVisibility(View.GONE);
                                    llAllcheck.setVisibility(View.GONE);
                                    /*noData.setVisibility(View.VISIBLE);*/

                                } else {
                                    rcvSmsStudent.setVisibility(View.VISIBLE);
                                    llAllcheck.setVisibility(View.VISIBLE);
                                    /*noData.setVisibility(View.GONE);*/
                                }
                                //searchArrayList.addAll(studentListResponse.getStudents());
                                studentAdapter.notifyDataSetChanged();
                            }
                            if (Constants.SWR == studentResponse.getStatus()) {
                                /*noData.setVisibility(View.VISIBLE);*/
                                rcvSmsStudent.setVisibility(View.GONE);
                                llAllcheck.setVisibility(View.GONE);
                                Toast.makeText(getActivity(), "Something went wrong redirect to back", Toast.LENGTH_SHORT).show();
                            } else if (Constants.NDF == studentResponse.getStatus()) {
                                /*noData.setVisibility(View.VISIBLE);*/
                                rcvSmsStudent.setVisibility(View.GONE);
                                llAllcheck.setVisibility(View.GONE);
                                Toast.makeText(getActivity(), "NO Datafound", Toast.LENGTH_SHORT).show();
                            } else if (Constants.CMF == studentResponse.getStatus()) {
                                /*noData.setVisibility(View.VISIBLE);*/
                                rcvSmsStudent.setVisibility(View.GONE);
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

            if (UIUtil.isInternetAvailable(getContext())) {

                UIUtil.startProgressDialog(getContext(), "Please Wait.. Getting Details");
                JsonObject jsonObject = new JsonObject();
                jsonObject.addProperty("body", etSmsCompose.getText().toString());
                jsonObject.add("student_ids", presentItem);


                RetrofitAPI.getInstance(getContext()).getApi().getStudentSendSms(jsonObject, new Callback<StudentSentSmsResponse>() {
                    @Override
                    public void success(StudentSentSmsResponse studentSentSmsResponse, Response response) {
                        UIUtil.stopProgressDialog(getActivity());

                        //Toast.makeText(getActivity(), "Messages sent successfully.", Toast.LENGTH_SHORT).show();

                        if (studentSentSmsResponse.getStatus() == Constants.SUCCESS) {
                            getStudentDetails();
                            Toast.makeText(getActivity(), studentSentSmsResponse.getMessage(), Toast.LENGTH_SHORT).show();
                        } else if (studentSentSmsResponse.getStatus() == Constants.SWR) {
                            Toast.makeText(getActivity(), studentSentSmsResponse.getMessage(), Toast.LENGTH_SHORT).show();
                        } else if (studentSentSmsResponse.getStatus() == Constants.NDF) {
                            Toast.makeText(getActivity(), studentSentSmsResponse.getMessage(), Toast.LENGTH_SHORT).show();
                        } else if (studentSentSmsResponse.getStatus() == Constants.CMF) {
                            Toast.makeText(getActivity(), studentSentSmsResponse.getMessage(), Toast.LENGTH_SHORT).show();
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
