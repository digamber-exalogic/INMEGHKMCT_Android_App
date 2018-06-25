package com.example.exalogicsolutions.inmegh_kmct.Fragments;


import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.exalogicsolutions.inmegh_kmct.Adapters.SmsAdapters.TeachersAdapter;
import com.example.exalogicsolutions.inmegh_kmct.Api.RetrofitAPI;
import com.example.exalogicsolutions.inmegh_kmct.Models.SmsResponse.TeachingResponse.Teaching;
import com.example.exalogicsolutions.inmegh_kmct.R;
import com.example.exalogicsolutions.inmegh_kmct.Utilities.UIUtil;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.Objects;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class TeachersFragment extends Fragment {
    private AppCompatSpinner spinCourse, spinDepartment;
    private AppCompatCheckBox chkAll;
    private RecyclerView rcvSmsTeacher;
    private TeachersAdapter teachersAdapter;
    private ArrayList<Teaching> teacherArraylist, searchArraylist;
    private FloatingActionButton flbCreateSms;
    private TextInputEditText etSmsCompose;
    private ArrayList<String> items, number;


    public TeachersFragment() {
        // Required empty public constructor
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

        rcvSmsTeacher.setHasFixedSize(true);
        rcvSmsTeacher.setLayoutManager(new LinearLayoutManager(getActivity()));

        teachersAdapter = new TeachersAdapter(getActivity(), teacherArraylist);
        rcvSmsTeacher.setAdapter(teachersAdapter);
        teachersAdapter.notifyDataSetChanged();

        return view;
    }

    private String stringpass() {
        String numbers = "";
        for (int i = 0; i < teacherArraylist.size(); i++) {
            numbers = numbers + teacherArraylist.get(i).getMobileNo() + ",";
            number.add(teacherArraylist.get(i).getMobileNo());
        }
        Log.e("Strimnf", "=========" + numbers);
        Log.e("number", "=========" + number);
        return numbers;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private AlertDialog composeBox() {


        AlertDialog.Builder builder = new AlertDialog.Builder(Objects.requireNonNull(getActivity()));
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View promptView = inflater.inflate(R.layout.sms_alert_compose_box, null);
        etSmsCompose = promptView.findViewById(R.id.etSmsCompose);


        builder.setView(promptView)
                .setPositiveButton("Send", new DialogInterface.OnClickListener() {
                    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
                    public void onClick(DialogInterface dialog, int id) {
                        // editMessage();
                        String sms = Objects.requireNonNull(etSmsCompose.getText()).toString();
                        messageToAll(sms);
                    }
                })
                .setNegativeButton("Close", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        /* cheakboxall.setChecked(false);*/
                        dialog.cancel();
                    }
                });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
        return builder.create();

    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private AlertDialog composeBox1() {


        AlertDialog.Builder builder = new AlertDialog.Builder(Objects.requireNonNull(getActivity()));
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View promptView = inflater.inflate(R.layout.sms_alert_compose_box, null);
        etSmsCompose = promptView.findViewById(R.id.etSmsCompose);


        builder.setView(promptView)
                .setPositiveButton("Send", new DialogInterface.OnClickListener() {
                    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
                    public void onClick(DialogInterface dialog, int id) {
                        // editMessage();
                        String sms = etSmsCompose.getText().toString();
                        composeToAll(sms);
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

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void messageToAll(String sms) {
        try {
            if (UIUtil.isInternetAvailable(Objects.requireNonNull(getActivity()))) {

                UIUtil.startProgressDialog(getActivity(), "Please Wait.. Getting Details");


                JsonObject jsonObject = new JsonObject();
                jsonObject.addProperty("message", sms);
                jsonObject.addProperty("mobile_no", stringpass());

                RetrofitAPI.getInstance(getActivity()).getApi().getEmployeeSendSms(jsonObject, new Callback<JsonObject>() {
                    @Override
                    public void success(JsonObject object, Response response) {
                        Log.e("jsonObject", "jsonObject --- " + object.toString());
                        UIUtil.stopProgressDialog(getActivity());
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        UIUtil.stopProgressDialog(getActivity());
                    }
                });
            } else {
                Toast.makeText(getActivity(), "Please Connect to Internet", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void composeToAll(String sms) {
        try {
            if (UIUtil.isInternetAvailable(Objects.requireNonNull(getActivity()))) {

                UIUtil.startProgressDialog(getActivity(), "Please Wait.. Getting Details");


                JsonObject jsonObject = new JsonObject();
                jsonObject.addProperty("message", sms);
                jsonObject.addProperty("mobile_no", items.toString());

                RetrofitAPI.getInstance(getActivity()).getApi().getEmployeeSendSms(jsonObject, new Callback<JsonObject>() {
                    @Override
                    public void success(JsonObject object, Response response) {
                        Log.e("jsonObject", "jsonObject --- " + object.toString());
                        UIUtil.stopProgressDialog(getActivity());
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        UIUtil.stopProgressDialog(getActivity());
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

