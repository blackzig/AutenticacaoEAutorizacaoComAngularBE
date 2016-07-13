/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.blackzig.defenseagainstrest.recurso;

import com.blackzig.defenseagainstrest.DAO.PedidoDAO;
import com.blackzig.defenseagainstrest.modelo.Pedido;
import com.google.gson.Gson;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
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
@Path("pedido")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PedidoRecurso {

    @Inject
    private PedidoDAO pedidoDAO;

    @POST
    public Response salvarPedido(String conteudo) {
        System.out.println("conteudo " + conteudo);
        Pedido pedidos = (Pedido) new Gson().fromJson(conteudo, Pedido.class);
        
        try {

            Pedido pedido = pedidoDAO.salvar(pedidos);
            return Response.ok(pedido).status(200).build();

        } catch (Exception ex) {
            System.out.println("Erro salvarPedido " + ex.getMessage());
        }

        return null;
    }
    
    @GET
    public String teste(){
        return "OK";
    }

}
