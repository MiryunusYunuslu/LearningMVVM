package com.example.retromvvm.Fragment;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.retromvvm.R;
import com.google.gson.internal.bind.util.ISO8601Utils;

import org.w3c.dom.ls.LSOutput;

public class DialogExample extends AppCompatDialogFragment {
    private EditText myname;
    private ImageView imageView;
    private  String myName;
    static String name="21";
    DialogExampleListener dialogExampleListener;
    private  ProfileFragment profileFragment=new ProfileFragment();

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater layoutInflater = getActivity().getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.dialog_layout, null);
        myname = view.findViewById(R.id.typeName);
        imageView = view.findViewById(R.id.updateimage);
        builder.setView(view).setTitle("Update Profile").setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        }).setPositiveButton("Done!", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
             if(!TextUtils.isEmpty(myname.getText())){
                 String name2=myname.getText().toString();
                 dialogExampleListener.applychanges(name2);
             }
            }
        });
        return builder.create();
    }

    @Override
    public void onAttach(@NonNull Context context) {

        super.onAttach(context);
        try {
            dialogExampleListener=(DialogExampleListener)context;
        }
        catch(ClassCastException e){
            throw  new ClassCastException(context.toString()+"must implemented ExampleDialogListener");
        }
    }
    public String getMyName() {
        return myName;
    }
    public interface DialogExampleListener{
        void applychanges(String name);
    }
}
