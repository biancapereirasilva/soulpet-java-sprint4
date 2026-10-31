package br.com.fiap.model.to;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class MissaoTO {
    //atributos
    private Long codigo;
    //annotations
    @NotBlank
    @Size(max = 100)
    private String titulo;
    @Size(max = 300)
    private String descricao;
    @NotNull
    private Integer pontosRecompensa;
    private boolean concluida;
    @NotNull
    private Long codComunidade;

    //construtores
    public MissaoTO() {}

    public MissaoTO(Long codigo, String titulo, String descricao, Integer pontosRecompensa, boolean concluida, Long codComunidade) {
        this.codigo = codigo;
        this.titulo = titulo;
        this.descricao = descricao;
        this.pontosRecompensa = pontosRecompensa;
        this.concluida = concluida;
        this.codComunidade = codComunidade;
    }

    //métodos getters/setters
    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getPontosRecompensa() {
        return pontosRecompensa;
    }

    public void setPontosRecompensa(Integer pontosRecompensa) {
        this.pontosRecompensa = pontosRecompensa;
    }

    public boolean isConcluida() {
        return concluida;
    }

    public void setConcluida(boolean concluida) {
        this.concluida = concluida;
    }

    public Long getCodComunidade() {
        return codComunidade;
    }

    public void setCodComunidade(Long codComunidade) {
        this.codComunidade = codComunidade;
    }
}
