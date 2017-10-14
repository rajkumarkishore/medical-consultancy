package mcs.auth;

import mcs.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository repository;

	@Override
	public User getUserByUsername(String userName) {
		return repository.findByUsername(userName);
	}

}
