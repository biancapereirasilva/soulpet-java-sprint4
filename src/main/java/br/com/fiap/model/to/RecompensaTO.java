package br.com.fiap.model.to;

import jakarta.validation.constraints.*;

public class RecompensaTO {
    private Long codigo;
    @NotBlank
    @Size(max = 100)
    private String nome;
    @Size(max = 300)
    private String descricao;
    @NotNull
    @PositiveOrZero
    private Integer custoPontos;
    @NotBlank
    @Size(max = 30)
    private String tipo;
    @NotNull
    @PositiveOrZero
    private Integer estoque;

    public RecompensaTO() {
    }

    public RecompensaTO(Long codigo, String nome, String descricao, Integer custoPontos, String tipo, Integer estoque) {
        this.codigo = codigo;
        this.nome = nome;
        this.descricao = descricao;
        this.custoPontos = custoPontos;
        this.tipo = tipo;
        this.estoque = estoque;
    }

    //métodos getters/setters
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

    public Integer getCustoPontos() {
        return custoPontos;
    }

    public void setCustoPontos(Integer custoPontos) {
        this.custoPontos = custoPontos;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Integer getEstoque() {
        return estoque;
    }

    public void setEstoque(Integer estoque) {
        this.estoque = estoque;
    }
}
