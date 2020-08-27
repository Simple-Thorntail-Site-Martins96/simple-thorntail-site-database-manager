package com.lucamartinelli.app.simplesite.database.persistency;

import com.lucamartinelli.app.simplesite.database.model.UserVO;

public interface DatabaseManager {
	
	
	public UserVO login(String username, String encodedPassword);
	
	
	
}
