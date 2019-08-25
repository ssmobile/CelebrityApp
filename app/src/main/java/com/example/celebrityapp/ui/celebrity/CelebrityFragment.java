package com.example.celebrityapp.ui.celebrity;

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

import com.example.celebrityapp.CelebrityAdapter;
import com.example.celebrityapp.R;
import com.example.celebrityapp.model.Celebrity;
import com.example.celebrityapp.model.datasource.local.contentprovider.CelebrityProviderContract;

import java.util.ArrayList;

public class CelebrityFragment extends Fragment {

    public static final String TAG = "TAG_CelebFragment";

    private ArrayList<Celebrity> celebrityList;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        Log.d(TAG, "onCreateView: ");

        View root = inflater.inflate(R.layout.fragment_view_celebrities, container, false);
        celebrityList = new ArrayList<>();
        ContentResolver resolver = getActivity().getContentResolver();
        Uri celebrityListURI = CelebrityProviderContract.CelebrityEntry.CELEBRITY_CONTENT_URI;
        Cursor c = resolver.query(
                celebrityListURI,
                Celebrity.keys,
                null,
                null,
                null);

        if (c.moveToFirst()) {
            do {
                Celebrity celeb = new Celebrity();
                Log.d(TAG, "fromCursor: fname: " + c.getString(0));

                celeb.setFirstName(c.getString(0));
                celeb.setLastName(c.getString(1));
                celeb.setHeight(c.getString(2));
                celeb.setIndustry(c.getString(3));
                celeb.setDob(c.getString(4));
                celeb.setFavorite(c.getInt(5)!=0);

                Log.d(TAG, "fromCursor: celeb: " + celeb.toString());
                celebrityList.add(celeb);
            } while (c.moveToNext());

            c.close();
        }

        RecyclerView recyclerView = root.findViewById(R.id.celebrity_list_recyclerview);
        CelebrityAdapter adapter = new CelebrityAdapter(celebrityList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());


        Log.d(TAG, "onCreateView: ");

        return root;
    }
}