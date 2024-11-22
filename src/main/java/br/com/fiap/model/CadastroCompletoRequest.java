package br.com.fiap.model;

import br.com.fiap.beans.Cliente;

import br.com.fiap.beans.Login;

public class CadastroCompletoRequest {
    private Cliente cliente;
    private Login login;
    private Endereco endereco;

    
    public CadastroCompletoRequest(Cliente cliente, Login login, Endereco endereco) {
		super();
		this.cliente = cliente;
		this.login = login;
		this.endereco = endereco;
	}
    
    

	public CadastroCompletoRequest() {
		super();
	}



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

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }



	@Override
	public String toString() {
		return "CadastroCompletoRequest [cliente=" + cliente + ", login=" + login + ", endereco=" + endereco + "]";
	}
    
    
}
