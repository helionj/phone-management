package br.com.helion.phonemanagement.services;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.helion.phonemanagement.dtos.ProviderDTO;
import br.com.helion.phonemanagement.entities.Provider;
import br.com.helion.phonemanagement.repositories.ProviderRepository;
import br.com.helion.phonemanagement.services.exceptions.DatabaseException;
import br.com.helion.phonemanagement.services.exceptions.ResourceNotFoundException;

@Service
public class ProviderService {

	@Autowired
	private ProviderRepository repository;
	
	@Transactional(readOnly = true)
	public Page<ProviderDTO> findAllPaged(PageRequest pageRequest){
		Page<Provider> list  = repository.findAll(pageRequest);
		return list.map(department -> (new ProviderDTO(department)));
	}
	
	@Transactional(readOnly = true)
	public ProviderDTO findById(Long id) {
		Optional<Provider> obj = repository.findById(id);
		Provider entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
		return new ProviderDTO(entity, entity.getSimcards());
	}
	
	@Transactional
	public ProviderDTO insert(ProviderDTO dto) {
		Provider entity = new Provider();
		entity.setName(dto.getName());
		entity = repository.save(entity);
		return new ProviderDTO(entity);
	}

	@Transactional
	public ProviderDTO update(Long id, ProviderDTO dto) {
		try {
			Provider entity = repository.getById(id);
			entity.setName(dto.getName());
			entity= repository.save(entity);
			return new ProviderDTO(entity);
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
