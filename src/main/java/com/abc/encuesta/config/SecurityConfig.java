package com.abc.encuesta.config;

import com.abc.encuesta.service.UserDetailServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
            .csrf(csrf -> csrf.disable()) // Deshabilitar CSRF para simplificar
            .authorizeHttpRequests(auth -> {
                // Endpoints públicos
                auth.requestMatchers("/css/**", "/js/**", "/images/**").permitAll();

                auth.requestMatchers(HttpMethod.GET,  "/admin","/admpreguntas","/admcapitulos","/admencuestas","/admusuarios").hasAnyRole("ADMIN");
                auth.requestMatchers(HttpMethod.GET, "/api/**"  ,"/login","/user","/redirect").hasAnyRole("USER","ADMIN");
                auth.requestMatchers(HttpMethod.POST,  "/**").hasAnyRole("ADMIN");
                auth.requestMatchers(HttpMethod.PUT,  "/**").hasAnyRole("ADMIN");
                auth.requestMatchers(HttpMethod.DELETE,  "/**").hasAnyRole("ADMIN");
               
            })
            .formLogin(form -> form
                .loginPage("/login") // Página personalizada de inicio de sesión
                .defaultSuccessUrl("/redirect", true) // Redirigir al usuario a /home después de iniciar sesión
                .permitAll()
            )
            .logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login?logout") // Redirige a la página de inicio de sesión después de cerrar sesión
                .permitAll()
            )
            .build();
    }

    // @Bean
    // public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity)
    // throws Exception {
    // return httpSecurity
    // .csrf(csrf -> csrf.disable())
    // .httpBasic(Customizer.withDefaults())
    // .sessionManagement(session ->
    // session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
    // .build();
    // }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
            throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(UserDetailServiceImpl userDetailService) {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder());
        provider.setUserDetailsService(userDetailService);
        return provider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
}
