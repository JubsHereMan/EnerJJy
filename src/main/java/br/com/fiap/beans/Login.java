package br.com.fiap.beans;

public class Login {

	private String email;
	private String senha;

	public Login() {
		super();
	}

	public Login(String email, String senha) {
		super();
		this.email = email;
		this.senha = senha;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	@Override
	public String toString() {
		return "Login [email=" + email + ", senha=" + senha + "]";
	}

}