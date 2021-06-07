//package com.example.final_projects.Alarm;
//
//import android.content.BroadcastReceiver;
//import android.content.Context;
//import android.content.Intent;
//
//import androidx.core.app.NotificationCompat;
//
//public class Alarm_Receiver extends BroadcastReceiver {
//    @Override
//    public void onReceive(Context context, Intent intent) {
//       NotificayionHelper notificayionHelper =new NotificayionHelper(context);
//        NotificationCompat.Builder nb = notificayionHelper.getChannelNotification();
//        notificayionHelper.getManager().notify(1, nb.build());
//
//    }
//}
