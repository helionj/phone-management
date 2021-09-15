package br.com.helion.phonemanagement.dtos;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import br.com.helion.phonemanagement.entities.TelephoneLine;

public class TelephoneLineDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Long id;
	@NotBlank(message="Campo obrigatório")
	private String lineNumber;
	
	public TelephoneLineDTO() {}

	public TelephoneLineDTO(Long id, String lineNumber) {
		this.id = id;
		this.lineNumber = lineNumber;
	};
	
	public TelephoneLineDTO(TelephoneLine entity) {
		id = entity.getId();
		lineNumber = entity.getLineNumber();
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
	
	
	
	
	
}
