package br.com.helion.phonemanagement.dtos;

import java.io.Serializable;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.helion.phonemanagement.entities.Expenditure;
import br.com.helion.phonemanagement.entities.TelephoneLine;

public class ExpenditureDTO implements Serializable{
	

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private Double quantityDataMB;
	private Double localMinutes;
	private Double longDistanceMinutes;
	private Double value;
	
	@JsonFormat(pattern="dd-MM-yyyy")
	private LocalDate referenceDate;
	
	private TelephoneLineDTO telephoneLine;
	
	
	public ExpenditureDTO() {
		
	}
	
    public ExpenditureDTO(Long id, Double quantityDataMB, Double localMinutes, 
    		Double longDistanceMinutes, Double value, LocalDate referenceDate, TelephoneLine line) {
    	
    	this.id =id;
    	this.quantityDataMB = quantityDataMB;
    	this.localMinutes = localMinutes;
    	this.longDistanceMinutes = longDistanceMinutes;
    	this.value = value;
    	this.referenceDate = referenceDate;
    	this.telephoneLine = new TelephoneLineDTO(line);
    }
    
    public ExpenditureDTO(Expenditure entity) {
		id = entity.getId();
		quantityDataMB = entity.getQuantityDataMB();
		localMinutes = entity.getLocalMinutes();
		longDistanceMinutes = entity.getLongDistanceMinutes();
		value = entity.getValue();
		referenceDate = entity.getReferenceDate();
		telephoneLine = new TelephoneLineDTO(entity.getTelephoneLine());
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getQuantityDataMB() {
		return quantityDataMB;
	}

	public void setQuantityDataMB(Double quantityDataMB) {
		this.quantityDataMB = quantityDataMB;
	}

	public Double getLocalMinutes() {
		return localMinutes;
	}

	public void setLocalMinutes(Double localMinutes) {
		this.localMinutes = localMinutes;
	}

	public Double getLongDistanceMinutes() {
		return longDistanceMinutes;
	}

	public void setLongDistanceMinutes(Double longDistanceMinutes) {
		this.longDistanceMinutes = longDistanceMinutes;
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

	public LocalDate getReferenceDate() {
		return referenceDate;
	}

	public void setReferenceDate(LocalDate referenceDate) {
		this.referenceDate = referenceDate;
	}

	public TelephoneLineDTO getTelephoneLine() {
		return telephoneLine;
	}

	public void setTelephoneLine(TelephoneLineDTO telephoneLine) {
		this.telephoneLine = telephoneLine;
	}
    
    

}
