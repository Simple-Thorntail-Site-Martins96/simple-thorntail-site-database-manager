package com.lucamartinelli.app.simplesite.database.rest;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.lucamartinelli.app.simplesite.database.model.CredentialsVO;
import com.lucamartinelli.app.simplesite.database.model.UserVO;
import com.lucamartinelli.app.simplesite.database.persistency.DatabaseManager;
import com.lucamartinelli.app.simplesite.database.persistency.DatabaseManagerImpl;

@Path("/login")
public class LoginDBService {
	
	@EJB(beanInterface = DatabaseManagerImpl.class)
	private DatabaseManager dbManager;
	
	@POST
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.TEXT_PLAIN})
	public UserVO login(CredentialsVO cred) {
		
		if (cred == null || cred.getPassword() == null || cred.getUsername() == null)
			return null;
		return dbManager.login(cred.getUsername(), cred.getPassword());
	}
	
	
}
