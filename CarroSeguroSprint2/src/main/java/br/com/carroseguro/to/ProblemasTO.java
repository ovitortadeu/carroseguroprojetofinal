package br.com.carroseguro.to;

public class ProblemasTO {
    private int idProblema;
    private int idCarro;
    private String dcProblema;
    private String tpPecaProblema;

    // Getters e Setters
    public int getIdProblema() {
        return idProblema;
    }

    public void setIdProblema(int idProblema) {
        this.idProblema = idProblema;
    }

    public int getIdCarro() {
        return idCarro;
    }

    public void setIdCarro(int idCarro) {
        this.idCarro = idCarro;
    }

    public String getDcProblema() {
        return dcProblema;
    }

    public void setDcProblema(String dcProblema) {
        this.dcProblema = dcProblema;
    }

    public String getTpPecaProblema() {
        return tpPecaProblema;
    }

    public void setTpPecaProblema(String tpPecaProblema) {
        this.tpPecaProblema = tpPecaProblema;
    }
}
