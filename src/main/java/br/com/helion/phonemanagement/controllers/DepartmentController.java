package br.com.helion.phonemanagement.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.helion.phonemanagement.dtos.DepartmentDTO;
import br.com.helion.phonemanagement.services.DepartmentService;

@RestController
@RequestMapping(value ="/departments")
public class DepartmentController {
	
	@Autowired
	private DepartmentService service;
	
	@GetMapping
	public ResponseEntity<List<DepartmentDTO>> findAll(){
		
		List<DepartmentDTO> list = service.findAll();
		return  ResponseEntity.ok().body(list);
	}
	@GetMapping(value="/{id}")
	public ResponseEntity<DepartmentDTO> findById(@PathVariable Long id){
		
		DepartmentDTO dto = service.findById(id);
		return  ResponseEntity.ok().body(dto);
	}
	
	@PostMapping
	public ResponseEntity<DepartmentDTO> insert(@RequestBody DepartmentDTO dto){
		dto = service.insert(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
				.buildAndExpand(dto.getId()).toUri();
		return ResponseEntity.created(uri).body(dto);
	}
	
	@PutMapping(value="/{id}")
	public ResponseEntity<DepartmentDTO> update(@PathVariable Long id, @RequestBody DepartmentDTO dto){
		dto = service.update(id, dto);
		return ResponseEntity.ok().body(dto);
	}

}
