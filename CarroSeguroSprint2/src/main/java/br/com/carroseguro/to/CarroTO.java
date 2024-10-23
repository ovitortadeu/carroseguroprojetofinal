package br.com.carroseguro.to;

public class CarroTO {
    private int idCarro;
    private String marcaCarro;
    private String modeloCarro;
    private int idUsuario;

    public CarroTO() {
    }

    public CarroTO(int idCarro, String marcaCarro, String modeloCarro, int idUsuario) {
        this.idCarro = idCarro;
        this.marcaCarro = marcaCarro;
        this.modeloCarro = modeloCarro;
        this.idUsuario = idUsuario;
    }

    public int getIdCarro() {
        return idCarro;
    }

    public void setIdCarro(int idCarro) {
        this.idCarro = idCarro;
    }

    public String getMarcaCarro() {
        return marcaCarro;
    }

    public void setMarcaCarro(String marcaCarro) {
        this.marcaCarro = marcaCarro;
    }

    public String getModeloCarro() {
        return modeloCarro;
    }

    public void setModeloCarro(String modeloCarro) {
        this.modeloCarro = modeloCarro;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }
}
