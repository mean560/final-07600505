package com.example.speedrecords.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.speedrecords.R;
import com.example.speedrecords.model.Record;

import java.util.Locale;

public class RecordAdapter extends RecyclerView.Adapter<RecordAdapter.MyViewHolder> {

    private Context mContext;
    private Record[] mRecords;

    public RecordAdapter(Context context, Record[] records) {
        this.mContext = context;
        this.mRecords = records;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Record record = mRecords[position];

        String str_sum = String.format(Locale.getDefault(), "%.1f", record.sum);
        String str_dis = String.format(Locale.getDefault(), "%.1f", record.distance);
        String str_dura = String.format(Locale.getDefault(), "%.1f", record.duration);


        holder.sumTextView.setText(str_sum + " KM/H");
        holder.distanceTextView.setText(str_dis + " METERS");
        holder.durationTextView.setText(str_dura + " SECONDS");
        holder.cowredImageView.setImageResource(record.sum >= 80 ? R.drawable.red_cow : null);
    }


    @Override
    public int getItemCount() {
        return mRecords.length;
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView sumTextView;
        TextView distanceTextView;
        TextView durationTextView;
        ImageView cowredImageView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            this.sumTextView = itemView.findViewById(R.id.sum_text_view);
            this.distanceTextView = itemView.findViewById(R.id.distance_text_view);
            this.durationTextView = itemView.findViewById(R.id.duration_text_view);
            this.cowredImageView = itemView.findViewById(R.id.cow_red_image_viwe);
        }
    }
}
