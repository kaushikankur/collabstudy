package com.example.cs553.collaborativestudy;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 */
public class CreatePostFragment extends Fragment {
    private DatabaseReference mDatabase;
    private String mUserId;
    private FirebaseAuth mFirebaseAuth;
    private FirebaseUser mFirebaseUser;
    View rootView;

    public CreatePostFragment() {
        // Required empty public constructor

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_create_post, container, false);
        final EditText courseId = (EditText) rootView.findViewById(R.id.courseId);
        final EditText courseDesc = (EditText) rootView.findViewById(R.id.courseDesc);
        final EditText location = (EditText) rootView.findViewById(R.id.location);
        final EditText time = (EditText) rootView.findViewById(R.id.time);
        final Button button = (Button) rootView.findViewById(R.id.createPost);


        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseUser = mFirebaseAuth.getCurrentUser();
        mDatabase = FirebaseDatabase.getInstance().getReference();


        mUserId = mFirebaseUser.getUid();

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(getActivity(), "I am here", Toast.LENGTH_LONG).show();
                Map<String, String> users = new HashMap<>();


                users.put("courseId", courseId.getText().toString());
                users.put("courseDescription", courseDesc.getText().toString());
                users.put("location", location.getText().toString());
                users.put("time", time.getText().toString());

                mDatabase.child("users").child(mUserId).push().setValue(users);

                courseId.setText("");
                courseDesc.setText("");
                location.setText("");
                time.setText("");

            }
        });
        return rootView;
    }
}


