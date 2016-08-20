/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.blackzig.defenseagainstrest.recurso;

import com.blackzig.defenseagainstrest.DAO.ProdutoDAO;
import com.blackzig.defenseagainstrest.modelo.Pedido;
import com.blackzig.defenseagainstrest.modelo.Produto;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;
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
@Path("produto")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ProdutoRecurso {

    @Inject
    ProdutoDAO produtoDAO;

    @GET
    public List<Produto> buscarTudo() {
        List<Produto> lista = new ArrayList<>();
        lista = produtoDAO.buscarTodos();
        return lista;
    }

    @POST
    public Response salvarProduto(String conteudo) {
        System.out.println("conteudo " + conteudo);
        Produto produto = (Produto) new Gson().fromJson(conteudo, Produto.class);

        try {

            produto = produtoDAO.salvar(produto);
            return Response.ok(produto).status(200).build();

        } catch (Exception ex) {
            System.out.println("Erro salvarProduto " + ex.getMessage());
        }

        return null;
    }

}
