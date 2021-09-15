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

import br.com.helion.phonemanagement.dtos.TelephoneLineDTOMax;
import br.com.helion.phonemanagement.entities.TelephoneLine;
import br.com.helion.phonemanagement.repositories.DeviceRepository;
import br.com.helion.phonemanagement.repositories.SimCardRepository;
import br.com.helion.phonemanagement.repositories.TelephoneLineRepository;
import br.com.helion.phonemanagement.repositories.UserRepository;
import br.com.helion.phonemanagement.services.exceptions.DatabaseException;
import br.com.helion.phonemanagement.services.exceptions.ResourceNotFoundException;

@Service
public class TelephoneLineService {
	
	@Autowired
	private TelephoneLineRepository repository;
	
	@Autowired
	private DeviceRepository deviceRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private SimCardRepository simCardRepository;
	
	@Transactional(readOnly = true)
	public Page<TelephoneLineDTOMax> findAllPaged(PageRequest pageRequest){
		
		Page<TelephoneLine> list  = repository.findAll(pageRequest);
		return list.map(x -> (new TelephoneLineDTOMax(x)));
	}
	
	@Transactional(readOnly = true)
	public TelephoneLineDTOMax findById(Long id) {
		Optional<TelephoneLine> obj = repository.findById(id);
		TelephoneLine entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
		return new TelephoneLineDTOMax(entity);
	}
	
	@Transactional
	public TelephoneLineDTOMax insert(TelephoneLineDTOMax dto) {
		TelephoneLine entity = new TelephoneLine();
		copyDtoToEntity(dto, entity);
		entity = repository.save(entity);
		return new TelephoneLineDTOMax(entity);
	}

	@Transactional
	public TelephoneLineDTOMax update(Long id, TelephoneLineDTOMax dto) {
		try {
			TelephoneLine entity = repository.getById(id);
			copyDtoToEntity(dto, entity);
			entity= repository.save(entity);
			return new TelephoneLineDTOMax(entity);
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
	
	void copyDtoToEntity(TelephoneLineDTOMax dto, TelephoneLine entity){
		entity.setLineNumber(dto.getLineNumber());
		try {
			if(dto.getSimCard() != null) {
				entity.setSimCard(simCardRepository.getById(dto.getSimCard().getId()));
			}
			
			if(dto.getDevice() != null) {
				entity.setDevice(deviceRepository.getById(dto.getDevice().getId()));
			}
			
			if(dto.getUser() != null) {
				entity.setUser(userRepository.getById(dto.getUser().getId()));
			}
			
				
		}
		catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("Id not found ");
		}
		
		
			
	}

	
	
}
