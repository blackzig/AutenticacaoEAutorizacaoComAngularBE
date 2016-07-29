/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.blackzig.defenseagainstrest.autenticacao;

import com.blackzig.defenseagainstrest.modelo.Acesso;
import java.io.IOException;
import java.util.Base64;
import java.util.StringTokenizer;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
//http://javapapers.com/web-service/restful-services-http-basic-authentication/

public class AuthenticationService {

    //chamar o DAO dá erro. Por que? não sei.
//    @Inject
//    AcessoDAO acessoDAO;
    public boolean authenticate(String authCredentials) {

        if (null == authCredentials) {
            return false;
        }
        // header value format will be "Basic encodedstring" for Basic
        // authentication. Example "Basic YWRtaW46YWRtaW4="
        final String encodedUserPassword = authCredentials.replaceFirst("Basic"
                + " ", "");
        System.out.println("encodedUserPassword " + encodedUserPassword);
        String usernameAndPassword = null;
        try {
            byte[] decodedBytes = Base64.getDecoder().decode(
                    encodedUserPassword);
            usernameAndPassword = new String(decodedBytes, "UTF-8");
        } catch (IOException e) {
            System.out.println("Erro authenticate " + e.getMessage());
        }
        final StringTokenizer tokenizer = new StringTokenizer(
                usernameAndPassword, ":");
        final String username = tokenizer.nextToken();
        final String password = tokenizer.nextToken();

        // we have fixed the userid and password as admin
        // call some UserService/LDAP here
        System.out.println("username " + username);
        System.out.println("password " + password);

        Boolean authenticationStatus = false;

        if (authenticationStatus = "admin".equals(username) && "admin".equals(password)) {
            //usuário está fazendo login no sistema
            System.out.println("logando");
            authenticationStatus = true;
        } else {
            //usuário está acessando serviços do sistema
            authenticationStatus = autorizandoAcesso(password, username);
            System.out.println("authenticationStatus " + authenticationStatus);
//            Acesso acesso = new Acesso();
//            acesso = acessoDAO.autorizarAcao(username, password);
//            System.out.println("acesso " + acesso);
//            if(acesso!=null){
//                authenticationStatus = true;
//            }else{
//                authenticationStatus = false;
//            }
//            System.out.println("authenticationStatus " + authenticationStatus);
        }

//        authenticationStatus = "admin".equals(username)
//                && "admin".equals(password);
        return authenticationStatus;
    }

    public Boolean autorizandoAcesso(String papel, String chave) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("DARUP");
        EntityManager em = emf.createEntityManager();
        Acesso a = new Acesso();
      //  String papel = "ADMIN";
       // String chave = "U5IojwyB1k";
        try {
            em.getTransaction().begin();
            a = (Acesso) em.createQuery("select a from Acesso a where "
                    + "a.papel = :papel and a.chave = :chave")
                    .setParameter("papel", papel)
                    .setParameter("chave", chave)
                    .getSingleResult();
            em.getTransaction().commit();
            System.out.println("acesso " + a);
            
            return a!=null;
            
        } catch (Exception e) {
            System.out.println("erro " + e.getMessage());
        }
        return false;
    }

}
