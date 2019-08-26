package com.example.celebrityapp.ui.add;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.celebrityapp.R;
import com.example.celebrityapp.model.Celebrity;
import com.example.celebrityapp.model.datasource.local.contentprovider.CelebrityProviderContract;
import com.example.celebrityapp.ui.celebrity.CelebritiesFragment;


public class AddFragment extends Fragment {

   private static final String TAG = "TAG_AddFragment";
    private EditText firstNameET;
    private EditText lastNameET;
    private EditText heightET;
    private EditText dobET;
    private EditText industryET;
    private Button addButton;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             final ViewGroup container, Bundle savedInstanceState) {

        Log.d(TAG, "onCreateView: ");
        final View root = inflater.inflate(R.layout.fragment_add,container,false);

        bindViews(root);

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

                    Celebrity c = new Celebrity(firstName, lastName, height, industry, dob, false);
                    Log.d(TAG, "onClick: c: " + c.getId());
                    ContentResolver resolver = getActivity().getContentResolver();
                    ContentValues values = c.getContentValues();
                    Log.d(TAG, "onClick: values: " + values.getAsLong("id"));
                    Uri insertUri = CelebrityProviderContract.CONTENT_URI;
                    resolver.insert(insertUri, values);
                    FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                    Bundle args = new Bundle();
                    args.putString("type", "All Celebrities");
                    Fragment fragment = new CelebritiesFragment();
                    fragment.setArguments(args);
                    ft.replace(R.id.fragment_container, fragment).commit();
                }

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
    }

}
