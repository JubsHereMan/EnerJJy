package br.com.fiap.model;

import br.com.fiap.beans.Cliente;
import br.com.fiap.beans.Login;

public class CadastroCompletoRequest {
    private Cliente cliente;
    private Login login;
    private Endereco endereco;

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
}
