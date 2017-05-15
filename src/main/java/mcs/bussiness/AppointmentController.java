package mcs.bussiness;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import mcs.model.Patient;
import mcs.vo.AppointmentVO;
import mcs.vo.ServiceVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/appointment")
public class AppointmentController {

	@Value("${dateformat}")
	private String dateFormat;

	@Autowired
	private AppointmentService service;

	@Autowired
	private RegistrationService regnService;
	
	@Autowired
	@Qualifier("AppointmentValidator")
	private FormValidator validator;

	private final SimpleDateFormat df = new SimpleDateFormat();

	@RequestMapping(value = "book/{patientId}/{patientName}", method = RequestMethod.GET)
	public String bookAppointmentView(
			@ModelAttribute("appointment") AppointmentVO vo,
			@PathVariable("patientId") String patientId,
			@PathVariable("patientName") String patientName, Model model) {

		df.applyPattern(dateFormat);
		String currDate = df.format(new Date());
		vo.setDateOfAppointment(currDate);
		vo.setNameOfPatient(patientName);
		model.addAttribute("patientId", patientId);

		Patient patient = regnService.findPatient(Integer.parseInt(patientId));
		model.addAttribute("patient", patient);

		return "bussiness/book-appointment";
	}

	@RequestMapping(value = "book/{patientId}/{patientName}", method = RequestMethod.POST)
	@ResponseBody
	public AppointmentVO saveAppointment(@RequestBody AppointmentVO vo) {
		Map<String, String> errors = validator.validate(vo);
		vo.setErrors(errors);

		if (errors.isEmpty()) {
			service.bookAppointment(vo);
		}

		return vo;
	}

	@RequestMapping(value = "book", method = RequestMethod.GET)
	public String bookAppointmentView(
			@ModelAttribute("appointment") AppointmentVO appointmentVO,
			Model model) {
		df.applyPattern(dateFormat);
		String currDate = df.format(new Date());
		appointmentVO.setDateOfAppointment(currDate);

		List<ServiceVO> services = new ArrayList<ServiceVO>();
		services.add(new ServiceVO()); // initialize with 1 element
		appointmentVO.setServices(services);

		return "appointment/booking";
	}

	@RequestMapping(value = "book", method = RequestMethod.POST, params = { "add" })
	public String addService(
			@ModelAttribute("appointment") AppointmentVO appointmentVO,
			Model model) {
		List<ServiceVO> services = appointmentVO.getServices();
		services.add(new ServiceVO()); // add new element
		return "appointment/booking";
	}

	@RequestMapping(value = "book", method = RequestMethod.POST, params = { "remove" })
	public String removeService(
			@ModelAttribute("appointment") AppointmentVO appointmentVO,
			Model model) {
		List<ServiceVO> updatedList = new ArrayList<ServiceVO>();
		List<ServiceVO> services = appointmentVO.getServices();
		for (ServiceVO s : services) {
			if (s.getIndex() == null) {
				updatedList.add(s);
			}
		}
		appointmentVO.setServices(updatedList);
		return "appointment/booking";
	}

	@RequestMapping(value = "book", method = RequestMethod.POST, params = { "save" })
	public String save(
			@ModelAttribute("appointment") AppointmentVO appointmentVO,
			Model model, RedirectAttributes redirectAttr) {

		service.bookAppointment(appointmentVO);
		redirectAttr.addFlashAttribute("success", true);
		return "redirect:book";
	}

	@RequestMapping(value = "book", method = RequestMethod.POST, params = { "cancel" })
	public String cancel(
			@ModelAttribute("appointment") AppointmentVO appointmentVO,
			RedirectAttributes redirectAttr) {
		System.out.println("SAVE INSTRUCTION....");
		return "redirect:book";
	}

	@RequestMapping(value = "/{patientId}/ack-slip", method = RequestMethod.GET)
	public String printAcknowledgementSlip(
			@PathVariable("patientId") String patientId, Model model) {

		Patient patient = regnService.findPatient(Integer.parseInt(patientId));
		model.addAttribute("patient", patient);
		model.addAttribute("curr_date", new Date());

		return "bussiness/ack-slip-printing";
	}

}
