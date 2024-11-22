package br.com.fiap.BO;

import java.sql.SQLException;
import br.com.fiap.DAO.EnderecoDAO;
import br.com.fiap.model.Endereco;
import br.com.fiap.services.ViaCepService;

public class EnderecoBO {

    private EnderecoDAO enderecoDAO;

    public EnderecoBO() {
        this.enderecoDAO = new EnderecoDAO();
    }

    // Método para atualizar o endereço com integração à API ViaCEP
    public boolean atualizarEndereco(Endereco endereco, int idCliente) throws SQLException {
        // Validação básica
        if (idCliente <= 0 || endereco == null || endereco.getCep() == null || endereco.getCep().length() != 8) {
            System.out.println("Dados inválidos para atualização de endereço");
            return false;
        }

        // Consultar API ViaCEP se o logradouro estiver vazio
        if (endereco.getLogradouro() == null || endereco.getLogradouro().isEmpty()) {
            System.out.println("Buscando dados do endereço via API ViaCEP...");
            ViaCepService viaCepService = new ViaCepService();
            try {
                Endereco enderecoViaCep = viaCepService.buscarEnderecoPorCep(endereco.getCep());

                // Preencher os dados do endereço com a resposta da API
                endereco.setLogradouro(enderecoViaCep.getLogradouro());
                endereco.setBairro(enderecoViaCep.getBairro());
                endereco.setLocalidade(enderecoViaCep.getLocalidade());
                endereco.setUf(enderecoViaCep.getUf());
            } catch (Exception e) {
                System.out.println("Erro ao buscar endereço na API ViaCEP: " + e.getMessage());
                return false;
            }
        }

        // Chamar o DAO para atualizar o endereço
        return enderecoDAO.atualizar(endereco, idCliente);
    }
}
