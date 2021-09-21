package br.com.helion.phonemanagement.entities;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name="tb_expenditure")
public class Expenditure {

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;
	private Double quantityDataMB;
	private Double localMinutes;
	private Double longDistanceMinutes;
	private Double value;
	
	@JsonFormat(pattern="dd-MM-yyyy")
	private LocalDate referenceDate;
	
	@ManyToOne
	@JoinColumn(name = "telephone_line_id")
	private TelephoneLine telephoneLine;
	
	public Expenditure() {}

	public Expenditure(Long id, Double quantityDataMB, Double localMinutes, Double longDistanceMinutes, Double value,
			LocalDate referenceDate, TelephoneLine telephoneLine) {
		
		this.id = id;
		this.quantityDataMB = quantityDataMB;
		this.localMinutes = localMinutes;
		this.longDistanceMinutes = longDistanceMinutes;
		this.value = value;
		this.referenceDate = referenceDate;
		this.telephoneLine = telephoneLine;
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

	public void setLongDistanceMinutes(Double longDistantanceMinutes) {
		this.longDistanceMinutes = longDistantanceMinutes;
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

	public TelephoneLine getTelephoneLine() {
		return telephoneLine;
	}

	public void setTelephoneLine(TelephoneLine telephoneLine) {
		this.telephoneLine = telephoneLine;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Expenditure other = (Expenditure) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	
	
}
