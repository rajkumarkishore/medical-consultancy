package mcs.bussiness;

import java.util.List;

import mcs.model.Patient;
import mcs.vo.RegistrationVO;

public interface RegistrationService {

	public Patient findPatient(Integer id);

	public List<Patient> searchPatients(RegistrationVO vo);

	public Patient savePatient(RegistrationVO vo);
	
	public void renewRegistration(RegistrationVO vo);

}
