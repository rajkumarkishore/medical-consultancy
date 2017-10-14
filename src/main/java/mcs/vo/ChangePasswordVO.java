package mcs.vo;

public class ChangePasswordVO {

	private String userName;
	private String oldPasword;
	private String newPassword1;
	private String newPassword2;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getOldPasword() {
		return oldPasword;
	}

	public void setOldPasword(String oldPasword) {
		this.oldPasword = oldPasword;
	}

	public String getNewPassword1() {
		return newPassword1;
	}

	public void setNewPassword1(String newPassword1) {
		this.newPassword1 = newPassword1;
	}

	public String getNewPassword2() {
		return newPassword2;
	}

	public void setNewPassword2(String newPassword2) {
		this.newPassword2 = newPassword2;
	}

}
