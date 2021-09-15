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

import br.com.helion.phonemanagement.dtos.ExpenditureDTO;
import br.com.helion.phonemanagement.entities.Expenditure;
import br.com.helion.phonemanagement.entities.TelephoneLine;
import br.com.helion.phonemanagement.repositories.ExpenditureRepository;
import br.com.helion.phonemanagement.repositories.TelephoneLineRepository;
import br.com.helion.phonemanagement.services.exceptions.DatabaseException;
import br.com.helion.phonemanagement.services.exceptions.ResourceNotFoundException;

@Service
public class ExpenditureService {

	@Autowired
	private ExpenditureRepository repository;
	
	@Autowired
	private TelephoneLineRepository telephoneLineRepository;
	
	@Transactional(readOnly = true)
	public Page<ExpenditureDTO> findAllPaged(PageRequest pageRequest){
		Page<Expenditure> list  = repository.findAll(pageRequest);
		return list.map(department -> (new ExpenditureDTO(department)));
	}
	
	@Transactional(readOnly = true)
	public ExpenditureDTO findById(Long id) {
		Optional<Expenditure> obj = repository.findById(id);
		Expenditure entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
		return new ExpenditureDTO(entity);
	}
	
	@Transactional
	public ExpenditureDTO insert(ExpenditureDTO dto) {
		Expenditure entity = new Expenditure();
		copyDtoToEntity(dto, entity);
		entity = repository.save(entity);
		return new ExpenditureDTO(entity);
	}

	

	@Transactional
	public ExpenditureDTO update(Long id, ExpenditureDTO dto) {
		try {
			Expenditure entity = repository.getById(id);
			copyDtoToEntity(dto,entity);
			entity= repository.save(entity);
			return new ExpenditureDTO(entity);
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
	
	private void copyDtoToEntity(ExpenditureDTO dto, Expenditure entity) {
		entity.setLocalMinutes(dto.getLocalMinutes());
		entity.setLongDistanceMinutes(dto.getLongDistanceMinutes());
		entity.setQuantityDataMB(dto.getQuantityDataMB());
		entity.setReferenceDate(dto.getReferenceDate());
		entity.setValue(dto.getValue());
		try {
			TelephoneLine line = telephoneLineRepository.getById(dto.getTelephoneLine().getId());
			entity.setTelephoneLine(line);
		}
		catch(EntityNotFoundException e) {
			throw new ResourceNotFoundException("Telephoneline Not Found Id:  "+ dto.getTelephoneLine().getId());
		}
		
		
	}

	
}
