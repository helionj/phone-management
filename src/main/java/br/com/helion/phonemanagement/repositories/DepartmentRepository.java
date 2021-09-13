package br.com.helion.phonemanagement.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.helion.phonemanagement.entities.Department;

public interface DepartmentRepository extends JpaRepository<Department, Long>{
	
	
}
