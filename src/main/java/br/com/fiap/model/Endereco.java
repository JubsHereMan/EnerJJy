package br.com.fiap.model;

public class Endereco {

	private int idEndereco;
	private int id;
    private int idCliente;
    private String logradouro;
    private String numero;
    private String cep;
    private String bairro;
    private String localidade;
    private String uf;


    public Endereco() {
    }


    public Endereco(int idCliente, String logradouro, String numero, String cep, String bairro, String localidade, String uf) {
        this.idCliente = idCliente;
        this.logradouro = logradouro;
        this.numero = numero;
        this.cep = cep;
        this.bairro = bairro;
        this.localidade = localidade;
        this.uf = uf;
    }

    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getLocalidade() {
        return localidade;
    }

    public void setLocalidade(String localidade) {
        this.localidade = localidade;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public int getIdEndereco() {
		return idEndereco;
	}

	public void setIdEndereco(int idEndereco) {
		this.idEndereco = idEndereco;
	}


	@Override
	public String toString() {
		return "Endereco [idEndereco=" + idEndereco + ", id=" + id + ", idCliente=" + idCliente + ", logradouro="
				+ logradouro + ", numero=" + numero + ", cep=" + cep + ", bairro=" + bairro + ", localidade="
				+ localidade + ", uf=" + uf + "]";
	}
	
	
}
