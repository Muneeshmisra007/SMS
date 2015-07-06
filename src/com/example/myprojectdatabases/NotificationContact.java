package com.example.myprojectdatabases;


public class NotificationContact {
	int _id;
	String _receive_time;
	String _event;
	String _event_type;
	String _latitude;
	String _longitude;
	
	public NotificationContact() {
	}
	
	public NotificationContact(int id, String receive_time, String event ,String event_type, String latitude,String longitude){
		this._id=id;
		this._receive_time=receive_time;
		this._event=event;
		this._event_type=event_type;
		this._latitude=latitude;
		this._longitude=longitude;
	}
	
	void setid(int id){
		this._id=id;
	}
	
	int getid(){
		return _id;
	}
	
	void setreceivetime(String receive_time){
		this._receive_time=receive_time;
	}
	
	String getreceivetime(){
		return _receive_time;
	}
	
	void setevent(String event){
		this._event=event;
	}
	
	String getevent(){
		return _event;
	}
	
	void seteventtype(String event_type){
		this._event_type=event_type;
	}
	
	String geteventtype(){
		return _event_type;
	}
	
	void setlatitude( String latitude){
		this._latitude=latitude;
	}
	
	String getlatitude(){
		return _latitude;
	}
	
	void setlongitude(String longitude){
		this._longitude=longitude;
	}
	
	String getlongitude(){
		return _longitude;
	}
	
}
