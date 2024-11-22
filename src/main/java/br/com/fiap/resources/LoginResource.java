package br.com.fiap.resources;

import java.sql.SQLException;

import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.fiap.BO.LoginBO;
import br.com.fiap.beans.Login;

@Path("/login")
public class LoginResource {

    private LoginBO loginBO;

    public LoginResource() throws ClassNotFoundException, SQLException {
        this.loginBO = new LoginBO();
    }

    @PUT
    @Path("/alterar/{idCliente}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response alterarLogin(@PathParam("idCliente") int idCliente, Login login) {
        System.out.println("Método alterarLogin chamado");
        System.out.println("ID Cliente: " + idCliente);
        System.out.println("Novo Email: " + login.getEmail());
        System.out.println("Nova Senha: " + login.getSenha());

        try {
            boolean atualizado = loginBO.atualizarLogin(idCliente, login.getEmail(), login.getSenha());

            if (atualizado) {
                return Response.status(Response.Status.OK)
                        .entity("Login atualizado com sucesso.")
                        .build();
            } else {
                return Response.status(Response.Status.BAD_REQUEST).entity("Erro ao atualizar login").build();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro interno ao atualizar login: " + e.getMessage())
                    .build();
        }
    }

}
