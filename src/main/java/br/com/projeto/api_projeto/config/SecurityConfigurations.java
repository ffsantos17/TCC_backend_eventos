package br.com.projeto.api_projeto.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfigurations {

    @Autowired
    SecurityFilter securityFilter;


    @Bean
    public SecurityFilterChain SecurityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(HttpMethod.POST, "/auth/login").permitAll()
                        .requestMatchers(HttpMethod.POST, "/auth/registrar").permitAll()
                        .requestMatchers(HttpMethod.GET, "/eventos/listar").permitAll()
                        .requestMatchers(HttpMethod.GET, "/eventos/buscar").permitAll()
                        .requestMatchers(HttpMethod.GET, "/eventos/obter-vagas").permitAll()
                        .requestMatchers(HttpMethod.GET, "/eventos/inserir-visita").permitAll()
                        .requestMatchers(HttpMethod.GET, "/documentos/listar").permitAll()
                        .requestMatchers(HttpMethod.POST, "/documentos/upload").permitAll()
                        .requestMatchers(HttpMethod.GET, "/documentos/download/").permitAll()
                        .requestMatchers(HttpMethod.GET, "/documentos/delete").permitAll()
                        .requestMatchers(HttpMethod.GET, "/documentos/img/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/alerta/ler_alerta").permitAll()
                        .requestMatchers(HttpMethod.GET, "/usuario/listar").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/documentos/criar").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/documentos/visualizar_documento").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/eventos/buscar-participantes").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/eventos/alterar_status").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/usuario/buscar-documentos-usuario").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/usuario/buscar").permitAll()
                        .requestMatchers(HttpMethod.POST, "/usuario/registrar-usuario-evento").permitAll()
                        .requestMatchers(HttpMethod.POST, "/usuario/buscar-evento-usuario").permitAll()
                        .requestMatchers(HttpMethod.POST, "/alerta/listar").permitAll()
                        .requestMatchers(HttpMethod.POST, "/alerta/criar").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/eventos/cadastrar").hasRole("ADMIN").anyRequest().authenticated()
                )
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception{
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


}
