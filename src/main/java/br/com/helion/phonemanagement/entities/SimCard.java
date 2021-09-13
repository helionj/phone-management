package br.com.helion.phonemanagement.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="tb_sim_card")
public class SimCard implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String number;
	
	@OneToOne(mappedBy ="simCard")
	private TelephoneLine telephoneLine;
	
	@ManyToOne
	@JoinColumn(name = "provider_id")
	private Provider provider;
	
	public SimCard() {}

	public SimCard(Long id, String number, Provider provider, TelephoneLine telephoneLine) {
		
		this.id = id;
		this.number = number;
		this.setProvider(provider);
		this.setTelephoneLine(telephoneLine);
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

	public Provider getProvider() {
		return provider;
	}

	public void setProvider(Provider provider) {
		this.provider = provider;
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
		SimCard other = (SimCard) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	
	
}
