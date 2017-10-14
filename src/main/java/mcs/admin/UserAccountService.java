package mcs.admin;

import java.text.ParseException;

import mcs.vo.UserAccountVO;

public interface UserAccountService {
	
	public void createUserAccount(UserAccountVO vo) throws ParseException;

}
