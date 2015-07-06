package com.example.sendmessage;
import java.util.List;

import com.example.myprojectdatabases.Contact;
import com.example.myprojectdatabases.DatabaseHandler;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.NotificationCompat;
import android.telephony.SmsManager;
import android.telephony.gsm.SmsMessage;
import android.util.Log;


public class SMSreceiver extends  BroadcastReceiver   {
	final String MyPREFERENCES = "MyPrefs";
	String frwrdNumber;
	static Context ctx;
	private final String BOOT_ACTION = "android.intent.action.BOOT_COMPLETED";
	private final String TAG = this.getClass().getSimpleName();
	@Override
	public void onReceive(Context context, Intent intent) {
		Bundle extras = intent.getExtras();
		if (intent.getAction().equalsIgnoreCase(BOOT_ACTION)) {
            //check for boot complete event & start your service
			startService(context);

} 
		ctx=context;
        String strMessage = "";
        String strMsgBody="";
        if ( extras != null )
        {
            Object[] smsextras = (Object[]) extras.get( "pdus" );
            for ( int i = 0; i < smsextras.length; i++ )
            {
                SmsMessage smsmsg = SmsMessage.createFromPdu((byte[])smsextras[i]);
                strMsgBody = smsmsg.getMessageBody().toString();
                String strMsgSrc = smsmsg.getOriginatingAddress();
                strMessage += "SMS from " + strMsgSrc + " : " + strMsgBody;                    
                SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
                String message = sharedPreferences.getString("Name", null);
                // send Notofication when message receive 
                //generateNotification(context,"message");
            }
            		String fullmesage =strMessage;
            		String refindmessage=fullmesage.replaceAll("[-+.^:,\\s]","");
            		
            		String knownNumber="2148422665";
            		String knownNumberRef   = knownNumber.replaceAll("[-+.^:,\\s]","");
            		Log.d(TAG, "Number: " + knownNumberRef);
            		generateNotification(context,"message");
            	        		
        
            if(refindmessage.contains(knownNumberRef)){
            		SmsManager smsManager = SmsManager.getDefault();
            		frwrdNumber = "2148422665";
            		smsManager.sendTextMessage(frwrdNumber, null,"i am replying", null, null);
            		// send notofication when message forword
            		
            		generateNotification(context,"message");
            		DatabaseHandler db = new DatabaseHandler(context);
            		db.addContact(new Contact("Fence", "on", "flag", 1));        
         	        db.addContact(new Contact("Cityguard", "off", "flag", 2));
         	        Log.d("Reading: ", "Reading all contacts..");
         	        List<Contact> contacts = db.getAllContacts();       
         	       for (Contact cn : contacts) {
       	            String log = "KEY: "+cn.getkey()+",value: " + cn.getValue() + " ,Type: " + cn.gettype()
       	            		 + "Notification : "+ cn.getnotificationid() ;
       	            Log.d("Name: ", log);
         	        }
            }
        }
	}
	 private void startService(Context context) {
		// TODO Auto-generated method stub
		//here, you will start your service
			Intent service = new Intent(context, ServiceCommunicator.class);  
	        context.startService(service);
	}
	private static void generateNotification(Context context, String message) {
         	int icon = R.drawable.ic_launcher;
         	long when = System.currentTimeMillis();
         	NotificationManager notificationManager = (NotificationManager)
                 context.getSystemService(Context.NOTIFICATION_SERVICE);
         	Notification notification = new Notification(icon, message, when);
         	String title = context.getString(R.string.app_name);
         	Intent notificationIntent = new Intent(context, MainActivity.class);
         	// set intent so it does not start a new activity
         	notificationIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                 Intent.FLAG_ACTIVITY_SINGLE_TOP);
         	PendingIntent intent =
                 PendingIntent.getActivity(context, 0, notificationIntent, 0);
         	notification.setLatestEventInfo(context, title, message, intent);
         	notification.flags |= Notification.FLAG_AUTO_CANCEL;
         	notification.sound = Uri.parse("android.resource://"+ context.getPackageName() + R.raw.button);
         	// Vibrate if vibrate is enabled
         	NotificationCompat.Builder mBuilder =
        	        new NotificationCompat.Builder(ctx)
        	        .setSmallIcon(R.drawable.ic_launcher)
        	        .setContentTitle("Notification Hub Demo")
        	        .setContentText("hello");
         	notification.defaults |= Notification.DEFAULT_VIBRATE;
         	notificationManager.notify(0, notification);      
     }
}
