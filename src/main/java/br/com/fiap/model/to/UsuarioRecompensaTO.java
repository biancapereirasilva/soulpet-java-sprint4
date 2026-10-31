package br.com.fiap.model.to;

import jakarta.validation.constraints.*;

import java.time.LocalDate;

public class UsuarioRecompensaTO {
    private Long codigo;
    @PastOrPresent
    private LocalDate dataResgate;
    @NotNull
    @Positive
    private Long codRecompensa;
    @NotNull
    @Positive
    private Long codUsuario;

    public UsuarioRecompensaTO() {
    }

    public UsuarioRecompensaTO(Long codigo, LocalDate dataResgate, Long codRecompensa, Long codUsuario) {
        this.codigo = codigo;
        this.dataResgate = dataResgate;
        this.codRecompensa = codRecompensa;
        this.codUsuario = codUsuario;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public LocalDate getDataResgate() {
        return dataResgate;
    }

    public void setDataResgate(LocalDate dataResgate) {
        this.dataResgate = dataResgate;
    }

    public Long getCodRecompensa() {
        return codRecompensa;
    }

    public void setCodRecompensa(Long codRecompensa) {
        this.codRecompensa = codRecompensa;
    }

    public Long getCodUsuario() {
        return codUsuario;
    }

    public void setCodUsuario(Long codUsuario) {
        this.codUsuario = codUsuario;
    }
}
