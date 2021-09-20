package br.com.helion.phonemanagement.dtos;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import br.com.helion.phonemanagement.entities.SimCard;

public class SimCardDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	@NotBlank(message="Campo obrigatório")
	private String number;
    private TelephoneLineDTO telephoneLine;
    private ProviderDTO provider;
	
	
	

	public SimCardDTO() {};
	
	public SimCardDTO(SimCard entity) {
		id = entity.getId();
		number= entity.getNumber();
		provider = new ProviderDTO(entity.getProvider());
		if(entity.getTelephoneLine()!=null) {
			telephoneLine = new TelephoneLineDTO(entity.getTelephoneLine());
		}
		
	}
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
     
	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public TelephoneLineDTO getTelephoneLine() {
		return telephoneLine;
	}

	public void setTelephoneLine(TelephoneLineDTO telephoneLine) {
		this.telephoneLine = telephoneLine;
	}

	public ProviderDTO getProvider() {
		return provider;
	}

	public void setProvider(ProviderDTO provider) {
		this.provider = provider;
	}

	
	
	

	
	
}
