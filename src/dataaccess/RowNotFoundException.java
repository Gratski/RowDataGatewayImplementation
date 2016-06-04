package dataaccess;

import business.ApplicationException;

/**
 * This class represents a row not found exception
 * This exception should only be raised when a certain record
 * was supposed to be found by executing a certain instruction and
 * was not
 * 
 * @author JoaoR
 *
 */
public class RowNotFoundException extends ApplicationException{

	/**
	 * Constructor
	 * 
	 * @param msg, exception message
	 * @param e, previous exception to stack if needed
	 */
	public RowNotFoundException(String msg, Exception e){
		super(msg, e);
	}
	
	/**
	 * Constructor
	 * 
	 * @param msg, exception message
	 */
	public RowNotFoundException(String msg){
		super(msg);
	}
	
}
