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

import com.example.celebrityapp.CelebritiesAdapter;
import com.example.celebrityapp.R;
import com.example.celebrityapp.model.Celebrity;
import com.example.celebrityapp.model.datasource.local.contentprovider.CelebrityProviderContract;

import java.util.ArrayList;

public class CelebritiesFragment extends Fragment {

    public static final String TAG = "TAG_CelebFragment";

    private ArrayList<Celebrity> celebrityList;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_celebrities, container, false);
        celebrityList = new ArrayList<>();
        Bundle b = getArguments();
        String title = "";
        String industry = "";

        try {
            title = b.getString("type");
            industry = b.getString("industry");
        } catch (NullPointerException e) {
            Log.e(TAG, "onCreateView: ", e);
        }

        Log.d(TAG, "onCreateView: title: " + title);
        String selection = null;
        String[] selectionArgs = null;

        switch (title) {
            case "All Celebrities":
                selection = null;
                selectionArgs = null;
                break;
            case "Favorites":
                selection = "is_favorite = ?";
                selectionArgs = new String[]{"1"};
                break;
            case "Industry":
                Log.d(TAG, "onCreateView: INDUSTRY");
                selection = "industry = ?";
                selectionArgs = new String[]{industry};
        }

        ContentResolver resolver = getActivity().getContentResolver();
        Uri celebrityListURI = CelebrityProviderContract.CelebrityEntry.CELEBRITY_CONTENT_URI;


        Cursor c = resolver.query(
                celebrityListURI,
                Celebrity.keys,
                selection,
                selectionArgs,
                null);

        if (c.moveToFirst()) {
            do {

                Celebrity celeb = new Celebrity();
                celeb.setId(c.getLong(0));
                celeb.setFirstName(c.getString(1));
                celeb.setLastName(c.getString(2));
                celeb.setHeight(c.getString(3));
                celeb.setIndustry(c.getString(4));
                celeb.setDob(c.getString(5));
                celeb.setFavorite(c.getInt(6)!=0);

                celebrityList.add(celeb);
            } while (c.moveToNext());

            c.close();
        }

        RecyclerView recyclerView = root.findViewById(R.id.celebrity_list_recyclerview);
        CelebritiesAdapter adapter = new CelebritiesAdapter(celebrityList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        return root;
    }
}