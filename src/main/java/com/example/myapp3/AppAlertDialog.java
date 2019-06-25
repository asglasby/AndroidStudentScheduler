package com.example.myapp3;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDialogFragment;

public class AppAlertDialog extends AppCompatDialogFragment {
    public static int alertType = 0;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        switch (alertType){
            case 1:
                AlertDialog.Builder termBuilder = new AlertDialog.Builder(getActivity());
                termBuilder.setTitle("Delete Courses from Term")
                        .setMessage("Please delete courses from Term Course List and try again.")
                        .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        });
                return termBuilder.create();
            default:
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("Action Not Allowed")
                        .setMessage("Please check your entries and try again.")
                        .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        });
                return builder.create();

        }
    }
}
