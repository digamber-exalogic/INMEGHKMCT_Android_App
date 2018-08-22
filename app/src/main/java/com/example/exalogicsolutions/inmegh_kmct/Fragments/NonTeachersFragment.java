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
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.example.exalogicsolutions.inmegh_kmct.Adapters.SmsAdapters.NonTeachersAdapter;
import com.example.exalogicsolutions.inmegh_kmct.Api.RetrofitAPI;
import com.example.exalogicsolutions.inmegh_kmct.Models.SmsResponse.NonTeachingResponse.NonTeaching;
import com.example.exalogicsolutions.inmegh_kmct.Models.SmsResponse.NonTeachingResponse.NonTeachingResponse;
import com.example.exalogicsolutions.inmegh_kmct.Models.SmsResponse.NonTeachingResponse.NonTeachingSentSmsResponse;
import com.example.exalogicsolutions.inmegh_kmct.R;
import com.example.exalogicsolutions.inmegh_kmct.Utilities.Constants;
import com.example.exalogicsolutions.inmegh_kmct.Utilities.UIUtil;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

import java.util.ArrayList;
import java.util.Objects;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class NonTeachersFragment extends Fragment {

    private AppCompatSpinner spinCourse, spinBatch, spinSection;
    /*private ArrayList<Teaching> employeeDetailArrayList;*/
    private AppCompatCheckBox chkAll;
    private RecyclerView rcvSmsNonTeacher;
    private NonTeachersAdapter nonTeachersAdapter;
    private ArrayList<NonTeaching> nonTeachersArraylist, searchArraylist;
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

    public NonTeachersFragment() {
        super();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_non_teachers, container, false);
        chkAll = view.findViewById(R.id.chkAll);
        rcvSmsNonTeacher = view.findViewById(R.id.rcvSmsStudent);
        flbCreateSms = view.findViewById(R.id.flbCreateSms);
        llAllcheck = view.findViewById(R.id.llAllcheck);

        rcvSmsNonTeacher.setHasFixedSize(true);
        rcvSmsNonTeacher.setLayoutManager(new LinearLayoutManager(getActivity()));
        nonTeachersArraylist = new ArrayList<>();
        presentItem = new JsonArray();

        nonTeachersAdapter = new NonTeachersAdapter(getActivity(), nonTeachersArraylist);
        rcvSmsNonTeacher.setAdapter(nonTeachersAdapter);

        getNonTeacherDetails();

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

                    for (int i = 0; i < nonTeachersArraylist.size(); i++) {
                        nonTeachersArraylist.get(i).setCheck_box(1);
                        presentItem.add(new JsonPrimitive(nonTeachersArraylist.get(i).getId().toString()));
                        Log.e("data3", "===" + presentItem);
                    }
                    nonTeachersAdapter.notifyDataSetChanged();
                } else {
                    for (int i = 0; i < nonTeachersArraylist.size(); i++) {
                        nonTeachersArraylist.get(i).setCheck_box(2);
                        presentItem.remove(new JsonPrimitive(nonTeachersArraylist.get(i).getId().toString()));
                        Log.e("data4", "===" + presentItem);

                    }
                    nonTeachersAdapter.notifyDataSetChanged();
                }
                if (presentItem.size() <= 0) {
                    flbCreateSms.setVisibility(View.GONE);
                } else {
                    flbCreateSms.setVisibility(View.VISIBLE);
                }
            }
        });

        nonTeachersAdapter.SetOnItemClickListener(new NonTeachersAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

            }

            @Override
            public void oncheakboxClick(View view, int position) {

                if (nonTeachersArraylist.get(position).getCheck_box() == 2) {
                    nonTeachersArraylist.get(position).setCheck_box(1);

                    presentItem.add(new JsonPrimitive(nonTeachersArraylist.get(position).getId().toString()));

                    nonTeachersAdapter.notifyDataSetChanged();

                    Log.e("data1", "===" + presentItem);

                } else {
                    nonTeachersArraylist.get(position).setCheck_box(2);

                    presentItem.remove(new JsonPrimitive(nonTeachersArraylist.get(position).getId().toString()));
                    nonTeachersAdapter.notifyDataSetChanged();

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

    private void getNonTeacherDetails() {
        try {

            if (UIUtil.isInternetAvailable(Objects.requireNonNull(getContext()))) {

                UIUtil.startProgressDialog(getContext(), "Please Wait.. Getting Details");

                RetrofitAPI.getInstance(getContext()).getApi().getNonTeachingEmployeeSmsDetails(new Callback<NonTeachingResponse>() {
                    @Override
                    public void success(NonTeachingResponse nonTeachingResponse, Response response) {
                        UIUtil.stopProgressDialog(getActivity());

                        try {
                            if (Constants.SUCCESS == nonTeachingResponse.getStatus()) {
                                nonTeachersArraylist.clear();
                                //tutorArraylist.clear();
                                nonTeachersArraylist.addAll(nonTeachingResponse.getEmployees());
                                if (nonTeachersArraylist.size() <= 0) {
                                    rcvSmsNonTeacher.setVisibility(View.GONE);
                                    llAllcheck.setVisibility(View.GONE);
                                    /*noData.setVisibility(View.VISIBLE);*/

                                } else {
                                    rcvSmsNonTeacher.setVisibility(View.VISIBLE);
                                    llAllcheck.setVisibility(View.VISIBLE);
                                    /*noData.setVisibility(View.GONE);*/
                                }
                                //searchArrayList.addAll(studentListResponse.getStudents());
                                nonTeachersAdapter.notifyDataSetChanged();
                            }
                            if (Constants.SWR == nonTeachingResponse.getStatus()) {
                                /*noData.setVisibility(View.VISIBLE);*/
                                rcvSmsNonTeacher.setVisibility(View.GONE);
                                llAllcheck.setVisibility(View.GONE);
                                Toast.makeText(getActivity(), "Something went wrong redirect to back", Toast.LENGTH_SHORT).show();
                            } else if (Constants.NDF == nonTeachingResponse.getStatus()) {
                                /*noData.setVisibility(View.VISIBLE);*/
                                rcvSmsNonTeacher.setVisibility(View.GONE);
                                llAllcheck.setVisibility(View.GONE);
                                Toast.makeText(getActivity(), "NO Datafound", Toast.LENGTH_SHORT).show();
                            } else if (Constants.CMF == nonTeachingResponse.getStatus()) {
                                /*noData.setVisibility(View.VISIBLE);*/
                                rcvSmsNonTeacher.setVisibility(View.GONE);
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
                jsonObject.add("staff_ids", presentItem);


                RetrofitAPI.getInstance(getContext()).getApi().getNonTeachingEmployeeSendSms(jsonObject, new Callback<NonTeachingSentSmsResponse>() {
                    @Override
                    public void success(NonTeachingSentSmsResponse nonTeachingSentSmsResponse, Response response) {
                        UIUtil.stopProgressDialog(getActivity());

                        //Toast.makeText(getActivity(), "Messages sent successfully.", Toast.LENGTH_SHORT).show();

                        if (nonTeachingSentSmsResponse.getStatus() == Constants.SUCCESS) {
                            getNonTeacherDetails();
                            Toast.makeText(getActivity(), nonTeachingSentSmsResponse.getMessage(), Toast.LENGTH_SHORT).show();
                        } else if (nonTeachingSentSmsResponse.getStatus() == Constants.SWR) {
                            Toast.makeText(getActivity(), nonTeachingSentSmsResponse.getMessage(), Toast.LENGTH_SHORT).show();
                        } else if (nonTeachingSentSmsResponse.getStatus() == Constants.NDF) {
                            Toast.makeText(getActivity(), nonTeachingSentSmsResponse.getMessage(), Toast.LENGTH_SHORT).show();
                        } else if (nonTeachingSentSmsResponse.getStatus() == Constants.CMF) {
                            Toast.makeText(getActivity(), nonTeachingSentSmsResponse.getMessage(), Toast.LENGTH_SHORT).show();
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
