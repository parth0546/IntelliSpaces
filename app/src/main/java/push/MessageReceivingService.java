//package push;
//
//
//import android.app.Notification;
//import android.app.NotificationManager;
//import android.app.PendingIntent;
//import android.app.Service;
//import android.content.Context;
//import android.content.Intent;
//import android.content.SharedPreferences;
//import android.os.Build.VERSION;
//import android.os.Bundle;
//import android.os.IBinder;
//import android.preference.PreferenceManager;
//import android.support.v4.app.NotificationCompat;
//import android.text.TextUtils;
//
//
//import wtech.com.intellispaces.R;
//
//
///*
// * This service is designed to run in the background and receive messages from gcm. If the app is in the foreground
// * when a message is received, it will immediately be posted. If the app is not in the foreground, the message will be saved
// * and a notification is posted to the NotificationManager.
// */
//public class MessageReceivingService extends Service {
//
//    public static SharedPreferences savedValues;
//    static NotificationManager mManager;
//    public static void sendToApp(Bundle extras, Context context){
//        Intent newIntent = new Intent();
//        newIntent.setClass(context, MainActivity.class);
//        newIntent.putExtras(extras);
//        newIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        context.startActivity(newIntent);
//    }
//
//    public void onCreate(){
//        super.onCreate();
//        final String preferences = getString(R.string.preferences);
//        savedValues = getSharedPreferences(preferences, Context.MODE_PRIVATE);
//        // In later versions multi_process is no longer the default
//        if(VERSION.SDK_INT >  9){
//            savedValues = getSharedPreferences(preferences, Context.MODE_MULTI_PROCESS);
//        }
//        SharedPreferences savedValues = PreferenceManager.getDefaultSharedPreferences(this);
//        if(savedValues.getBoolean(getString(R.string.first_launch), true)){
//            SharedPreferences.Editor editor = savedValues.edit();
//            editor.putBoolean(getString(R.string.first_launch), false);
//            editor.commit();
//        }
//        // Let AndroidMobilePushApp know we have just initialized and there may be stored messages
//         //sendToApp(new Bundle(), this);
//    }
//
//    protected static void saveToLog(Bundle extras, Context context){
//    	String requestid = extras.getString("requestid");
//    	String rowid = extras.getString("rowid");
//    	String message = extras.getString("message");
//    	if (!(TextUtils.isEmpty(requestid)|| TextUtils.isEmpty(rowid))) {
//    		 Intent intent = new Intent("unique_name");
//            intent.putExtra(ApplicationData.EXTRA_REQUEST_ID, requestid);
//            intent.putExtra(ApplicationData.EXTRA_ROW_ID, rowid);
//            intent.putExtra(ApplicationData.EXTRA_MESSAGE, message);
//            context.sendBroadcast(intent);
////            Intent notiIntent = new Intent(context, MainActivity.class);
////            notiIntent.putExtra(ApplicationData.EXTRA_REQUEST_ID, requestid);
////            notiIntent.putExtra(ApplicationData.EXTRA_ROW_ID, rowid);
////            postNotification(notiIntent, context);
//		}
//    }
//
//    public static void postNotification(Intent intentAction, Context context){
//    		final NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
//            final PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intentAction, Notification.DEFAULT_LIGHTS | Notification.FLAG_AUTO_CANCEL);
//            final Notification notification = new NotificationCompat.Builder(context).setSmallIcon(R.drawable.ic_launcher)
//                    .setContentTitle("Wiimr")
//                    .setContentText("New Passenger Alert")
//                    .setContentIntent(pendingIntent)
//                    .setAutoCancel(true)
//                    .setDefaults(Notification.DEFAULT_SOUND)
//                    .getNotification();
//
//            mNotificationManager.notify(R.string.notification_number, notification);
//
//
//    }
//
//    public IBinder onBind(Intent arg0) {
//        return null;
//    }
//
//}