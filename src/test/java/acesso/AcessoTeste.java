/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acesso;

import com.blackzig.defenseagainstrest.DAO.AcessoDAO;
import com.blackzig.defenseagainstrest.modelo.Acesso;
import javax.inject.Inject;

/**
 *
 * @author Michel
 */
public class AcessoTeste {
    
    @Inject
    private AcessoDAO acessoDAO;

    public void vai() {
        try {
            Acesso acesso = new Acesso();
            String papel = "ADMIN";
            String chave = "U5IojwyB1k";
            
            acesso.setChave(chave);
            acesso.setPapel(papel);
            
            acesso = acessoDAO.autorizarAcao(papel, chave);
        } catch (Exception e) {
            System.out.println("erro " + e.getMessage());
        }

    }

}
