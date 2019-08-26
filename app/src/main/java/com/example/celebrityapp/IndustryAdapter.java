package com.example.celebrityapp;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.celebrityapp.ui.celebrity.CelebritiesFragment;

import java.util.ArrayList;

public class IndustryAdapter extends RecyclerView.Adapter<IndustryAdapter.ViewHolder> {

    private static final String TAG = "TAG_IndustryAdapter";
    private ArrayList<String> industryList;
    private Context context;
    private View parent;


    public IndustryAdapter(ArrayList<String> industryList) {
        Log.d(TAG, "IndustryAdapter: ");
        this.industryList = industryList;
        Log.d(TAG, "IndustryAdapter: list: " + this.industryList.toString());
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        this.parent = parent;
        View inflatedItem = LayoutInflater.from(context)
                .inflate(R.layout.industry_layout_item, parent, false);
        return new ViewHolder(inflatedItem);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final String industry = industryList.get(position);
        holder.industryTV.setText(industry);

        holder.industryTV.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                FragmentTransaction ft = ((FragmentActivity)context).getSupportFragmentManager().beginTransaction();
                Log.d(TAG, "onTouch: ");
                Fragment frag = new CelebritiesFragment();
                Bundle args = new Bundle();
                args.putString("type", "Industry");
                args.putString("industry", industry);
                frag.setArguments(args);
                ft.replace(R.id.fragment_container, frag).commit();
                return false;
            }
        });
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
