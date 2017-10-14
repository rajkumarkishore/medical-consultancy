package mcs.admin;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import mcs.auth.UserRepository;
import mcs.model.User;
import mcs.model.UserProfile;
import mcs.vo.UserAccountVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserAccountServiceImpl implements UserAccountService {

	@Autowired
	private UserRepository repository;

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Value("${dateformat}")
	private String dateFormat;
	
	private SimpleDateFormat df = new SimpleDateFormat();

	@Override
	public void createUserAccount(UserAccountVO vo) throws ParseException {

		User user = new User();

		user.setUsername(vo.getUserName());
		user.setPassword(passwordEncoder.encode(vo.getPassword()));

		UserProfile profile = new UserProfile();
		profile.setFirstName(vo.getFirstName());
		profile.setMiddleName(vo.getMiddleName());
		profile.setLastName(vo.getLastName());
		profile.setGender(vo.getGender());
		
		df.applyPattern(dateFormat);
		Date dob = df.parse(vo.getDateOfBirth());
		
		profile.setDateOfBirth(dob);
		profile.setDateCreated(new Date());
		profile.setDateLastUpdated(new Date());
		profile.setAddress(vo.getAddress());
		profile.setMobileNo(vo.getMobileNo());
		profile.setEmailId(vo.getEmailId());

		
		user.setProfile(profile);

		repository.save(user);
	}

}
