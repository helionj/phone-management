package br.com.helion.phonemanagement.dtos;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import br.com.helion.phonemanagement.entities.SimCard;
import br.com.helion.phonemanagement.entities.TelephoneLine;
import br.com.helion.phonemanagement.entities.User;

public class TelephoneLineDTOMax implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	@NotBlank(message="Campo obrigatório")
	private String lineNumber;
	private SimCardDTO simCard;
	private UserDTO user;

	
	public TelephoneLineDTOMax() {}

	public TelephoneLineDTOMax(Long id, String lineNumber, SimCard simCard, User user) {
		this.id = id;
		this.lineNumber = lineNumber;
		this.simCard = new SimCardDTO(simCard);
		this.user = new UserDTO(user);
		
	};
	
	public TelephoneLineDTOMax(TelephoneLine entity) {
		id = entity.getId();
		lineNumber = entity.getLineNumber();
		
		if(entity.getSimCard()!= null) {
			simCard = new SimCardDTO(entity.getSimCard());
		}
		if(entity.getUser()!= null) {
			user = new UserDTO(entity.getUser());
		}
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLineNumber() {
		return lineNumber;
	}

	public void setLineNumber(String lineNumber) {
		this.lineNumber = lineNumber;
	}

	public SimCardDTO getSimCard() {
		return simCard;
	}

	public void setSimCard(SimCardDTO simCard) {
		this.simCard = simCard;
	}

	public UserDTO getUser() {
		return user;
	}

	public void setUser(UserDTO user) {
		this.user = user;
	}

	
	
	

	
	
	
	
	
}
