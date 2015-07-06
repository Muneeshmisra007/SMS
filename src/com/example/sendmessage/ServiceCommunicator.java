package com.example.sendmessage;

import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;
public class ServiceCommunicator  extends Service {
	
	/*public class LocalBinder extends Binder{
		
		public ServiceCommunicator getService()
		{
			return ServiceCommunicator.this;
		}
		
	}*/
	
	/*private final LocalBinder mBinder = new LocalBinder();*/
	private SMSreceiver mSMSreceiver;
    private IntentFilter mIntentFilter;
	@Override
    public void onCreate()
    {
        super.onCreate();
        //SMS event receiver
        mSMSreceiver = new SMSreceiver();
        mIntentFilter = new IntentFilter();
        mIntentFilter.addAction("android.provider.Telephony.SMS_RECEIVED");
        registerReceiver(mSMSreceiver, mIntentFilter);
    }

    @Override
    public void onDestroy()
    {
        super.onDestroy();
        unregisterReceiver(mSMSreceiver);
    }


	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// TODO Auto-generated method stub
		super.onStartCommand(intent, flags, startId);
		/*Runnable r = new Runnable() {
		       public void run() {
		        	
		        for (int i = 0; i < 3; i++)
		        {
		        	long endTime = System.currentTimeMillis() + 10*1000;
		        		
		        	while (System.currentTimeMillis() < endTime) {
		        		synchronized (this) {
		        			try {
		        				wait(endTime - 
	                                     System.currentTimeMillis());
		        			} catch (Exception e) {
		        		      }
		        	       }
		           }		
		           Log.i("TAG", "Service running ");
		       }
		       stopSelf();
		    }
	      };
		    
	      Thread t = new Thread(r);
		t.start();	*/
		  return android.app.Service.START_STICKY;
	}

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}
}
