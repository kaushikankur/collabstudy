package com.example.cs553.collaborativestudy;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment {

    private DatabaseReference mDatabase;
    private String mUserId;
    private FirebaseAuth mFirebaseAuth;
    private FirebaseUser mFirebaseUser;
    View rootView;
    public MainFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
      //  super.onCreateView(inflater, container, savedInstanceState);

         rootView = inflater.inflate(R.layout.fragment_main, container, false);

        final ListView myList = (ListView) rootView.findViewById(R.id.myListView);

        myList.setClickable(true);
        final ArrayList<String> links = new ArrayList<String>();
        links.add("Ankky");
        links.add("Herms");
        links.add( "Ceres" );
        links.add( "Pluto" );
        links.add( "Haumea");
        links.add( "Makemake" );
        links.add( "Eris" );
        //final HashMap<String,String> linksHash = new HashMap<String,String>();
        final ArrayAdapter<String> aa = new ArrayAdapter<String>(getActivity(), R.layout.rowitem, links);
        myList.setAdapter(aa);

        return rootView;
    }

}
