/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.blackzig.defenseagainstrest.seguranca;

import java.net.MalformedURLException;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.io.*;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

public class HttpsClient {

    public static void main(String[] args) {
        new HttpsClient().testIt();
    }

    private TrustManager[] get_trust_mgr() {
        TrustManager[] certs = new TrustManager[]{
            new X509TrustManager() {
                @Override
                public X509Certificate[] getAcceptedIssuers() {
                    return null;
                }

                @Override
                public void checkClientTrusted(X509Certificate[] certs, String t) {
                }

                @Override
                public void checkServerTrusted(X509Certificate[] certs, String t) {
                }
            }
        };
        return certs;
    }

    private void testIt() {
        String https_url = "https://localhost:8181/DefenseAgainstREST/pedido";
        URL url;
        try {

            // Create a context that doesn't check certificates.
            SSLContext ssl_ctx = SSLContext.getInstance("TLS");
            TrustManager[] trust_mgr = get_trust_mgr();
            ssl_ctx.init(null, // key manager
                    trust_mgr, // trust manager
                    new SecureRandom()); // random number generator
            HttpsURLConnection.setDefaultSSLSocketFactory(ssl_ctx.getSocketFactory());

            url = new URL(https_url);
            HttpsURLConnection con = (HttpsURLConnection) url.openConnection();

            // Guard against "bad hostname" errors during handshake.
            con.setHostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String host, SSLSession sess) {
                    return host.equals("localhost");
                }
            });

            //dumpl all cert info
            print_https_cert(con);

            //dump all the content
            print_content(con);

        } catch (MalformedURLException e) {
            System.out.println("MalformedURLException " + e.getMessage());
        } catch (IOException | NoSuchAlgorithmException | KeyManagementException e) {
            System.out.println("várias exceções " + e.getMessage());
        }
    }

    private void print_https_cert(HttpsURLConnection con) {
        if (con != null) {

            try {

                System.out.println("Response Code : " + con.getResponseCode());
                System.out.println("Cipher Suite : " + con.getCipherSuite());
                System.out.println("\n");

                Certificate[] certs = con.getServerCertificates();
                for (Certificate cert : certs) {
                    System.out.println("Cert Type : " + cert.getType());
                    System.out.println("Cert Hash Code : " + cert.hashCode());
                    System.out.println("Cert Public Key Algorithm : " + cert.getPublicKey().getAlgorithm());
                    System.out.println("Cert Public Key Format : " + cert.getPublicKey().getFormat());
                    System.out.println("\n");
                }

            } catch (SSLPeerUnverifiedException e) {
                System.out.println("SSLPeerUnverifiedException " + e.getMessage());
            } catch (IOException e) {
                System.out.println("IOException " + e.getMessage());
            }
        }
    }

    private void print_content(HttpsURLConnection con) {
        if (con != null) {

            try {

                System.out.println("****** Content of the URL ********");

                try (BufferedReader br = new BufferedReader(
                        new InputStreamReader(con.getInputStream()))) {
                    String input;

                    while ((input = br.readLine()) != null) {
                        System.out.println(input);
                    }
                }

            } catch (IOException e) {
                System.out.println("IOException " + e.getMessage());
            }
        }
    }

}
