//
//  Testing.java
//  Isolation
//
//  Created by Jonathan Dalrymple on 01/02/2007.
//  Copyright 2007 Float:Right. All rights reserved.
//
//Testing Class
import java.nio.charset.CharsetDecoder;


public class Testing{
	private HTTPData Data = new HTTPData();
	private int i = 0;
	private byte [] testResponse = {71, 69, 84, 32, 47, 32, 72, 84, 84, 80, 47, 49, 46, 49, 92, 114, 92, 110, 72, 111, 115, 116, 58, 32, 119, 119, 119, 46, 101, 110, 103, 97, 100, 103, 101, 116, 46, 99, 111, 109, 92, 114, 92, 110};
	
	/*
	private void decodeText(){
		Charset convertor = new Charset( "UTF-8", null );
		CharBuffer charData;
		
		try{

			int i = 0;
			
			charData = convertor.decode( ByteBuffer.put( testResponse ) );
			
			while( i <= charData.length() ){
				System.out.print( charData.get() );
			}
		}
		catch(Exception ignore){
			
		}
	}*/
	
	public void testBody(){
		while(i < testResponse.length ){
			Data.write( testResponse[i] );
			
			i++;
		}
		
		System.out.println("Filling of Array Complete");
		System.out.println("Data pointer is @ " + Data.pointerLocation() );
		
		Data.resetPointer();
		
		while( Data.ready() ){
			System.out.print( Data.read() );
			i--;
		}
		
		Data.resetPointer();
		
		System.out.println("Data pointer is @ " + Data.pointerLocation() );
		
		System.out.println("Data length is @ " + Data.length() );
		
		Data.compact();
		
		while( Data.ready() ){
			System.out.print( Data.read() );
		}
		
		System.out.println("Data pointer is @ " + Data.pointerLocation() );
		
		System.out.println("Data length is @ " + Data.length() );
	}
}
