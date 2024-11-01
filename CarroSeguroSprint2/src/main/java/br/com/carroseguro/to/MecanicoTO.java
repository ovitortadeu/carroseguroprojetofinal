package br.com.carroseguro.to;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class MecanicoTO {
    @NotNull
    private Integer idMecanico;
    @NotNull
    private Integer idCarro;
    @NotNull
    private String zonaMecanico;
    @NotNull
    private String nomeOficina;
    @NotBlank
    private String enderecoOficina;

    public MecanicoTO(int idMecanico, int idCarro, String zonaMecanico, String nomeOficina, String enderecoOficina) {
        this.idMecanico = idMecanico;
        this.idCarro = idCarro;
        this.zonaMecanico = zonaMecanico;
        this.nomeOficina = nomeOficina;
        this.enderecoOficina = enderecoOficina;
    }

    public MecanicoTO() {
    }
    public int getIdMecanico() {
        return idMecanico;
    }
    public void setIdMecanico(int idMecanico) {
        this.idMecanico = idMecanico;
    }
    public int getIdCarro() {
        return idCarro;
    }
    public void setIdCarro(int idCarro) {
        this.idCarro = idCarro;
    }
    public String getZonaMecanico() {
        return zonaMecanico;
    }
    public void setZonaMecanico(String zonaMecanico) {
        this.zonaMecanico = zonaMecanico;
    }
    public String getNomeOficina() {
        return nomeOficina;
    }
    public void setNomeOficina(String nomeOficina) {
        this.nomeOficina = nomeOficina;
    }
    public String getEnderecoOficina() {
        return enderecoOficina;
    }
    public void setEnderecoOficina(String enderecoOficina) {
        this.enderecoOficina = enderecoOficina;
    }
}
