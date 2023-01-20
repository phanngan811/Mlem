package com.example.mlem;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

public class NetworkChangeListener extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if(!NetworkingChange.isConnectedToInternet(context)){
            AlertDialog.Builder builder =new AlertDialog.Builder(context);
            View layout_Dialog = LayoutInflater.from(context).inflate(R.layout.no_internet, null);
            builder.setView(layout_Dialog);

            Button btnTry = layout_Dialog.findViewById(R.id.btnTry);

            //show dialog
            AlertDialog dialog = builder.create();
            dialog.show();
            dialog.setCancelable(false);

            dialog.getWindow().setGravity(Gravity.CENTER);

            btnTry.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.dismiss();
                    onReceive(context, intent);
                }
            });
        }
    }
}