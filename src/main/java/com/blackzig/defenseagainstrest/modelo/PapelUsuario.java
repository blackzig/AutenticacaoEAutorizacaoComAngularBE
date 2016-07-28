/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.blackzig.defenseagainstrest.modelo;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Michel
 */
@Entity
@Table(name = "papel_usuario")
public class PapelUsuario implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_papel_usuario;
    private String nome;

//    @ManyToMany(mappedBy = "papeis")
//    private List<Usuario> usuarios = new ArrayList<>();
//
//    public List<Usuario> getUsuarios() {
//        return usuarios;
//    }
//
//    public void setUsuarios(List<Usuario> usuarios) {
//        this.usuarios = usuarios;
//    }

    public Long getId_papel_usuario() {
        return id_papel_usuario;
    }

    public void setId_papel_usuario(Long id_papel_usuario) {
        this.id_papel_usuario = id_papel_usuario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

}
