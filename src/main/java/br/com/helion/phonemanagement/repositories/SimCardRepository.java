package br.com.helion.phonemanagement.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.helion.phonemanagement.entities.SimCard;

public interface SimCardRepository extends JpaRepository<SimCard, Long>{
	
	SimCard findByNumber(String number);
}
