package com.example.exalogicsolutions.inmegh_kmct.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.exalogicsolutions.inmegh_kmct.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class NonTeachersFragment extends Fragment {


    public NonTeachersFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_non_teachers, container, false);
    }

}
