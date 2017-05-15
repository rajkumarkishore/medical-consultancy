package mcs.auth;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import mcs.bussiness.AuthenticationService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AuthenticationController {

	private Logger log = LoggerFactory
			.getLogger(AuthenticationController.class);

	@Autowired
	private AuthenticationService service;

	// load login view
	@RequestMapping(value = { "/", "/login" }, method = RequestMethod.GET)
	public String loginView() {
		log.debug("Login view loaded!");
		boolean activeUser = service.isUserSessionActive();
		if (activeUser) {
			return "redirect:/registration";
		}
		return "auth/login";
	}

	// login success
	@RequestMapping(value = { "/authenticated" }, method = RequestMethod.POST)
	public String loginSuccess(HttpServletRequest request) {
		log.debug("Login Success!");
		return "redirect:/registration";
	}

	// login error
	@RequestMapping(value = { "/bad-credentials" }, method = RequestMethod.GET)
	public String loginError(Model model) {
		log.debug("Login Failure!");
		model.addAttribute("error", true);
		return "auth/login";
	}

}
