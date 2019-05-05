package com.example.login;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.widget.Toast;

public class Dialogs extends DialogFragment {

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        String tag = getTag();
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        switch (tag){
            case "close":
                builder.setMessage("Estas seguro que deseas salir?")
                        .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                /*Close the entire application and killing the process*/
                                getActivity().finishAffinity();
                            }
                        }).setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                });
            break;

            case "end_session":
                builder.setMessage("La sesión se ha cerrado")
                        .setNeutralButton("Aceptar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        getActivity().finish();
                    }
                });
            break;
            
            default:
                Toast.makeText(getActivity(), "Opcion incorrecta", Toast.LENGTH_SHORT).show();
            break;
        }

        return builder.create();
    }
}
