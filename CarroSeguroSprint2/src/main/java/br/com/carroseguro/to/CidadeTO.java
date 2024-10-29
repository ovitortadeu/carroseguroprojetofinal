package br.com.carroseguro.to;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public class CidadeTO {

    @Positive // ID da cidade deve ser positivo
    private int idCidade;
    @NotBlank // Nome do estado não pode ser vazio ou nulo.
    private String nmCidade;
    @Positive // Número de DDD não pode ser vazio ou nulo.
    private int nrDDD;
    @Positive// ID da estado deve ser positivo
    private int idEstado;

    public CidadeTO() {
    }

    public CidadeTO(int idCidade, String nmCidade, int nrDDD, int idEstado) {
        this.idCidade = idCidade;
        this.nmCidade = nmCidade;
        this.nrDDD = nrDDD;
        this.idEstado = idEstado;
    }

    public int getIdCidade() {
        return idCidade;
    }

    public void setIdCidade(int idCidade) {
        this.idCidade = idCidade;
    }

    public String getNmCidade() {
        return nmCidade;
    }

    public void setNmCidade(String nmCidade) {
        this.nmCidade = nmCidade;
    }

    public int getNrDDD() {
        return nrDDD;
    }

    public void setNrDDD(int nrDDD) {
        this.nrDDD = nrDDD;
    }

    public int getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(int idEstado) {
        this.idEstado = idEstado;
    }
}
