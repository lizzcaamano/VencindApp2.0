package com.vecindapp.controller;

import com.vecindapp.utils.JwtResponse;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static com.vecindapp.utils.Tools.*;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*")
@RestController("login")
public class LoginController {
    AuthenticationManager authManager;

    public LoginController(AuthenticationManager authManager) {
        super();
        this.authManager = authManager;
    }
    @PostMapping(value = "login", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JwtResponse> login(@RequestParam("user") String user,
                                             @RequestParam("pwd") String pwd) {
        try {
            // Autenticar al usuario
            Authentication authentication = authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(user, pwd)
            );

            System.out.println("Usuario autenticado: " + authentication.getName());
            System.out.println("Autoridades: " + authentication.getAuthorities());

            // Generar el token
            String token = getToken(authentication);

            // Retornar el token encapsulado en JwtResponse
            return ResponseEntity.ok(new JwtResponse(token));
        } catch (AuthenticationException ex) {
            ex.printStackTrace();
            System.out.println("Error de autenticación: " + ex.getMessage());
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    private String getToken(Authentication authentication) {
        List<String> authorities = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        System.out.println("Autoridades: " + authorities);

        String token = Jwts.builder()
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setSubject(authentication.getName())
                .claim("authorities", authorities)
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24))
                .signWith(Keys.hmacShaKeyFor(CLAVE.getBytes()))
                .compact();
        return token;
    }
}
