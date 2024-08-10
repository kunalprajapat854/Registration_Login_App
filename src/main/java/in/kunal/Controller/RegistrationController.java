package in.kunal.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import in.kunal.Entity.User;
import in.kunal.Service.UserServices;
import in.kunal.binding.Registration;

@Controller
@RequestMapping("/registration")
public class RegistrationController {
	
	@Autowired
	private UserServices services;
	
	@GetMapping
	public String registratonform() {
		return "registration";
	}
	
	@ModelAttribute("user")
	public Registration form() {
		return new Registration();
	}
	
	@PostMapping
	public String savedata(@ModelAttribute("user")   Registration registration) {
		User saveuser = services.saveuser(registration);
		return "redirect:/registration?success";
		
	}
	
	

}
