/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.blackzig.defenseagainstrest.DAO;

import com.blackzig.defenseagainstrest.modelo.Usuario;
import javax.inject.Inject;
import javax.persistence.EntityManager;

/**
 *
 * @author Michel
 */
public class UsuarioDAO {

    @Inject
    private EntityManager em;

    public Usuario entrarNoSistema(String email, String senha) {
        return (Usuario) em.createQuery("SELECT u from Usuario u where u.email = :email "
                + "and u.senha = :senha")
                .setParameter("email", email)
                .setParameter("senha", senha)
                .getSingleResult();
    }

}
