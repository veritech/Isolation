//
//  ServerInstance.java
//  Isolation
//
//  Created by Jonathan Dalrymple on 02/02/2007.
//  Copyright 2007 Float:Right. All rights reserved.
//

import java.io.*;
import java.net.*;

public class ServerInstance {
	
	private Socket Connection;
	private BufferedOutputStream Outbound;
	private InputStream  Inbound;
	private HTTPData RawHttpData;

	private int listeningPort;
	
	//ClassFlags
	private boolean connectionEstablished = false;
	private boolean instanceComplete = false;
	
	public ServerInstance(){
		//This one Sets all variables to defaults
		listeningPort = Preferences.DEFAULTPORT;
	}
	
	/*public ServerInstance(Preferences){
	
	}*/
	
	public boolean run(){
		ServerSocket ListenSocket;
		
		boolean retBool = false;
		boolean endLoop = false;
		int readValue = -1024;	// Set to extreme value to show that it is default;
		int i = 0;
		
		/*
			*	How the Server Will worl
			*
			*	1. Get the Data from the browser / Read the Stream
			*	2. Send Data off to extract the headers
			*	3. While Data is being Processed Send data back to the client
			*	4. Flag that server action is complete, and shutdown and clean up
			*
		*/
		try{
			ListeningSocket = new ServerSocket( listeningPort );
			
			Connection = ListeningSocket.accept();
			
			connectionEstablished = true;
			
			//1. Get Data from Browser
			Inbound = Connection.getInputStream();
			
			Outbound = new BufferedOutputStream ( Connection.getOutputStream() );	//Place output in a Buffered Stream
			
			//Write to both Objects simultainously
			while( !endLoop ){
				readValue = Inbound.read();
				
				if(readValue != -1){
					Outbound.write( readValue );	//Send data to the client
					RawHttpData.write( readValue );		//Write data to a HTTPData Object
				}
				else{
					endLoop = true;
				}
				i++;
			}
			
			Outbound.flush();	//Make sure all the data has been sent back to the browser
			//Just while we code
			System.out.println("Server: has RX/TX " + i + " Bytes of data");
			
			//4. Clean up
			Inbound.close();
			Outbound.close();
			Connection.close();
			
			instanceComplete = true;
		}
		catch(IOException e){
			System.out.println("<--  Server Error  -->");
			System.err.print( e );
		}
		catch(IllegalBlockingModeException e){
			//I doubt this will come up, but refer to java documentation on serversocket.accept() in the event it does
			System.out.println("<--  Server says no! -->");
			System.err.print( e );
		}
		finally{
			return retBool;
		}
	}
	
	public boolean isConnected(){
		return connectionEstablished;
	}
	
	public boolean isComplete(){
		return instanceComplete;
	}
}
