package br.com.fiap.model.to;

import jakarta.validation.constraints.*;

public class ComunidadeOngTO {
    private Long codigo;
    @NotBlank
    @Size(max = 100)
    private String nome;
    @NotBlank
    @Size(max = 300)
    private String descricao;
    @Size(max = 150)
    private String contato;

    public ComunidadeOngTO() {
    }

    public ComunidadeOngTO(Long codigo, String nome, String descricao, String contato) {
        this.codigo = codigo;
        this.nome = nome;
        this.descricao = descricao;
        this.contato = contato;
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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getContato() {
        return contato;
    }

    public void setContato(String contato) {
        this.contato = contato;
    }
}
