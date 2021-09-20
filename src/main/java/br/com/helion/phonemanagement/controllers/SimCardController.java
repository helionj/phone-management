package br.com.helion.phonemanagement.controllers;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.helion.phonemanagement.dtos.SimCardDTO;
import br.com.helion.phonemanagement.dtos.SimCardDTOInsert;
import br.com.helion.phonemanagement.dtos.SimCardDTOUpdate;
import br.com.helion.phonemanagement.services.SimCardService;

@RestController
@RequestMapping(value = "/simcards")
public class SimCardController {

	@Autowired
	private SimCardService service;

	@GetMapping
	public ResponseEntity<Page<SimCardDTO>> findAll(Pageable pageable) {
		
		
		Page<SimCardDTO> list = service.findAllPaged(pageable);
		return ResponseEntity.ok().body(list);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<SimCardDTO> findById(@PathVariable Long id) {

		SimCardDTO dto = service.findById(id);
		return ResponseEntity.ok().body(dto);
	}

	@PostMapping
	public ResponseEntity<SimCardDTO> insert(@Valid @RequestBody SimCardDTOInsert dto) {
		SimCardDTO newdto = service.insert(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(newdto.getId()).toUri();
		return ResponseEntity.created(uri).body(newdto);
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<SimCardDTO> update(@PathVariable Long id, @Valid @RequestBody SimCardDTOUpdate dto) {
		SimCardDTO newdto = service.update(id, dto);
		return ResponseEntity.ok().body(newdto);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

}
