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
public class AdminTrashMailFragment extends Fragment {


    public AdminTrashMailFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_admin_trash_mail, container, false);
    }

}
