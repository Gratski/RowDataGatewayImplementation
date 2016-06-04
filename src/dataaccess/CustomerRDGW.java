package dataaccess;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * This class represents a Customer Row Data Gateway
 * It follows the RDGW pattern
 * 
 * @author JoaoR
 *
 */
public class CustomerRDGW {

	/**
	 * Customer id
	 */
	private int id;
	
	/**
	 * Customer VAT number
	 */
	private int vat;
	
	/**
	 * Customer comercial denomination
	 */
	private String denomination;
	
	/**
	 * Customer email contact
	 */
	private String email;
	
	/**
	 * Default constructor
	 */
	public CustomerRDGW(){}
	
	/**
	 * Full parameters constructor
	 * 
	 * @param id, customer id
	 * @param vat, customer vat number
	 * @param denomination, customer string denomination
	 * @param email, customer email
	 */
	public CustomerRDGW(int id, int vat, String denomination, String email){
		this.id = id;
		this.vat = vat;
		this.denomination = denomination;
		this.email = email;
	}
	
	/**
	 * Semi full constructor
	 * It doesnt initialize the id attribute
	 * 
	 * @param vat, customer vat
	 * @param denomination, customer denomination
	 * @param email, customer email
	 */
	public CustomerRDGW(int vat, String denomination, String email){
		this.vat = vat;
		this.denomination = denomination;
		this.email = email;
	}
	
	/**
	 * SQL - Insert new customer
	 */
	private static String INSERT_CUSTOMER_SQL = "INSERT INTO Customer "
			+ "(id, vat, denomination, email) "
			+ "values (DEFAULT, ?, ?, ?)";
	
	
	/**
	 * Persists a new customer into database
	 * This method follows the insertion of RowDataGateway Pattern
	 * 
	 * @throws PersistenceException
	 */
	public void insert() throws PersistenceException{
		
		// prepare the statement
		try(PreparedStatement statement = DataSource.INSTANCE.prepareStatement(INSERT_CUSTOMER_SQL)){
			
			// set it's attributes
			statement.setInt(1, vat);
			statement.setString(2, denomination);
			statement.setString(3, email);
			
			// persist new customer
			statement.executeUpdate();
			
			// obatin the generated keys
			try(ResultSet rs = statement.getGeneratedKeys()) {
				
				// assign it to customer id attribute
				rs.next();
				this.id = rs.getInt(1);
				
			} catch (SQLException e) {
				throw new PersistenceException("Error getting new customer's id", e);
			}
			
		} catch (SQLException e) {
			throw new PersistenceException("Error inserting a new customer", e);
		}
		
	}
	
	/**
	 * SQL - Find a customer based on a given VAT number 
	 */
	private static String FIND_CUSTOMER_BY_VAT_SQL = "SELECT * FROM Customer c WHERE c.vat = ?";
	
	/**
	 * Gets a customerRDGW by the given vat number
	 * 
	 * @param vat, customer's vat number
	 * @return the corresponding customer
	 * 
	 * @throws PersistenceException
	 */
	public static CustomerRDGW getCustomerByVAT(int vat) throws PersistenceException, RowNotFoundException{
		
		// prepare statement
		try(PreparedStatement statement = DataSource.INSTANCE.prepareStatement(FIND_CUSTOMER_BY_VAT_SQL)){
			
			// set statement attributes
			statement.setInt(1, vat);
			
			// fetch results
			ResultSet rs = statement.executeQuery();
			
			// if not customer was found with this vat number
			if(rs.getFetchSize() == 0)
				throw new RowNotFoundException("Customer not exists");
			
			// loads a customerRDGW
			try {				
				return load(rs);
			} catch (ObjectLoadingException e) {
				throw new PersistenceException("Error loading customer object", e);
			}
			
		} catch (SQLException e) {
			throw new PersistenceException("Error fetching customer by vat", e);
		}
		
	}
	
	private boolean valid(){	
		return
				validVAT()
				&& validDenomination()
				&& validEmail();
	}
	
	private boolean validVAT(){
		return true;
	}
	private boolean validDenomination(){
		return true;
	}
	private boolean validEmail(){
		return true;
	}
	
	private static CustomerRDGW load(ResultSet rs) throws ObjectLoadingException{
		
		try {	
			// moves result set pointer to next row
			rs.next();
			
			// build customerRGDW object
			CustomerRDGW customer = new CustomerRDGW();
			customer.setId(rs.getInt("id"));
			customer.setVat(rs.getInt("vat"));
			customer.setDenomination(rs.getString("denomination"));
			customer.setEmail(rs.getString("email"));
			
			return customer;
		} catch (SQLException e) {
			throw new ObjectLoadingException("Error loading customer object", e);
		}
		
	}

	// GETTERS and SETTERS
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getVat() {
		return vat;
	}

	public void setVat(int vat) {
		this.vat = vat;
	}

	public String getDenomination() {
		return denomination;
	}

	public void setDenomination(String denomination) {
		this.denomination = denomination;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
}
