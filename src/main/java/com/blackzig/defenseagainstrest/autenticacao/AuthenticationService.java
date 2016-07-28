/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.blackzig.defenseagainstrest.autenticacao;

import com.blackzig.defenseagainstrest.DAO.AcessoDAO;
import java.io.IOException;
import java.util.Base64;
import java.util.StringTokenizer;
import javax.inject.Inject;
//http://javapapers.com/web-service/restful-services-http-basic-authentication/

public class AuthenticationService {
    
    @Inject
    AcessoDAO acessoDAO;

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
        
        if(authenticationStatus = "admin".equals(username)&& "admin".equals(password)){
            //usuário está fazendo login no sistema
        }else{
            //usuário está acessando serviços do sistema
            
        }
            

//        authenticationStatus = "admin".equals(username)
//                && "admin".equals(password);

        return authenticationStatus;
    }
}
