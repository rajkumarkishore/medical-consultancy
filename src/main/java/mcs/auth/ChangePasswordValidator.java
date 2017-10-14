package mcs.auth;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import mcs.bussiness.FormValidator;
import mcs.model.PasswordLog;
import mcs.model.User;
import mcs.vo.ChangePasswordVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component(value = "ChangePasswordValidator")
public class ChangePasswordValidator implements FormValidator {

	/**
	 * (?=.*\d) 		# must contains one digit from 0-9 
	 * (?=.*[a-z])	 	# must contains one lowercase characters 
	 * (?=.*[A-Z]) 		# must contains one  uppercase characters
	 * (?=.*[@#$%]) 	# must contains one special symbols in the list "@#$%" 
	 * {8,20} 			# length at least 8 characters and maximum of 20
	 * */
	private static final String PASSWORD_PATTERN = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,20})";

	
	@Autowired
	private UserService service;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public Map<String, String> validate(Object vo) {

		ChangePasswordVO form = null;
		
		if (vo instanceof ChangePasswordVO) {
			form = (ChangePasswordVO) vo;
		}

		Map<String, String> errors = new HashMap<String, String>();
		
		// get user from database
		User user = service.getUserByUsername(form.getUserName());
		
		if(form.getOldPasword()==null || form.getOldPasword().trim().length()==0){
			errors.put("oldPassword", "cannot be empty");
		}else {

			if(!passwordEncoder.matches(form.getOldPasword(),user.getPassword())){
				errors.put("oldPassword", "Incorrect password");
			}
		}
		
		if(form.getNewPassword1()==null || form.getNewPassword1().trim().length()==0){
			errors.put("newPassword1", "cannot be empty");
		}else if(!form.getNewPassword1().matches(PASSWORD_PATTERN)){
			errors.put("newPassword1", "password not as per policy");
		}else{

			// get all previous passwords
			Set<PasswordLog> passwords= user.getPreviousPasswords();
			
			// get sub-set of previous passwords; get last 3 updated passwords
			List<PasswordLog> pwdsList=new ArrayList<PasswordLog>(passwords).subList(0, 2);
			
			for(PasswordLog p:pwdsList){
				
				if(passwordEncoder.matches(form.getNewPassword1(),p.getPassword())){{
					errors.put("newPassword1", "cannot be among the previous 3 passwords");
					break;
				}
			}

			}
		}
		
		if(form.getNewPassword2()==null || form.getNewPassword2().trim().length()==0){
			errors.put("newPassword2", "cannot be empty");
		}else if(!form.getNewPassword1().equals(form.getNewPassword2())){
			errors.put("newPassword2", "new & confirm passwords didnot match");
		}
		
		return errors;
	}

}
