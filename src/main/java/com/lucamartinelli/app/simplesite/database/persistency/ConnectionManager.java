package com.lucamartinelli.app.simplesite.database.persistency;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.LocalBean;
import javax.ejb.Singleton;
import javax.inject.Inject;

import org.eclipse.microprofile.config.inject.ConfigProperty;

@Singleton
@LocalBean
public class ConnectionManager {
	
	private final Logger log;
	private Connection conn;
	
	@Inject
	@ConfigProperty(name = "db.service.database.url", defaultValue = "jdbc:mysql://127.0.0.1:3306/simple_site")
	private String databaseUrl;
	@Inject
	@ConfigProperty(name = "db.service.database.username", defaultValue = "simple_site_user")
	private String databaseUser;
	@Inject
	@ConfigProperty(name = "db.service.database.password", defaultValue = "Me6aKI6ENoCi")
	private String databasePwd;
	
	public ConnectionManager() {
		log = Logger.getLogger(this.getClass().getCanonicalName());
	}
	
	/**
	 * Create the connection is not already initialized or the older one is closed
	 * 
	 * @return The connection to DB
	 * @throws SQLException if the connection is not created successfully
	 */
	public Connection getConnection() throws SQLException{
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			if (conn == null || conn.isClosed()) {
				conn = DriverManager
						.getConnection(databaseUrl, databaseUser, databasePwd);
			}
			
			return conn;

		} catch (SQLException e) {
			log.log(Level.SEVERE, "Error in login DB, \n"
					+ "Username: " + databaseUser + '\n'
					+ "Password: " + databasePwd + '\n'
					+ "URL:      " + databaseUrl);
			throw e;
		} catch (ClassNotFoundException e) {
			log.log(Level.SEVERE, "Missing MySQL Drivers", e);
			throw new SQLException(e);
		}
	}

}
