package dataaccess;

import business.ApplicationException;

/**
 * This class represents a Persistence Exception
 * This exception should be raised to represent an error either when
 * trying to access persisted data
 * or
 * trying to manipulate persisted related information / data
 * 
 * @author JoaoR
 *
 */
public class PersistenceException extends ApplicationException{

	/**
	 * Constructor
	 * 
	 * @param msg, exception message
	 * @param e, previous exception to stack if needed
	 */
	public PersistenceException(String msg, Exception e){
		super(msg,e);	
	}
	
	/**
	 * Constructor
	 * 
	 * @param msg, exception message
	 */
	public PersistenceException(String msg){
		super(msg);
	}
	
}
