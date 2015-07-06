/////////////////Parser for incomming and 

package com.example.sendmessage;

public class SmsParser {
	
public String refineMessage(String msg)
{
	String refinedmessage=msg.replaceAll("[-+.^:,\\s]","");
	
	return refinedmessage;
}

public boolean matchSender(String refinedmessage, String contact)
{	
if(refinedmessage.contains(contact))
{
	return true;
}
else
{
	return false;
}
}

///////////Fence Me////////////////

public String fanceMe(String pwd)
{
	String body = "Move"+pwd+" "+"0020";
	return body;
}

public boolean fanceMeResponse(String body)
{
	String mBody=refineMessage(body);
	
	
	if(mBody.contains("moveok"))
	{
		return true;
	}
	else
	{
		return false;
	}
}
//////////////////Where are you////////////////////


public String whereAreYou(String pwd)
{
	String body = "Address"+pwd;
	return body;
}

public boolean whereAreYouResponse(String body)
{
	//String mBody=refineMessage(body);
	
	
	if(body.contains("moveok"))
	{
		return true;
	}
	else
	{
		return false;
	}
}








}