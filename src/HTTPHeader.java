//
//  HTTPHeader.java
//  Isolation
//
//  Created by Jonathan Dalrymple on 01/02/2007.
//  Copyright 2007 Float:Right. All rights reserved.
//

public class HTTPHeader {
	
	private byte Type;
	
	//Get & Post fields
	private String Host;
	private String URI;
	private String AcceptEncoding;
	private String AcceptLanguage;
	private String AcceptCharset;
	
	
	//Response Fields
	private int StatusCode;
	private String ContentType;
	private int ContentLength;
	private String TransferEncoding;
	private String ContentEncoding;
	private String Server;
	private String Date;
	
	
	//public constant fields
	public final static byte GET = 0;
	public final static byte POST = 1;
	public final static byte RESPONSE = 2;
	
	//Constructors
	public HTTPHeader(byte pType){
		switch( pType ){
			case 1:		//Post
				
				break;
			case 2:		//Response
				
				break;
			default:	//Defaults to Get
				
				break;
		}
		
	}
}
