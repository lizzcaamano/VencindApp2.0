package com.vecindapp.controller;

import com.vecindapp.entity.Rol;
import com.vecindapp.entity.Usuario;
import com.vecindapp.repository.dao.RolDAO;
import com.vecindapp.repository.dao.UsuarioDAO;
import com.vecindapp.utils.JwtResponse;
import com.vecindapp.utils.LoginRequest;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;


import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import static com.vecindapp.utils.Tools.*;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;



@RestController
public class LoginController {

    AuthenticationManager authManager;
    BCryptPasswordEncoder passwordEncoder;
    UsuarioDAO usuarioDAO;
    RolDAO rolDAO;


    public LoginController(AuthenticationManager authManager, BCryptPasswordEncoder passwordEncoder, UsuarioDAO usuarioDAO, RolDAO rolDAO) {
        super();
        this.authManager = authManager;
        this.passwordEncoder = passwordEncoder;
        this.usuarioDAO = usuarioDAO;
        this.rolDAO = rolDAO;
    }

    @PostMapping(value = "/login-cliente", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> loginCliente(@RequestBody LoginRequest loginRequest) {
        return login(loginRequest, "ROLE_CLIENTE");
    }

    @PostMapping(value = "/login-trabajador", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> loginTrabajador(@RequestBody LoginRequest loginRequest) {
        return login(loginRequest, "ROLE_TRABAJADOR");
    }

    @PostMapping(value = "/login-Admin", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> loginAdmin(@RequestBody LoginRequest loginRequest) {
        return login(loginRequest, "ROLE_ADMINISTRADOR");
    }

    @PostMapping(value = "login", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JwtResponse> login(@RequestBody LoginRequest loginRequest,  String expectedRole) {
        try {
            // Accede a los parámetros desde el objeto loginRequest
            String user = loginRequest.getUser();
            String pwd = loginRequest.getPwd();

            // Recuperar el usuario directamente desde el repositorio
            Usuario usuario = usuarioDAO.findByEmail(user);

            if(usuario == null){
                new BadCredentialsException("Usuario no encontrado");
            }

            // Validar manualmente la contraseña
            if (!passwordEncoder.matches(pwd, usuario.getPassword())) {
                throw new BadCredentialsException("Credenciales inválidas");
            }

            // Autenticar al usuario
            Authentication authentication = authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(user, pwd)
            );


            // Validar el rol esperado
            boolean hasExpectedRole = usuario.getUsuarioRols().stream()
                    .anyMatch(usuarioRol -> usuarioRol.getRole().getNombre().equals(expectedRole));

            if (!hasExpectedRole) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN)
                        .body(new JwtResponse("No tienes permisos para acceder con este rol.", 0, 0));
            }

            System.out.println("Usuario autenticado: " + authentication.getName());
            System.out.println("Autoridades: " + authentication.getAuthorities());


            // Obtener el clientId (puedes obtenerlo desde el usuario autenticado)
            Integer userId = getClientId(authentication.getName()); // Este es un ejemplo, debes implementar esta función

            System.out.println(userId);

            Integer rolId = getRolid(userId);
            System.out.println(rolId);

            // Generar el token
            String token = getToken(authentication, Long.valueOf(userId), rolId);

            // Retornar el token encapsulado en JwtResponse
            return ResponseEntity.ok(new JwtResponse(token, userId, rolId));
        } catch (AuthenticationException ex) {
            ex.printStackTrace();
            System.out.println("Error de autenticación: " + ex.getMessage());
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    private String getToken(Authentication authentication, Long userId, int rolId) {
        List<String> authorities = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        System.out.println("Autoridades: " + authorities);

        String token = Jwts.builder()
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setSubject(authentication.getName())
                .claim("authorities", authorities)
                .claim("User_id", userId)
                .claim("Role_id", rolId)
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24))
                .signWith(Keys.hmacShaKeyFor(CLAVE.getBytes()))
                .compact();
        return token;
    }

    private Integer getClientId(String email) {
        // Busca el usuario por nombre
        Usuario usuarios = usuarioDAO.findByEmail(email);

        // Verifica si se encontró algún usuario
        if (usuarios != null) {
            // Si hay un usuario, retorna su clientId
            return usuarios.getId(); // Aquí suponemos que el `id` es el `clientId`
        }
        // Si no se encontró el usuario, lanza una excepción o maneja el error
        throw new RuntimeException("Usuario no encontrado");
    }

    private Integer getRolid(Integer id){
        List<Integer> roles = rolDAO.findRolByUsuarioId(id);

        if (roles != null) {
            // Si hay un usuario, retorna su clientId
            return roles.get(0);
        }
        throw new RuntimeException("rol no encontrado");
    }
}