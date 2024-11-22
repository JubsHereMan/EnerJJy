package br.com.fiap.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.fiap.BO.EnderecoBO;
import br.com.fiap.model.Endereco;

@Path("/endereco")
public class EnderecoResource {

    private EnderecoBO enderecoBO;

    public EnderecoResource() {
        this.enderecoBO = new EnderecoBO();
    }

    @PUT
    @Path("/atualizar/{idCliente}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response atualizarEndereco(@PathParam("idCliente") int idCliente, Endereco endereco) {
        System.out.println("Método atualizarEndereco chamado");
        System.out.println("ID Cliente: " + idCliente);
        System.out.println("CEP: " + endereco.getCep());
        System.out.println("Número: " + endereco.getNumero());
        try {      
            boolean atualizado = enderecoBO.atualizarEndereco(endereco, idCliente);

            if (atualizado) {
                return Response.status(Response.Status.OK)
                        .entity("Endereço atualizado com sucesso.")
                        .build();
            } else {
                return Response.status(Response.Status.BAD_REQUEST).entity("Erro ao atualizar endereço").build();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro interno ao atualizar endereço: " + e.getMessage())
                    .build();
        }
    }
}