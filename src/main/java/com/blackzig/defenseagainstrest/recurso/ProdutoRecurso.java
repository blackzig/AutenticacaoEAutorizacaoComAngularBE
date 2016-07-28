/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.blackzig.defenseagainstrest.recurso;

import com.blackzig.defenseagainstrest.DAO.ProdutoDAO;
import com.blackzig.defenseagainstrest.modelo.Produto;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

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

}
