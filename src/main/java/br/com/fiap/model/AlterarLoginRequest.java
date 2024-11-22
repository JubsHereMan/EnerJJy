package br.com.fiap.model;

public class AlterarLoginRequest {

    private String novoEmail;
    private String novaSenha;

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
}
