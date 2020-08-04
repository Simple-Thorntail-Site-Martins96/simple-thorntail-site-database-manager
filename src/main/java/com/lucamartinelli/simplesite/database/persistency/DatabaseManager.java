package com.lucamartinelli.simplesite.database.persistency;

import com.lucamartinelli.simplesite.database.model.UserVO;

public interface DatabaseManager {
	
	
	public UserVO login(String username, String encodedPassword);
	
	
	
}
