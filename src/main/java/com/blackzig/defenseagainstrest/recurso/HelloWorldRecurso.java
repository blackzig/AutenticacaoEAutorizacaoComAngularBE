/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.blackzig.defenseagainstrest.recurso;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;

/**
 *
 * @author Michel
 */
@Path("/helloworld")
public class HelloWorldRecurso {

    @GET
    @Produces("text/plain")
    public String hello(@Context HttpServletRequest req) {

        HttpSession session = req.getSession(true);
        Object foo = session.getAttribute("foo");
        if (foo != null) {
            System.out.println(foo.toString());
        } else {
            foo = "bar";
            session.setAttribute("foo", "bar");
        }
        return foo.toString();

    }

}
