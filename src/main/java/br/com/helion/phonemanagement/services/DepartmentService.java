package br.com.helion.phonemanagement.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.helion.phonemanagement.dtos.DepartmentDTO;
import br.com.helion.phonemanagement.entities.Department;
import br.com.helion.phonemanagement.repositories.DepartmentRepository;
import br.com.helion.phonemanagement.services.exceptions.ResourceNotFoundException;

@Service
public class DepartmentService {

	@Autowired
	private DepartmentRepository repository;
	
	@Transactional(readOnly = true)
	public List<DepartmentDTO> findAll(){
		List<Department> list  = repository.findAll();
		return list.stream().map(department -> (new DepartmentDTO(department))).collect(Collectors.toList());
	}
	
	@Transactional(readOnly = true)
	public DepartmentDTO findById(Long id) {
		Optional<Department> obj = repository.findById(id);
		return new DepartmentDTO(obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found")));
	}
	
	@Transactional
	public DepartmentDTO insert(DepartmentDTO dto) {
		Department entity = new Department();
		entity.setName(dto.getName());
		entity = repository.save(entity);
		return new DepartmentDTO(entity);
	}

	@Transactional
	public DepartmentDTO update(Long id, DepartmentDTO dto) {
		try {
			Department entity = repository.getById(id);
			entity.setName(dto.getName());
			entity= repository.save(entity);
			return new DepartmentDTO(entity);
		}
		catch(EntityNotFoundException e) {
			throw new ResourceNotFoundException("Id not found "+ id);
		}
		
	
	}
}
