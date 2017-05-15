package mcs.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;

import mcs.util.Utility;

@Entity
public class Appointment {
	@Id
	@GeneratedValue
	private Long id;

	@Column(name = "uid")
	private String uniqueId;

	@Column(name = "date_of_appointment")
	private Date appointmentDate;

	@ManyToOne(cascade = CascadeType.ALL)
	private Doctor doctor;

	@ManyToOne
	private Patient patient;

	@Column(name = "referred_by", nullable = false)
	private String referredBy;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "appointment_services", joinColumns = @JoinColumn(name = "appointment_id"), inverseJoinColumns = @JoinColumn(name = "service_id"))
	@OrderBy("id ASC")
	private Set<Service> services;

	@Column(name = "is_bill_generated", nullable = true)
	private Boolean isBilled;

	@OneToOne(cascade = CascadeType.ALL)
	private Bill bill;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUniqueId() {

		return Utility.buildReferenceNumber(this.id);
	}

	public void setUniqueId(String uniqueId) {

	}

	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public Set<Service> getServices() {
		return services;
	}

	public void setServices(Set<Service> services) {
		this.services = services;
	}

	public Date getAppointmentDate() {
		return appointmentDate;
	}

	public void setAppointmentDate(Date appointmentDate) {
		this.appointmentDate = appointmentDate;
	}

	public Boolean getIsBilled() {
		return isBilled;
	}

	public void setIsBilled(Boolean isBilled) {
		this.isBilled = isBilled;
	}

	public String getReferredBy() {
		return referredBy;
	}

	public void setReferredBy(String referredBy) {
		this.referredBy = referredBy;
	}

	public Bill getBill() {
		return bill;
	}

	public void setBill(Bill bill) {
		this.bill = bill;
	}

}
