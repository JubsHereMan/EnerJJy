package br.com.fiap.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import br.com.fiap.DAO.EnderecoDAO;
import br.com.fiap.model.Endereco;

@Path("/endereco")
public class EnderecoResource {

    private EnderecoDAO enderecoDAO;

    // Construtor para inicializar o DAO
    public EnderecoResource() {
        try {
            this.enderecoDAO = new EnderecoDAO();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao inicializar EnderecoDAO", e);
        }
    }

    @POST
    @Path("/cadastro/{idCliente}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response cadastrarEndereco(@PathParam("idCliente") int idCliente, Endereco endereco) {
        System.out.println("Método cadastrarEndereco chamado");
        System.out.println("ID Cliente: " + idCliente);
        System.out.println("CEP: " + endereco.getCep());
        System.out.println("Número: " + endereco.getNumero());
        try {
            // Validação do ID do cliente
            if (idCliente <= 0) {
                return Response.status(Response.Status.BAD_REQUEST).entity("ID do cliente inválido").build();
            }

            // Inserir o endereço vinculado ao ID do cliente
            int idEndereco = enderecoDAO.inserir(endereco, idCliente);
            if (idEndereco > 0) {
                return Response.status(Response.Status.CREATED)
                        .entity("Endereço cadastrado com sucesso. ID do Endereço: " + idEndereco)
                        .build();
            } else {
                return Response.status(Response.Status.BAD_REQUEST).entity("Erro ao cadastrar endereço").build();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro interno ao cadastrar endereço: " + e.getMessage())
                    .build();
        }
    }
}
