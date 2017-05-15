package mcs.bussiness;

import java.util.Date;
import java.util.List;

import mcs.model.Bill;
import mcs.model.Fee;
import mcs.model.ItemType;
import mcs.model.Patient;
import mcs.model.ServiceItem;
import mcs.vo.RegistrationVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RegistrationServiceImpl implements RegistrationService {

	@Autowired
	private PatientRepository repository;
	/*
	 * @Autowired private PatientRepository repository;
	 */

	@Value("${patient.regn.validity.days}")
	private Integer validityInDays;

	@Override
	public List<Patient> searchPatients(RegistrationVO vo) {

		int id = 0;
		if (vo.getSearchInput().matches("\\d+(\\.\\d+)*")) {
			id = Integer.parseInt(vo.getSearchInput());
		}
		return repository.findByIdOrNameIgnoreCaseContaining(id,
				vo.getSearchInput());
	}

	@Override
	public Patient findPatient(Integer id) {
		return repository.findOne(id);
	}

	@Override
	public Patient savePatient(RegistrationVO vo) {
		Patient patient = new Patient();
		patient.setName(vo.getNameOfPatient());
		patient.setAge(Integer.parseInt(vo.getAgeOfPatientStr()));
		patient.setGender(vo.getGenderOfPatient());
		patient.setAddress(vo.getAddressOfPatient());
		patient.setRegistrationDate(new Date());// current date
		patient.setValidityInDays(validityInDays);

		patient.setEmail(vo.getEmail());
		patient.setMobile(vo.getMobile());
		patient.setRegnFee(Double.parseDouble(vo.getRegnFee()));
		return repository.save(patient);

	}

	@Override
	public void renewRegistration(RegistrationVO vo) {
		/* make registration; re-registration in one to many relationship */

		// get Patient
		Patient patient = repository.findOne(vo.getPatientId());
		patient.setRegistrationDate(new Date());
		patient.setValidityInDays(validityInDays);

		// build service item
		ServiceItem item = new ServiceItem();
		item.setName(ItemType.RENEW_REGISTRATION.toString());
		item.setType(String.valueOf(ItemType.RENEW_REGISTRATION));

		// build fee
		Fee fee = new Fee();
		fee.setServiceItem(item);
		fee.setPaid(Double.parseDouble(vo.getRenewalFee()));
		fee.setTotal(Double.parseDouble(vo.getRenewalFee()));

		// set fee into service item
		item.setFee(fee);

		// build bill
		Bill bill = new Bill();
		bill.setGeneratedDate(new Date());
		bill.getItems().add(item);

		// patient.set

		/*
		 * 
		 * 
		 * mcs.model.Service service = new mcs.model.Service(); // ***** NEED TO
		 * ESTABLISH RELATION BETWEEN PATIENT AND FEE Fee renewalFee = new
		 * Fee();
		 * 
		 * renewalFee.setFeeType(String.valueOf(FeeType.RENEW_REGISTRATION));
		 * renewalFee.setPaid(Double.parseDouble(vo.getRenewalFee()));
		 * renewalFee.setTotal(Double.parseDouble(vo.getRenewalFee()));
		 * 
		 * service.setFee(renewalFee);
		 * service.setName(FeeType.RENEW_REGISTRATION.toString());
		 * 
		 * renewalFee.setService(service);
		 */

		repository.save(patient);
	}
}
