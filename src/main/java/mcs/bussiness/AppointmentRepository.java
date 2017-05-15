package mcs.bussiness;

import java.util.Date;
import java.util.List;

import mcs.model.Appointment;

import org.springframework.data.repository.CrudRepository;

public interface AppointmentRepository extends
		CrudRepository<Appointment, Long> {

	public List<Appointment> findByPatientNameIgnoreCaseContaining(
			String patientName);

	public List<Appointment> findById(Long id);

	public List<Appointment> findByIdAndPatientNameIgnoreCaseContaining(
			Long id, String patientName);

	public List<Appointment> findByAppointmentDateBetween(Date from, Date to);

	public List<Appointment> findByAppointmentDateBetweenAndDoctorNameIgnoreCaseContaining(
			Date from, Date to, String doctorName);

}
