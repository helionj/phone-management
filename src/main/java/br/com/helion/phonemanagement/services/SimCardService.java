package br.com.helion.phonemanagement.services;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.helion.phonemanagement.dtos.SimCardDTO;
import br.com.helion.phonemanagement.dtos.SimCardDTOInsert;
import br.com.helion.phonemanagement.dtos.SimCardDTOUpdate;
import br.com.helion.phonemanagement.entities.SimCard;
import br.com.helion.phonemanagement.repositories.ProviderRepository;
import br.com.helion.phonemanagement.repositories.SimCardRepository;
import br.com.helion.phonemanagement.services.exceptions.DatabaseException;
import br.com.helion.phonemanagement.services.exceptions.ResourceNotFoundException;

@Service
public class SimCardService {

	@Autowired
	private SimCardRepository repository;
	
	@Autowired
	private ProviderRepository providerRepository;
	
	@Transactional(readOnly = true)
	public Page<SimCardDTO> findAllPaged(Pageable pageable){
		Page<SimCard> list  = repository.findAll(pageable);
		return list.map(department -> (new SimCardDTO(department)));
	}
	
	@Transactional(readOnly = true)
	public SimCardDTO findById(Long id) {
		Optional<SimCard> obj = repository.findById(id);
		return new SimCardDTO(obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found")));
	}
	
	@Transactional
	public SimCardDTO insert(SimCardDTOInsert dto) {
		SimCard entity = new SimCard();
		entity.setNumber(dto.getNumber());
		entity.setProvider(providerRepository.getById(dto.getProvider().getId()));
		entity = repository.save(entity);
		return new SimCardDTO(entity);
	}

	@Transactional
	public SimCardDTO update(Long id, SimCardDTOUpdate dto) {
		try {
			SimCard entity = repository.getById(id);
			entity.setNumber(dto.getNumber());
			entity= repository.save(entity);
			return new SimCardDTO(entity);
		}
		catch(EntityNotFoundException e) {
			throw new ResourceNotFoundException("Id not found "+ id);
		}
		
	
	}

	public void delete(Long id) {
		try {
			repository.deleteById(id);
		}
		catch(EmptyResultDataAccessException e) {
			throw new  ResourceNotFoundException("id not found "+ id);
		}
		catch(DataIntegrityViolationException e) {
			throw new DatabaseException("Integrity violation");
		}
		
	}

	
}
