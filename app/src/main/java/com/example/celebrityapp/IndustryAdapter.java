package com.example.celebrityapp;

import android.content.ContentResolver;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class IndustryAdapter extends RecyclerView.Adapter<IndustryAdapter.ViewHolder> {

    private static final String TAG = "TAG_IndustryAdapter";
    private ArrayList<String> industryList;

    public IndustryAdapter(ArrayList<String> industryList) {
        Log.d(TAG, "IndustryAdapter: ");
        this.industryList = industryList;
        Log.d(TAG, "IndustryAdapter: list: " + this.industryList.toString());
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View inflatedItem = LayoutInflater.from(context)
                .inflate(R.layout.industry_layout_item, parent, false);
        return new ViewHolder(inflatedItem);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final String industry = industryList.get(position);
        holder.industryTV.setText(industry);
    }

    @Override
    public int getItemCount() {
        return industryList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView industryTV;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            industryTV = itemView.findViewById(R.id.recycler_text_view);

        }
    }
}
