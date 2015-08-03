package com.omr.solutions.utils.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Toast;

import visitas.solutions.moov.com.omarutils.R;

/**
 * Created by omar on 3/08/15.
 */
public class DialogFragmentGeneric extends DialogFragment implements DialogInterface.OnClickListener {


    DialogFinishListener dialogFinishListener;


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());


        builder.setMessage(R.string.message)
                .setPositiveButton(R.string.ok, this)
                .setNegativeButton(R.string.cancel, this);

        return builder.create();


    }

    @Override
    public void onClick(DialogInterface dialog, int which) {

        String message =  null;

        if (which == DialogInterface.BUTTON_POSITIVE){
            message = "Click OK";
        }
        if (which == DialogInterface.BUTTON_NEGATIVE){
            message = "Click Cancel";
        }
        if (which == DialogInterface.BUTTON_NEUTRAL){
            message = "Click neutral";
        }

        Toast.makeText(getActivity(),message,Toast.LENGTH_SHORT).show();

        dialog.dismiss();
    }
}
