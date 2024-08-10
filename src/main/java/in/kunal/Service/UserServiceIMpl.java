package in.kunal.Service;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import in.kunal.Entity.Role;
import in.kunal.Entity.User;
import in.kunal.Repository.RegistrationRepo;
import in.kunal.binding.Registration;

@Service
public class UserServiceIMpl implements UserServices {

	@Autowired
	private RegistrationRepo repo;

	@Autowired
	private BCryptPasswordEncoder encoder;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = repo.findByEmail(username);
		if (user == null) {
			throw new UsernameNotFoundException("Invalid");
		}
		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), mapRolesToAuthorities(user.getRoles()));
	}

	public User saveuser(Registration registration) {
		User user = new User(null, registration.getFirstname(), registration.getLastname(), registration.getEmail(),
				encoder.encode(registration.getPassword()));
		return repo.save(user);
	}

	private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getRoles())).collect(Collectors.toList());
	}

}
