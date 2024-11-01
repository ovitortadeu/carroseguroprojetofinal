package br.com.carroseguro.to;

import jakarta.validation.constraints.NotNull;

public class UsuarioTO {

    @NotNull
    private Integer idUsuario;
    @NotNull
    private Long usCPF;
    @NotNull
    private String nmUsuario;
    @NotNull
    private String emailUsuario;
    @NotNull
    private String senhaUsuario;

    public UsuarioTO() {
    }


    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNmUsuario() {
        return nmUsuario;
    }

    public void setNmUsuario(String nmUsuario) {
        this.nmUsuario = nmUsuario;
    }

    public Long getUsCPF() {
        return usCPF;
    }

    public void setUsCPF(Long usCPF) {
        this.usCPF = usCPF;
    }

    public String getEmailUsuario() {
        return emailUsuario;
    }

    public void setEmailUsuario(String emailUsuario) {
        this.emailUsuario = emailUsuario;
    }

    public String getSenhaUsuario() {
        return senhaUsuario;
    }

    public void setSenhaUsuario(String senhaUsuario) {
        this.senhaUsuario = senhaUsuario;
    }
}
