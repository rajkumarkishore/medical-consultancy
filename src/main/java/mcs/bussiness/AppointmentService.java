package mcs.bussiness;

import java.text.ParseException;
import java.util.List;

import mcs.model.Appointment;
import mcs.vo.AppointmentVO;

public interface AppointmentService {

	public void bookAppointment(AppointmentVO vo);

	public List<Appointment> listAllAppointmentsByPatientName(String patientName);

	public List<Appointment> listAllAppointmentsByRefrenceNumber(
			String referenceNo);

	public List<Appointment> listAllAppointmentsByPatientNameAndReferenceNumber(
			String patientName, String referenceNo);

	public List<Appointment> listAllAppointmentsByDateRange(String fromDate,
			String toDate) throws ParseException;

	public List<Appointment> listAllAppointmentsByDateRangeAndDoctor(
			String fromDate, String toDate, String doctorName)
			throws ParseException;

	public Appointment findAppointByReferenceNo(String refNo);
	

}
