//package push;
//
//import android.content.BroadcastReceiver;
//import android.content.Context;
//import android.content.Intent;
//import android.os.Bundle;
//
//
//public class ExternalReceiver extends BroadcastReceiver {
//
//	 static final String TAG = "GmcBroadcastReceiver";
//	   Context ctx;
//    public void onReceive(Context context, Intent intent) {
//        if(intent!=null){
//            Bundle extras = intent.getExtras();
//            if(!AndroidMobilePushApp.inBackground){
//                MessageReceivingService.sendToApp(extras, context);
//            }
//            else{
//                 MessageReceivingService.saveToLog(intent.getExtras(), context);
//                MessageReceivingService.postNotification(null,context);
//            }
//        }
//    }
//
//	/*
//
//    private void postNotification(Bundle data) {
//        String msg = data.getString("message");
//        Log.i(TAG, "message: " + msg);
//
//         if (msg == null)  // on app startup, this was always getting called with empty message
//           return;
//
//        int badge = Integer.parseInt(data.getString("badge","0"));
//
//        Intent intent = new Intent(ctx, SlidingMenuActivity.class);
//        PendingIntent contentIntent = PendingIntent.getActivity(ctx, 0, intent, 0); //, data);
//
//      //  Uri sound = Uri.parse("android.resource://com.peoplefun.wordchums/raw/push");
//        NotificationCompat.Builder builder = new NotificationCompat.Builder(ctx)
//        .setSmallIcon(R.drawable.ibinder72)
//        .setContentTitle("Word Chums")
//        .setContentText(msg)
//        .setTicker(msg)
//        .setStyle(new NotificationCompat.BigTextStyle().bigText(msg))
//        .setAutoCancel(true)
//        .setOnlyAlertOnce(true)
//        //.setSound(sound)
//        .setDefaults(Notification.DEFAULT_VIBRATE);
//        if (badge > 0)
//           builder.setNumber(badge);
//
//        //builder.setContentIntent(contentIntent);
//        NotificationManager notificationManager = (NotificationManager)ctx.getSystemService(Context.NOTIFICATION_SERVICE);
//        notificationManager.notify((int)System.currentTimeMillis(), builder.build());
//     }*/
//}
//
