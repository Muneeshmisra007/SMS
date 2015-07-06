package com.example.myprojectdatabases;


public class Contact {

	String _key;
	String _value;
	String _type;
	int Notification_id;
	
	public Contact(){
	}
	
	public Contact(String key, String value , String type , int notification_id){
		this._key=key;
		this._value=value;
		this._type=type;
		this.Notification_id=notification_id;
	}
	
	void setkey(String key){
		this._key=key;
	}
	
	public String getkey(){
		return _key;
	}
	
	void setvalue(String value){
		this._value=value;
	}
	
	public String getValue(){
		return _value;
	}
	
	void settype(String type){
		this._type=type;
	}
	
	public String gettype(){
		return _type;
	}
	
	void setnotificationid(int notification_id){
		this.Notification_id=notification_id;
	}
	
	public int getnotificationid(){
		return Notification_id;
	}
	
}
