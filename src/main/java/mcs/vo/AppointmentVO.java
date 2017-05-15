package mcs.vo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AppointmentVO {

	private String nameOfDoctor;
	private String idOfPatient;
	private String nameOfPatient;
	private String addressOfPatient;
	private Integer ageOfPatient;
	private String genderOfPatient;
	private String dateOfAppointment;
	private String referredBy;

	private String validRegistration;

	private String regnRenewalFee;

	private List<ServiceVO> services = new ArrayList<ServiceVO>();

	private Map<String, String> errors;

	public String getNameOfDoctor() {
		return nameOfDoctor;
	}

	public void setNameOfDoctor(String nameOfDoctor) {
		this.nameOfDoctor = nameOfDoctor;
	}

	public String getIdOfPatient() {
		return idOfPatient;
	}

	public void setIdOfPatient(String idOfPatient) {
		this.idOfPatient = idOfPatient;
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

	public String getDateOfAppointment() {
		return dateOfAppointment;
	}

	public void setDateOfAppointment(String dateOfAppointment) {
		this.dateOfAppointment = dateOfAppointment;
	}

	public String getReferredBy() {
		return referredBy;
	}

	public void setReferredBy(String referredBy) {
		this.referredBy = referredBy;
	}

	public List<ServiceVO> getServices() {
		return services;
	}

	public void setServices(List<ServiceVO> services) {
		this.services = services;
	}

	public String getRegnRenewalFee() {
		return regnRenewalFee;
	}

	public void setRegnRenewalFee(String regnRenewalFee) {
		this.regnRenewalFee = regnRenewalFee;
	}

	public Map<String, String> getErrors() {
		return errors;
	}

	public void setErrors(Map<String, String> errors) {
		this.errors = errors;
	}

	public String getValidRegistration() {
		return validRegistration;
	}

	public void setValidRegistration(String validRegistration) {
		this.validRegistration = validRegistration;
	}

}
