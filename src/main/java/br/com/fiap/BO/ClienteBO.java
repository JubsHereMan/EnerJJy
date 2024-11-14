package br.com.fiap.BO;

import java.sql.SQLException;
import java.util.ArrayList;

import br.com.fiap.DAO.ClienteDAO;
import br.com.fiap.DAO.EnderecoDAO;
import br.com.fiap.DAO.LoginDAO;
import br.com.fiap.beans.Cliente;
import br.com.fiap.beans.Login;
import br.com.fiap.model.CadastroCompletoRequest;
import br.com.fiap.model.Endereco;
import br.com.fiap.services.ViaCepService;

public class ClienteBO {

    private ClienteDAO clienteDAO;
    private LoginDAO loginDAO;
    private EnderecoDAO enderecoDAO;

    // Construtor que inicializa os DAOs
    public ClienteBO() throws ClassNotFoundException, SQLException {
        this.clienteDAO = new ClienteDAO();
        this.loginDAO = new LoginDAO();
        this.enderecoDAO = new EnderecoDAO();
    }

    // Método para selecionar todos os clientes
    public ArrayList<Cliente> selecionarBo() throws ClassNotFoundException, SQLException {
        return (ArrayList<Cliente>) clienteDAO.listarTodos();
    }

    // Método para cadastro completo do cliente, login e endereço
    public boolean cadastrarCompleto(CadastroCompletoRequest request) throws SQLException {
        try {
            // Cadastro do cliente
            Cliente cliente = request.getCliente();
            int idCliente = clienteDAO.inserirCliente(cliente);
            if (idCliente <= 0) {
                System.out.println("Erro ao cadastrar cliente.");
                return false;
            }

            // Cadastro do login
            Login login = request.getLogin();
            boolean loginInserido = loginDAO.inserirLogin(idCliente, login);
            if (!loginInserido) {
                System.out.println("Erro ao cadastrar login.");
                return false;
            }

            // Cadastro do endereço
            Endereco endereco = request.getEndereco();
            endereco.setIdCliente(idCliente);

            // Verificar se o logradouro está vazio e buscar na API ViaCEP
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

            // Inserir o endereço no banco de dados
            int idEndereco = enderecoDAO.inserir(endereco, idCliente);
            if (idEndereco <= 0) {
                System.out.println("Erro ao cadastrar endereço.");
                return false;
            }

            return true;

        } catch (Exception e) {
            e.printStackTrace();
            throw new SQLException("Erro ao cadastrar completo: " + e.getMessage());
        }
    }

    // Método para cadastrar apenas o cliente e o login
    public int cadastrarCliente(Cliente cliente, Login login) throws SQLException {
        int idCliente = clienteDAO.inserirCliente(cliente);
        if (idCliente > 0) {
            boolean loginInserido = loginDAO.inserirLogin(idCliente, login);
            if (loginInserido) {
                return idCliente;
            }
        }
        return -1;
    }

    // Método para atualizar um cliente
    public void atualizarBo(Cliente cliente) throws ClassNotFoundException, SQLException {
        clienteDAO.atualizar(cliente);
    }

    // Método para deletar um cliente
    public void deletarBo(int idCliente) throws ClassNotFoundException, SQLException {
        clienteDAO.deletar(idCliente);
    }
}
