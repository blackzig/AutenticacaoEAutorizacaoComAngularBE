/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.blackzig.defenseagainstrest.DAO;

import com.blackzig.defenseagainstrest.jpa.Transactional;
import com.blackzig.defenseagainstrest.modelo.Pedido;
import com.blackzig.defenseagainstrest.recurso.PedidoRecurso;
import javax.inject.Inject;
import javax.persistence.EntityManager;

/**
 *
 * @author Michel
 */
public class PedidoDAO {
    @Inject
    private EntityManager em;
    
    @Transactional
    public Pedido salvar(Pedido pedido) {
        em.merge(pedido);
        return pedido;
    }    
}
