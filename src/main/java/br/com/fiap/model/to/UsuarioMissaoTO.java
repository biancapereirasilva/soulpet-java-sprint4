package br.com.fiap.model.to;

import jakarta.validation.constraints.*;

import java.time.LocalDate;

public class UsuarioMissaoTO {
    private Long codigo;
    private boolean concluida;
    //annotations
    @PastOrPresent
    private LocalDate dataConclusao;
    @NotNull
    @Positive
    private Long codUsuario;
    @NotNull
    @Positive
    private Long codMissao;

    public UsuarioMissaoTO() {
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public boolean isConcluida() {
        return concluida;
    }

    public void setConcluida(boolean concluida) {
        this.concluida = concluida;
    }

    public LocalDate getDataConclusao() {
        return dataConclusao;
    }

    public void setDataConclusao(LocalDate dataConclusao) {
        this.dataConclusao = dataConclusao;
    }

    public Long getCodUsuario() {
        return codUsuario;
    }

    public void setCodUsuario(Long codUsuario) {
        this.codUsuario = codUsuario;
    }

    public Long getCodMissao() {
        return codMissao;
    }

    public void setCodMissao(Long codMissao) {
        this.codMissao = codMissao;
    }
}
