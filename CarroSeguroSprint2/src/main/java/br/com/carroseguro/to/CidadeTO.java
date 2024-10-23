package br.com.carroseguro.to;

public class CidadeTO {

    private int idCidade;
    private String nmCidade;
    private int nrDDD;
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
