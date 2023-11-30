package com.example.pts;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

public class ContactDialoog extends AppCompatDialogFragment {
    TextView phonetxt,emailtxt;
    String phone,email;

    public void setText(String phone, String email){
        this.phone = phone;
        this.email = email;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.layout_dialog_contact,null);
        phonetxt = view.findViewById(R.id.phonenum);
        emailtxt = view.findViewById(R.id.emailadd);
        phonetxt.setText(phone);
        emailtxt.setText(email);

        builder.setView(view).setTitle("Contact")
                .setNegativeButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });




        return builder.create();
    }
}
