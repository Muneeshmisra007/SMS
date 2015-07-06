package com.example.myprojectdatabases;


import java.util.ArrayList;
import java.util.List;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHandler extends SQLiteOpenHelper {
	
	private static final int DATABASE_VERSION = 1;
	private static final String DATABASE_NAME = "contactsManagers.db";
	private static final String TABLE_NAME = "profile";
	private static final String KEY = "_key";
	private static final String VALUE = "_value";
	private static final String TYPE = "_type";
	private static final String NOTIFICATION_ID = "notification_id";
	public DatabaseHandler(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		 String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_NAME + "("
	                + KEY + " TEXT," + VALUE + " TEXT,"
	                + TYPE + " TEXT," +   NOTIFICATION_ID + " TEXT"+ ")";
		 Log.v("Message", " Table " + CREATE_CONTACTS_TABLE);
		 db.execSQL(CREATE_CONTACTS_TABLE);
		 
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		
	}
	
	public void addContact(Contact contact){
		SQLiteDatabase db = DatabaseHandler.this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY, contact.getkey());
        values.put(VALUE, contact.getValue());
        values.put(TYPE, contact.gettype());
        values.put(NOTIFICATION_ID, contact.getnotificationid());
        db.insert(TABLE_NAME, null, values);
        db.close();
	}
	
	Contact getContact(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
 
        Cursor cursor = db.query(TABLE_NAME, new String[] { KEY,
        		VALUE, TYPE, NOTIFICATION_ID }, NOTIFICATION_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
 
        Contact contact = new Contact(cursor.getString(0), cursor.getString(1),cursor.getString(2),
        		Integer.parseInt(cursor.getString(3)));
        return contact;
    }
	
	public List<Contact> getAllContacts() {
        List<Contact> contactList = new ArrayList<Contact>();
        String selectQuery = "SELECT  * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null); 
        if (cursor.moveToFirst()) {
            do {
                Contact contact = new Contact();
                contact.setkey(cursor.getString(0));
                contact.setvalue(cursor.getString(1));
                contact.settype(cursor.getString(2));
                contact.setnotificationid(Integer.parseInt(cursor.getString(3)));
                // Adding contact to list
                contactList.add(contact);
            } while (cursor.moveToNext());
        }
        db.close();
        return contactList; 
    }
	
	public void deleteContact(Contact contact) {
	    SQLiteDatabase db = this.getWritableDatabase();
	    db.delete(TABLE_NAME, NOTIFICATION_ID + " = ?",
	            new String[] { String.valueOf(contact.getnotificationid()) });
	    db.close();
	}

}
