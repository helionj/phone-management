package br.com.helion.phonemanagement.services;

import java.util.List;
import java.util.stream.Collectors;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.helion.phonemanagement.dtos.DepartmentDTO;
import br.com.helion.phonemanagement.entities.Department;
import br.com.helion.phonemanagement.repositories.DepartmentRepository;

@Service
public class DepartmentService {

	@Autowired
	private DepartmentRepository repository;
	
	@Transactional(readOnly = true)
	public List<DepartmentDTO> findAll(){
		List<Department> list  = repository.findAll();
		return list.stream().map(department -> (new DepartmentDTO(department))).collect(Collectors.toList());
	}
}
