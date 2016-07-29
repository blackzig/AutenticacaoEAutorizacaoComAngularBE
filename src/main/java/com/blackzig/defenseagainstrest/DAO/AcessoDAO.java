/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.blackzig.defenseagainstrest.DAO;

import com.blackzig.defenseagainstrest.jpa.Transactional;
import com.blackzig.defenseagainstrest.modelo.Acesso;
import javax.inject.Inject;
import javax.persistence.EntityManager;

/**
 *
 * @author Michel
 */
public class AcessoDAO {

    @Inject
    private EntityManager em;

    @Transactional
    public Acesso salvar(Acesso acesso) {
        em.merge(acesso);
        return acesso;
    }
    
    public Acesso autorizarAcao(String papel, String chave) {
        return (Acesso) em.createQuery("select a from Acesso a where "
                + "a.papel = :papel and a.chave = :chave")
                .setParameter("papel", papel)
                .setParameter("chave", chave)
                .getSingleResult();
    }    

}
