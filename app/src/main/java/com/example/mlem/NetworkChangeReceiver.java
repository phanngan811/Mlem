package com.example.mlem;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

public class NetworkChangeReceiver {
    public static boolean isConnectedToInternet(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cm != null) {
            NetworkInfo[] info = cm.getAllNetworkInfo();
            if(info!=null){
                for(int i=0; i<info.length;i++){
                    if(info[i].getState() == NetworkInfo.State.CONNECTED)
                        return true;
                }
            }
        }
        return false;
    }
//    @Override
//    public void onReceive(Context context, Intent intent) {
//        try{
//            if(isOnline(context)){
//                Toast.makeText(context, "Network Connected", Toast.LENGTH_SHORT).show();
//            }else{
//                Toast.makeText(context, "No Network Connection", Toast.LENGTH_SHORT).show();
//            }
//        }
//        catch (NullPointerException e){
//            e.printStackTrace();
//        }
//    }
//    public boolean isOnline(Context context){
//        try{
//            ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
//            NetworkInfo networkInfo = cm.getActiveNetworkInfo();
//            return (networkInfo!=null && networkInfo.isConnected());
//        }catch (NullPointerException e){
//            e.printStackTrace();
//            return false;
//        }
//    }
}