package br.com.projeto.api_projeto.models;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;


public class Usuario implements UserDetails {


    private int id;
    private String nome;
    private String senha;
    private String cpf;
    private String matricula;
    private String email;
    private int tipoUsuarioId;
    private String tipoUsuarioNome;
    private List<EventosUsuario> eventos;

    public Usuario() {
    }

    public Usuario(int id, String nome, String senha, String cpf, String matricula, String email, int tipoUsuarioId, String tipoUsuarioNome) {
        this.id = id;
        this.nome = nome;
        this.senha = senha;
        this.cpf = cpf;
        this.matricula = matricula;
        this.email = email;
        this.tipoUsuarioId = tipoUsuarioId;
        this.tipoUsuarioNome = tipoUsuarioNome;
    }

    public Usuario(String nome, String senha, String cpf, String matricula, String email, int tipoUsuarioId) {
        this.nome = nome;
        this.senha = senha;
        this.cpf = cpf;
        this.matricula = matricula;
        this.email = email;
        this.tipoUsuarioId = tipoUsuarioId;
    }

    public Usuario(String email, String senha) {
        this.email = email;
        this.senha = senha;
    }

    public Usuario(String email) {
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getTipoUsuarioId() {
        return tipoUsuarioId;
    }

    public void setTipoUsuarioId(int tipoUsuarioId) {
        this.tipoUsuarioId = tipoUsuarioId;
    }

    public String getTipoUsuarioNome() {
        return tipoUsuarioNome;
    }

    public void setTipoUsuarioNome(String tipoUsuarioNome) {
        this.tipoUsuarioNome = tipoUsuarioNome;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (this.tipoUsuarioId == 1 || this.tipoUsuarioId == 3)
            return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_USER"));
        else return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    public List<EventosUsuario> getEventos() {
        return eventos;
    }

    public void setEventos(List<EventosUsuario> eventos) {
        this.eventos = eventos;
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
