package com.example.celebrityapp.ui.industry;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.celebrityapp.IndustryAdapter;
import com.example.celebrityapp.R;
import com.example.celebrityapp.model.datasource.local.contentprovider.CelebrityProviderContract;

import java.util.ArrayList;
import java.util.HashSet;

public class IndustryFragment extends Fragment {

    private HashSet<String> industrySet;
    private static final String TAG = "TAG_IndustryFragment";

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_industry, container, false);
        industrySet = new HashSet<>();
        ContentResolver resolver = getActivity().getContentResolver();
        Uri industryURI = CelebrityProviderContract.CelebrityEntry.CELEBRITY_CONTENT_URI;
        Cursor c = resolver.query(
                industryURI,
                new String[]{"industry"},
                null,
                null,
                null);

        Log.d(TAG, "onCreateView: c:" + c.getCount());
        if (c.moveToFirst()) {
            do {
                industrySet.add(c.getString(0));
            } while (c.moveToNext());

            c.close();
        }

        ArrayList<String> industryList = new ArrayList<String>();
        industryList.addAll(industrySet);
        RecyclerView recyclerView = root.findViewById(R.id.industry_recycler_view);
        IndustryAdapter adapter = new IndustryAdapter(industryList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        return root;

    }
}