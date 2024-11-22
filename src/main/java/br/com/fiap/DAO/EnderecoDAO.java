package br.com.fiap.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.fiap.model.Endereco;
import br.com.fiap.services.ViaCepService;

public class EnderecoDAO {

    private Connection conexao;


    public EnderecoDAO() {
        try {
            this.conexao = DriverManager.getConnection(
                "jdbc:oracle:thin:@oracle.fiap.com.br:1521:ORCL", "RM557774", "080403"
            );
        } catch (SQLException e) {
            System.out.println("Erro ao conectar ao banco de dados: " + e.getMessage());
        }
    }

    
    public int inserir(Endereco endereco, int idCliente) {
        try {
            
            if (endereco.getCep() != null && endereco.getCep().length() == 8 && 
                (endereco.getLogradouro() == null || endereco.getLogradouro().isEmpty())) {
                
                System.out.println("Buscando dados do endereço via API ViaCEP...");
                ViaCepService viaCepService = new ViaCepService();
                Endereco enderecoViaCep = viaCepService.buscarEnderecoPorCep(endereco.getCep());

               
                endereco.setLogradouro(enderecoViaCep.getLogradouro());
                endereco.setBairro(enderecoViaCep.getBairro());
                endereco.setLocalidade(enderecoViaCep.getLocalidade());
                endereco.setUf(enderecoViaCep.getUf());
            }

    
            String sql = "INSERT INTO tbl_endereco (id_cliente, logradouro, numero, cep, bairro, cidade, estado) VALUES (?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement stmt = conexao.prepareStatement(sql, new String[]{"id_endereco"})) {
                stmt.setInt(1, idCliente);
                stmt.setString(2, endereco.getLogradouro());
                stmt.setString(3, endereco.getNumero());
                stmt.setString(4, endereco.getCep());
                stmt.setString(5, endereco.getBairro());
                stmt.setString(6, endereco.getLocalidade());
                stmt.setString(7, endereco.getUf());

                int rowsAffected = stmt.executeUpdate();
                if (rowsAffected > 0) {
                    ResultSet rs = stmt.getGeneratedKeys();
                    if (rs.next()) {
                        return rs.getInt(1);
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao inserir endereço: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Erro ao buscar endereço na API ViaCEP: " + e.getMessage());
        }
        return -1;
    }

  
    public Endereco buscarPorId(int idEndereco) {
        String sql = "SELECT * FROM tbl_endereco WHERE id_endereco = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, idEndereco);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
            	return new Endereco(
            		    rs.getInt("idCliente"),
            		    rs.getString("logradouro"),
            		    rs.getString("numero"),
            		    rs.getString("cep"),
            		    rs.getString("bairro"),
            		    rs.getString("localidade"),
            		    rs.getString("uf")
            		);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar endereço: " + e.getMessage());
        }
        return null;
    }

    
    public boolean atualizar(Endereco endereco, int idCliente) {
        String sql = "UPDATE tbl_endereco SET logradouro = ?, numero = ?, cep = ?, bairro = ?, cidade = ?, estado = ? WHERE id_cliente = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, endereco.getLogradouro());
            stmt.setString(2, endereco.getNumero());
            stmt.setString(3, endereco.getCep());
            stmt.setString(4, endereco.getBairro());
            stmt.setString(5, endereco.getLocalidade());
            stmt.setString(6, endereco.getUf());
            stmt.setInt(7, idCliente);

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar endereço no DAO: " + e.getMessage());
        }
        return false;
    }
    
    

    public boolean deletarEndereco(int idEndereco) {
        String sql = "DELETE FROM tbl_endereco WHERE id_endereco = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, idEndereco);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.out.println("Erro ao deletar endereço no DAO: " + e.getMessage());
        }
        return false;
    }
    
    
    
   
    public void fecharConexao() {
        try {
            if (conexao != null && !conexao.isClosed()) {
                conexao.close();
            }
        } catch (SQLException e) {
            System.out.println("Erro ao fechar a conexão: " + e.getMessage());
        }
    }
}
