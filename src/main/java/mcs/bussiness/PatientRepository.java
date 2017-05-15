package mcs.bussiness;

import java.util.List;

import mcs.model.Patient;

import org.springframework.data.repository.CrudRepository;

public interface PatientRepository extends CrudRepository<Patient, Integer> {
	
	List<Patient> findByIdOrNameIgnoreCaseContaining(Integer id,String searchTerm);
}
