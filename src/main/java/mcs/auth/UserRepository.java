package mcs.auth;

import mcs.model.User;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
	
	public User findByUsername(String username);
}
