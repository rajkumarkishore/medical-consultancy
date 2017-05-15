package mcs.vo;

import java.util.List;

public class SearchVO {

	private String patientName;

	private String appointmentId;

	private List<ServiceVO> services;

	public String getPatientName() {
		return patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	public String getAppointmentId() {
		return appointmentId;
	}

	public void setAppointmentId(String appointmentId) {
		this.appointmentId = appointmentId;
	}

	public List<ServiceVO> getServices() {
		return services;
	}

	public void setServices(List<ServiceVO> services) {
		this.services = services;
	}

}
