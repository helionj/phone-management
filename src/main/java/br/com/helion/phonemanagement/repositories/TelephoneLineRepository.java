package br.com.helion.phonemanagement.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.helion.phonemanagement.entities.TelephoneLine;

public interface TelephoneLineRepository extends JpaRepository<TelephoneLine, Long>{
	
	
}
