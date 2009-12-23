//
//  ClientInstance.java
//  Isolation
//
//  Created by Jonathan Dalrymple on 02/02/2007.
//  Copyright 2007 Float:Right. All rights reserved.
//

import java.net.*;
import java.io.*;


public class ClientInstance{
	//Declarations
	private Socket Connection;
	private OutputStream Output;
	private InputStream Input;
	
	private InetAddress RemoteHost;
	private int portNo;
	
	private HTTPData rawData;	//Transmit object
	private boolean isReceivedDataReady = false;	//Return object, intialized to false until server has data
		
	public ClientInstance(){
		
	}
	
	public ClientInstance(Preferences pPrefs, HTTPData pTransmitData){
		//Configure the server here
	}
		
	public boolean run(){
		int i = 0;
		boolean retVal = false;
		HTTPData retData;
		
		try{
			Connection = new Socket( RemoteHost, portNo );
			
			Input = Connection.getInputStream();
			Output = Connection.getOutputStream();
			
			//Reset the pointer just to ensure we read from the start of data
			rawData.resetPointer();
			
			while( rawData.ready() ){
				Output.write( rawData.read() );
			}
			
			//Destroy the object
			rawData = null;
			
			//Reset the increment variable
			i = 0;
			retData = new HTTPData( Input.available() );
			
			while( i <= Input.available() ){
				retData.write( Input.read() );
			}
			
			
			rawData = retData;	//Resign the internal HTTP data object with the new data
			isReceivedDataReady = true; //Set a class wide flag for the other method to know that the data has been retrieved.
			
			retVal = true;
		}
		catch(Exception e){
			System.out.println( "Client: Error"  );
			System.err.print( e );
		}
		finally{
			return retVal;
		}
	}
	
	// Get local port number
	public int getLocalPortNo(){
		int retVal = 0;
		
		if(Connection != null ){
			retVal = Connection.getLocalPort();
		}
		
		return retVal;
	}
	
	public LinkedList getHTTPData(){
		//The Linked list is the wrapper object
		LinkedList WrapperList = new LinkedList();
		
		//Test for both conditions to ensure that both the internal class, and the actual instance is ready to go.
		if( rawData.ready() && isReceivedDataReady ){
			WrapperList.add( rawData );
		}
		
		return WrapperList;
		//Return a List reguardless of whether i put the object in it.
	}
	
}
