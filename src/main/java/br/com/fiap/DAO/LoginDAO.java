package br.com.fiap.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.fiap.beans.Login;
import br.com.fiap.conexoes.ConexaoFactory;

public class LoginDAO {
    private Connection conexao;


    public LoginDAO() throws ClassNotFoundException, SQLException {

		this.conexao = ConexaoFactory.getConnection();
    }


     public boolean inserirLogin(int idCliente, Login login) {
        String sql = "INSERT INTO TBL_LOGIN (id_cliente, email, senha) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, idCliente);
            stmt.setString(2, login.getEmail());
            stmt.setString(3, login.getSenha());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Erro ao inserir login: " + e.getMessage());
        }
        return false;
    }


    public Login buscarPorClienteId(int clienteId) {
        String sql = "SELECT * FROM TBL_LOGIN WHERE cliente_id = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, clienteId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Login(rs.getString("email"), rs.getString("senha"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao buscar login: " + e.getMessage(), e);
        }
        return null;
    }

    
    public boolean atualizarLogin(int idCliente, String novoEmail, String novaSenha) {
        String sql = "UPDATE tbl_login SET email = ?, senha = ? WHERE id_cliente = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, novoEmail);
            stmt.setString(2, novaSenha);
            stmt.setInt(3, idCliente);

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar login no DAO: " + e.getMessage());
        }
        return false;
    }

    
    

    public void fecharConexao() {
        if (conexao != null) {
            try {
                conexao.close();
                System.out.println("FECHEI");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
