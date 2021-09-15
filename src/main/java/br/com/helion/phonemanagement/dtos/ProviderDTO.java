package br.com.helion.phonemanagement.dtos;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.NotBlank;

import br.com.helion.phonemanagement.entities.Provider;
import br.com.helion.phonemanagement.entities.SimCard;

public class ProviderDTO  implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	@NotBlank(message="Campo obrigatório")
	private String name;
	Set<SimCardDTO>simcards = new HashSet<>();
	
	public ProviderDTO() {
		
	}
	
	public ProviderDTO(Long id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public ProviderDTO(Provider entity) {
		id = entity.getId();
		name = entity.getName();
	}
	
	public ProviderDTO(Provider entity, Set<SimCard> simcards) {
		this(entity);
		simcards.forEach(x -> this.simcards.add(new SimCardDTO(x)));
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<SimCardDTO> getSimcards() {
		return simcards;
	}
	
	
}
