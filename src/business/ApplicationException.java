package business;

/**
 * This class represents an ApplicationException
 * It is the most general type of exception of the entire application
 * 
 * @author JoaoR
 *
 */
public class ApplicationException extends Exception{

	/**
	 * Exception msg
	 */
	private String msg;
	
	/**
	 * Constructor
	 * 
	 * @param msg, exception message
	 * @param e, previous exception to stack it if needed
	 * 
	 */
	public ApplicationException(String msg, Exception e){
		super(e);
		this.msg = msg;
	}
	
	/**
	 * Constructor 
	 * 
	 * @param msg, exception message
	 */
	public ApplicationException(String msg){
		super(msg);
	}
	
	/**
	 * Gets the exception message
	 * 
	 * @return exception message
	 */
	public String getMsg(){
		return this.msg;
	}
	
}
