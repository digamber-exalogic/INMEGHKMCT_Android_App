package com.example.exalogicsolutions.inmegh_kmct.Adapters;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.exalogicsolutions.inmegh_kmct.Models.HolidayResponse.HolidayView;
import com.example.exalogicsolutions.inmegh_kmct.R;
import com.example.exalogicsolutions.inmegh_kmct.Utilities.Constants;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class HolidayAdapter extends RecyclerView.Adapter<HolidayAdapter.ViewHolder> implements Filterable {
    private Context context;
    private List<HolidayView> arrayList;
    private List<HolidayView> holidayListFiltered;
    private Activity activity;
    private LayoutInflater inflater;
    public static HolidayAdapter.OnItemClickListener mItemClickListener;
    private HolidayAdapter listener;


    public HolidayAdapter(Activity activity, List<HolidayView> arrayList, Context context ) {
        this.context = context;
        this.arrayList = arrayList;
        this.holidayListFiltered = arrayList;
        this.activity = activity;


    }

    @NonNull
    @Override
    public HolidayAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.holiday_cardview_list, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(HolidayAdapter.ViewHolder holder, final int position) {

        HolidayView holidayView = holidayListFiltered.get(position);

        holder.tvHolidayName.setText("" + holidayView.getName());
        if (holidayView.getSingleDay() == null) {
            holder.tvfromDate.setText("" + holidayView.getmStartDate());
            holder.txEndDate.setText("" + holidayView.getmEndDate());
            holder.tv_to.setVisibility(View.VISIBLE);
            holder.txEndDate.setVisibility(View.VISIBLE);
            Log.e("single date", "===" + (holidayView.getSingleDay() == null));
        } else {
            holder.tvfromDate.setText("" + holidayView.getSingleDay());
            holder.tv_to.setVisibility(View.GONE);
            holder.txEndDate.setVisibility(View.GONE);
        }
        /*Picasso.get().load(Constants.HostImage + HolidayView.getImage()).resize(30, 30)
                .transform(new CircleTransform()).into(holder.imageview_holiday);*/
    }

    @Override
    public int getItemCount() {
        return holidayListFiltered.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    holidayListFiltered = arrayList;
                } else {
                    List<HolidayView> filteredList = new ArrayList<>();
                    for (HolidayView row : arrayList) {

                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match
                        if (row.getName().toLowerCase().contains(charString.toLowerCase()) ) {
                            filteredList.add(row);
                        }
                    }

                    holidayListFiltered = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = holidayListFiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                holidayListFiltered = (ArrayList<HolidayView>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }


    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tvHolidayName, tvfromDate, tv_to, txEndDate;
        ImageView imageview_holiday;
        ImageView delete_holiday, edit_holiday;

        public ViewHolder(View v) {
            super(v);

            this.tvHolidayName = (TextView) v.findViewById(R.id.txHolidayName);
            this.tvfromDate = (TextView) v.findViewById(R.id.txStartDate);
            this.tv_to = (TextView) v.findViewById(R.id.txDivider);
            this.txEndDate = (TextView) v.findViewById(R.id.txEndDate);
            this.imageview_holiday = v.findViewById(R.id.imgHoliday);
            this.delete_holiday = v.findViewById(R.id.btnHolidayDelete);
            this.edit_holiday = v.findViewById(R.id.btnHolidayEdit);

            this.edit_holiday.setOnClickListener(this);
            this.delete_holiday.setOnClickListener(this);
            v.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btnHolidayEdit:
                    mItemClickListener.onEditHoliday(v, getAdapterPosition());

                    break;
                case R.id.btnHolidayDelete:
                    mItemClickListener.onDeleteHoliday(v, getAdapterPosition());
                    break;

            }
        }
    }

    public interface OnItemClickListener {
        public void onEditHoliday(View view, int position);

        public void onDeleteHoliday(View view, int position);

    }

    public void SetOnItemClickListener(HolidayAdapter.OnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }

    public interface HolidayAdapterListener {
        void onHolidaySelected(HolidayView holiday);
    }
}
