package dataaccess;

import business.ApplicationException;

/**
 * This class represents an object loading exception
 * This exception should only be raised when a load object method fails
 * 
 * @author JoaoR
 *
 */
public class ObjectLoadingException extends ApplicationException{

	/**
	 * Constructor
	 * 
	 * @param msg, exception message
	 */
	public ObjectLoadingException(String msg){
		super(msg);
	} 
	
	/**
	 * Constructor
	 * 
	 * @param msg, exception message
	 * @param e, previous exception to stack if needed
	 */
	public ObjectLoadingException(String msg, Exception e){
		super(msg, e);
	}

}
