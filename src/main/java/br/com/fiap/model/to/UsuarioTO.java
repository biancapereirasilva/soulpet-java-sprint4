package br.com.fiap.model.to;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;

public class UsuarioTO {
    private Long codigo;
    @NotBlank
    @Size(max = 100)
    private String nome;
    @NotBlank
    @Email
    @Size(max = 150)
    private String email;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Size(max = 100)
    private String senha;
    @NotBlank
    @Size(max = 20)
    private String telefone;
    @NotNull
    @PositiveOrZero
    private Integer pontos;
    @NotBlank
    @Size(max = 20)
    private String nivel;

    public UsuarioTO() {
    }

    public UsuarioTO(Long codigo, String nome, String email, String senha, String telefone, Integer pontos, String nivel) {
        this.codigo = codigo;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.telefone = telefone;
        this.pontos = pontos;
        this.nivel = nivel;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Integer getPontos() {
        return pontos;
    }

    public void setPontos(Integer pontos) {
        this.pontos = pontos;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }
}
