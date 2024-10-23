package br.com.carroseguro.to;

public class TelefoneUsuarioTO {

    private int idTelefone;
    private int nrDDI;
    private int nrDDD;
    private int nrTelefone;
    private String tpTelefone;
    private String stTelefone;
    private int idUsuario;

    public TelefoneUsuarioTO() {
    }

    public TelefoneUsuarioTO(int idTelefone, int nrDDI, int nrDDD, int nrTelefone, String tpTelefone, String stTelefone, int idUsuario) {
        this.idTelefone = idTelefone;
        this.nrDDI = nrDDI;
        this.nrDDD = nrDDD;
        this.nrTelefone = nrTelefone;
        this.tpTelefone = tpTelefone;
        this.stTelefone = stTelefone;
        this.idUsuario = idUsuario;
    }

    public int getIdTelefone() {
        return idTelefone;
    }

    public void setIdTelefone(int idTelefone) {
        this.idTelefone = idTelefone;
    }

    public int getNrDDD() {
        return nrDDD;
    }

    public void setNrDDD(int nrDDD) {
        this.nrDDD = nrDDD;
    }

    public int getNrDDI() {
        return nrDDI;
    }

    public void setNrDDI(int nrDDI) {
        this.nrDDI = nrDDI;
    }

    public int getNrTelefone() {
        return nrTelefone;
    }

    public void setNrTelefone(int nrTelefone) {
        this.nrTelefone = nrTelefone;
    }

    public String getStTelefone() {
        return stTelefone;
    }

    public void setStTelefone(String stTelefone) {
        this.stTelefone = stTelefone;
    }

    public String getTpTelefone() {
        return tpTelefone;
    }

    public void setTpTelefone(String tpTelefone) {
        this.tpTelefone = tpTelefone;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }
}
