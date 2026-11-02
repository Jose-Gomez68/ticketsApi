package com.ticket.code.tickets.security;

import io.jsonwebtoken.Jwts;

import java.util.Base64;

public class GenerateJwtSecret {

    public static void main(String[] args) {

       /* GENERA AUTOMATICAMENTE LA CLAVE SECRETA
        String secret = Base64.getEncoder().encodeToString(
                Jwts.SIG.HS256.key().build().getEncoded()
        );

        System.out.println("JWT SECRET:");
        System.out.println(secret);*/

        //YO ELEGI LA CLAVE SECRETA
        String mySecret = "jqwdpjpjoqwd";

        String secretBase64 = Base64.getEncoder()
                .encodeToString(mySecret.getBytes());

        System.out.println(secretBase64);
    }

}
