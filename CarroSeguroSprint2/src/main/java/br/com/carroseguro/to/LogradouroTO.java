package br.com.carroseguro.to;

public class LogradouroTO {

    private int idLogradouro;
    private String nmLogradouro;
    private int nrLogradouro;
    private int nrCep;
    private int idBairro;
    private int idUsuario;

    public LogradouroTO() {
    }

    public LogradouroTO(int idLogradouro, String nmLogradouro, int nrLogradouro, int idUsuario, int nrCep, int idBairro) {
        this.idLogradouro = idLogradouro;
        this.nmLogradouro = nmLogradouro;
        this.nrLogradouro = nrLogradouro;
        this.idUsuario = idUsuario;
        this.nrCep = nrCep;
        this.idBairro = idBairro;
    }

    public int getIdLogradouro() {
        return idLogradouro;
    }

    public void setIdLogradouro(int idLogradouro) {
        this.idLogradouro = idLogradouro;
    }

    public String getNmLogradouro() {
        return nmLogradouro;
    }

    public void setNmLogradouro(String nmLogradouro) {
        this.nmLogradouro = nmLogradouro;
    }

    public int getNrLogradouro() {
        return nrLogradouro;
    }

    public void setNrLogradouro(int nrLogradouro) {
        this.nrLogradouro = nrLogradouro;
    }

    public int getNrCep() {
        return nrCep;
    }

    public void setNrCep(int nrCep) {
        this.nrCep = nrCep;
    }

    public int getIdBairro() {
        return idBairro;
    }

    public void setIdBairro(int idBairro) {
        this.idBairro = idBairro;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }
}
