package vista;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;

/**
 *esta  clase  facilita  al captura de  datos desde  consola
 * @author mike
 */

public class CReadConsole {
	

	    private static String GetBufferString()
	            {
	             int noMoreInput = -1;
	             char enterKeyHit = '\n';
	             int InputChar;
	              StringBuffer InputBuffer = new StringBuffer( 30 );

	             try
	                        {
	                         InputChar = System.in.read();

	                         while( InputChar != noMoreInput )
	                                    {
	                                     if( (char) InputChar != enterKeyHit )
	                                                {
	                                                 InputBuffer.append( (char) InputChar );
	                                                }
	                                     else
	                                                {
	                                                 InputBuffer.setLength(InputBuffer.length());
	                                                 break;
	                                                }
	                                     InputChar = System.in.read();
	                                    }
	                        }
	            catch( IOException IOX )
	                        {
	                         System.err.println( IOX );
	                        }

	             return InputBuffer.toString();

	    }  //  final del metodo GetConsoleString
	    
	    public static Integer GetConsoleInteger()
	    {
	    	String data="";
	    	Integer num=0;
	    	data=GetBufferString();
	    	data=data.substring(0,data.indexOf('\r'));
	    	num= Integer.parseInt(data);
	    	return num;
	    	
	    }
	    public static String GetConsoleString()
	    {
	    	String data="";
	    	data=GetBufferString();
	    	data=data.substring(0,data.indexOf('\r'));
	    
	    	return data;
	    	
	    }

	}
