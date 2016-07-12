/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.blackzig.defenseagainstrest.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

/**
 *
 * @author Michel
 */
@Entity
public class Pedido implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_pedido;

    private String member_id;
    private Long order_number;
    private String gift_message;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "pedido_produto", joinColumns = {
        @JoinColumn(name = "id_pedido", referencedColumnName = "id_pedido")}, inverseJoinColumns = {
        @JoinColumn(name = "id_produto", referencedColumnName = "id_produto")})
    List<Produto> produtos = new ArrayList<>();

    public Long getId_pedido() {
        return id_pedido;
    }

    public void setId_pedido(Long id_pedido) {
        this.id_pedido = id_pedido;
    }

    public String getMember_id() {
        return member_id;
    }

    public void setMember_id(String member_id) {
        this.member_id = member_id;
    }

    public Long getOrder_number() {
        return order_number;
    }

    public void setOrder_number(Long order_number) {
        this.order_number = order_number;
    }

    public String getGift_message() {
        return gift_message;
    }

    public void setGift_message(String gift_message) {
        this.gift_message = gift_message;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }

}
