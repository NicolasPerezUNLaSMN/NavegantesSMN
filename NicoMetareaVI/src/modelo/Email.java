package modelo;

public class Email {

	private int idEmail;
	private String email;
	
	
	public Email() {};
	
	
	
	
	
	
	public Email(int idEmail, String email) {
		super();
		this.idEmail = idEmail;
		this.email = email;
	}

	
	

	public Email( String email) {
		super();
		
		this.email = email;
	}






	@Override
	public String toString() {
		return "Email [idEmail=" + idEmail + ", email=" + email + "]";
	}






	public int getIdEmail() {
		return idEmail;
	}
	protected void setIdEmail(int idEmail) {
		this.idEmail = idEmail;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
	
	
	
}
