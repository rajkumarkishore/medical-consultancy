package mcs.vo;

import java.util.Map;

public class RegistrationVO {

	private String searchInput;

	private String nameOfPatient;
	private String addressOfPatient;

	private Integer ageOfPatient;
	private String ageOfPatientStr;

	private String genderOfPatient;

	private String regnFee;
	private String email;
	private String mobile;

	

	private String renewalFee;

	private Map<String, String> errors;
	private Integer patientId;

	public String getRenewalFee() {
		return renewalFee;
	}

	public void setRenewalFee(String renewalFee) {
		this.renewalFee = renewalFee;
	}

	public Map<String, String> getErrors() {
		return errors;
	}

	public void setErrors(Map<String, String> errors) {
		this.errors = errors;
	}

	public Integer getPatientId() {
		return patientId;
	}

	public void setPatientId(Integer patientId) {
		this.patientId = patientId;
	}

	public String getNameOfPatient() {
		return nameOfPatient;
	}

	public void setNameOfPatient(String nameOfPatient) {
		this.nameOfPatient = nameOfPatient;
	}

	public String getAddressOfPatient() {
		return addressOfPatient;
	}

	public void setAddressOfPatient(String addressOfPatient) {
		this.addressOfPatient = addressOfPatient;
	}

	public Integer getAgeOfPatient() {
		return ageOfPatient;
	}

	public void setAgeOfPatient(Integer ageOfPatient) {
		this.ageOfPatient = ageOfPatient;
	}

	public String getGenderOfPatient() {
		return genderOfPatient;
	}

	public void setGenderOfPatient(String genderOfPatient) {
		this.genderOfPatient = genderOfPatient;
	}

	public String getSearchInput() {
		return searchInput;
	}

	public void setSearchInput(String searchInput) {
		this.searchInput = searchInput;
	}

	public String getRegnFee() {
		return regnFee;
	}

	public void setRegnFee(String regnFee) {
		this.regnFee = regnFee;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getAgeOfPatientStr() {
		return ageOfPatientStr;
	}

	public void setAgeOfPatientStr(String ageOfPatientStr) {
		this.ageOfPatientStr = ageOfPatientStr;
	}

	

}
