package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Model {

	// PROPIEDADES
	
	private String url;
    private String dbName;
    private String driver;
    private String userName;
    private String password;
    private String tabla;
    private Connection conn;
    private boolean validConnection = false;
    
    // CONSTRUCTOR
    
	public Model(String url, String dbName, String driver, String userName, String password) {
		this.url = url;
		this.dbName = dbName;
		this.driver = driver;
		this.userName = userName;
		this.password = password;
	}
	
	
	// METODOS
	
	public Connection connectToBD() {	
		this.conn = null;
		try {
			
			//Registro el driver JDBC
			Class.forName(this.driver);		
			//Abrimos una coneccion a la Base de Datos
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(this.url+this.dbName,this.userName,this.password);
			//comprueba si la coneccion es validad y asigno el valor true o false al atributo "validConnection"
			this.validConnection = conn.isValid(1000); 
			System.out.println("Database connection ok...");
		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} 
	
		return conn;
	}
	
	
	public ResultSet readOnBD(String DBname, String sql) {
		ResultSet myResultSet = null;
		try {
			Statement myStatement = conn.createStatement();
			myStatement.execute("use "+ DBname);
			myResultSet = myStatement.executeQuery(sql);	

		} catch (SQLException e) {;
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return myResultSet;
	}
	
	
	
	//	 GETTERS Y SETTERS

	public String getUrl() {
		return url;
	}

	public String getDbName() {
		return dbName;
	}

	public String getDriver() {
		return driver;
	}

	public String getUserName() {
		return userName;
	}

	public String getPassword() {
		return password;
	}

	public String getTabla() {
		return tabla;
	}

	public Connection getConn() {
		return conn;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void setDbName(String dbName) {
		this.dbName = dbName;
	}

	public void setDriver(String driver) {
		this.driver = driver;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setTabla(String tabla) {
		this.tabla = tabla;
	}

	public void setConn(Connection conn) {
		this.conn = conn;
	}

	public boolean isValidConnection() {
		return validConnection;
	}

	public void setValidConnection(boolean validConnection) {
		this.validConnection = validConnection;
	}
	
    

    
	
}
