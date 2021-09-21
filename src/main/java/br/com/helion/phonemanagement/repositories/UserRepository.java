package br.com.helion.phonemanagement.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.helion.phonemanagement.entities.Department;
import br.com.helion.phonemanagement.entities.User;

public interface UserRepository extends JpaRepository<User, Long>{
	
	User findByEmail(String email);

	@Query("SELECT obj FROM User obj WHERE "
			+ " (:department IS NULL OR :department = obj.department) AND "
			+ "(LOWER(obj.name) LIKE LOWER(CONCAT('%', :name, '%')))")
	Page<User> find(Department department, String name, Pageable pageable);
	
}
