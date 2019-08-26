package com.example.celebrityapp;

import android.os.Bundle;

import com.example.celebrityapp.ui.add.AddFragment;
import com.example.celebrityapp.ui.celebrity.CelebritiesFragment;
import com.example.celebrityapp.ui.fileio.FileIOFragment;
import com.example.celebrityapp.ui.industry.IndustryFragment;
import com.example.celebrityapp.ui.main.MainFragment;

import android.util.Log;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private NavigationView navigationView;
    private Toolbar toolbar;
    private DrawerLayout drawer;
    public static final String TAG = "TAG_MainActivity";
    public static int celebType = 0;
    private Fragment frag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bindViews();
        configureViews();
        frag = new CelebritiesFragment();

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new MainFragment()).commit();
        }

        navigationView.setCheckedItem(R.id.nav_main);
    }

    private void bindViews() {
        drawer = findViewById(R.id.drawer_layout);
        toolbar = findViewById(R.id.toolbar);
        navigationView = findViewById(R.id.nav_view);

    }

    private void configureViews() {
        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                int itemid = menuItem.getItemId();
                switch (itemid) {
                    case R.id.nav_fileio:
                        frag = new FileIOFragment();
                        break;
                    case R.id.nav_add:
                        Log.d(TAG, "onNavigationItemSelected: navadd");
                        frag = new AddFragment();
                        break;
                    case R.id.nav_industries:
                        frag = new IndustryFragment();
                        break;
                    default:
                        frag = new CelebritiesFragment();
                        Log.d(TAG, "onNavigationItemSelected: defualt");
                        Bundle args = new Bundle();
                        args.putString("type", menuItem.getTitle().toString());
                        frag.setArguments(args);
                }

                Log.d(TAG, "onNavigationItemSelected: ftreplace");
                ft.replace(R.id.fragment_container, frag).commit();

                drawer.closeDrawer(GravityCompat.START);
                return false;

            }
        });

    }

    public void onClick(MenuItem item) {
        Log.d(TAG, "onClick: ");

    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.onNavDestinationSelected(item, navController)
                || super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        Log.d(TAG, "onNavigationItemSelected: ");

        return false;
    }
}
