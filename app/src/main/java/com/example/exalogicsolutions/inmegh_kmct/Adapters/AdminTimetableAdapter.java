package com.example.exalogicsolutions.inmegh_kmct.Adapters;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.exalogicsolutions.inmegh_kmct.R;

import java.util.ArrayList;
import java.util.List;

public class AdminTimetableAdapter extends RecyclerView.Adapter<AdminTimetableAdapter.ViewHolder> {
    private List<ArrayList> arrayList;
    private Activity activity;
    String letter;
    Context context;

    public AdminTimetableAdapter(List<ArrayList> arrayList, Activity activity) {
        this.arrayList = arrayList;
        this.activity = activity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.timetable_cardview_list, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ArrayList myArrayList = arrayList.get(position);

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView txDate, txMonth, txStartTime, txLectureName,
                txEndTime, txCourse, txBatch, txSection, txTimetableName, txSemester;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txDate = itemView.findViewById(R.id.txDate);
            txMonth = itemView.findViewById(R.id.txMonth);
            txStartTime = itemView.findViewById(R.id.txStartTime);
            txLectureName = itemView.findViewById(R.id.txLectureName);
            txEndTime = itemView.findViewById(R.id.txEndTime);
            txCourse = itemView.findViewById(R.id.txCourse);
            txBatch = itemView.findViewById(R.id.txBatch);
            txSection = itemView.findViewById(R.id.txSection);
            txTimetableName = itemView.findViewById(R.id.txTimetableName);
            txSemester = itemView.findViewById(R.id.txSemester);
        }
    }
}
