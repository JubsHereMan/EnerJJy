package br.com.fiap.BO;

import java.sql.SQLException;
import java.util.ArrayList;

import br.com.fiap.DAO.ClienteDAO;
import br.com.fiap.DAO.LoginDAO;
import br.com.fiap.beans.Cliente;
import br.com.fiap.beans.Login;

public class ClienteBO {
    
    private ClienteDAO clienteDAO;

    public ClienteBO() throws ClassNotFoundException, SQLException {
        clienteDAO = new ClienteDAO();
    }

    // Selecionar todos os clientes
    public ArrayList<Cliente> selecionarBo() throws ClassNotFoundException, SQLException {
        // Regra de negócios (se houver)
        return (ArrayList<Cliente>) clienteDAO.listarTodos();
    }
    
    public int cadastrarCliente(Cliente cliente, Login login) throws SQLException, ClassNotFoundException {
        ClienteDAO clienteDAO = new ClienteDAO();
        LoginDAO loginDAO = new LoginDAO();

        int idCliente = clienteDAO.inserirCliente(cliente);
        if (idCliente > 0) {
            if (loginDAO.inserirLogin(idCliente, login)) {
                return idCliente;
            }
        }
        return -1;
    }

    
    // Atualizar um cliente
    public void atualizarBo(Cliente cliente) throws ClassNotFoundException, SQLException {
        // Regra de negócios (se houver)
        clienteDAO.atualizar(cliente);
    }
    
    // Deletar um cliente
    public void deletarBo(int idCliente) throws ClassNotFoundException, SQLException {
        // Regra de negócios (se houver)
        clienteDAO.deletar(idCliente);
    }
}
