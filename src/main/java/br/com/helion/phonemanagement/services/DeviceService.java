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

import br.com.helion.phonemanagement.dtos.DeviceDTO;
import br.com.helion.phonemanagement.dtos.DeviceDTOMax;
import br.com.helion.phonemanagement.entities.Device;
import br.com.helion.phonemanagement.repositories.DeviceRepository;
import br.com.helion.phonemanagement.services.exceptions.DatabaseException;
import br.com.helion.phonemanagement.services.exceptions.ResourceNotFoundException;

@Service
public class DeviceService {

	@Autowired
	private DeviceRepository repository;
	
	
	@Transactional(readOnly = true)
	public Page<DeviceDTO> findAllPaged(PageRequest pageRequest){
		Page<Device> list  = repository.findAll(pageRequest);
		return list.map(department -> (new DeviceDTO(department)));
	}
	
	@Transactional(readOnly = true)
	public DeviceDTOMax findById(Long id) {
		Optional<Device> obj = repository.findById(id);
		Device entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
		return new DeviceDTOMax(entity);
	}
	
	@Transactional
	public DeviceDTO insert(DeviceDTO dto) {
		Device entity = new Device();
		copyDtoToEntity(dto, entity);
		entity = repository.save(entity);
		return new DeviceDTO(entity);
	}

	

	@Transactional
	public DeviceDTO update(Long id, DeviceDTO dto) {
		try {
			Device entity = repository.getById(id);
			copyDtoToEntity(dto, entity);
			entity= repository.save(entity);
			return new DeviceDTO(entity);
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
	
	
	private void copyDtoToEntity(DeviceDTO dto, Device entity) {
		entity.setActivationDate(dto.getActivationDate());
		entity.setManufacture(dto.getManufacture());
		entity.setModel(dto.getModel());
		
		
	}

	
}
