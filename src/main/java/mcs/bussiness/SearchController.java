package mcs.bussiness;

import java.util.List;

import mcs.model.Appointment;
import mcs.vo.SearchVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/search")
public class SearchController {
	@Autowired
	private SearchService service;

	@RequestMapping(value = { "/", "" }, method = RequestMethod.GET)
	public String searchView(@ModelAttribute("search") SearchVO vo, Model model) {
		return "search/search";
	}

	@RequestMapping(value = { "/", "" }, method = RequestMethod.POST)
	public String searchAppointment(@ModelAttribute("search") SearchVO vo,
			Model model) {
		List<Appointment> appointments = service.search(vo);
		model.addAttribute("appointments", appointments);
		return "search/search";
	}
}
