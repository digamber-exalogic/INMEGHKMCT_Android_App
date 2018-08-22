package com.example.exalogicsolutions.inmegh_kmct.Adapters.SmsAdapters;

import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.example.exalogicsolutions.inmegh_kmct.Models.SmsResponse.NonTeachingResponse.NonTeaching;
import com.example.exalogicsolutions.inmegh_kmct.R;

import java.util.ArrayList;

public class NonTeachersAdapter extends RecyclerView.Adapter<NonTeachersAdapter.MyViewHolder>{
    private static final String TAG = "TeacherAdapter";

    private ArrayList<NonTeaching> mNonTeachingArrayList;
    private FragmentActivity activity;
    public static NonTeachersAdapter.OnItemClickListener mItemClickListener;
    private boolean isSelectedAll;

    public NonTeachersAdapter (FragmentActivity activity, ArrayList<NonTeaching> nonTeachingArrayList) {

        this.activity = activity;
        mNonTeachingArrayList = nonTeachingArrayList;
    }

    @NonNull
    @Override
    public NonTeachersAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sms_cardview_list, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    public void selectAll(){
        Log.e("onClickSelectAll","yes");
        isSelectedAll=true;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull NonTeachersAdapter.MyViewHolder holder, int position) {

        NonTeaching nonTeaching = mNonTeachingArrayList.get(position);

        holder.txProfileName.setText(nonTeaching.getName());
        /*holder.txRegNo.setText(nonTeaching.getId());*/
        holder.txNumber.setText(nonTeaching.getMobileNo());

        if (!isSelectedAll) holder.chkSmsSelect.setChecked (false);
        else holder.chkSmsSelect.setChecked(true);

        if (nonTeaching.getCheck_box() == 1) {
            holder.chkSmsSelect.setChecked(true);
        } else if (nonTeaching.getCheck_box() == 0) {
            holder.chkSmsSelect.setChecked(false);
        }

    }

    @Override
    public int getItemCount() {
        return mNonTeachingArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private AppCompatCheckBox chkSmsSelect;
        private TextView txProfileName, txRegNo, txNumber;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            chkSmsSelect = itemView.findViewById(R.id.chkSmsSelect);
            txProfileName = itemView.findViewById(R.id.txProfileName);
            txRegNo = itemView.findViewById(R.id.txRegNo);
            txNumber = itemView.findViewById(R.id.txNumber);

            this.chkSmsSelect.setOnClickListener(this);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.chkSmsSelect:
                    mItemClickListener.oncheakboxClick(view, getAdapterPosition());
                    break;
                default:
                    mItemClickListener.onItemClick(view, getAdapterPosition());
            }
        }
    }

    public interface OnItemClickListener {

        public void onItemClick(View view, int position);
        public void oncheakboxClick(View view, int position);
    }

    public void SetOnItemClickListener(OnItemClickListener mItemClickListener) {
        NonTeachersAdapter.mItemClickListener = mItemClickListener;
    }
}
