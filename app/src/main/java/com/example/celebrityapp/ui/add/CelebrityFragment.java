package com.example.celebrityapp.ui.add;

import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.celebrityapp.R;
import com.example.celebrityapp.model.Celebrity;
import com.example.celebrityapp.model.datasource.local.contentprovider.CelebrityProviderContract;
import com.example.celebrityapp.ui.celebrity.CelebritiesFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class CelebrityFragment extends Fragment {

   private static final String TAG = "TAG_AddFragment";
    private EditText firstNameET;
    private EditText lastNameET;
    private EditText heightET;
    private EditText dobET;
    private EditText industryET;
    private TextView firstNameTV;
    private TextView lastNameTV;
    private TextView heightTV;
    private TextView dobTV;
    private TextView industryTV;
    private Button addButton;
    private FloatingActionButton editFab;
    private FloatingActionButton delFab;

    private Celebrity celebrity;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             final ViewGroup container, Bundle savedInstanceState) {

        Log.d(TAG, "onCreateView: ");
        final View root = inflater.inflate(R.layout.fragment_add,container,false);

        bindViews(root);
        final Bundle args = getArguments();

        try {
            celebrity = args.getParcelable("celebrity");
        } catch (Exception e) {
            Log.e(TAG, "onCreateView: ", e);
        }
        configureViews();



        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isFilled = true;
                ViewGroup rootGroup = (ViewGroup) root;

                for (int i = 0 ; i < rootGroup.getChildCount() ; i++) {
                    View v = rootGroup.getChildAt(i);

                    if (v instanceof EditText) {
                        if (TextUtils.isEmpty(((EditText)v).getText())) {
                            ((EditText)v).setError("This field is required");
                            isFilled = false;
                        }
                    }
                }

                if (isFilled) {

                    String firstName = firstNameET.getText().toString();
                    String lastName = lastNameET.getText().toString();
                    String dob = dobET.getText().toString();
                    String industry = industryET.getText().toString();
                    String height = heightET.getText().toString();
                    boolean isFavorite = false;
                    if (args != null) {isFavorite = celebrity.isFavorite();}
                    Celebrity c = new Celebrity(firstName, lastName, height, industry, dob, isFavorite);
                    ContentResolver resolver = getActivity().getContentResolver();
                    ContentValues values = c.getContentValues();
                    if (args == null) {
                        Uri insertUri = CelebrityProviderContract.CONTENT_URI;
                        resolver.insert(insertUri, values);
                    } else {
                        c.setId(celebrity.getId());
                        Uri updateUri = CelebrityProviderContract.CONTENT_URI;
                        resolver.update(updateUri, values, null,
                                new String[]{String.valueOf(celebrity.getId())});
                    }

                    FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                    Bundle args = new Bundle();
                    args.putString("type", "All Celebrities");
                    Fragment fragment = new CelebritiesFragment();
                    fragment.setArguments(args);
                    ft.replace(R.id.fragment_container, fragment).commit();
                }

            }
        });

        editFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firstNameTV.setVisibility(View.INVISIBLE);
                lastNameTV.setVisibility(View.INVISIBLE);
                heightTV.setVisibility(View.INVISIBLE);
                industryTV.setVisibility(View.INVISIBLE);
                dobTV.setVisibility(View.INVISIBLE);

                firstNameET.setVisibility(View.VISIBLE);
                lastNameET.setVisibility(View.VISIBLE);
                heightET.setVisibility(View.VISIBLE);
                industryET.setVisibility(View.VISIBLE);
                dobET.setVisibility(View.VISIBLE);
                addButton.setVisibility(View.VISIBLE);
                addButton.setText("DONE");
                editFab.hide();
                delFab.hide();
            }
        });


        delFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog alertDialog = new AlertDialog.Builder(getContext()).create();
                alertDialog.setTitle("WARNING");
                alertDialog.setMessage(
                        String.format("Are you sure you want to delete %s %s from your list?",
                                celebrity.getFirstName(), celebrity.getLastName()));

                alertDialog.setButton("DELETE", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        ContentResolver resolver = getContext().getContentResolver();
                        Uri deleteUri = CelebrityProviderContract.CelebrityEntry.CELEBRITY_CONTENT_URI;
                        resolver.delete(deleteUri,
                                "id = ?",
                                new String[]{String.valueOf(celebrity.getId())});

                        dialogInterface.dismiss();
                        FragmentTransaction ft = getActivity().getSupportFragmentManager()
                                .beginTransaction();
                        Fragment frag = new CelebritiesFragment();
                        ft.replace(R.id.fragment_container, frag).commit();

                    }
                });

                alertDialog.show();

            }
        });

        return root;
    }

    private void bindViews(View root) {
        firstNameET = root.findViewById(R.id.first_nameET);
        lastNameET = root.findViewById(R.id.last_nameET);
        heightET = root.findViewById(R.id.heightET);
        dobET = root.findViewById(R.id.dobET);
        industryET = root.findViewById(R.id.industryET);
        addButton = root.findViewById(R.id.add_celeb_button);
        editFab = root.findViewById(R.id.edit_fab);
        delFab = root.findViewById(R.id.del_fab);
        firstNameTV = root.findViewById(R.id.first_nameTV);
        lastNameTV = root.findViewById(R.id.last_nameTV);
        heightTV = root.findViewById(R.id.heightTV);
        dobTV = root.findViewById(R.id.dobTV);
        industryTV = root.findViewById(R.id.industryTV);

    }

    private void configureViews() {

        if (celebrity!=null) {
            firstNameTV.setText(celebrity.getFirstName());
            lastNameTV.setText(celebrity.getLastName());
            heightTV.setText(celebrity.getHeight());
            industryTV.setText(celebrity.getIndustry());
            dobTV.setText(celebrity.getDob());

            firstNameET.setText(celebrity.getFirstName());
            lastNameET.setText(celebrity.getLastName());
            heightET.setText(celebrity.getHeight());
            industryET.setText(celebrity.getIndustry());
            dobET.setText(celebrity.getDob());

            firstNameTV.setVisibility(View.VISIBLE);
            lastNameTV.setVisibility(View.VISIBLE);
            heightTV.setVisibility(View.VISIBLE);
            industryTV.setVisibility(View.VISIBLE);
            dobTV.setVisibility(View.VISIBLE);

            firstNameET.setVisibility(View.INVISIBLE);
            lastNameET.setVisibility(View.INVISIBLE);
            heightET.setVisibility(View.INVISIBLE);
            industryET.setVisibility(View.INVISIBLE);
            dobET.setVisibility(View.INVISIBLE);
            addButton.setVisibility(View.INVISIBLE);

            delFab.show();
            editFab.show();
        }

    }

}
