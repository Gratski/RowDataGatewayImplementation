package dataaccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * This class represents a datasource object
 * 
 * @author JoaoR
 *
 */
public enum DataSource {
	INSTANCE;
	
	/**
	 * SQL Connection 
	 */
	private Connection connection;
	
	/**
	 * Establishes the connection with the given database
	 * 
	 * @param url, database url
	 * @param username, database user name
	 * @param password, database user password
	 * 
	 * @throws SQLException
	 */
	public void connect(String url, String username, String password) throws SQLException{
		this.connection = DriverManager.getConnection(url, username, password);
	}
	
	/**
	 * Prepares a statement using the previous established connection
	 * 
	 * @param sql, sql query string 
	 * @return the corresponding prepared statement object
	 * 
	 * @throws SQLException
	 */
	public PreparedStatement prepareStatement(String sql) throws PersistenceException{
		try {			
			PreparedStatement statement = this.connection.prepareStatement(sql);
			return statement;
		} catch (SQLException e) {
			throw new PersistenceException("Error preparing statement", e);
		}
	}
	
	/**
	 * Closes this connection
	 * 
	 * @throws SQLException
	 */
	public void closeConnection() throws PersistenceException{
		try{			
			this.connection.close();
		} catch (SQLException e) {
			throw new PersistenceException("Error closing current connection", e);
		}
	}
	
	/**
	 * Begins a transaction
	 * 
	 * @throws PersistenceException
	 */
	public void beginTransaction() throws PersistenceException{
		try {
			this.connection.setAutoCommit(false);
		} catch (SQLException e) {
			throw new PersistenceException("Error beggining transaction", e);
		}
	}
	
	/**
	 * Ends a transaction by doing commit
	 * 
	 * @throws PersistenceException
	 */
	public void endTransaction() throws PersistenceException{
		try {
			this.connection.setAutoCommit(true);
			this.connection.commit();			
		} catch (SQLException e) {
			throw new PersistenceException("Error ending transaction", e);
		}
	}
	
	/**
	 * Rolls back the current transaction
	 * 
	 * @throws PersistenceException
	 */
	public void rollBack() throws PersistenceException{
		try {			
			this.connection.rollback();
		} catch (SQLException e) {
			throw new PersistenceException("Error doing rollback", e);
		}
	}
	
}
