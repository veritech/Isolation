//
//  HTTPProtocol.java
//  Isolation
//
//  Created by Jonathan Dalrymple on 02/02/2007.
//  Copyright 2007 Float:Right. All rights reserved.
//

public class HTTPProtocol {
	
	Preferences PrefsInstance;
	String userAgent;
	String host;
	//Constructors
	public HTTPProtocol(){
		//Configure the class with defaults
	}
	public HTTPProtocol( Preferences pPrefs ){
		//Configure the class with Preferences
		userAgent = pPrefs.getUserAgent();
	}
	
	//
	private String requestMaker(String pURI, String pHost, String pUserAgent){
		String t = "\r\n";	//Line terminator
		String r;
		
		r = "Get " + pURI + " HTTP/1.1" + t;
		r += "Host: " + pHost + t;
		r += "User-Agent: " + pUserAgent + t;
		r += "Accept:" + "text/html, text/javascript, text/xml" + t;
		r += "Accept-Language: en-us,en" + t;
		r += "Accept-Charset: ISO-8859-1,utf-8" + t;
		r += "Proxy-Connection: " + t;
		
		return r;
	}
	
	public String makeGetRequest(){
		return requestMaker("/", host, userAgent);
	}
	
	public String makeGetRequest(String pURI){
		return requestMaker( pURI, host, userAgent);
	}
	
	public String makeGetRequest(String pURI, String pHost){
		return requestMaker( pURI, pHost, userAgent);
	}
	
	//Not quite ready yet!!
	private String postMaker(String pURI, String pUserAgent, String pContent){
		String t = "\r\n";	//Line terminator
		String p;
		
		p = "POST " + pURI + t;
		p += "From: " + t;
		p += "User-Agent: " + pUserAgent + t;
		p += "Content-Type: " + "application/x-www-form-urlencoded" + t;
		p += "Content-Length: " + t;
		
		return p;
	}
	
	public String makePostResponse(String pURI){
		return postMaker( "/",  userAgent, "test=hello");
	}
	
}
