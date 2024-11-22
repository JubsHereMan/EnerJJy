package br.com.fiap.resources;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.fiap.BO.ClienteBO;
import br.com.fiap.beans.Cliente;
import br.com.fiap.model.CadastroCompletoRequest;

@Path("/cliente")
public class ClienteResource {

    private ClienteBO clienteBO;

  
    public ClienteResource() {
        try {
            this.clienteBO = new ClienteBO();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao inicializar ClienteBO", e);
        }
    }

    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<Cliente> selecionarClientes() throws ClassNotFoundException, SQLException {
        return clienteBO.selecionarBo();
    }

   
    @POST
    @Path("/cadastroCompleto")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response cadastrarCompleto(CadastroCompletoRequest request) {
        try {
            // Cadastro completo usando o ClienteBO
            boolean sucesso = clienteBO.cadastrarCompleto(request);

            if (sucesso) {
                return Response.status(Response.Status.CREATED).entity("Cadastro completo realizado com sucesso").build();
            } else {
                return Response.status(Response.Status.BAD_REQUEST).entity("Erro ao realizar o cadastro completo").build();
            }

        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro interno: " + e.getMessage()).build();
        }
    }

    // Endpoint para atualizar um cliente
    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response atualizarCliente(Cliente cliente, @PathParam("id") int id) throws ClassNotFoundException, SQLException {
        // Define o ID do cliente para atualização
        cliente.setId(id);
        clienteBO.atualizarBo(cliente);
        return Response.ok("Cliente atualizado com sucesso!").build();
    }


    // Endpoint para deletar um cliente
    @DELETE
    @Path("/{id}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response deletarCliente(@PathParam("id") int id) throws ClassNotFoundException, SQLException {
        try {
            clienteBO.deletarBo(id);
            return Response.ok("Cliente deletado com sucesso!").build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                           .entity("Erro ao deletar cliente: " + e.getMessage())
                           .build();
        }
    }
}
