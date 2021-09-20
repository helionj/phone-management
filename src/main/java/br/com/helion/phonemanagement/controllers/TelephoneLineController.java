package br.com.helion.phonemanagement.controllers;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.helion.phonemanagement.dtos.TelephoneLineDTOInsert;
import br.com.helion.phonemanagement.dtos.TelephoneLineDTOMax;
import br.com.helion.phonemanagement.dtos.TelephoneLineDTOUpdate;
import br.com.helion.phonemanagement.services.TelephoneLineService;

@RestController
@RequestMapping(value = "/lines")
public class TelephoneLineController {
	@Autowired
	private TelephoneLineService service;

	@GetMapping
	public ResponseEntity<Page<TelephoneLineDTOMax>> findAll(@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "12") Integer linesPerPage,
			@RequestParam(value = "orderBy", defaultValue = "lineNumber") String orderBy,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage,Direction.valueOf(direction), orderBy);
		
		Page<TelephoneLineDTOMax> list = service.findAllPaged(pageRequest);
		return ResponseEntity.ok().body(list);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<TelephoneLineDTOMax> findById(@PathVariable Long id) {

		TelephoneLineDTOMax dto = service.findById(id);
		return ResponseEntity.ok().body(dto);
	}

	@PostMapping
	public ResponseEntity<TelephoneLineDTOMax> insert(@Valid @RequestBody TelephoneLineDTOInsert dto) {
		TelephoneLineDTOMax newdto = service.insert(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(dto.getId()).toUri();
		return ResponseEntity.created(uri).body(newdto);
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<TelephoneLineDTOMax> update(@PathVariable Long id, @Valid @RequestBody TelephoneLineDTOUpdate dto) {
		TelephoneLineDTOMax newdto = service.update(id, dto);
		return ResponseEntity.ok().body(newdto);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}
