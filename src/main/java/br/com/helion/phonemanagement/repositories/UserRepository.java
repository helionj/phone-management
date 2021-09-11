package br.com.helion.phonemanagement.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.helion.phonemanagement.entities.User;

public interface UserRepository extends JpaRepository<User, Long>{
	
	User findByEmail(String email);
	
}
