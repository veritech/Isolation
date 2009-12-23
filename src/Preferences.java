//
//  Preferences.java
//  Isolation
//
//  Created by Jonathan Dalrymple on 31/01/2007.
//  Copyright 2007 Float:Right. All rights reserved.
//

import java.net.*;

public class Preferences {
	//Primitives
	private int ListeningPort = 1280;
	private int MaxServerInstances = 1;
	
	//Objects
	//private Proxy UserProxy = Proxy.Type.DIRECT;
	private String UserAgent = "Isolation-Personal-Proxy/beta";
	//private String 
	
	//Default Static Values
	static int DEFAULTPORT = 1280;
	
	//Constructors
	public Preferences(){
		
	}
	
	public Preferences(int pPort, int pMaxServers, String pUserAgent ){
		ListeningPort = pPort;
		MaxServerInstances = pMaxServers;
		UserAgent = pUserAgent;
	}
	
	//Private Functions
	
	
	//Public Functions
	public String getUserAgent(){
		return UserAgent;
	}
	
	public void setUserAgent(String pUserAgent){
		UserAgent = pUserAgent;
	}
	
}
