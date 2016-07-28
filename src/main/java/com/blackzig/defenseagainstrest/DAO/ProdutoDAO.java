/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.blackzig.defenseagainstrest.DAO;

import com.blackzig.defenseagainstrest.modelo.Produto;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;

/**
 *
 * @author Michel
 */
public class ProdutoDAO {

    @Inject
    private EntityManager em;

    public List<Produto> buscarTodos() {
        return em.createQuery("SELECT p FROM Produto p").getResultList();
    }
}
