package mcs.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.Transient;

import mcs.util.Utility;

@Entity
public class Bill {

	@Id
	@GeneratedValue
	private Long id;

	@Column(name = "bill_no")
	private String number;

	@Column(name = "generated_on")
	private Date generatedDate;

	@OneToOne(cascade = CascadeType.ALL)
	private Appointment appointment;

	@OneToMany(cascade = CascadeType.ALL)
	@OrderBy("id ASC")
	private Set<ServiceItem> items;

	@Transient
	private Double billAmount;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNumber() {
		return Utility.buildReferenceNumber(this.id);
	}

	public void setNumber(String number) {
		// this.number = number;
	}

	public Date getGeneratedDate() {
		return generatedDate;
	}

	public void setGeneratedDate(Date generatedDate) {
		this.generatedDate = generatedDate;
	}

	public Appointment getAppointment() {
		return appointment;
	}

	public void setAppointment(Appointment appointment) {
		this.appointment = appointment;
	}

	public Set<ServiceItem> getItems() {
		return items;
	}

	public void setItems(Set<ServiceItem> items) {
		this.items = items;
	}

	public Double getBillAmount() {

		Double total = 0.00;
		for (ServiceItem s : this.items) {
			total+=s.getFee().getTotal();
		}
		return total;
	}

	public void setBillAmount(Double billAmount) {
		// this.billAmount = billAmount;
	}

}
