package com.example.celebrityapp;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;

import com.example.celebrityapp.model.Celebrity;
import com.example.celebrityapp.model.Film;
import com.example.celebrityapp.model.datasource.local.contentprovider.CelebrityContentProvider;
import com.example.celebrityapp.model.datasource.local.contentprovider.CelebrityProviderContract;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.util.Log;
import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private NavigationView navigationView;
    private Toolbar toolbar;
    private FloatingActionButton fab;
    public static final String TAG = "TAG_MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bindViews();
        configureViews();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    private void bindViews() {
        fab = findViewById(R.id.fab);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        toolbar = findViewById(R.id.toolbar);
        navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow,
                R.id.nav_tools, R.id.nav_share, R.id.nav_send)
                .setDrawerLayout(drawer)
                .build();
    }

    private void configureViews() {
        setSupportActionBar(toolbar);
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

                Celebrity celebrity = new Celebrity("John", "Cena", "6.1",
                        "Wrestler", "01/01/1970", true);
                ContentValues contentValues = celebrity.getContentValues();

                ContentResolver contentResolver = getContentResolver();
                contentResolver.insert(
                        CelebrityProviderContract.CONTENT_URI, contentValues);
                Cursor c = contentResolver.query(
                        CelebrityProviderContract.CelebrityEntry.CELEBRITY_CONTENT_URI,
                        Celebrity.keys,
                        null,
                        null,
                        null);

                if (c.moveToFirst()) {
                    do {
                        for (int i=0 ; i < Celebrity.keys.length ; i++) {
                            String s = c.getString(i);
                            Log.d(TAG, "onClick: s: " + s);
                        }
                    } while (c.moveToNext());
                }
                Log.d(TAG, "onClick: c.count=" + c.getCount());


            }
        });
    }
}
