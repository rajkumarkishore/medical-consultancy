package mcs.bussiness;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import mcs.model.Appointment;
import mcs.model.Bill;
import mcs.model.Doctor;
import mcs.model.Fee;
import mcs.model.ItemType;
import mcs.model.Patient;
import mcs.model.ServiceItem;
import mcs.vo.AppointmentVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

@org.springframework.stereotype.Service
public class AppointmentServiceImpl implements AppointmentService {

	@Autowired
	private AppointmentRepository repository;

	@Autowired
	private PatientRepository patientRepository;

	@Value("${dateformat}")
	private String dateFormat;

	@Value("${patient.regn.validity.days}")
	private Integer validityInDays;

	private SimpleDateFormat df = new SimpleDateFormat();

	@Override
	public void bookAppointment(AppointmentVO vo) {

		// get Patient
		Patient patient = patientRepository.findOne(Integer.parseInt(vo
				.getIdOfPatient()));

		Appointment appointment = new Appointment();

		if (!patient.getIsRegistrationValid()) {
			// build service item
			ServiceItem item = new ServiceItem();
			item.setName(ItemType.RENEW_REGISTRATION.toString());
			item.setType(String.valueOf(ItemType.RENEW_REGISTRATION));
			// build renewal fee
			Fee fee = new Fee();
			fee.setServiceItem(item);
			fee.setPaid(Double.parseDouble(vo.getRegnRenewalFee()));
			fee.setTotal(Double.parseDouble(vo.getRegnRenewalFee()));

			// set fee for service item
			item.setFee(fee);
			
			// build bill
			Bill bill = new Bill();
			bill.setGeneratedDate(new Date());

			Set<ServiceItem> items = new HashSet<ServiceItem>();
			items.add(item);
			bill.setItems(items);
			
			// tag bill into appointment
			appointment.setBill(bill);
			
			//patient-renew fee tagging
			patient.setRegistrationDate(new Date());
			patient.setValidityInDays(validityInDays);
			patient.setRegnFee(Double.parseDouble(vo.getRegnRenewalFee()));
		}

		appointment.setIsBilled(false);
		appointment.setReferredBy(vo.getReferredBy());

		try {
			df.applyPattern(dateFormat);
			appointment.setAppointmentDate(df.parse(vo.getDateOfAppointment()));
		} catch (ParseException e) {
			e.printStackTrace();
		}

		Doctor doctor = new Doctor();
		doctor.setName(vo.getNameOfDoctor());
		appointment.setDoctor(doctor);
		appointment.setPatient(patient);

		patient.getAppointments().add(appointment);

		patientRepository.save(patient);
	}

	@Override
	public List<Appointment> listAllAppointmentsByPatientName(String patientName) {

		return repository.findByPatientNameIgnoreCaseContaining(patientName);
	}

	@Override
	public List<Appointment> listAllAppointmentsByDateRange(String fromDate,
			String toDate) throws ParseException {

		df.applyPattern(dateFormat);
		Date from = df.parse(fromDate);
		Date to = df.parse(toDate);

		List<Appointment> appointments = repository
				.findByAppointmentDateBetween(from, to);

		return appointments;
	}

	@Override
	public List<Appointment> listAllAppointmentsByDateRangeAndDoctor(
			String fromDate, String toDate, String doctorName)
			throws ParseException {

		df.applyPattern(dateFormat);
		Date from = df.parse(fromDate);
		Date to = df.parse(toDate);

		List<Appointment> appointments = repository
				.findByAppointmentDateBetweenAndDoctorNameIgnoreCaseContaining(
						from, to, doctorName);

		return appointments;
	}

	@Override
	public List<Appointment> listAllAppointmentsByRefrenceNumber(
			String referenceNo) {
		Long id = Long.parseLong(referenceNo);
		return repository.findById(id);
	}

	@Override
	public List<Appointment> listAllAppointmentsByPatientNameAndReferenceNumber(
			String patientName, String referenceNo) {
		Long id = Long.parseLong(referenceNo);
		return repository.findByIdAndPatientNameIgnoreCaseContaining(id,
				patientName);
	}

	@Override
	public Appointment findAppointByReferenceNo(String referenceNo) {
		Long id = Long.parseLong(referenceNo);

		return repository.findOne(id);
	}

}
