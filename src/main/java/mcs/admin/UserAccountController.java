package mcs.admin;

import java.text.ParseException;

import mcs.vo.UserAccountVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("admin")
public class UserAccountController {
	
	@Autowired
	private UserAccountService service;

	@RequestMapping(value = "account/new", method = RequestMethod.GET)
	public String initView(@ModelAttribute("account") UserAccountVO vo) {
		
		return "admin/new-user-account";
	}
	
	@RequestMapping(value = "account/new", method = RequestMethod.POST)
	public String createAccount(@ModelAttribute("account") UserAccountVO vo,Model model) {
		try {
			service.createUserAccount(vo);
			model.addAttribute("success", true);
		} catch (ParseException e) {
			e.printStackTrace();
			model.addAttribute("success", false);
		}
		return "admin/new-user-account";
	}

}
