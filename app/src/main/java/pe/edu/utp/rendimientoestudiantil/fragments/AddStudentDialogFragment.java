package pe.edu.utp.rendimientoestudiantil.fragments;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;

import pe.edu.utp.rendimientoestudiantil.R;

/**
 * Created by ORACLE on 11/06/2016.
 */
public class AddStudentDialogFragment  extends DialogFragment {

    public interface  AddStudentDialogListener {
        public void onDialogPositiveClick(DialogFragment dialog);
        public void onDialogNegativeClick(DialogFragment dialog);
    }
    AddStudentDialogListener mListener;


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        // Verify that the host activity implements the callback interface
        try {
            // Instantiate the NoticeDialogListener so we can send events to the host
            mListener = (AddStudentDialogListener) activity;
        } catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException(activity.toString()
                    + " must implement AddStudentDialogListener");
        }
    }
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Build the dialog and set up the button click handlers
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle( R.string.title_activity_add_student );
        LayoutInflater inflater = getActivity().getLayoutInflater();

        View promptsView = inflater.inflate(R.layout.dialog_add_student, null);

        builder.setView( promptsView )
                .setPositiveButton(R.string.action_add, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        mListener.onDialogPositiveClick(AddStudentDialogFragment.this);
                    }
                })
                .setNegativeButton(R.string.action_cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        AddStudentDialogFragment.this.getDialog().cancel();
                    }
                });

        return builder.create();
    }
}
