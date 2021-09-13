package br.com.helion.phonemanagement.dtos;

import java.io.Serializable;
import java.time.Instant;

import br.com.helion.phonemanagement.entities.Device;

public class DeviceDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	private String model;
	private String manufacture;
	private Instant activationDate;
	
	public DeviceDTO() {}

	public DeviceDTO(Long id, String model, String manufacture, Instant activationDate) {
		super();
		this.id = id;
		this.model = model;
		this.manufacture = manufacture;
		this.activationDate = activationDate;
	}
	
	public DeviceDTO(Device entity) {
		id = entity.getId();
		model = entity.getModel();
		manufacture = entity.getManufacture();
		activationDate = entity.getActivationDate();
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
	
}
