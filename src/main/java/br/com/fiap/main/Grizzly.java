
package br.com.fiap.main;

import java.net.URI;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import br.com.fiap.resources.ClienteResource;
import br.com.fiap.resources.EnderecoResource;
import br.com.fiap.resources.LoginResource;

public class Grizzly {
    public static void main(String[] args) {
        URI baseUri = URI.create("http://localhost:8080/ProjetoGs/");

      
        ResourceConfig config = new ResourceConfig()
        	    .register(ClienteResource.class)
        	    .register(EnderecoResource.class)
        	    .register(LoginResource.class);

        HttpServer server = GrizzlyHttpServerFactory.createHttpServer(baseUri, config);
        System.out.println("Servidor Grizzly iniciado em " + baseUri);

        try {
            server.start();
            System.in.read(); 
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
