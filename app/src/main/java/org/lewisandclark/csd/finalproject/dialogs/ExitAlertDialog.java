package org.lewisandclark.csd.finalproject.dialogs;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

import org.lewisandclark.csd.finalproject.R;

public class ExitAlertDialog extends DialogFragment {

    private static final String TITLE_KEY = "title_key";
    private static final String MESSAGE_KEY = "message_key";

    public ExitAlertDialog() {
    }

    public static ExitAlertDialog newInstance(String title, String message) {

        Bundle args = new Bundle();
        args.putString(TITLE_KEY, title);
        args.putString(MESSAGE_KEY, message);

        ExitAlertDialog fragment = new ExitAlertDialog();
        fragment.setArguments(args);
        return fragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        Bundle args = getArguments();
        if (args == null) throw new AssertionError();

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setTitle(R.string.exit_warning);
        builder.setMessage(R.string.exit_question)
                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //exits app
                        getActivity().finish();
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                    }
                });

     //   builder.setPositiveButton(android.R.string.ok, null);
        return builder.create();

    }

}