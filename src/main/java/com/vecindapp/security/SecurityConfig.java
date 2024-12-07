package com.vecindapp.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.util.List;
import java.util.Map;

@EnableWebSecurity
@Configuration
public class SecurityConfig {
    AuthenticationManager auth;

    //bean Convierte el método en un objeto
    @Bean
    public AuthenticationManager authManager(AuthenticationConfiguration conf) {
        try {
            auth= conf.getAuthenticationManager();
        } catch (Exception e) {
            System.out.println(""+e.getMessage());
            e.printStackTrace();
        }
        return auth;
    }



    @Bean
    public SecurityFilterChain FilterChain(HttpSecurity http) throws Exception {

        http.csrf(cus->cus.disable())
                .authorizeHttpRequests(aut->
                        aut
                                //.requestMatchers(HttpMethod.POST,"/trabajador/add").hasAnyRole("ADMINISTRADOR", "CLIENTE")
                                //.requestMatchers(HttpMethod.GET,"/trabajador/all").hasRole("ADMINISTRADOR")
                                //.requestMatchers(HttpMethod.GET,"/**").authenticated()
                                .requestMatchers(HttpMethod.POST, "/login").permitAll()// Permite el acceso al login sin autenticación
                                .requestMatchers("/swagger-ui/**", "/v3/api-docs/**", "vecindapi-docs/**", "/swagger-ui.html", "/swagger-resources/*", "/vecindapi-docs", "/swagger-ui-vecindapi.html", "/swagger-ui/index.html").permitAll()
                                .requestMatchers(HttpMethod.POST, "/**").authenticated() // Protege todas las demás rutas POST
                                .requestMatchers(HttpMethod.GET, "/**").authenticated()  // Protege todas las rutas GET
                                .anyRequest().permitAll()
                )
                .addFilter(new AuthorizationFilterJWT(auth));

        return http.build();

    }


    @Bean
    public JdbcUserDetailsManager usersDetailsJdbc() {
        DriverManagerDataSource ds=new DriverManagerDataSource();
        ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
        ds.setUrl("jdbc:mysql://localhost:3306/vecindapp");
        ds.setUsername("root");
        ds.setPassword("");

        JdbcUserDetailsManager jdbcDetails=new JdbcUserDetailsManager(ds);

        jdbcDetails.setUsersByUsernameQuery(
                "SELECT u.email, u.password, \n" +
                        "       CASE \n" +
                        "           WHEN u.estado = 'activo' THEN TRUE\n" +
                        "           ELSE FALSE\n" +
                        "       END AS estado\n" +
                        "FROM Usuarios u \n" +
                        "WHERE u.email = ? AND u.estado = 'activo'"
        );

        jdbcDetails.setAuthoritiesByUsernameQuery(
                "SELECT u.email, r.nombre AS rol " +
                        "FROM Usuarios u " +
                        "INNER JOIN Usuarios_Roles ur ON u.user_id = ur.user_id " +
                        "INNER JOIN Roles r ON ur.role_id = r.role_id " +
                        "WHERE u.email = ?"
        );



        printUsers(ds);
        return jdbcDetails;
    }

    // Método para imprimir usuarios y roles
    private void printUsers(DriverManagerDataSource ds) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(ds);

        // Imprimir usuarios
        /*List<String> users = jdbcTemplate.query("SELECT use_user FROM users", (rs, rowNum) -> rs.getString("use_user"));
        System.out.println("Usuarios en la base de datos: " + users);

        // Imprimir roles (asumiendo que deseas imprimir todos los roles)
        List<String> roles = jdbcTemplate.query("SELECT rol FROM roles", (rs, rowNum) -> rs.getString("rol"));
        System.out.println("Roles en la base de datos: " + roles);*/
    }

}
