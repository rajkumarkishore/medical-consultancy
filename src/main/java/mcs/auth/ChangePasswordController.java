package mcs.auth;

import java.util.Map;

import mcs.bussiness.FormValidator;
import mcs.vo.ChangePasswordVO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ChangePasswordController {

	private Logger log = LoggerFactory
			.getLogger(ChangePasswordController.class);

	@Autowired
	private ChangePasswordService service;

	@Autowired
	@Qualifier("ChangePasswordValidator")
	private FormValidator validator;

	@RequestMapping(value = "/change-password", method = RequestMethod.GET)
	public String changeOwnPasswordView(
			@ModelAttribute("changePassword") ChangePasswordVO vo) {
		return "auth/change-password";
	}

	@RequestMapping(value = "/change-password", method = RequestMethod.POST)
	public String changeOwnPasswordSave(
			@ModelAttribute("changePassword") ChangePasswordVO vo, Model model) {
		
		// get username of logged in user
		String userName = SecurityContextHolder.getContext()
				.getAuthentication().getName();
		
		// set username
		vo.setUserName(userName);

		// validation errors
		Map<String, String> errors = validator.validate(vo);

		if (errors.isEmpty()) {
			// update database
			service.changePassword(vo);
			model.addAttribute("success", true);
		} else {
			for (Map.Entry<String, String> m : errors.entrySet()) {
				model.addAttribute(m.getKey().concat("Error"), m.getValue());
			}
		}

		return "auth/change-password";
	}

}
