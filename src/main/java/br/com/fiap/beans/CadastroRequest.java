package br.com.fiap.beans;

public class CadastroRequest {

    private Cliente cliente;
    private Login login;

    
    
    
    public CadastroRequest(Cliente cliente, Login login) {
		super();
		this.cliente = cliente;
		this.login = login;
	}

	public CadastroRequest() {
		super();
	}

	// Getters e Setters
    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Login getLogin() {
        return login;
    }

    public void setLogin(Login login) {
        this.login = login;
    }

	@Override
	public String toString() {
		return "CadastroRequest [cliente=" + cliente + ", login=" + login + "]";
	}
    
    
    
}
