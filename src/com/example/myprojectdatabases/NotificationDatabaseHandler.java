package com.example.myprojectdatabases;


import java.util.ArrayList;
import java.util.List;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class NotificationDatabaseHandler extends SQLiteOpenHelper {
	private static final int DATABASE_VERSION = 2;
	private static final String DATABASE_NAME = "contactManagers.db";
	private static final String TABLE_NAME = "notification";
	private static final String ID = "_id";
	private static final String RECEIVE_TIME = "_receive_time";
	private static final String EVENT = "_event";
	private static final String EVENT_TYPE = "_event_type";
	private static final String LATITUDE = "_latitude";
	private static final String LONGITUDE = "_longitude";
	public NotificationDatabaseHandler(Context context) {
		super(context,TABLE_NAME, null, DATABASE_VERSION);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		String CREATE_NOTIFICATION_TABLE="CREATE TABLE " + TABLE_NAME + "("
                + ID + "TEXT," + RECEIVE_TIME + " TEXT,"
                + EVENT + " TEXT ,"  + EVENT_TYPE + " TEXT," + LATITUDE + " TEXT," +
                		LONGITUDE + " TEXT," + ")";
		db.execSQL(CREATE_NOTIFICATION_TABLE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	}
	public void addContact(NotificationContact notificationContact){
		SQLiteDatabase db= this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(ID, notificationContact.getid());
		values.put(RECEIVE_TIME, notificationContact.getreceivetime());
		values.put(EVENT, notificationContact.getevent());
		values.put(EVENT_TYPE, notificationContact.geteventtype());
		values.put(LATITUDE, notificationContact.getlatitude());
		values.put(LONGITUDE, notificationContact.getlatitude());
	}
	
	NotificationContact getContact(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
 
        Cursor cursor = db.query(TABLE_NAME, new String[] {ID,
                RECEIVE_TIME, EVENT,EVENT_TYPE,LATITUDE,LONGITUDE }, ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
 
        NotificationContact notificationContact = new NotificationContact(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2),cursor.getString(3),
                cursor.getString(4),cursor.getString(5));
        return notificationContact ;
    }
	
	public int updateContact(NotificationContact contact) {
        SQLiteDatabase db = this.getWritableDatabase();
 
        ContentValues values = new ContentValues();
        values.put(RECEIVE_TIME, contact.getreceivetime());
        values.put(EVENT, contact.getevent());
        values.put(EVENT_TYPE, contact.geteventtype());
        values.put(LATITUDE,contact.getlatitude());
        values.put(LONGITUDE,contact.getlongitude());
 
        // updating row
        return db.update(TABLE_NAME, values,ID + " = ?",
                new String[] { String.valueOf(contact.getid()) });
    }
	
	public List<NotificationContact> getAllContacts() {
        List<NotificationContact> contactList = new ArrayList<NotificationContact>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
 
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                NotificationContact mContact = new NotificationContact();
                mContact.setid(Integer.parseInt(cursor.getString(0)));
                mContact.setreceivetime(cursor.getString(1));
                mContact.setevent(cursor.getString(2));
                mContact.seteventtype(cursor.getString(3));
                mContact.setlatitude(cursor.getString(4));
                mContact.setlongitude(cursor.getString(5));
                // Adding contact to list
                contactList.add(mContact);
            } while (cursor.moveToNext());
        }
        db.close();
        // return contact list
        Log.d("total number", " msg "+contactList);
        return contactList; 
    }
 

}
