package br.com.fiap.model;

public class AlterarLoginRequest {

    private String novoEmail;
    private String novaSenha;

    
    
    
    public AlterarLoginRequest(String novoEmail, String novaSenha) {
		super();
		this.novoEmail = novoEmail;
		this.novaSenha = novaSenha;
	}

	public AlterarLoginRequest() {
		super();
	}

	// Getters e Setters
    public String getNovoEmail() {
        return novoEmail;
    }

    public void setNovoEmail(String novoEmail) {
        this.novoEmail = novoEmail;
    }

    public String getNovaSenha() {
        return novaSenha;
    }

    public void setNovaSenha(String novaSenha) {
        this.novaSenha = novaSenha;
    }

	@Override
	public String toString() {
		return "AlterarLoginRequest [novoEmail=" + novoEmail + ", novaSenha=" + novaSenha + "]";
	}
    
    
}
