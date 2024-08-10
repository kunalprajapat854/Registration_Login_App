package in.kunal.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import in.kunal.Entity.User;

public interface RegistrationRepo extends JpaRepository<User, Integer> {

	public User findByEmail(String email);

}
