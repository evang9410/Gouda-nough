package com.nough.gouda.goudanough;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;

/**
 * Created by Evang on 2016-11-24.
 */

public class GoudaNoughAlertDialog extends DialogFragment {
    private String header; // user getArguments to retrieve these values.
    private String text;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        String header = "";
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        if(getArguments() != null){
            Bundle bundle = getArguments();
            if(bundle.containsKey("header")){
                header = bundle.getString("header");
                builder.setTitle(header);
            }
        }

        LayoutInflater inflater = getActivity().getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.gouda_nough_alert, null))
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
        return builder.create();
    }
}
