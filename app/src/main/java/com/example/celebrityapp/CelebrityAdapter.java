package com.example.celebrityapp;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.celebrityapp.model.Celebrity;

import java.util.ArrayList;

public class CelebrityAdapter extends RecyclerView.Adapter<CelebrityAdapter.ViewHolder> {

    private static final String TAG = "TAG_CelebrityAdapter";

    ArrayList<Celebrity> celebrityList;

    public CelebrityAdapter(ArrayList<Celebrity> celebrityList) {
        Log.d(TAG, "CelebrityAdapter: ");
        this.celebrityList = celebrityList;
        Log.d(TAG, "CelebrityAdapter: list: " + this.celebrityList.toString());
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflatedItem = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.celebrity_layout_item, parent, false);
        return new ViewHolder(inflatedItem);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Celebrity celebrity = celebrityList.get(position);
        holder.celebrityNameView.setText(celebrity.getFirstName().concat(celebrity.getLastName()));

        holder.favButtonView.setImageResource(
            celebrity.isFavorite() ? R.drawable.ic_heart_full : R.drawable.ic_heart_empty);
    }

    @Override
    public int getItemCount() {
        return celebrityList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView celebrityNameView;
        private ImageButton favButtonView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            celebrityNameView = itemView.findViewById(R.id.fname_lname);
            favButtonView = itemView.findViewById(R.id.fav_button);
        }
    }
}
