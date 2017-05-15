package mcs.bussiness;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import mcs.model.Appointment;
import mcs.model.Bill;
import mcs.model.ItemType;
import mcs.model.ServiceItem;
import mcs.vo.BillingVO;

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
@RequestMapping("/billing")
public class BillingController {

	@Autowired
	private AppointmentService service;

	@Autowired
	private BillingService billingService;

	@Autowired
	@Qualifier("BillingValidator")
	private FormValidator validator;

	@RequestMapping(value = "/{refNo}/services", method = RequestMethod.GET)
	@ResponseBody
	public Appointment getAppointmentForBilling(
			@PathVariable("refNo") String refNo) {
		Appointment appointment = service.findAppointByReferenceNo(refNo);
		return appointment;
	}

	@RequestMapping(value = "/{appointmentNo}/bill", method = RequestMethod.GET)
	public String appointmentBillForm(@ModelAttribute("billing") BillingVO vo,
			@PathVariable("appointmentNo") String refNo, Model model) {

		Appointment appointment = service.findAppointByReferenceNo(refNo);

		model.addAttribute("appointmentNo", refNo);
		model.addAttribute("nameOfPatient", appointment.getPatient().getName());
		model.addAttribute("dateOfAppointment",
				appointment.getAppointmentDate());

		model.addAttribute("defaultservice", ItemType.CONSULTANCY.toString());
		model.addAttribute("doctor", appointment.getDoctor().getName());

		return "bussiness/billing";
	}

	@RequestMapping(value = "/{appointmentNo}/requested-services", method = RequestMethod.GET)
	public String loadAppoitmentServices(
			@ModelAttribute("billing") BillingVO vo,
			@PathVariable("appointmentNo") String refNo, Model model) {

		Appointment appointment = service.findAppointByReferenceNo(refNo);

		Bill bill = (appointment.getBill() == null ? new Bill() : appointment
				.getBill());

		List<ServiceItem> service = new ArrayList<ServiceItem>();
		service.add(new ServiceItem());

		appointment.setBill(bill);

		model.addAttribute("services", bill.getItems());

		List<ItemType> serviceTypes = Arrays.asList(ItemType.values());
		model.addAttribute("serviceTypes", serviceTypes);

		return "bussiness/requested-services";
	}

	@RequestMapping(value = "/{appointmentNo}/requested-services/addition/{rowIndex}", method = RequestMethod.GET)
	public String addAppoitmentServices(
			@PathVariable("appointmentNo") String refNo,
			@PathVariable("rowIndex") String rowIndex, Model model) {

		List<ItemType> serviceTypes = Arrays.asList(ItemType.values());
		model.addAttribute("serviceTypes", serviceTypes);

		model.addAttribute("rowIndex", rowIndex);
		model.addAttribute("serviceType", ItemType.SERVICE);

		return "bussiness/requested-services";
	}

	@RequestMapping(value = "/{appointmentNo}/processing", method = RequestMethod.POST)
	@ResponseBody
	public BillingVO processBill(@RequestBody BillingVO vo,
			@PathVariable("appointmentNo") String refNo, Model model) {
		Map<String, String> errors = validator.validate(vo);
		vo.setErrors(errors);

		if (errors.isEmpty()) {
			vo.setAppointmentId(refNo);
			billingService.saveBill(vo);
		}

		return vo;
	}

	@RequestMapping(value = "/{appointmentNo}/print-bill", method = RequestMethod.GET)
	public String printAcknowledgementSlip(
			@PathVariable("appointmentNo") String refNo, Model model) {

		Appointment appointment = service.findAppointByReferenceNo(refNo);
		model.addAttribute("appointment", appointment);
		model.addAttribute("curr_date", new Date());

		return "bussiness/bill-printing";
	}

}
