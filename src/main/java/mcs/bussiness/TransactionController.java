package mcs.bussiness;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import mcs.model.Appointment;
import mcs.vo.TransactionsVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/transactions")
public class TransactionController {

	@Value("${dateformat}")
	private String dateFormat;

	private final SimpleDateFormat df = new SimpleDateFormat();

	@Autowired
	private TransactionService service;

	@RequestMapping(value = { "/", "" }, method = RequestMethod.GET)
	public String searchView(@ModelAttribute("transaction") TransactionsVO vo,
			Model model) {
		df.applyPattern(dateFormat);
		String currDate = df.format(new Date());
		vo.setFromDate(currDate);
		vo.setToDate(currDate);

		List<Appointment> appointments = service.findTransactions(vo);
		model.addAttribute("appointments", appointments);

		return "bussiness/transactions";
	}

	@RequestMapping(value = { "/", "" }, method = RequestMethod.POST)
	public String searchAppointment(
			@ModelAttribute("transaction") TransactionsVO vo, Model model) {
		List<Appointment> appointments = service.findTransactions(vo);
		model.addAttribute("appointments", appointments);
		return "bussiness/transactions";
	}
}
