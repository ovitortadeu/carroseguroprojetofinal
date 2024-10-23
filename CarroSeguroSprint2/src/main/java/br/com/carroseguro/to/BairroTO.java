package br.com.carroseguro.to;

public class BairroTO {

    private int idBairro;
    private String nmBairro;
    private String nmZonaBairro;
    private int idCidade;

    public BairroTO() {
    }

    public BairroTO(int idBairro, String nmBairro, String nmZonaBairro, int idCidade) {
        this.idBairro = idBairro;
        this.nmBairro = nmBairro;
        this.nmZonaBairro = nmZonaBairro;
        this.idCidade = idCidade;
    }

    public int getIdBairro() {
        return idBairro;
    }

    public void setIdBairro(int idBairro) {
        this.idBairro = idBairro;
    }

    public String getNmBairro() {
        return nmBairro;
    }

    public void setNmBairro(String nmBairro) {
        this.nmBairro = nmBairro;
    }

    public int getIdCidade() {
        return idCidade;
    }

    public void setIdCidade(int idCidade) {
        this.idCidade = idCidade;
    }

    public String getNmZonaBairro() {
        return nmZonaBairro;
    }

    public void setNmZonaBairro(String nmZonaBairro) {
        this.nmZonaBairro = nmZonaBairro;
    }
}
