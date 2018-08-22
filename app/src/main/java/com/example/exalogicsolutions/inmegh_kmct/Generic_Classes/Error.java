package com.example.exalogicsolutions.inmegh_kmct.Generic_Classes;

import android.content.Context;
import android.widget.Toast;

import com.example.exalogicsolutions.inmegh_kmct.Utilities.Constants;

public class Error {

    Context context;

    public void errorStatus(Integer status) {
        try {
            if (status.equals(Constants.SWR)) {
                Toast.makeText(context, "Something went wrong redirect to back ", Toast.LENGTH_SHORT).show();
            } else if (status.equals(Constants.NDF)) {
                Toast.makeText(context, " NO Datafound ", Toast.LENGTH_SHORT).show();
            } else if (status.equals(Constants.CMF)) {
                Toast.makeText(context, "Check all the mandatory fields", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
