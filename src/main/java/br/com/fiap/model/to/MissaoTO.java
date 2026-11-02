package br.com.fiap.model.to;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class MissaoTO {
    private Long codigo;
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

    public MissaoTO() {}

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
