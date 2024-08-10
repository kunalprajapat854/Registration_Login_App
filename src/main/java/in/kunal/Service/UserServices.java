package in.kunal.Service;

import org.springframework.security.core.userdetails.UserDetailsService;


import in.kunal.Entity.User;
import in.kunal.binding.Registration;

public interface UserServices extends UserDetailsService {
	
	public User saveuser(Registration registration);

}
