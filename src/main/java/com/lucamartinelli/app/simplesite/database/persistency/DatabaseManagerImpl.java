package com.lucamartinelli.app.simplesite.database.persistency;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.lucamartinelli.app.simplesite.database.model.UserVO;

@Stateless
@LocalBean
public class DatabaseManagerImpl implements DatabaseManager {
	
	private final Logger log;
	
	@EJB
	private ConnectionManager connManager;
	
	public DatabaseManagerImpl() {
		log = Logger.getLogger(this.getClass().getCanonicalName());
	}
	
	@Override
	public UserVO login(String username, String encodedPassword) throws RuntimeException {
		try {
			log.info("Login with user: " + username);
			final Connection conn = connManager.getConnection();
			final PreparedStatement ps = conn.prepareStatement(loadQuery());
			ps.setString(1, username);
			ps.setString(2, encodedPassword);
			if (ps.execute()) {
				final ResultSet rs = ps.getResultSet();
				if (!rs.next())
					return null;
				final UserVO user = new UserVO(rs.getString("USERNAME"),
						rs.getString("PASSWORD"), splitRoles(rs.getString("ROLES")),
						rs.getString("NAME"), rs.getString("SURNAME")); 
				log.fine("User from Database: " + user);
				return user;
			}
			
		} catch (SQLException e) {
			log.log(Level.SEVERE, "Error during login on Database ", e);
			throw new RuntimeException(e);
		} catch (IOException e) {
			log.log(Level.SEVERE, "Error during reading query file ", e);
			throw new RuntimeException(e);
		}
		
		log.warning("Username " + username + " fail to login");
		return null;
	}
	
	
	private String loadQuery() throws IOException {
		final Properties prop = new Properties();
		prop.load(this.getClass().getResourceAsStream("/query/login.properties"));
		return prop.getProperty("login.query");
	}
	
	private String[] splitRoles(String r) {
		if (r == null)
			return new String[0];
		if (!r.contains(","))
			return new String[] {r};
		return r.split(",");
	}
	
	
}
