//
//  HTTPData.java
//  Isolation
//
//  Created by Jonathan Dalrymple on 31/01/2007.
//  Copyright 2007 Float:Right. All rights reserved.
//

/*
 *	# - Denotes subclass
 *	
 *	What does this Class do
 *	1. Stores Raw Data retrived from the net
 *	2. Parses the Files to extract HTTP Header Data #
 *	3. Provides easy methods to convert that data to a file for storage #
 *
 */

import java.io.*;
import java.util.*;

public class HTTPData{
	
	//Declarations
	//Primitives
	private byte [] dataArray;
	private int dataArrayPointer;
	//private int 
	
	//Flags
	private boolean pointerLock = false;
	private boolean HTTPHeaderExists = false;
	
	//Objects
	private Preferences Prefs;
	private HTTPHeader Header;
	private File dataFile;
		
	//Constructors
	/*
	 *	get prefs file
	 *	initize dataArray
	 *	
	 */
	public HTTPData( /*Preferences pPrefInstance*/){
		//Prefs = pPrefInstance;
		dataArray = new byte[1500];
	}
	public HTTPData( int pDataSize ){
		//Prefs = pPrefInstance;
		dataArray = new byte[pDataSize];
	}
	
	public HTTPData( Preferences pPrefInstance, int pDataSize ){
		Prefs = pPrefInstance;
		dataArray = new byte[pDataSize];
	}
	
	//Private Functions
	//private 
	
	//Public Functions
	
	//Read from the class
	public byte read(){
		byte retVal;
		
		pointerLock = true;
		
		retVal = dataArray[dataArrayPointer];
		
		dataArrayPointer++;
		
		pointerLock = false;
		
		return retVal;
		
	}
	
	//Write to the class
	public void write( byte pNewData ){
		pointerLock = true;	//Lock the pointer
		
		dataArray[dataArrayPointer] = pNewData;
		
		dataArrayPointer++;
		
		pointerLock = false;
	}
	//Write to the class, accept int input
	public void write( int pNewData ){
		pointerLock = true;	//Lock the pointer
		
		dataArray[dataArrayPointer] = (byte) pNewData;
		
		dataArrayPointer++;
		
		pointerLock = false;
	}
	
	//Status ready
	public boolean ready(){
		boolean retBool = true;
		
		if( pointerLocation() >= length() ){
			retBool = false;
		}
		
		return retBool;
	}
	
	//Get pointer Location
	public int pointerLocation(){
		return dataArrayPointer;
	}
	
	//Reset the pointer Location
	public void resetPointer(){
		dataArrayPointer = 0;
	}
	
	//Move the pointer to ...
	public boolean movePointerTo(int pNewPosition ){
		boolean retVal;
		
		if(!pointerLock && !( pNewPosition > dataArray.length ) ){
			dataArrayPointer = pNewPosition;
			retVal = true;	//Return on true on sucess
		}
		else{
			retVal = false;
		}
		
		return retVal;
	}
	
	//Return the length of the interal data array
	public int length(){
		return dataArray.length;
	}
	
	//Compact the array and 
	public boolean compact(){
		boolean retBool = false, x = true;
		byte [] tempArray;
		int i = 0;
		int newArrayLength;
		
		try{
			while( x ){
				//Try it in the reverse
				if( dataArray[i] == 0 ){
					x = false;
				}
				i++;	  
			}
			
			newArrayLength = i;
			
			//i is now equal to the total amount
			
			tempArray = new byte[newArrayLength];
		
			i = 0;	//Reset i
			
			System.arraycopy(dataArray,0,tempArray,0,tempArray.length);
			
			dataArray = tempArray;
			
			//Explictly destory the object
			tempArray = null;
			
			retBool = true;
		}
		catch(ArrayIndexOutOfBoundsException e){
			System.out.println("HTTPData: compact(): Array Index!");
			System.err.print( e );
			retBool = false;
		}
		
		return retBool;
	}
	
	
	//Extract header information
	public boolean createHeader(){
		
		Header = new HTTPHeader(HTTPHeader.GET);
		boolean retVal;
		
		//Check if the file has been intinstiated
		if(dataFile != null){
			try{
				Scanner Parser = new Scanner( dataFile );
				
				Parser.useDelimiter(":");
				
				//Print to the command line to test
				//Parser.findInLine( "Host" );
				System.out.print( Parser.next() );
				
				Parser.close();
				//Return Value
				retVal = true;
			}
			catch(FileNotFoundException e){
				System.out.println( "HTTPData: No File found" );
				System.err.print( e );
				retVal = false;
			}
			catch(NoSuchElementException e){
				System.out.println( "HTTPData: Element not found" );
				System.err.print( e );
				retVal = false;
			}
			catch(IllegalStateException e){
				System.out.println( "HTTPData: The Scanner Appears to be closed" );
				System.err.print( e );
				retVal = false;
			}
		}
		else{
			retVal = false;
		}
		
		return retVal;
	}
	
	//Get header
	/*public HTTPHeader getHTTPHeader(){
		
	}*/
	
	// Return a File with the data enclosed
	public File getFile(){
		dataFile = new File("test");
		FileInputStream W;
		int i = 0;
		
		try{
			W = new FileInputStream( dataFile );
			
			if(dataFile.createNewFile() && dataFile.canWrite() ){
				W.read( dataArray );
				
				W.close();
			}
			
		}catch(IOException e){
			System.err.print( e );
		}
		
		return dataFile;
	}
}