package br.com.fiap.BO;

import java.sql.SQLException;

import br.com.fiap.DAO.LoginDAO;

public class LoginBO {

    private LoginDAO loginDAO;

    public LoginBO() throws ClassNotFoundException, SQLException {
        this.loginDAO = new LoginDAO();
    }

    // Método para atualizar login (e-mail e senha)
    public boolean atualizarLogin(int idCliente, String novoEmail, String novaSenha) {
        if (idCliente <= 0 || novoEmail == null || novoEmail.isEmpty() || novaSenha == null || novaSenha.isEmpty()) {
            System.out.println("Dados inválidos para atualização de login");
            return false;
        }

        return loginDAO.atualizarLogin(idCliente, novoEmail, novaSenha);
    }
}
