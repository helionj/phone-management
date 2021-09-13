package br.com.helion.phonemanagement.dtos;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import br.com.helion.phonemanagement.entities.Device;
import br.com.helion.phonemanagement.entities.TelephoneLine;
import br.com.helion.phonemanagement.entities.User;

public class UserDTO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String name;
	private String email;
	private DepartmentDTO departmentDTO;
	
	private Set<RoleDTO>roles = new HashSet<>();
	
	private Set<TelephoneLineDTO> lines = new HashSet<>();
	

	private Set<DeviceDTO> devices = new HashSet<>();
	
	
	
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
	
	public UserDTO(User entity, Set<TelephoneLine> lines, Set<Device> devices) {
		this(entity);
		lines.forEach(x -> this.lines.add(new TelephoneLineDTO(x)));
		devices.forEach(x -> this.devices.add(new DeviceDTO(x)));
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


	public Set<RoleDTO> getRoles() {
		return roles;
	}


	public Set<TelephoneLineDTO> getLines() {
		return lines;
	}


	public Set<DeviceDTO> getDevices() {
		return devices;
	}
	
	
	
	
	
	
	
	
	
}
