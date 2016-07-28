/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.blackzig.defenseagainstrest.servico;

import com.blackzig.defenseagainstrest.DAO.AcessoDAO;
import com.blackzig.defenseagainstrest.DO.DadosUsuario;
import com.blackzig.defenseagainstrest.modelo.Acesso;
import com.blackzig.defenseagainstrest.modelo.Usuario;
import javax.inject.Inject;

/**
 *
 * @author Michel
 */
public class UsuarioServico {
    
    @Inject
    AcessoDAO acessoDAO;

    public DadosUsuario prepararDadosDoUsuario(Usuario u) {
        DadosUsuario dadosUsuario = new DadosUsuario();
        dadosUsuario.setPapel(u.getPapeis().get(0).getNome());
        
        String chave  = gerarChave();
        
        dadosUsuario.setChave(chave);
        dadosUsuario.setEmail(u.getEmail());
        
        gravarChaveDeAcesso(dadosUsuario);
        
        return dadosUsuario;
    }
    
    private void gravarChaveDeAcesso(DadosUsuario dadosUsuario){
        Acesso acesso = new Acesso();
        acesso.setChave(dadosUsuario.getChave());
        acesso.setPapel(dadosUsuario.getPapel());
        acessoDAO.salvar(acesso);
    }

    public String gerarChave() {

        String chave = "";
        try {
            String[] carct = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
            for (int i = 0; i < 1; i++) {
                for (int x = 0; x < 10; x++) {
                    int j = (int) (Math.random() * carct.length);
                    chave += carct[j];
                }
            }
        } catch (Exception e) {
            System.out.println("Erro chave " + e.getMessage());
        }
        return chave;
    }

}
