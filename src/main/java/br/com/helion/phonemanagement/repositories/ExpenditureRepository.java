package br.com.helion.phonemanagement.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.helion.phonemanagement.entities.Expenditure;

public interface ExpenditureRepository extends JpaRepository<Expenditure, Long>{
	
	
}
