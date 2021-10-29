package com.figueiras.photocontest.backend.rest.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private JwtGenerator jwtGenerator;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.cors().and().csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .addFilter(new JwtFilter(authenticationManager(), jwtGenerator))
                .authorizeRequests()
                .antMatchers("/catalogo-usuarios/usuarios").permitAll()
                .antMatchers("/catalogo-usuarios/usuarios/*").permitAll()
                .antMatchers("/catalogo-usuarios/registrarse").permitAll()
                .antMatchers("/catalogo-usuarios/iniciar-sesion").permitAll()
                .antMatchers("/catalogo-usuarios/usuarios/*/cambio-contrasena").permitAll()
                .antMatchers("/catalogo-usuarios/usuarios/*/recuperar-cuenta").permitAll()
                .antMatchers("/catalogo-usuarios/usuarios/*/eliminar-cuenta").permitAll()
                .antMatchers("/catalogo-usuarios/usuarios/*/restablecer-contrasena/*").permitAll()
                .antMatchers("/taller/asistencias/*").permitAll()
                .anyRequest().hasRole("USER");

    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {

        CorsConfiguration config = new CorsConfiguration();
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

        config.setAllowCredentials(true);
        config.setAllowedOrigins(Arrays.asList("http://192.168.1.42:3000", "http://192.168.1.60:3000", "http://localhost:3000"));
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");

        source.registerCorsConfiguration("/**", config);

        return source;

    }

}
