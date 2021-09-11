package br.com.helion.phonemanagement.dtos;

import java.util.HashSet;
import java.util.Set;

import br.com.helion.phonemanagement.entities.User;

public class UserDTO {
	
	private Long id;
	private String name;
	private String email;
	private DepartmentDTO departmentDTO;
	
	private Set<RoleDTO>roles = new HashSet<>();
	
	
	public UserDTO() {}


	public UserDTO(Long id, String name, String email, DepartmentDTO departmentDTO) {
		
		this.id = id;
		this.name = name;
		this.email = email;
		this.departmentDTO = departmentDTO;
	}
	
	public UserDTO(User entity) {
		id=entity.getId();
		name= entity.getName();
		email=entity.getEmail();
		departmentDTO =new DepartmentDTO(entity.getDepartment());
		entity.getRoles().forEach(role -> this.roles.add(new RoleDTO(role)));
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


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public DepartmentDTO getDepartmentDTO() {
		return departmentDTO;
	}


	public void setDepartmentDTO(DepartmentDTO departmentDTO) {
		this.departmentDTO = departmentDTO;
	}
	
	
	
	
	
	
	
	
	
}
