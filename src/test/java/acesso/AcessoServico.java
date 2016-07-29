/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acesso;

import com.blackzig.defenseagainstrest.modelo.Acesso;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Michel
 */
public class AcessoServico {

    public static void main(String args[]) {
//        AcessoTeste acessoTeste = new AcessoTeste();
//        acessoTeste.vai();

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("DARUP");
        EntityManager em = emf.createEntityManager();
        Acesso a = new Acesso();
        String papel = "ADMIN";
        String chave = "U5IojwyB1k";
        try {
            em.getTransaction().begin();
            a = (Acesso) em.createQuery("select a from Acesso a where "
                    + "a.papel = :papel and a.chave = :chave")
                    .setParameter("papel", papel)
                    .setParameter("chave", chave)
                    .getSingleResult();
            em.flush();
            em.getTransaction().commit();
            System.out.println("acesso " + a);
        } catch (Exception e) {
            System.out.println("erro " + e.getMessage());
        }
    }

}
