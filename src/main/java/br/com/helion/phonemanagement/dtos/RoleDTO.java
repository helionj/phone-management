package br.com.helion.phonemanagement.dtos;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import br.com.helion.phonemanagement.entities.Role;

public class RoleDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	Long id;
	
	@NotBlank(message="Campo obrigatório")
	String authority;
	
	public RoleDTO() {}

	public RoleDTO(Long id, String authority) {
		
		this.id = id;
		this.authority = authority;
	}
	
	public RoleDTO(Role entity) {
		id = entity.getId();
		authority = entity.getAuthority();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}
	
	
	
	
	
}
