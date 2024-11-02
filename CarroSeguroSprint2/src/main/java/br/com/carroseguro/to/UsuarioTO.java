package br.com.carroseguro.to;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class UsuarioTO {

    @NotNull
    private int idUsuario;
    @NotNull
    private Long usCPF; // Não pode ser nulo
    @NotNull
    @Size(min = 3, max = 40)
    private String nmUsuario; // Tamanho mínimo e máximo
    @Email // Deve ser um email válido
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
