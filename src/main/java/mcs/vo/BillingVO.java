package mcs.vo;

import java.util.List;
import java.util.Map;

public class BillingVO {

	private String appointmentId;

	List<ServiceVO> services;

	private Map<String, String> errors;

	public List<ServiceVO> getServices() {
		return services;
	}

	public void setServices(List<ServiceVO> services) {
		this.services = services;
	}

	public String getAppointmentId() {
		return appointmentId;
	}

	public void setAppointmentId(String appointmentId) {
		this.appointmentId = appointmentId;
	}

	public Map<String, String> getErrors() {
		return errors;
	}

	public void setErrors(Map<String, String> errors) {
		this.errors = errors;
	}

}
