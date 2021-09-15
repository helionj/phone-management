package br.com.helion.phonemanagement.dtos;

public class UserDTOInsert extends UserDTO{


	private static final long serialVersionUID = 1L;
	
	private String password;
	
	public UserDTOInsert() {
		super();
	}

	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
	
}
