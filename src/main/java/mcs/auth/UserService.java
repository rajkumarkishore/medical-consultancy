package mcs.auth;

import mcs.model.User;

public interface UserService {
	
	public User getUserByUsername(String userName);

}
