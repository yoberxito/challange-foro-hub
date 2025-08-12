package yccweb.com.infra.security;/*
 * Copyright (c) 2025 yober cieza coronel. Todos los derechos reservados.
 *
 * Este archivo es parte de foro-hub.
 *
 * foro-hub es software propietario: no puedes redistribuirlo y/o modificarlo sin el
 * permiso expreso del propietario. Está sujeto a los términos y condiciones
 * que acompañan el uso del software.
 *
 * Cualquier uso no autorizado puede ser sancionado según la ley vigente.
 */

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import yccweb.com.domain.ususario.Usuario;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;

@Service
public class TokenService {
    @Value("${api.security.token.secret}")
    private String secret;

    public String generarToken(Usuario usuario) {
        try {

            var algoritmo  = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withIssuer("Api-Foro-Hub")
                    .withSubject(usuario.getUser())
                    .withExpiresAt(fechaExpiracion())
                    .sign(algoritmo);
        } catch (
                JWTCreationException exception) {
            throw new RuntimeException("error al generar el token JWT", exception);
        }
    }

    private Instant fechaExpiracion() {
        return LocalDateTime.now(ZoneId.of("America/Lima"))
                .plusHours(2)
                .toInstant(ZoneOffset.of("-05:00"));
    }

    public String getSubject(String tokenJWT) {
        try {
            var algoritmo = Algorithm.HMAC256(secret);
            return JWT.require(algoritmo)
                    .withIssuer("Api-Foro-Hub")
                    .build()
                    .verify(tokenJWT)
                    .getSubject();
        } catch (JWTVerificationException exception){
            System.out.println(exception.getMessage());
            throw new RuntimeException("Token JWT invalido o expirado!");
        }
    }

}
