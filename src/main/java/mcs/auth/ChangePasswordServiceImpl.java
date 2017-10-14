package mcs.auth;

import java.util.Date;

import javax.transaction.Transactional;

import mcs.model.PasswordLog;
import mcs.model.User;
import mcs.vo.ChangePasswordVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class ChangePasswordServiceImpl implements ChangePasswordService {

	@Autowired
	private UserRepository repository;

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	@Transactional
	public void changePassword(ChangePasswordVO vo) {

		String userName = vo.getUserName();

		User user = repository.findByUsername(userName);

		// get encrypted value of new password provided
		String newEncryptedPassword = passwordEncoder.encode(vo.getNewPassword1());

		user.setPassword(newEncryptedPassword);

		PasswordLog previousPassword = new PasswordLog();
		previousPassword.setPassword(user.getPassword());
		previousPassword.setUpdatedOn(new Date());
		previousPassword.setUser(user);

		user.getPreviousPasswords().add(previousPassword);

	}

}
