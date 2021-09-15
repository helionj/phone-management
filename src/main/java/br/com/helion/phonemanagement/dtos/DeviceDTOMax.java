package br.com.helion.phonemanagement.dtos;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.NotBlank;

import br.com.helion.phonemanagement.entities.Device;
import br.com.helion.phonemanagement.entities.TelephoneLine;
import br.com.helion.phonemanagement.entities.User;

public class DeviceDTOMax implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	
	@NotBlank(message="Campo obrigatório")
	private String model;
	
	@NotBlank(message="Campo obrigatório")
	private String manufacture;
	private Instant activationDate;
	private UserDTOMin user;
	private Set<TelephoneLineDTO> lines = new HashSet<>();
	
	public DeviceDTOMax() {}

	public DeviceDTOMax(Long id, String model, String manufacture, Instant activationDate, User user) {
		super();
		this.id = id;
		this.model = model;
		this.manufacture = manufacture;
		this.activationDate = activationDate;
		this.user=new UserDTOMin(user);
	}
	
	
	public DeviceDTOMax(Device entity) {
		id = entity.getId();
		model = entity.getModel();
		manufacture = entity.getManufacture();
		activationDate = entity.getActivationDate();
		user = new UserDTOMin(entity.getUser());
	}
	
	public DeviceDTOMax(Device entity, Set<TelephoneLine> lines) {
		this(entity);
		lines.forEach(line -> this.lines.add(new TelephoneLineDTO(line)));
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getManufacture() {
		return manufacture;
	}

	public void setManufacture(String manufacture) {
		this.manufacture = manufacture;
	}

	public Instant getActivationDate() {
		return activationDate;
	}

	public void setActivationDate(Instant activationDate) {
		this.activationDate = activationDate;
	}
	
	public UserDTOMin getUser() {
		return user;
	}

	public void setUser(UserDTOMin user) {
		this.user = user;
	}

	public Set<TelephoneLineDTO> getLines() {
		return lines;
	}
	
	
	
}
