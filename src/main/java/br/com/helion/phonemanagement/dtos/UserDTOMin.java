package br.com.helion.phonemanagement.dtos;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import br.com.helion.phonemanagement.entities.User;

public class UserDTOMin implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	@Size(min = 5, max =60, message = "Deve ter entre 5 e 60 caracteres")
	@NotBlank(message="Campo obrigatório")
	private String name;
	@Email(message= "Favor entrar com um e-mail válido")
	private String email;
	private DepartmentDTO departmentDTO;
	
	
	
	public UserDTOMin() {}


	public UserDTOMin(Long id, String name, String email, DepartmentDTO departmentDTO) {
		
		this.id = id;
		this.name = name;
		this.email = email;
		this.departmentDTO = departmentDTO;
	}
	
	public UserDTOMin(User entity) {
		id=entity.getId();
		name= entity.getName();
		email=entity.getEmail();
		departmentDTO =new DepartmentDTO(entity.getDepartment());
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
