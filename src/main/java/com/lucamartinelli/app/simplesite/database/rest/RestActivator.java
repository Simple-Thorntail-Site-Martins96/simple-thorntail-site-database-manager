package com.lucamartinelli.app.simplesite.database.rest;

import javax.annotation.security.DeclareRoles;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("/database")
//@LoginConfig(authMethod = "MP-JWT", realmName = "database-realm")
@DeclareRoles({"dev", "test"})
public class RestActivator extends Application {
	
}
