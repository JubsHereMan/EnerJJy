package br.com.fiap.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.fiap.beans.Cliente;
import br.com.fiap.conexoes.ConexaoFactory;

public class ClienteDAO {



	private Connection conexao;

	public ClienteDAO() throws ClassNotFoundException, SQLException {
	    this.conexao = ConexaoFactory.getConnection();
	    if (this.conexao == null) {
	        throw new IllegalStateException("Erro ao estabelecer a conexão com o banco de dados.");
	    }
	}


    public ClienteDAO(Connection conexao) {
        this.conexao = conexao;
    }

    public int inserirCliente(Cliente cliente) {
        String sql = "INSERT INTO TBL_CLIENTE (nome, cpf, telefone) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, cliente.getNome());
            stmt.setLong(2, cliente.getCpf());
            stmt.setLong(3, cliente.getTelefone());
            stmt.executeUpdate();

            // Obter o ID do cliente
            String queryId = "SELECT id_cliente FROM TBL_CLIENTE WHERE cpf = ?";
            try (PreparedStatement stmtId = conexao.prepareStatement(queryId)) {
                stmtId.setLong(1, cliente.getCpf());
                ResultSet rs = stmtId.executeQuery();
                if (rs.next()) {
                    return rs.getInt("id_cliente");
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao inserir cliente: " + e.getMessage());
        }
        return -1;
    }

   



    public Cliente buscarPorId(int idCliente) {
        String sql = "SELECT * FROM tbl_cliente WHERE id_cliente = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, idCliente);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Cliente(
                );
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar cliente: " + e.getMessage());
        }
        return null;
    }

    public List<Cliente> listarTodos() {
        List<Cliente> listaClientes = new ArrayList<>();
        String sql = "SELECT id_cliente, nome, cpf, telefone FROM TBL_CLIENTE";
        
        try (PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setId(rs.getInt("id_cliente"));
                cliente.setNome(rs.getString("nome"));
                cliente.setCpf(rs.getLong("cpf"));
                cliente.setTelefone(rs.getLong("telefone"));

                listaClientes.add(cliente);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar clientes: " + e.getMessage());
        }

        return listaClientes;
    }

    public void atualizar(Cliente cliente) throws SQLException {
        String sql = "UPDATE TBL_CLIENTE SET nome = ?, cpf = ?, telefone = ? WHERE id_cliente = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, cliente.getNome());
            stmt.setLong(2, cliente.getCpf());
            stmt.setLong(3, cliente.getTelefone());
            stmt.setInt(4, cliente.getId()); // ID deve ser o último parâmetro

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected == 0) {
                System.out.println("Nenhum cliente encontrado para atualizar.");
            }
        }
    }

    public void deletar(int idCliente) throws SQLException {
        String sql = "DELETE FROM TBL_CLIENTE WHERE id_cliente = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, idCliente);
            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected == 0) {
                System.out.println("Nenhum cliente encontrado para deletar.");
            }
        }
    }

    public void fecharConexao() {
        try {
            if (conexao != null && !conexao.isClosed()) {
                conexao.close();
                System.out.println("FECHEI");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao fechar a conexão: " + e.getMessage());
        }
    }

}
