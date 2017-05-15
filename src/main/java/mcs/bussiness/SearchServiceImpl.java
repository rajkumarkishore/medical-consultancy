package mcs.bussiness;

import java.util.ArrayList;
import java.util.List;

import mcs.model.Appointment;
import mcs.vo.SearchVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class SearchServiceImpl implements SearchService {

	@Value("${dateformat}")
	private String dateFormat;

	@Autowired
	private AppointmentRepository appointmentRepository;

	@Autowired
	private AppointmentService service;

	@Override
	public List<Appointment> search(SearchVO vo) {
		List<Appointment> appointments = new ArrayList<Appointment>();

		// check if patient name is empty
		boolean patientNameNotEmpty = (vo.getPatientName() != null & vo
				.getPatientName().trim().length() > 0);
		// check if reference number is empty
		boolean referenceNoNotEmpty = vo.getAppointmentId() != null
				&& vo.getAppointmentId().trim().length() > 0;

		// when both patient name & reference number are not empty
		if (patientNameNotEmpty && referenceNoNotEmpty) {
			// Long id = Long.parseLong(vo.getAppointmentId());
			appointments = service
					.listAllAppointmentsByPatientNameAndReferenceNumber(
							vo.getPatientName(), vo.getAppointmentId());
			// when patient name is not empty & reference number is empty
		} else if (patientNameNotEmpty && !referenceNoNotEmpty) {
			appointments = service.listAllAppointmentsByPatientName(vo
					.getPatientName());
			// when patient name is empty & reference number not is empty
		} else if (!patientNameNotEmpty && referenceNoNotEmpty) {
			appointments = service.listAllAppointmentsByRefrenceNumber(vo
					.getAppointmentId());
		}

		return appointments;
	}
}
