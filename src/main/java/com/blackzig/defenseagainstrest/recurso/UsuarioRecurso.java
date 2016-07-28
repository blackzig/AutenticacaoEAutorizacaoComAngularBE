/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.blackzig.defenseagainstrest.recurso;

import com.blackzig.defenseagainstrest.DAO.UsuarioDAO;
import com.blackzig.defenseagainstrest.DO.DadosUsuario;
import com.blackzig.defenseagainstrest.modelo.Usuario;
import com.blackzig.defenseagainstrest.servico.UsuarioServico;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Michel
 */
@Path("user")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UsuarioRecurso {

    @Inject
    private UsuarioDAO usuarioDAO;

    @Inject
    private UsuarioServico usuarioServico;

    @GET
    public String teste() {
        return "User OK";
    }

    @POST
    @Produces("application/json")
    @Consumes("application/x-www-form-urlencoded")
    public Response authenticateUser(@FormParam("username") String username,
            @FormParam("password") String password) {

        try {

            System.out.println("username " + username);
            System.out.println("password " + password);

            Usuario u = new Usuario();
            u = usuarioDAO.entrarNoSistema(username, password);

            DadosUsuario dadosUsuario = new DadosUsuario();
            dadosUsuario = usuarioServico.prepararDadosDoUsuario(u);
            
//            // Authenticate the user using the credentials provided
//            authenticate(username, password);
//
//            // Issue a token for the user
//            String token = issueToken(username);
//
//            // Return the token on the response
            return Response.ok(dadosUsuario).build();

        } catch (Exception e) {
            System.out.println("erro " + e.getMessage());
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
    }

}
