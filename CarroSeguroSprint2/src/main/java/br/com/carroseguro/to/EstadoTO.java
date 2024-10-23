package br.com.carroseguro.to;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public class EstadoTO {

    @Positive  // Id do Estado deve ser maior que zero
    private int idEstado;

    @NotBlank // Sigla não pode ser vazio ou nulo.
    private String sgEstado;

    @NotBlank // Nome do estado não pode ser vazio ou nulo.
    private String nmEstado;

    public EstadoTO() {
    }

    public EstadoTO(int idEstado, String sgEstado, String nmEstado) {
        this.idEstado = idEstado;
        this.sgEstado = sgEstado;
        this.nmEstado = nmEstado;
    }

    public int getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(int idEstado) {
        this.idEstado = idEstado;
    }

    public String getSgEstado() {
        return sgEstado;
    }

    public void setSgEstado(String sgEstado) {
        this.sgEstado = sgEstado;
    }

    public String getNmEstado() {
        return nmEstado;
    }

    public void setNmEstado(String nmEstado) {
        this.nmEstado = nmEstado;
    }
}
