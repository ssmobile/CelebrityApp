package com.example.celebrityapp;

import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.celebrityapp.model.Celebrity;
import com.example.celebrityapp.model.datasource.local.contentprovider.CelebrityProviderContract;

import java.util.ArrayList;

public class CelebrityAdapter extends RecyclerView.Adapter<CelebrityAdapter.ViewHolder> {

    private static final String TAG = "TAG_CelebrityAdapter";
    private ArrayList<Celebrity> celebrityList;
    private Context context;

    public CelebrityAdapter(ArrayList<Celebrity> celebrityList) {
        Log.d(TAG, "CelebrityAdapter: ");
        this.celebrityList = celebrityList;
        Log.d(TAG, "CelebrityAdapter: list: " + this.celebrityList.toString());
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View inflatedItem = LayoutInflater.from(context)
                .inflate(R.layout.celebrity_layout_item, parent, false);
        return new ViewHolder(inflatedItem);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Celebrity celebrity = celebrityList.get(position);
        final ImageButton favButton = holder.favButtonView;
        final ViewGroup parent = (ViewGroup)holder.itemView.getParent();
        final View itemView = holder.itemView.findViewById(R.id.constraint_layout);
        Log.d(TAG, "onBindViewHolder: itemView: "  + itemView.toString());
        holder.celebrityNameView.setText(
                String.format("%s %s",celebrity.getFirstName(),celebrity.getLastName()));
        favButton.setImageResource(
            celebrity.isFavorite() ? R.drawable.ic_heart_full : R.drawable.ic_heart_empty);
        favButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ContentResolver resolver = context.getContentResolver();
                Uri updateURI = CelebrityProviderContract.CelebrityEntry.CELEBRITY_CONTENT_URI;
                celebrity.setFavorite(!celebrity.isFavorite());
                resolver.update(updateURI,
                        celebrity.getContentValues(),
                        "id = ?",
                        new String[]{String.valueOf(celebrity.getId())});

                favButton.setImageResource(
                        celebrity.isFavorite() ? R.drawable.ic_heart_full : R.drawable.ic_heart_empty);

            }
        });

        holder.celebrityNameView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                AlertDialog alertDialog = new AlertDialog.Builder(context).create();
                alertDialog.setTitle("WARNING");
                alertDialog.setMessage(
                        String.format("Are you sure you want to delete %s %s from your list?",
                        celebrity.getFirstName(), celebrity.getLastName()));

                alertDialog.setButton("DELETE", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                            ContentResolver resolver = context.getContentResolver();
                            Uri deleteUri = CelebrityProviderContract.CelebrityEntry.CELEBRITY_CONTENT_URI;
                            resolver.delete(deleteUri,
                                    "id = ?",
                                    new String[]{String.valueOf(celebrity.getId())});

                        Log.d(TAG, "onClick: itemView" + itemView.toString());
                        itemView.setVisibility(View.GONE);
                        dialogInterface.dismiss();
                    }
                });

                alertDialog.show();

                return false;
            }
        });
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
