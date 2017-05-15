package mcs.bussiness;

import java.util.Date;
import java.util.List;
import java.util.Map;

import mcs.model.Patient;
import mcs.vo.RegistrationVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;



@Controller
@RequestMapping("/registration")
public class RegistrationController {

	@Autowired
	private RegistrationService service;

	@Autowired
	@Qualifier("RegistrationValidator")
	private FormValidator validator;

	@RequestMapping(value = { "", "/" }, method = RequestMethod.GET)
	public String initView(@ModelAttribute("registration") RegistrationVO vo,
			Model model) {
		model.addAttribute("initLoad", true);
		return "bussiness/registration";
	}

	@RequestMapping(value = { "", "/" }, method = RequestMethod.POST, params = { "search" })
	public String search(@ModelAttribute("registration") RegistrationVO vo,
			Model model) {

		List<Patient> patients = service.searchPatients(vo);
		model.addAttribute("patients", patients);
		model.addAttribute("searched", true);

		return "bussiness/registration";
	}

	@RequestMapping(value = { "/patient" }, method = RequestMethod.GET)
	public String registerPatientForm(
			@ModelAttribute("register-patient") RegistrationVO vo, Model model) {
		return "bussiness/register-patient";
	}

	@RequestMapping(value = { "/patient" }, method = RequestMethod.POST)
	@ResponseBody
	public RegistrationVO registerPatient(@RequestBody RegistrationVO vo) {

		RegistrationVO responseVO = new RegistrationVO();

		Map<String, String> errors = validator.validate(vo);
		responseVO.setErrors(errors);

		if (errors.isEmpty()) {
			Patient patient = service.savePatient(vo);
			responseVO.setPatientId(patient.getId());
		}

		return responseVO;
	}

	@RequestMapping(value = { "/{patientId}" }, method = RequestMethod.GET)
	public String patientDetailView(
			@PathVariable("patientId") Integer patientId, Model model) {
		Patient patient = service.findPatient(patientId);
		model.addAttribute("patient", patient);
		return "bussiness/registration-single-window";
	}

	@RequestMapping(value = { "/renew/{patientId}/{patientName}" }, method = RequestMethod.GET)
	public String renewRegistrationView(
			@ModelAttribute("renew-registration") RegistrationVO vo,
			@PathVariable("patientName") String patientName, Model model) {

		model.addAttribute("nameOfPatient", patientName);
		model.addAttribute("renewDate", new Date());
		return "bussiness/renew-registration";
	}

	@RequestMapping(value = { "/renew/{patientId}/{patientName}" }, method = RequestMethod.POST)
	@ResponseBody
	public RegistrationVO renewRegistration(@RequestBody RegistrationVO vo) {

		service.renewRegistration(vo);

		return vo;
	}

}
