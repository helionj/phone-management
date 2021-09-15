package br.com.helion.phonemanagement.services;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.helion.phonemanagement.dtos.DeviceDTO;
import br.com.helion.phonemanagement.dtos.RoleDTO;
import br.com.helion.phonemanagement.dtos.TelephoneLineDTO;
import br.com.helion.phonemanagement.dtos.UserDTO;
import br.com.helion.phonemanagement.dtos.UserDTOInsert;
import br.com.helion.phonemanagement.dtos.UserDTOUpdate;
import br.com.helion.phonemanagement.entities.Department;
import br.com.helion.phonemanagement.entities.Device;
import br.com.helion.phonemanagement.entities.Role;
import br.com.helion.phonemanagement.entities.TelephoneLine;
import br.com.helion.phonemanagement.entities.User;
import br.com.helion.phonemanagement.repositories.DepartmentRepository;
import br.com.helion.phonemanagement.repositories.DeviceRepository;
import br.com.helion.phonemanagement.repositories.RoleRepository;
import br.com.helion.phonemanagement.repositories.TelephoneLineRepository;
import br.com.helion.phonemanagement.repositories.UserRepository;
import br.com.helion.phonemanagement.services.exceptions.DatabaseException;
import br.com.helion.phonemanagement.services.exceptions.ResourceNotFoundException;

@Service
public class UserService {
	
	@Autowired
	private UserRepository repository;
	
	@Autowired
	private DepartmentRepository departmentRepository;
	
	@Autowired
	private DeviceRepository deviceRepository;
	
	@Autowired
	private TelephoneLineRepository telephoneLineRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Transactional(readOnly = true)
	public Page<UserDTO> findAllPaged(Pageable pageable){
		Page<User> list  = repository.findAll(pageable);
		return list.map(x -> (new UserDTO(x)));
	}
	
	@Transactional(readOnly = true)
	public UserDTO findById(Long id) {
		Optional<User> obj = repository.findById(id);
		User entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
		return new UserDTO(entity,entity.getLines(), entity.getDevices());
	}
	
	@Transactional
	public UserDTO insert(UserDTOInsert dto) {
		User entity = new User();
		copyDtoToEntity(dto, entity);
		entity.setPassword(passwordEncoder.encode(dto.getPassword()));
		entity = repository.save(entity);
		return new UserDTO(entity);
	}

	@Transactional
	public UserDTO update(Long id, UserDTOUpdate dto) {
		try {
			User entity = repository.getById(id);
			copyDtoToEntity(dto, entity);
			entity= repository.save(entity);
			return new UserDTO(entity);
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
	
	void copyDtoToEntity(UserDTO dto, User entity){
		entity.setName(dto.getName());
		entity.setEmail(dto.getEmail());
		
		Department department = departmentRepository.getById(dto.getDepartmentDTO().getId());
		entity.setDepartment(department);
		
		
		updateRoles(dto, entity);
		
		updateTelephoneLine(dto, entity);
		
		updateDevice(dto,entity);
		
		
	}

	private void updateRoles(UserDTO dto, User entity) {
		entity.getRoles().clear();
		for(RoleDTO roleDto: dto.getRoles()) {
			Role role = roleRepository.getById(roleDto.getId());
			entity.getRoles().add(role);
		}
	}

	private void updateTelephoneLine(UserDTO dto, User entity) {
		entity.getLines().clear();
		for(TelephoneLineDTO lineDTO: dto.getLines()) {
			try {
				TelephoneLine line = telephoneLineRepository.getById(lineDTO.getId());
				entity.getLines().add(line);
				line.setUser(entity);
				telephoneLineRepository.save(line);
			}
			catch(EntityNotFoundException e) {
				throw new  ResourceNotFoundException("Telephone line not found id: "+ lineDTO.getId());
			}
			
		}
	}
	
	private void updateDevice(UserDTO dto, User entity) {
		entity.getDevices().clear();
		for(DeviceDTO devDTO : dto.getDevices()) {
			try {
				Device device = deviceRepository.getById(devDTO.getId());
				entity.getDevices().add(device);
				device.setUser(entity);
				deviceRepository.save(device);
			}
			catch(EntityNotFoundException e) {
				throw new  ResourceNotFoundException("Device not found id: "+ devDTO.getId());
			}
		}
	}
	
}
